
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