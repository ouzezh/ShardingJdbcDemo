package com.ozz.sharding.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MyMasterSlaveMapper {

  List<Map<String, String>> select(@Param("id") Long id);

  void update(@Param("name") String name);
}
