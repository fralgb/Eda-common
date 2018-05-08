
#### omni-common各模块的定义说明
- common-biz	业务模型持久化、工作流核心的代码
- common-biz-annotation	定义业务模型时需要用的注解
- common-dao	DAO层相关的代码
- common-log	程序日志相关的代码，依赖Logback，对Logback的输出的格式有扩展，后续完善日志分析系统的功能
- common-microservice	新版微服务
- common-microservice-client	新版微服务客户端调用的相关代码
- common-microservice-service	新版微服务服务端定义的代码
- common-model	业务模型(BizModel)、数据模型(DbModel)等模型的定义代码
- common-protocol	定义所有项目规范性代码，比如异常、接口请求、返回的结构、错误代码等等
- common-spring-boot	spring-boot驱动的项目中能够复用的Configuration
- common-util	工具类
- common-v5Service-client	旧V5微服务客户端依赖的相关代码，旧V5微服务客户端代码生成器见[code=generator](https://www.omniselling.net/git/v5/code-generator)
- common-web	Web项目一些工具类
- common-zookeeper-client 封装的zookeeper客户端

示例代码地址[msarchetype](https://www.omniselling.net/git/v5/msarchetype/src/feature/mcroservice-0.0.4-biz/msdemo) ，会不定期更新

#### 打包方法
1 更新omni-common的版本

	mvn versions:set versions:commit -DnewVersion="0.0.X-SNAPSHOT"
2 编译代码、生成source.jar、打包、

	mvn clean source:jar install
3 发布到Nexus

	mvn deploy

2、3步骤可以用 mvn-deploy.sh


