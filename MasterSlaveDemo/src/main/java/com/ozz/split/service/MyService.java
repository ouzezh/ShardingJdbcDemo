package com.ozz.split.service;

import com.ozz.split.mapper.MySplitMapper;
import java.util.List;
import java.util.Map;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
  @Autowired
  MySplitMapper mySplitMapper;

  public void update(String name) {
    mySplitMapper.update(name);
  }

  public List<Map<String, String>> selectMaster() {
    try {
      HintManager.getInstance().setMasterRouteOnly();
      return select();
    } finally {
      HintManager.clear();
    }
  }

  public List<Map<String, String>> select() {
    return mySplitMapper.select(1L);
  }
}
