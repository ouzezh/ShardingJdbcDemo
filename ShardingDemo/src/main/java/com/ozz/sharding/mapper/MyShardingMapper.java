package com.ozz.sharding.mapper;

import com.ozz.sharding.model.TOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyShardingMapper {
  List<String> selectSql(@Param("sql") String sql);

  List<TOrder> selectOrder(@Param("userId") Long userId, @Param("orderId") Long orderId);

  void insertOrder(TOrder order);

  void updateSql(String sql);
}