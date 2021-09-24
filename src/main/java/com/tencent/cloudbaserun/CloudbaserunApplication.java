package com.tencent.cloudbaserun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.tencent.cloudbaserun.dao"})
public class CloudbaserunApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudbaserunApplication.class, args);
  }
}
