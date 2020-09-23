package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyShardingMapper;
import com.ozz.sharding.model.TOrder;
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
  MyShardingMapper myMapper;

  public List<TOrder> selectOrderMaster(Long orderId, Long userId) {
    try(HintManager hm = HintManager.getInstance()) {
      hm.setMasterRouteOnly();
      return myMapper.selectOrder(orderId, userId);
    }
  }

  public List<TOrder> selectOrderSlave() {
    return myMapper.selectOrder(null, null);
  }

  public void insertOrder(TOrder order) {
    myMapper.insertOrder(order);
  }

  @Transactional(rollbackFor =Exception.class)
  @ShardingTransactionType(value = TransactionType.XA)
  public void update() {
    myMapper.update();
  }
}
