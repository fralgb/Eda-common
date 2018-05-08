package com.omniselling.common.repository.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.core.Tuple;
import com.querydsl.core.support.NumberConversions;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Path;

public class TupleUtil
{
    private static Logger logger = LoggerFactory.getLogger(TupleUtil.class);
    private static final Map<String, Map<String, Field>> FIELDS_CACHE = new HashMap<>();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T convertToBean(Tuple tupl, Class<T> clazz, Expression<?> expression)
    {

        if (expression instanceof NumberConversions)
        {
            NumberConversions conv = (NumberConversions) expression;
            try
            {
                T bean = clazz.newInstance();
                List<Expression> exps = conv.getArgs();

                for (Expression exp : exps)
                {
                    String alias = null;
                    if (exp instanceof Operation)
                    {
                        Operation op = (Operation) exp;
                        alias = op.getArg(1).toString();
                        // throw new UnSupportExpressionException(
                        // "Please add as operation to selected expression.Such as
                        // select(QAccount.id.as('accountId'))");
                    }
                    else if (exp instanceof Path)
                    {
                        Path<?> path = (Path<?>) exp;
                        alias = path.getMetadata().getName();
                    }
                    if (alias == null)
                    {
                        throw new UnSupportExpressionException(
                                "Please add as operation to selected expression.Such as select(QAccount.id.as('accountId'))");
                    }

                    Field field = getFields(clazz, alias);
                    if (field == null)
                    {
                        logger.info("Tuple[" + alias + "] can't find field in " + clazz.getName());
                        continue;
                    }
                    field.set(bean, tupl.get(exp));
                }

                return bean;
            }
            catch (InstantiationException e)
            {
                logger.error(e.getMessage(), e);
            }
            catch (IllegalAccessException e)
            {
                logger.error(e.getMessage(), e);
            }
            return null;
        }
        else
        {
            throw new UnSupportExpressionException(expression.getClass().getName());
        }

    }

    private static <T> Field getFields(Class<T> clazz, String fieldName)
    {
        Map<String, Field> fields = FIELDS_CACHE.get(clazz.getName());
        if (fields == null)
        {
            Field[] fieldArray = clazz.getDeclaredFields();
            fields = new HashMap<>();
            for (Field f : fieldArray)
            {
                f.setAccessible(true);
                fields.put(f.getName(), f);
            }
            FIELDS_CACHE.put(clazz.getName(), fields);
        }
        return fields.get(fieldName);
    }

    static class UnSupportExpressionException extends RuntimeException
    {
        private static final long serialVersionUID = -7460313601502553726L;

        public UnSupportExpressionException(String msg)
        {
            super(msg);
        }

        public UnSupportExpressionException()
        {
        }
    }
}
