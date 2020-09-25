package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyShardingMapper;
import com.ozz.sharding.model.TOrder;
import java.util.List;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
  @Autowired
  MyShardingMapper myMapper;

  public List<TOrder> selectSql(String sql) {
    return myMapper.selectSql(sql);
  }

  public List<TOrder> selectOrder(Long userId, Long orderId) {
    return myMapper.selectOrder(userId, orderId);
  }

  public void insertOrder(TOrder order) {
    myMapper.insertOrder(order);
  }

  @Transactional(rollbackFor =Exception.class)
  @ShardingTransactionType(value = TransactionType.XA)
  public void update(String sql) {
    myMapper.updateSql(sql);
  }
}
