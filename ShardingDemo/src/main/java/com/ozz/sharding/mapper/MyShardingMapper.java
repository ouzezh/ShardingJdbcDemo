package com.ozz.sharding.mapper;

import com.ozz.sharding.model.TOrder;
import com.ozz.sharding.model.TOrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyShardingMapper {

  List<String> selectSql(@Param("sql") String sql);

  int updateSql(@Param("sql") String sql);

  int insertOrderItem(TOrderItem item);
}