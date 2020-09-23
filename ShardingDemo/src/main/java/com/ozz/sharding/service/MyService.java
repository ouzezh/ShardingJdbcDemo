package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyShardingMapper;
import java.util.List;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
  @Autowired
  MyShardingMapper myShardingMapper;

  @Transactional(rollbackFor =Exception.class)
  @ShardingTransactionType(value = TransactionType.XA)
  public void update() {
    myShardingMapper.update();
  }

  public List<String> selectOrderMaster() {
    try(HintManager hm = HintManager.getInstance()) {
      hm.setMasterRouteOnly();
      return myShardingMapper.selectOrder();
    }
  }

  public List<String> selectOrderSlave() {
    return myShardingMapper.selectOrder();
  }
}
