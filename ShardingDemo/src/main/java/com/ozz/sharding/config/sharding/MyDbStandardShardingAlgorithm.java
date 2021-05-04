package com.ozz.sharding.config.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;

public class MyDbStandardShardingAlgorithm extends AbstractStandardShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  @Override
  protected String getTargetName(String logicTableName, long index) {
    return String.format("ds%s", index);
  }
}
