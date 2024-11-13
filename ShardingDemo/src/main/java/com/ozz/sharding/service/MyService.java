package com.ozz.sharding.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.ozz.sharding.mapper.MyShardingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyService {
    @Resource
    private MyShardingMapper myMapper;

    @DS("ds1")
    public String selectById1(Long id) {
        return myMapper.selectById(id);
    }

    public String selectById2(Long id) {
        try {
            DynamicDataSourceContextHolder.push("ds2");
            return myMapper.selectById(id);
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }
}
