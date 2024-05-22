package org.apache.shardingsphere.shardingjdbc.jdbc.core.context;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.spi.database.type.DatabaseType;
import org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaData;
import org.apache.shardingsphere.sql.parser.binder.metadata.table.TableMetaData;
import org.apache.shardingsphere.underlying.common.config.DatabaseAccessConfiguration;
import org.apache.shardingsphere.underlying.common.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.underlying.common.metadata.datasource.DataSourceMetas;
import org.apache.shardingsphere.underlying.common.rule.BaseRule;

import javax.sql.DataSource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 源码: org.apache.shardingsphere:sharding-jdbc-core:4.1.1
 *
 * 加载元数据支持本地缓存
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
        SchemaMetaData schemaMetaData = myLoadSchemaMetaData(dataSourceMap);
        log.info("Meta data load Schema finished, cost {} milliseconds.", System.currentTimeMillis() - start);
        return new ShardingSphereMetaData(dataSourceMetas, schemaMetaData);
    }

    @SneakyThrows
    private SchemaMetaData myLoadSchemaMetaData(Map<String, DataSource> dataSourceMap) {
        // 尝试读取本地缓存
        File folder = Paths.get(FileSystemView.getFileSystemView().getHomeDirectory().getPath(), StrUtil.format("/Temp/{}", LocalDateTimeUtil.format(LocalDate.now(), "yyyyMM"))).toFile();
        File file = Paths.get(folder.getPath(), StrUtil.format("/shardingJdbc_SchemaMetaData_{}.json", LocalDate.now())).toFile();
        if(file.exists()) {
            log.info("Meta data load Schema: load cache from {}", file.getPath());
            String json = FileUtil.readString(file.getPath(), "UTF-8");
            Map<String, TableMetaData> tables = JSON.parseObject(json, new TypeReference<HashMap<String, TableMetaData>>(){});
            return new SchemaMetaData(tables);
        }

        // 从数据库加载
        log.info("Meta data load Schema: load from DataSource");
        SchemaMetaData schemaMetaData = loadSchemaMetaData(dataSourceMap);

        // 缓存到本地
        if(!folder.exists()) {
            Files.createDirectory(folder.toPath());
        }
        Map<String, TableMetaData> tables = new HashMap<>();
        schemaMetaData.getAllTableNames().forEach(t -> tables.put(t, schemaMetaData.get(t)));
        log.info("Meta data load Schema: write cache to {}", file.getPath());
        FileUtil.writeString(JSON.toJSONString(tables), file, "UTF-8");

        return schemaMetaData;
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
