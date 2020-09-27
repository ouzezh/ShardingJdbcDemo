package com.ozz.sharding.config.sharding;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.Collections;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

public class MyTableStandardShardingAlgorithm extends AbstractShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  protected String getTargetName(String logicTableName, long index) {
    return String.format("%s_%s", logicTableName, index);
  }

  @Override
  public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
    printShadingInfo(availableTargetNames, shardingValue);
    return checkTargetName(availableTargetNames, getTargetName(shardingValue.getLogicTableName(), shardingValue.getValue() % 2));
  }

  @Override
  public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
    printShadingInfo(availableTargetNames, shardingValue);
    Range range = shardingValue.getValueRange();
    Long lower = getEndPoint(range.hasLowerBound(), range.lowerBoundType(), range.lowerEndpoint(), 1);
    Long upper = getEndPoint(range.hasUpperBound(), range.upperBoundType(), range.upperEndpoint(), -1);
    if(lower == null || upper == null || lower > upper) {
      return availableTargetNames;
    } else {
      return Collections.singletonList(checkTargetName(availableTargetNames, getTargetName(shardingValue.getLogicTableName(), lower % 2)));
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
