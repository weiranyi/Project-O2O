- 展示
  - 店铺注册：http://localhost:8080/O2O/shopadmin/shopoperation
  - 店铺编辑：
    - 得到返回：http://localhost:8080/O2O/shopadmin/getshopbyid?shopid=1
    - 编辑店铺的页面：http://127.0.0.1:8080/O2O/shopadmin/shopoperation?shopId=1
- 收获：
    - SpringMVC：DispatcherServlet：
        - DispatcherServlet：是整个SpringMVC框架中最为核心的一块，它主要用来拦截符合要求的外部请求，并把请求分发到不同的控制器中去，根据控制器处理后的结果生成相应的响应发送到客户端。
    - Spring IOC和AOP：
        - Spring IOC：
            - 理解：由Spring来负责控制对象的生命周期和对象间的关系，而非传统实现中由程序代码直接操控。
            - 例子：依赖注入
        - AOP：
            - 理解：面向切面编程，动态代理，动态代理由JDK、CGLIB实现
            - 例子：增删改查的操作都需要作权限验证，我们并不期望权限验证的代码杂留在增删改查的代码中，可以通过AOP在程序运行的时候动态的将我们的权限代码植入到增删改查方法的前面以完成权限的验证
    - ORM
        - ORM是通过使用描述对象和数据库之间映射的元数据，将程序中的对象自动持久化到关系数据库中。


- Mac终端连接数据库&创建数据库：

```shell
docker exec -it mysql bash 
mysql -uroot -p
CREATE DATABASE IF NOT EXISTS `o2o` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use o2o
```

- 一些maven命令
    - mvn flyway:clean && mvn flyway:migrate
    - mvn -N io.takari:maven:0.7.7:wrapper
- 项目结构：
    - dto：弥补entity的不足

- DispatcherServlet:拦截符合要求的外部请求，分发到不同的控制器，根据控制器生成响应响应给客户端
- IoC和AOP：
    - 由Spring操控对象生命周期和对象间关系，非传统代码操控
    - AOP：动态代理
    - MyBatis:ORM
- 日志： 定位问题
    - 模块
        - logback-access：提供访问入口
        - logback-classic：方便更换日志系统
        - logback-core：提供基础服务
    - 标签：
        - logger：存放日志对象
        - appender：指定输出目的地
        - layout：格式化输出
- Mysql主从分离
    - 概要：一台服务器面对大量连接操作，数据库必然会崩溃。大型网站为了减轻服务器并发访问产生的性能问题，采用了很多解决方案。读写分离就是一种主流方案，读和写放到不同服务器去。
    - 解决：
        - 数据库层面：
        - 代码层面：