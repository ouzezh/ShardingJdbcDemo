package com.ozz.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@MapperScan("com.ozz.sharding.mapper")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingApp {

  public static void main(String[] args) {
    SpringApplication.run(ShardingApp.class, args);
  }

}
