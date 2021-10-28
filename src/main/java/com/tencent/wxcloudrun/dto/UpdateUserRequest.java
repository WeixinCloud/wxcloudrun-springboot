package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {

  private String name;

  private Integer age;

  private String email;

  private String phone;

  private String description;
}
