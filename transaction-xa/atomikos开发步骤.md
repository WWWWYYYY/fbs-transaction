
XA-2pc
解释：多数据源事务管理
一个应用服务直连多个数据源进行事务处理；不同于分布式事务，分布式事务是以多个服务为基础的事务，
分布式中一个服务不能直连其他服务的数据源。因此分布式事务比多数据源事务难一些
所数据源指代网络上的任意服务器上的数据源；多数据源可在不同服务器上的同一个库，或者同一个服务器上的不同库。只要能直连就可以算多数据源。

开发步骤：
1、pom添加spring-boot-starter-jta-atomikos、spring-boot-starter-jta-atomikos
2、配置多个数据源配置org.spring.config.DataSourceConfig1、org.spring.config.DataSourceConfig2...org.spring.config.DataSourceConfig(n)
    配置类中启用XA来管理事务
3、dao层、和mapping文件都要根据数据源进行分包。
4、service层方法使用@Transactional修饰