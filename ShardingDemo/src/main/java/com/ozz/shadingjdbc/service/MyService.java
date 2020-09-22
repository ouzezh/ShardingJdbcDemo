package com.ozz.shadingjdbc.service;

import com.ozz.shadingjdbc.mapper.MyMapper;
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
  MyMapper myMapper;

  @Transactional(rollbackFor =Exception.class)
  @ShardingTransactionType(value = TransactionType.XA)
  public void update() {
    myMapper.update();
  }

  public List<String> selectMaster() {
    try(HintManager hm = HintManager.getInstance()) {
      hm.setMasterRouteOnly();
      return myMapper.selectMaster();
    }
  }

  public List<String> selectSlave() {
    return myMapper.selectSlave();
  }
}
