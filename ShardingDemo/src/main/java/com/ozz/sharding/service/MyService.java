package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyShardingMapper;
import com.ozz.sharding.model.TOrder;
import com.ozz.sharding.model.TOrderItem;
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

  public List<String> selectSql(String sql) {
    return myMapper.selectSql(sql);
  }

  @Transactional(rollbackFor =Exception.class)
  @ShardingTransactionType(value = TransactionType.XA)
  public int update(String sql) {
    return myMapper.updateSql(sql);
  }

  public int insertOrderItem(TOrderItem item) {
    return myMapper.insertOrderItem(item);
  }
}
