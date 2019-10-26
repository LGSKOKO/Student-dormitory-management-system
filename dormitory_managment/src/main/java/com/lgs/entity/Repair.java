package com.lgs.entity;

//tb_repair表对应的实体类
public class Repair {

  private String repairNo;//报修编号
  private String repairName;//报修概要
  private String repairDetail;//报修明细
  private String dealWith;//是否处理
  private String roomNo;//外键 宿舍号
  private String buildingNo;//外键 宿舍楼号
  private Room room;//成员属性
  private Building building;//成员属性


  public String getRepairNo() {
    return repairNo;
  }

  public void setRepairNo(String repairNo) {
    this.repairNo = repairNo;
  }


  public String getRepairName() {
    return repairName;
  }

  public void setRepairName(String repairName) {
    this.repairName = repairName;
  }


  public String getRepairDetail() {
    return repairDetail;
  }

  public void setRepairDetail(String repairDetail) {
    this.repairDetail = repairDetail;
  }


  public String getDealWith() {
    return dealWith;
  }

  public void setDealWith(String dealWith) {
    this.dealWith = dealWith;
  }


  public String getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(String roomNo) {
    this.roomNo = roomNo;
  }


  public String getBuildingNo() {
    return buildingNo;
  }

  public void setBuildingNo(String buildingNo) {
    this.buildingNo = buildingNo;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }
}
