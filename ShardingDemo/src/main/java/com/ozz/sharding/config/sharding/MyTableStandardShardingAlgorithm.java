package com.ozz.sharding.config.sharding;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

public class MyTableStandardShardingAlgorithm extends AbstractStandardShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  @Override
  protected String getTargetName(String logicTableName, long index) {
    return String.format("%s_%s", logicTableName, index);
  }
}
