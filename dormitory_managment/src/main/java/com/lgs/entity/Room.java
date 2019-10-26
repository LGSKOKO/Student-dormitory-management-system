package com.lgs.entity;

//tb_room表对应的实体类
public class Room {

  private String roomNo;//房间号
  private String roomName;//房间名
  private long count;//当前房间电费余额
  private String buildingNo;//所依赖的外键 宿舍楼号
  private Building building;//成员属性


  public String getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(String roomNo) {
    this.roomNo = roomNo;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
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
