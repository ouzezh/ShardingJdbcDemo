package com.ozz.shadingjdbc;

import com.ozz.shadingjdbc.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MasterSlaveAppTests {

  @Autowired
  MyService myService;

  @Test
  void contextLoads() {
    System.out.println(myService.selectMaster());
    System.out.println(myService.selectSlave());
  }

}
