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

public abstract class AbstractStandardShardingAlgorithm extends AbstractShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
  protected abstract String getTargetName(String logicTableName, long index);

  @Override
  public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
    printShadingInfo(availableTargetNames, shardingValue);
    return checkTargetName(availableTargetNames, getTargetName(shardingValue.getLogicTableName(), shardingValue.getValue() % availableTargetNames.size()));
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
      List<String> list = new ArrayList<>();
      upper = Math.min(upper, lower.longValue() + availableTargetNames.size() - 1);
      for(long i=lower.longValue(); i<=upper.longValue(); i++) {
        list.add(checkTargetName(availableTargetNames, getTargetName(shardingValue.getLogicTableName(), i % availableTargetNames.size())));
      }
      return list;
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
