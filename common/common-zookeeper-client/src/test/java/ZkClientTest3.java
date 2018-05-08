import com.omniselling.common.zookeeper.ZKClient;

/**
 * @author xslong
 * @version 创建时间：Nov 23, 2016 5:36:34 PM
 * 
 */

public class ZkClientTest3 {

	public static void main(String[] args) throws Exception {
		ZKClient zkClient = new ZKClient("test.omniselling.net:2181");
		String path = "/test/x";
		
		for(int i =0;i<5;i++ ){
			String p = path +"/"+i;
			System.out.println(">>>>"+p );
			zkClient.createEphemeralNode(p, "i");
			zkClient.createEphemeralNode(p+"xxxx", "i");
			Thread.sleep(5000l);
			zkClient.deleteNode(p);
		}
		zkClient.destory();
	}

}
