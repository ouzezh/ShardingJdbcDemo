package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyEncryptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MyService {
    @Resource
    private MyEncryptMapper myEncryptMapper;

    public List<Map<String, Object>> select() {
        return myEncryptMapper.select(1L);
    }
}
