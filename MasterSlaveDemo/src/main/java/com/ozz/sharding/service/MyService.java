package com.ozz.sharding.service;

import com.ozz.sharding.mapper.MyMasterSlaveMapper;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MyService {
    @Resource
    private MyMasterSlaveMapper myMasterSlaveMapper;

    public void update(String name) {
        myMasterSlaveMapper.update(name);
    }

    public List<Map<String, String>> selectMaster() {
        try {
            HintManager.getInstance().setWriteRouteOnly();
            return select();
        } finally {
            HintManager.clear();
        }
    }

    public List<Map<String, String>> select() {
        return myMasterSlaveMapper.select(1L);
    }
}
