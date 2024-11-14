package com.ozz.sharding.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MyEncryptMapper {

  List<Map<String, Object>> select(@Param("id") Long id);

}
