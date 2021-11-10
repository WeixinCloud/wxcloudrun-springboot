package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class CreateToDoRequest {
  
  private String title;

  private String status;
}
