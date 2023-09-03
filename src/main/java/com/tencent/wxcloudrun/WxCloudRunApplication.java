package com.tencent.wxcloudrun;

import com.tencent.wxcloudrun.http.MyHttpConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = {"com.tencent.wxcloudrun.dao"})
public class WxCloudRunApplication {

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate template = new RestTemplate();
    template.getMessageConverters().add(new MyHttpConverter());
    return template;
  }

  public static void main(String[] args) {
    SpringApplication.run(WxCloudRunApplication.class, args);
  }
}
