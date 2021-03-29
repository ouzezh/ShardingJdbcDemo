package com.ozz.split.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyMasterSlaveMapper {
  List<String> selectMaster(@Param("limit") int limit);
  List<String> selectSlave(@Param("limit") int limit);
  void update();
}