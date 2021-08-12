
- Mac终端连接数据库&创建数据库：
```shell
docker exec -it mysql bash 
mysql -uroot -p
CREATE DATABASE IF NOT EXISTS `o2o` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use o2o
```
- mvn flyway:clean && mvn flyway:migrate

- 项目结构：
  - dto：弥补entity的不足