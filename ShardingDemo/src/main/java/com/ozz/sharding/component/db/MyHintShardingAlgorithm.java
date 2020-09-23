package com.ozz.sharding.component.db;

import java.util.Collection;
import java.util.Collections;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
  @Override
  public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
    System.out.println(availableTargetNames);
    return Collections.singletonList(String.format("ds-%s"));
  }
}
