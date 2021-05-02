package com.ozz.sharding.config.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;

public class MyDbStandardShardingAlgorithm extends MyTableStandardShardingAlgorithm implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Integer> {
  @Override
  protected String getTargetName(String logicTableName, long index) {
    return String.format("ds%s", index);
  }
}
