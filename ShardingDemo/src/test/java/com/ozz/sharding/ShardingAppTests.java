package com.ozz.sharding;

import com.ozz.sharding.model.TOrder;
import com.ozz.sharding.service.MyService;
import java.util.List;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ShardingAppTests {

  @Autowired
  MyService myService;

  @Test
  void contextLoads() {
    testSelect();
//    testShardingHint();
//    testShardingStandard();
//    testInsert();
//    testTransaction();
  }

  /**
   * 测试事务，其中一个表不存在：同物理库中提交会回滚、不同库不会回滚
   */
  private void testTransaction() {
    try {
      myService.update("update t_test_tx set name = '8' where id = 1");
    } catch (Exception e) {
      if(e.getMessage().replaceAll("[\\r\\n]", " ").matches(".*Table\\s+.*\\s+doesn't\\s+exist.*")) {
      } else {
        throw e;
      }
    }
  }

  private void testSelect() {
    // 广播表随机读一个库
    List<String> list = myService.selectSql("select name from t_dict");
    System.out.println(list);
    Assert.isTrue(list.size()==1, "select broadcast table error");

    // 未配置分片表读默认库
    list = myService.selectSql("select code from t_master_slave");
    System.out.println(list);
    Assert.isTrue(list.size()==1 && "mc".equals(list.get(0)), "select not match table error");

    // 配置分片未配置路由规则
    list = myService.selectSql("select 1 from t_test_hint");
    System.out.println(list);
    Assert.isTrue(list.size()==2, "select split and no route rule table error");

    // binding-tables
    list = myService.selectSql("select o.order_id from t_order o join t_order_item i on 1=1");
    System.out.println(list);
    Assert.isTrue(list.size()==8, "select split and no route rule table error");
  }

  private void testInsert() {
    TOrder order = new TOrder(1l, null);
    Assert.isNull(order.getOrderId(), "order is not null");
    myService.insertOrder(order);
    Assert.isNull(order.getOrderId(), "order is null");

    order = new TOrder(2l, null);
    Assert.isNull(order.getOrderId(), "order is not null");
    myService.insertOrder(order);
    Assert.isNull(order.getOrderId(), "order is null");
  }

  private void testShardingStandard() {
    myService.selectOrder(1l, 3l);
  }

  private void testShardingHint() {
    try (HintManager hm = HintManager.getInstance()) {
      hm.setDatabaseShardingValue("ds1");// 仅匹配数据库 ( 清空 database & table 配置 )
      myService.selectSql("select 1 from t_test_hint");
    }
    try (HintManager hm = HintManager.getInstance()) {
      hm.addDatabaseShardingValue("t_test_hint", "ds1");
      hm.addTableShardingValue("t_test_hint", "dual");
      myService.selectSql("select 1 from t_test_hint");
    }
  }

}
