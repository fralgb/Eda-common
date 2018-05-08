/**
 * 
 * @author xslong
 * @time Nov 18, 2016 9:16:51 PM
 */

public class V5ServiceProxyFactoryTest {
	/*
	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}

	public static void test1() throws ClassNotFoundException {
		String packageName = "com.omniselling.ms.test.generator.out.service";
		System.out.println();
		V5ServiceProxyFactory factory = V5ServiceProxyFactory.instance();
		List<Class> serviceInterface = ClassUtil.findClass(packageName, true, V5Service.class);
		System.out.println(serviceInterface);
		for (Class clazz : serviceInterface) {
			V5Service v5ServiceServie = (V5Service) clazz.getAnnotation(V5Service.class);
			System.out.println(v5ServiceServie.path());
			if (v5ServiceServie == null)
				continue;
			RemoteV5ServiceHttpExecutor t = new RemoteV5ServiceHttpExecutor(HttpClientBuilder.create(),
					"http://test.omniselling.net/omnibilling");
			factory.build(t, "omnibilling", clazz);
		}
	}

	public static void test2() throws ClassNotFoundException {
		V5ServiceProxyFactory factory = V5ServiceProxyFactory.instance();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		Class clazz = Class.forName("com.omniselling.ms.test.generator.out.service.MsBillingRecordClientX");
		RemoteV5ServiceHttpExecutor t = new RemoteV5ServiceHttpExecutor(httpClientBuilder,
				"http://test.omniselling.net/omnibilling");
		MsBillingRecordClientX client = (MsBillingRecordClientX) factory.build(t, "omnibilling", clazz);
		ClientRequest<Long> request = new ClientRequest<Long>();
		request.setData(10000l);
		request.setOperatorId(110l);
		request.setLanguage("en");
		ServerResponse<BizBillingRecord> response = client.getBillingRecord(request);
		if (response.hasError()) {
			for (ErrorInfo ei : response.getErrors()) {
				System.out.println(ei.getDesc());
			}
		} else {
			BizBillingRecord record = response.getData();
			System.out.println(record.getBusinessNum());
		}
	}
*/
}
