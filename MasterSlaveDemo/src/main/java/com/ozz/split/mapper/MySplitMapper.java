package com.ozz.split.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MySplitMapper {

  List<Map<String, String>> select(@Param("id") Long id);

  void update(@Param("name") String name);
}