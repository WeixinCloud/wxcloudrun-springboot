package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class UpdateToDoRequest {

  private Integer id;

  private String title;

  private String status;
}
