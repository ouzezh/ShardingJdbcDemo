package com.ozz.sharding.mapper;

import com.ozz.sharding.model.TOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyShardingMapper {
  List<TOrder> selectOrder(@Param("orderId") Long orderId, @Param("userId") Long userId);
  void update();

  void insertOrder(TOrder order);
}