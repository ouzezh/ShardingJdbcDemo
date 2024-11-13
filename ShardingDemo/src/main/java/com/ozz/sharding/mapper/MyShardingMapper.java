package com.ozz.sharding.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyShardingMapper {

  List<String> selectById(@Param("id") Long id);

}
