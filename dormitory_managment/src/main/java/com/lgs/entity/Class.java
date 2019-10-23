package com.lgs.entity;

//tb_class表对应的实体类
public class Class {

  private String classNo;//主键 班级编号
  private String className;//班级名
  private String collegeNo;//所依赖的外键 院系号
  private College college;//成员属性


  public String getClassNo() {
    return classNo;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getCollegeNo() {
    return collegeNo;
  }

  public void setCollegeNo(String collegeNo) {
    this.collegeNo = collegeNo;
  }

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }
}
