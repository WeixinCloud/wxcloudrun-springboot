package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

  private Integer id;

  private String name;

  private Integer age;

  private String email;

  private String phone;

  private String description;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
