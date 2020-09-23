package com.ozz.sharding.component.db;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.Collections;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

public class MyStandardShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  @Override
  public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
    return String.format("%s_%s", shardingValue.getLogicTableName(), shardingValue.getValue() % 2);
  }

  @Override
  public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
    Range range = shardingValue.getValueRange();
    Long lower = getEndPoint(range.hasLowerBound(), range.lowerBoundType(), range.lowerEndpoint(), 1);
    Long upper = getEndPoint(range.hasUpperBound(), range.upperBoundType(), range.upperEndpoint(), -1);
    if(lower == null || upper == null || lower > upper) {
      return availableTargetNames;
    } else {
      return Collections.singletonList(String.format("%s_%s", shardingValue.getLogicTableName(), lower % 2));
    }
  }

  private Long getEndPoint(boolean hasBound, BoundType boundType, Comparable endpoint, int closeFix) {
    if(!hasBound) {
      return null;
    }
    Long v = (Long) endpoint;
    return boundType == BoundType.CLOSED ? v : v + closeFix;
  }
}
