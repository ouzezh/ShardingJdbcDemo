package com.ozz.shadingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ozz.shadingjdbc.mapper")
public class MasterSlaveApp {

  public static void main(String[] args) {
    SpringApplication.run(MasterSlaveApp.class, args);
  }

}
