package com.ozz.sharding.mapper;

import java.util.List;

public interface MyShardingMapper {
  List<String> selectOrder();
  void update();
}