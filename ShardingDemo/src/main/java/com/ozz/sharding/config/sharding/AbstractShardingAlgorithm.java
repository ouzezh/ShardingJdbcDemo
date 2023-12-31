package com.ozz.sharding.config.sharding;

import cn.hutool.log.StaticLog;
import org.apache.shardingsphere.api.sharding.ShardingValue;

import java.util.Collection;

public abstract class AbstractShardingAlgorithm {
  protected void printShadingInfo(Collection<String> availableTargetNames, ShardingValue shardingValue) {
    StaticLog.info(String.format("\n[ShardingInfo] %s: availableTargetNames=%s, shardingValue=%s\n", getClass().getSimpleName(), availableTargetNames, shardingValue));
  }

  protected String checkTargetName(Collection<String> availableTargetNames, String targetName) {
    if(targetName==null || targetName.isEmpty()) {
      throw new RuntimeException("targetName is null or empty");
    }
    if(!availableTargetNames.contains(targetName)) {
      throw new RuntimeException(String.format("targetName error, targetName=%s, availableTargetNames=%s", targetName, availableTargetNames));
    }
    return targetName;
  }
}
