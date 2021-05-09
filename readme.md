# 概览
> [文档](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc)

> [SpringBoot配置](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/configuration/config-spring-boot)

> [示例工程](https://github.com/apache/shardingsphere-example/tree/dev/sharding-jdbc-example/sharding-example/sharding-spring-boot-mybatis-example)

初始化脚本：init_db.sql

# 读写分离
MasterSlaveDemo

# 分库、分表
ShardingDemo

```
新版本的sharding-jdbc启动时会读取数据库元数据，如果表非常多会很慢，可以降级到 4.0.1 解决这个问题
```

> [SpringBoot配置 数据分片 + 读写分离](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/configuration/config-spring-boot/#%E6%95%B0%E6%8D%AE%E5%88%86%E7%89%87--%E8%AF%BB%E5%86%99%E5%88%86%E7%A6%BB)

> [分片算法](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/features/sharding/concept/sharding)

# 分布式事务

> [Sharding-JDBC分布式事务](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/usage/transaction)