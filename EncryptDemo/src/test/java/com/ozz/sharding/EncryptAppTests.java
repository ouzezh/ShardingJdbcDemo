package com.ozz.sharding;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ozz.sharding.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MasterSlaveAppTests {

    @Resource
    private MyService myService;

    @Test
    void contextLoads() {
        List<Map<String, Object>> list = myService.select();
        System.out.println(JSONUtil.toJsonStr(list));
        Assert.isTrue(list.get(0).get("name").toString().startsWith("ENC_"));
    }

}
