package com.lgs.entity;

//tb_building表对应的实体类
public class Building {

  private String buildingNo;//主键 楼号
  private String buildingName;//宿舍楼名


  public String getBuildingNo() {
    return buildingNo;
  }

  public void setBuildingNo(String buildingNo) {
    this.buildingNo = buildingNo;
  }


  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

}
