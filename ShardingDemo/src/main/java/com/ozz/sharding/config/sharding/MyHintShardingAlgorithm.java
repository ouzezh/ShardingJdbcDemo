package com.ozz.sharding.config.sharding;

import java.util.Collection;
import java.util.stream.Collectors;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

public class MyHintShardingAlgorithm extends AbstractShardingAlgorithm implements HintShardingAlgorithm<String> {
  @Override
  public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
    printShadingInfo(availableTargetNames, shardingValue);
    return shardingValue.getValues().stream().map(t -> checkTargetName(availableTargetNames, t)).collect(Collectors.toList());
  }
}
