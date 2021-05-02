/**
 * 源代码: org.apache.shardingsphere:sharding-jdbc-core:4.1.1
 * 
 * 移除加载元数据代码
 */
package org.apache.shardingsphere.shardingjdbc.jdbc.core.context;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.spi.database.type.DatabaseType;
import org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaData;
import org.apache.shardingsphere.underlying.common.config.DatabaseAccessConfiguration;
import org.apache.shardingsphere.underlying.common.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.underlying.common.metadata.datasource.DataSourceMetas;
import org.apache.shardingsphere.underlying.common.rule.BaseRule;

/**
 * 源码: org.apache.shardingsphere:sharding-jdbc-core:4.1.1
 *
 * 移除加载元数据代码
 */
@Getter
@Slf4j(topic = "ShardingSphere-metadata")
public abstract class MultipleDataSourcesRuntimeContext<T extends BaseRule> extends AbstractRuntimeContext<T> {
    
    private final ShardingSphereMetaData metaData;
    
    protected MultipleDataSourcesRuntimeContext(final Map<String, DataSource> dataSourceMap, final T rule, final Properties props, final DatabaseType databaseType) throws SQLException {
        super(rule, props, databaseType);
        metaData = createMetaData(dataSourceMap, databaseType);
    }
    
    private ShardingSphereMetaData createMetaData(final Map<String, DataSource> dataSourceMap, final DatabaseType databaseType) throws SQLException {
        long start = System.currentTimeMillis();
        log.info("Meta data load DataSource start");
        DataSourceMetas dataSourceMetas = new DataSourceMetas(databaseType, getDatabaseAccessConfigurationMap(dataSourceMap));
        log.info("Meta data load DataSource finished, cost {} milliseconds.", System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        log.info("Meta data load Schema start");
        SchemaMetaData schemaMetaData = loadSchemaMetaData(dataSourceMap);
        log.info("Meta data load Schema finished, cost {} milliseconds.", System.currentTimeMillis() - start);
        return new ShardingSphereMetaData(dataSourceMetas, schemaMetaData);
//        log.info("Meta data load: skip by ozz");
//        return new ShardingSphereMetaData(null, null);
    }
    
    private Map<String, DatabaseAccessConfiguration> getDatabaseAccessConfigurationMap(final Map<String, DataSource> dataSourceMap) throws SQLException {
        Map<String, DatabaseAccessConfiguration> result = new LinkedHashMap<>(dataSourceMap.size(), 1);
        for (Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            DataSource dataSource = entry.getValue();
            try (Connection connection = dataSource.getConnection()) {
                DatabaseMetaData metaData = connection.getMetaData();
                result.put(entry.getKey(), new DatabaseAccessConfiguration(metaData.getURL(), metaData.getUserName(), null));
            }
        }
        return result;
    }
    
    protected abstract SchemaMetaData loadSchemaMetaData(Map<String, DataSource> dataSourceMap) throws SQLException;
}
