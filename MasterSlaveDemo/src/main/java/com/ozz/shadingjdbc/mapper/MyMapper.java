package com.ozz.shadingjdbc.mapper;

import java.util.List;

public interface MyMapper {
  List<String> selectMaster();
  List<String> selectSlave();
}