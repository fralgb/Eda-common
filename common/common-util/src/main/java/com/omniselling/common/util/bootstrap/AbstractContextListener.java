package com.omniselling.common.util.bootstrap;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ApplicationContextEvent;

public abstract class AbstractContextListener
{
    final protected Logger logger = LoggerFactory.getLogger(getClass());
    protected boolean runInParalle = false;

    protected synchronized <R extends IBootstrapAction> void executeBeanMethods(ApplicationContextEvent event,
            List<R> beans)
    {
        if (beans == null)
        {
            logger.warn("No bean has been defined, exiting..");
            return;
        }
        final long ts = System.currentTimeMillis();
        logger.info(">> executeBeanMethods start, runInParalle = " + runInParalle);
        logger.info("event = " + event);
        if (runInParalle)
        {
            List<Thread> threads = new LinkedList<>();
            for (R bean : beans)
            {
                Thread thread = new Thread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        bean.runFunc();
                    }
                });
                thread.setDaemon(true);
                thread.setName(getClass().getSimpleName() + "-" + bean.getClass().getSimpleName());
                thread.start();
                threads.add(thread);
            }
            //join

            for (Thread thread : threads)
            {
                try
                {
                    thread.join();
                    logger.info("Finished executing " + thread.getName());
                }
                catch (Exception e)
                {
                    logger.error("Error executing " + thread.getName(), e);
                }
            }

        }
        else
        {
            for (R bean : beans)
            {
                logger.info("Executing " + bean.getClass().getSimpleName());
                try
                {
                    bean.runFunc();
                }
                catch (Exception e)
                {
                    logger.error("Error executing " + bean.getClass().getSimpleName(), e);
                }
            }
        }
        logger.info("<< executeBeanMethods Done in " + (System.currentTimeMillis() - ts) / 1000 + "s.");
    }

    final public void setRunInParalle(boolean runInParalle)
    {
        this.runInParalle = runInParalle;
    }

}
