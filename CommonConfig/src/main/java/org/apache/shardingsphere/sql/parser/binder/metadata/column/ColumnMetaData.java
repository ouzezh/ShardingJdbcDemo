package org.apache.shardingsphere.sql.parser.binder.metadata.column;

import lombok.*;

/**
 * 源码:  org.apache.shardingsphere:shardingsphere-sql-parser-binder :4.1.1
 *
 * 加载元数据支持本地缓存：增加NoArgsConstructor支出序列化
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ColumnMetaData {
    
    private String name;
    
    private int dataType;
    
    private String dataTypeName;
    
    private boolean primaryKey;
    
    private boolean generated;
    
    private boolean caseSensitive;
}
