package com.ozz.sharding.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyShardingMapper {

  String selectById(@Param("id") Long id);

}
