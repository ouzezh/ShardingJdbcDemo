package com.ozz.sharding.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ozz.sharding.mapper.MyShardingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyService {
    @Resource
    private MyShardingMapper myMapper;


    public List<String> selectById(Long id) {
        return myMapper.selectById(id);
    }

    @DS("ds2")
    public List<String> selectByIdSharding(Long id) {
//        try {
//            DynamicDataSourceContextHolder.push("ds2");
//            return myMapper.selectById(id);
//        } finally {
//            DynamicDataSourceContextHolder.poll();
//        }
        return myMapper.selectById(id);
    }
}
