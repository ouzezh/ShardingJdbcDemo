package com.ozz.sharding;

import com.ozz.sharding.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingAppTests {

  @Autowired
  MyService myService;

  @Test
  void contextLoads() {
    System.out.println(myService.selectOrderMaster());
//    System.out.println(myService.selectSlave());
//    myService.update();
  }

}
