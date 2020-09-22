package com.ozz.shadingjdbc.component.db;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSource master0;
    @Autowired
    private DataSource master1;
    @Autowired
    private DataSource slave0;
    @Autowired
    private DataSource slave1;

//    @Autowired
//    private DatabaseShardingAlgorithm databaseShardingAlgorithm;
//
//    @Autowired
//    private TableShardingAlgorithm tableShardingAlgorithm;

    @Primary
    @Bean
    public DataSource getDataSource() throws SQLException {
//        return buildDataSource();
        return master0;
    }

//    private DataSource buildDataSource() throws SQLException {
//        //分库设置
//        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
//        //添加两个数据库database0和database1
//        dataSourceMap.put("master0", master0);
//        dataSourceMap.put("master1", master0);
//        dataSourceMap.put("slave0", master0);
//        dataSourceMap.put("slave1", master0);
//        //设置默认数据库
//        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "master0");
//
//        //分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
//        TableRule orderTableRule = TableRule.builder("goods")
//                .actualTables(Arrays.asList("goods_0", "goods_1"))
//                .dataSourceRule(dataSourceRule)
//                .build();
//
//        //分库分表策略
//        ShardingRule shardingRule = ShardingRule.builder()
//                .dataSourceRule(dataSourceRule)
//                .tableRules(Arrays.asList(orderTableRule))
//                .databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
//                .tableShardingStrategy(new TableShardingStrategy("goods_type", tableShardingAlgorithm)).build();
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
//        return dataSource;
//    }
//
//    @Bean
//    public KeyGenerator keyGenerator() {
//        DefaultKeyGenerator defaultKeyGenerator;
//        return new DefaultKeyGenerator();
//    }

}