package com.ozz.sharding;

import com.ozz.sharding.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
class MasterSlaveAppTests {

    @Resource
    private MyService myService;

    @Test
    void contextLoads() {
        testSelectMaster();
        testSelectSlave();
        testUpdate();
    }

    private void testSelectMaster() {
        String code1 = getProp("code", true);
        Assert.isTrue("mc".equals(code1), "selectMaster error");
    }

    private void testSelectSlave() {
        String code2 = getProp("code", false);
        Assert.isTrue("sc".equals(code2), "selectMaster error");
    }

    private void testUpdate() {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String v = getProp("name", true);
        Assert.isTrue(!ts.equals(v), "update check status before error");
        myService.update(ts);
        v = getProp("name", true);
        Assert.isTrue(ts.equals(v), "update check status after error");
        v = getProp("name", false);
        Assert.isTrue("sn".equals(v), "update check status after error");
    }

    private String getProp(String prop, boolean master) {
        List<Map<String, Object>> list = master ? myService.selectMaster() : myService.select();
        return Objects.toString(list.get(0).get(prop));
    }
}
