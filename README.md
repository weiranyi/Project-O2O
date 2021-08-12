
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