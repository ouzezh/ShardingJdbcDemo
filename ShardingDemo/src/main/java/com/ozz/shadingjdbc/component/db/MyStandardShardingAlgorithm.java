package com.ozz.shadingjdbc.component.db;

import com.google.common.collect.Range;
import java.util.Collection;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

public class MyStandardShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  @Override
  public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
    System.out.println(availableTargetNames);// XXX
    shardingValue.getLogicTableName();
    shardingValue.getColumnName();
    Long v = shardingValue.getValue();
    return String.format("xx_%s", v.longValue() % 2);
  }

  @Override
  public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
    System.out.println(availableTargetNames); // XXX
    shardingValue.getLogicTableName();
    shardingValue.getColumnName();
    Range range = shardingValue.getValueRange();
    if(range.hasLowerBound()) {
      range.lowerBoundType();
      range.lowerEndpoint();
    }
    if(range.hasUpperBound()) {
      range.upperBoundType();
      range.upperEndpoint();
    }
    if(true) {
      throw new RuntimeException("NOT_SUPPORT");
    }
    return null;
  }
}
