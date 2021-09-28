package com.ozz.sharding;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.ozz.sharding.model.TOrder;
import com.ozz.sharding.model.TOrderItem;
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
    testShardingHint();
    testShardingStandard();
    testInsert();
    testTransaction();
    testDynamicDataSource();
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

    // 配置分片未配置路由规则，读全部分片
    list = myService.selectSql("select 1 from t_hint_db_table");
    System.out.println(list);
    Assert.isTrue(list.size()==4, "select split and no route rule table error");

    // binding-tables
    // (1)如配置绑定表，执行2次查询，分别为： ds1-order0-item0, ds1-order1-item1
    // (2)如未配置绑定表，将执行2*2共4次查询，返回5条结果
    list = myService.selectSql("select i.order_item_id from t_order o join t_order_item i on o.user_id=i.user_id and o.order_id=i.order_id where o.user_id=?", 0L);
    System.out.println(list);
    Assert.isTrue(list.size()==2, "select binding-tables error");
  }

  private void testInsert() {
    TOrderItem item = new TOrderItem();
    item.setOrderId(3L);
    item.setUserId(1L);
    Assert.isNull(item.getOrderItemId(), "check status fail");
    myService.insertOrderItem(item);
    Assert.isTrue(item.getOrderItemId()!=null, "check status fail");
  }

  private void testShardingStandard() {
    List<String> list = myService.selectSql("select order_id from t_order where user_id=? and order_id=?", 1L, 3L);
    System.out.println(JSON.toJSONString(list));
    Assert.isTrue(list.size()==1 && "3".equals(list.get(0)), "check status fail");
  }

  private void testShardingHint() {
    List<String> list = myService.selectSql("select order_id from t_hint_db_table");
    System.out.println(list);
    Assert.isTrue(list.size()==4, "check status fail");
    try (HintManager hm = HintManager.getInstance()) {
      hm.setDatabaseShardingValue("ds1");// 仅匹配数据库 ( 清空 database & table 配置 )
      list = myService.selectSql("select order_id from t_hint_db_table");
      System.out.println(list);
      Assert.isTrue(list.size()==2, "check status fail");
    } finally {
      HintManager.clear();
    }

    list = myService.selectSql("select order_id from t_hint_db_table");
    System.out.println(list);
    Assert.isTrue(list.size()==4, "check status fail");
    try (HintManager hm = HintManager.getInstance()) {
      hm.addDatabaseShardingValue("t_hint_db_table", "ds1");
      hm.addTableShardingValue("t_hint_db_table", "t_order_1");
      list = myService.selectSql("select order_id from t_hint_db_table");
      System.out.println(list);
      Assert.isTrue(list.size()==1&&"3".equals(list.get(0)), "check status fail");
    } finally {
      HintManager.clear();
    }
  }

  /**
   * 测试事务，其中一个表不存在：同物理库中提交会回滚、不同库不会回滚
   */
  private void testTransaction() {
    try {
      HintManager.getInstance().setDatabaseShardingValue("ds0");
      List<String> list = myService.selectSql("select name from t_transaction");
      Assert.isTrue(list.size()==1&&"n0".equals(list.get(0)), "check status fail");
    } finally {
      HintManager.clear();
    }
    try {
      myService.update("update t_transaction set name=?", "updated");
    } catch (Exception e) {
      if(e.getMessage().replaceAll("[\\r\\n]", " ").matches(".*Table\\s+.*\\s+doesn't\\s+exist.*")) {
      } else {
        throw e;
      }
    }
    try {
      HintManager.getInstance().setDatabaseShardingValue("ds0");
      List<String> list = myService.selectSql("select name from ds0.t_transaction");
      Assert.isTrue(list.size()==1&&"n0".equals(list.get(0)), "check status fail");
    } finally {
      HintManager.clear();
    }
  }

  private void testDynamicDataSource() {
    String sql = "select code from t_dynamic";
    List<String> res = myService.selectDynamicSql(sql);
    checkDynamicDataSource(res);

    DynamicDataSourceContextHolder.push("myDynamicDS");
    try {
      res = myService.selectSql(sql);
      checkDynamicDataSource(res);
    } finally {
      DynamicDataSourceContextHolder.poll();
    }
  }
  private void checkDynamicDataSource(List<String> res) {
    System.out.println(res);
    Assert.isTrue(res.size()==1 && "dc".equalsIgnoreCase(res.get(0)), "check status fail");
  }
}
