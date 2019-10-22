package com.lgs.entity;

//tb_admin表对应的实体类
public class Admin {

  private String username;//主键 账号/编号
  private String password;//密码
  private String buildingNo;//所依赖的外键 宿舍楼号
  private Building building;//成员属性


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBuildingNo() {
    return buildingNo;
  }

  public void setBuildingNo(String buildingNo) {
    this.buildingNo = buildingNo;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }
}
