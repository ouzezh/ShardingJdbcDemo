package com.ozz.sharding;

import com.ozz.sharding.model.TOrder;
import com.ozz.sharding.service.MyService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingAppTests {

  @Autowired
  MyService myService;

  @Test
  void contextLoads() {
    testDefault();
    testShardingHint();
    testShardingStandard();
    testInsert();
  }

  private void testDefault() {
    myService.selectSql("select name from t_dict");
    myService.selectSql("select 1 from dual");
  }

  private void testInsert() {
    TOrder order = new TOrder(1l, null);
    myService.insertOrder(order);
    System.out.println(order);
  }

  private void testShardingStandard() {
    myService.selectOrder(1l, 3l);
  }

  private void testShardingHint() {
    try (HintManager hm = HintManager.getInstance()) {
      hm.setDatabaseShardingValue("ds1");// 清空database&table配置
      myService.selectSql("select 1 from test_hint");
    }
    try (HintManager hm = HintManager.getInstance()) {
      hm.addDatabaseShardingValue("test_hint", "ds1");
      hm.addTableShardingValue("test_hint", "dual");
      myService.selectSql("select 1 from test_hint");
    }
  }

}
