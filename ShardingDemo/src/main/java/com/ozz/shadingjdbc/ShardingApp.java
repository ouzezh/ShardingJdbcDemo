package com.ozz.shadingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@MapperScan("com.ozz.shadingjdbc.mapper")
public class ShardingApp {

  public static void main(String[] args) {
    SpringApplication.run(ShardingApp.class, args);
  }

}
