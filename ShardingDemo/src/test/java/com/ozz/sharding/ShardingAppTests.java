package com.ozz.sharding;

import com.ozz.sharding.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ShardingAppTests {

  @Resource
  private MyService myService;

  @Test
  void contextLoads() {
  }
}
