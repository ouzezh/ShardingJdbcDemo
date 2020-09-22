package com.ozz.shadingjdbc.service;

import com.ozz.shadingjdbc.mapper.MyMapper;
import java.util.List;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
  @Autowired
  MyMapper myMapper;

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
