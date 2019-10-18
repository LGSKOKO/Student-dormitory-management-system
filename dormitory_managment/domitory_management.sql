 #创建数据库
 create database dormitory_management;
 #使用数据库
 use dormitory_management;
 
 #创建超级管理员表
 drop table if exists tb_super;
 create table tb_super(
 id int not null auto_increment,
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 PRIMARY key(id,username)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"超级管理员表";
 
 #创建管理员表
 drop table if exists tb_admin;
 create table tb_admin(
 id int not null auto_increment,
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼号",
 PRIMARY key(id,username),
 CONSTRAINT `FK_admin_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"管理员表";
 
 #创建学生表
 drop table if exists tb_student;
 create table tb_student(
 id int not null auto_increment,
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 sex varchar(2) not null COMMENT"性别",
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼楼号",
 roomNO VARCHAR(5) not null COMMENT"宿舍号",
 classNO VARCHAR(5) not null COMMENT"班级编号",
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 PRIMARY key(id,username),
 CONSTRAINT `FK_student_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`),
 CONSTRAINT `FK_student_room` FOREIGN KEY (`roomNO`) REFERENCES `tb_room` (`roomNO`),
 CONSTRAINT `FK_student_class` FOREIGN KEY (`classNO`) REFERENCES `tb_class` (`classNO`),
 CONSTRAINT `FK_student_college` FOREIGN KEY (`collegeNO`) REFERENCES `tb_college` (`collegeNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"学生表";
 
  #创建宿舍楼表
 drop table if exists tb_building;
 create table tb_building(
 id int not null auto_increment,
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼楼号",
 buildingName VARCHAR(10) COMMENT"宿舍楼名字",
 PRIMARY key(id,buildingNO)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"宿舍楼表";
 
 #创建宿舍表
 drop table if exists tb_room;
 create table tb_room(
  id int not null auto_increment,
 roomNO VARCHAR(5) not null COMMENT"宿舍号",
 roomName VARCHAR(10) COMMENT"宿舍名",
 count   int(2)  DEFAULT 4 COMMENT"剩余床位", 
 buildingNO VARCHAR(5) COMMENT"宿舍楼楼号",
 PRIMARY key(id,roomNO),
  CONSTRAINT `FK_room_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"宿舍表";
 
  #创建班级表
 drop table if exists tb_class;
 create table tb_class(
  id int not null auto_increment,
 classNO VARCHAR(5) not null COMMENT"班级编号",
 className VARCHAR(10) COMMENT"班级名称",
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 PRIMARY key(id,classNO),
 CONSTRAINT `FK_class_college` FOREIGN KEY (`collegeNO`) REFERENCES `tb_college` (`collegeNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"班级表";
 
   #创建院系表
 drop table if exists tb_college;
 create table tb_college(
  id int not null auto_increment,
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 collegeName VARCHAR(10) COMMENT"院系名称",
 PRIMARY key(id,collegeNO)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"院系表";
 

 
 #填充数据
 INSERT INTO `tb_super` VALUES (1, 'super','123456');
 
 INSERT INTO `tb_building` VALUES (1, '20', '宿舍20栋');
 INSERT INTO `tb_building` VALUES (2, '21', '宿舍21栋');
 INSERT INTO `tb_building` VALUES (3, '22', '宿舍22栋');

 
 INSERT INTO `tb_room` VALUES (1, '102', '20栋102',4,'20');
 INSERT INTO `tb_room` VALUES (2, '103', '20栋103',4,'20');
  INSERT INTO `tb_room` VALUES (3, '104', '20栋104',4,'20');
 INSERT INTO `tb_room` VALUES (4, '105', '20栋105',4,'20');
  INSERT INTO `tb_room` VALUES (5, '202', '20栋202',4,'20');
 INSERT INTO `tb_room` VALUES (6, '203', '20栋203',4,'20');
  INSERT INTO `tb_room` VALUES (7, '204', '20栋204',4,'20');
 INSERT INTO `tb_room` VALUES (8, '205', '20栋205',4,'20');
 
  INSERT INTO `tb_room` VALUES (11, '102', '21栋102',4,'21');
 INSERT INTO `tb_room` VALUES (12, '103', '21栋103',4,'21');
  INSERT INTO `tb_room` VALUES (13, '104', '21栋104',4,'21');
 INSERT INTO `tb_room` VALUES (14, '105', '21栋105',4,'21');
  INSERT INTO `tb_room` VALUES (15, '202', '21栋202',4,'21');
 INSERT INTO `tb_room` VALUES (16, '203', '21栋203',4,'21');
  INSERT INTO `tb_room` VALUES (17, '204', '21栋204',4,'21');
 INSERT INTO `tb_room` VALUES (18, '205', '21栋205',4,'21');
 
  INSERT INTO `tb_room` VALUES (21, '102', '22栋102',4,'22');
 INSERT INTO `tb_room` VALUES (22, '103', '22栋103',4,'22');
  INSERT INTO `tb_room` VALUES (23, '104', '22栋104',4,'22');
 INSERT INTO `tb_room` VALUES (24, '105', '22栋105',4,'22');
  INSERT INTO `tb_room` VALUES (25, '202', '22栋202',4,'22');
 INSERT INTO `tb_room` VALUES (26, '203', '22栋203',4,'22');
  INSERT INTO `tb_room` VALUES (27, '204', '22栋204',4,'22');
 INSERT INTO `tb_room` VALUES (28, '205', '22栋205',4,'22');
 
INSERT INTO `tb_college` VALUES (1, '01', '计算机与信息工程院');
INSERT INTO `tb_college` VALUES (2, '02', '软件学院');
INSERT INTO `tb_college` VALUES (3, '03', '艺术学院');

INSERT INTO `tb_class` VALUES (1, '01171', '计17级1班','01');
INSERT INTO `tb_class` VALUES (2, '01172', '计17级2班','01');
INSERT INTO `tb_class` VALUES (11, '02171', '软17级1班','02');
INSERT INTO `tb_class` VALUES (22, '02172', '软17级2班','02');
INSERT INTO `tb_class` VALUES (31, '03171', '艺17级1班','03');
INSERT INTO `tb_class` VALUES (32, '03172', '艺17级2班','03');


INSERT INTO `tb_student` VALUES (1, '01171001', '123456','男','20', '102','01171','01');
INSERT INTO `tb_student` VALUES (2, '01172001', '123456','男','20', '102','01172','01');
INSERT INTO `tb_student` VALUES (3, '02171001', '123456','男','20', '103','02171','02');
INSERT INTO `tb_student` VALUES (4, '02172001', '123456','男','20', '104','02172','02');
INSERT INTO `tb_student` VALUES (5, '03171001', '123456','男','20', '105','03171','03');

INSERT INTO `tb_student` VALUES (21, '01171002', '123456','男','20', '102','01171','01');
INSERT INTO `tb_student` VALUES (22, '01172002', '123456','男','20', '102','01172','01');
INSERT INTO `tb_student` VALUES (23, '02171002', '123456','男','20', '103','02171','02');
INSERT INTO `tb_student` VALUES (24, '02172002', '123456','男','20', '104','02172','02');
INSERT INTO `tb_student` VALUES (25, '03171002', '123456','男','20', '105','03171','03');

INSERT INTO `tb_student` VALUES (31, '01171003', '123456','男','21', '102','01171','01');
INSERT INTO `tb_student` VALUES (32, '01172003', '123456','男','21', '102','01172','01');
INSERT INTO `tb_student` VALUES (33, '02171003', '123456','男','21', '103','02171','02');
INSERT INTO `tb_student` VALUES (34, '02172003', '123456','男','21', '104','02172','02');
INSERT INTO `tb_student` VALUES (35, '03171003', '123456','男','21', '105','03171','03');

INSERT INTO `tb_student` VALUES (41, '01171004', '123456','男','21', '102','01171','01');
INSERT INTO `tb_student` VALUES (42, '01172004', '123456','男','21', '102','01172','01');
INSERT INTO `tb_student` VALUES (43, '02171004', '123456','男','21', '103','02171','02');
INSERT INTO `tb_student` VALUES (44, '02172004', '123456','男','21', '104','02172','02');
INSERT INTO `tb_student` VALUES (45, '03171004', '123456','男','21', '105','03171','03');

INSERT INTO `tb_student` VALUES (51, '01171005', '123456','男','22', '102','01171','01');
INSERT INTO `tb_student` VALUES (52, '01172005', '123456','男','22', '102','01172','01');
INSERT INTO `tb_student` VALUES (53, '02171005', '123456','男','22', '103','02171','02');
INSERT INTO `tb_student` VALUES (54, '02172005', '123456','男','22', '104','02172','02');
INSERT INTO `tb_student` VALUES (55, '03171005', '123456','男','21', '105','03171','03');

INSERT INTO `tb_student` VALUES (61, '01171005', '123456','男','22', '102','01171','01');
INSERT INTO `tb_student` VALUES (62, '01172005', '123456','男','22', '102','01172','01');
INSERT INTO `tb_student` VALUES (63, '02171005', '123456','男','22', '103','02171','02');
INSERT INTO `tb_student` VALUES (64, '02172005', '123456','男','22', '104','02172','02');
INSERT INTO `tb_student` VALUES (65, '03171005', '123456','男','22', '105','03171','03');



insert into tb_admin values(1,'admin','123456','20');
insert into tb_admin values(1,'admin2','123456','20');

insert into tb_admin values(3,'admin3','123456','21');
insert into tb_admin values(4,'admin4','123456','21');

insert into tb_admin values(5,'admin5','123456','22');
insert into tb_admin values(6,'admin6','123456','22');
 
 
 
 
 
 
 
 