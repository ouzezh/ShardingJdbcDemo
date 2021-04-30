package com.ozz.split;

import com.ozz.split.service.MyService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MasterSlaveAppTests {

  @Autowired
  MyService myService;

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
    List<Map<String, String>> list = master ? myService.selectMaster() : myService.select();
    return list.get(0).get(prop);
  }
}
