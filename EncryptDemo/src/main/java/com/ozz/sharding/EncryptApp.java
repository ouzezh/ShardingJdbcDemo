package com.ozz.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ozz.sharding.mapper")
public class EncryptApp {

  public static void main(String[] args) {
    SpringApplication.run(EncryptApp.class, args);
  }

}
