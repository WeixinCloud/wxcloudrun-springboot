package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ToDo implements Serializable {

  private Integer id;

  private String title;

  private String status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
