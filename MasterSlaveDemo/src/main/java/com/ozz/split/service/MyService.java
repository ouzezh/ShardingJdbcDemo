package com.ozz.split.service;

import com.ozz.split.mapper.MyMasterSlaveMapper;
import java.util.List;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
  @Autowired
  MyMasterSlaveMapper myMapper;

  public void update() {
    myMapper.update();
  }

  public List<String> selectMaster() {
    try {
      HintManager.getInstance().setMasterRouteOnly();
      return myMapper.selectMaster();
    } finally {
      HintManager.clear();
    }
  }

  public List<String> selectSlave() {
    return myMapper.selectSlave();
  }
}
