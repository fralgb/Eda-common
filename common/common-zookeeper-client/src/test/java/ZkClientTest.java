import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.omniselling.common.zookeeper.ZKClient;

/**
 * @author xslong
 * @version 创建时间：Nov 23, 2016 5:36:34 PM
 * 
 */

public class ZkClientTest {

	public static void main(String[] args) throws Exception {
		ZKClient zkClient = new ZKClient("test.omniselling.net:2181");
		// String path = "/microSerivce";
		String path = "/microSerivce/distribution";
		printChild("/microSerivce",zkClient);
		Watcher watcher = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getPath() + "   > " + event.getType());
				try {
					zkClient.addWatch(path, false, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		zkClient.addWatch(path, false, watcher);

		while (true) {
			Thread.sleep(10000);
		}
	}

	public static void printChild(String path, ZKClient zkClient) {
		System.out.println(path);
		List<String> childs = zkClient.listChildren(path);
		if (childs == null || childs.size() == 0)
			return;
		for (String c : childs) {
			printChild(path + "/" + c, zkClient);
		}
	}

}
