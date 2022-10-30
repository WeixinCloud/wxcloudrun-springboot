package com.tencent.wxcloudrun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * index控制器
 */
@Controller
@ApiIgnore
public class IndexController {

  /**
   * 主页页面
   * @return API response html
   */
  @GetMapping
  public String index() {
    return "index";
  }

}
