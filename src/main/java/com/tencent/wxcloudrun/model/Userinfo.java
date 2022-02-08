package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Userinfo implements Serializable {

  private String id;

  private String sex;

  private String location;

  private String emotion;

//  public void setSex(String sex){
//    switch (sex){
//      case "male":
//        this.sex = SexEnum.M;
//        break;
//      default:
//        this.sex = SexEnum.F;
//    }
//  }
//
//
//  public enum SexEnum{
//    M("male"),
//    F("female");
//
//    private String sex;
//
//    private SexEnum(String sex){
//      this.sex=sex;
//    }
//
//
//    public String getSex() {
//      return sex;
//    }
//
//    public void setSex(String sex) {
//      this.sex = sex;
//    }





//  }



}
