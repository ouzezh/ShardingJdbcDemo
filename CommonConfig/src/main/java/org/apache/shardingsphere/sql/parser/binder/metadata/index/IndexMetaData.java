package org.apache.shardingsphere.sql.parser.binder.metadata.index;

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
public class IndexMetaData {
    
    private String name;
}
