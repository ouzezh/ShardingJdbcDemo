package com.ozz.split.mapper;

import java.util.List;

public interface MyMasterSlaveMapper {
  List<String> selectMaster();
  List<String> selectSlave();
  void update();
}