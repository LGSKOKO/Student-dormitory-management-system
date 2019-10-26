package com.lgs.entity;

//tb_student表对应的实体类
public class Student {

  private String username;//主键 编号/账号
  private String password;//密码
  private String sex;//性别
  private long money;//卡内余额
  private String buildingNo;//所依赖的外键 宿舍楼号
  private String roomNo;//所依赖的外键 宿舍号
  private String classNo;//所依赖的外键 班级号
  private String collegeNo;//所依赖的外键 院系号
  private Building building;//成员属性
  private Room room;//成员属性
  private Class classes;//成员属性
  private College college;//成员属性



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

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBuildingNo() {
    return buildingNo;
  }

  public void setBuildingNo(String buildingNo) {
    this.buildingNo = buildingNo;
  }

  public String getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(String roomNo) {
    this.roomNo = roomNo;
  }

  public String getClassNo() {
    return classNo;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }

  public String getCollegeNo() {
    return collegeNo;
  }

  public void setCollegeNo(String collegeNo) {
    this.collegeNo = collegeNo;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Class getClasses() {
    return classes;
  }

  public void setClasses(Class classes) {
    this.classes = classes;
  }

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }
}
