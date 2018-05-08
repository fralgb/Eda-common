import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;

import com.omniselling.common.zookeeper.ZKClient;

/**
 * @author xslong
 * @version 创建时间：Nov 23, 2016 5:36:34 PM
 * 
 */

public class ZkClientTest2
{

	public static void main(String[] args)
	{
		ZKClient zkClient = new ZKClient("test.omniselling.net:2181");
		String path = "/test/x";
		PathChildrenCache cache = new PathChildrenCache(zkClient.getClient(), path, false);
		try
		{
			cache.start();

			System.out.println("监听开始/zk........");
			PathChildrenCacheListener plis = new PathChildrenCacheListener()
			{
				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception
				{
					switch (event.getType())
					{
					case CHILD_ADDED: {
						System.out.println("Node added: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
						break;
					}
					case CHILD_UPDATED: {
						System.out.println("Node changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
						break;
					}
					case CHILD_REMOVED: {
						System.out.println("Node removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
						break;
					}
					default:
						break;
					}
				}
			};
			// 注册监听
			cache.getListenable().addListener(plis);
			while (true)
			{
				try
				{
					Thread.sleep(10000l);
				} catch (InterruptedException e)
				{
					e.printStackTrace();

				}
			}
		} catch (Exception e2)
		{
			e2.printStackTrace();
			try
			{
				cache.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

	}

}
