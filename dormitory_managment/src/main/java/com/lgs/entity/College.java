package com.lgs.entity;

//tb_college表对应的实体类
public class College {

  private String collegeNo;//主键 院系编号
  private String collegeName;//院系名


  public String getCollegeNo() {
    return collegeNo;
  }

  public void setCollegeNo(String collegeNo) {
    this.collegeNo = collegeNo;
  }


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

}
