 #创建数据库
 create database dormitory_management;
 #使用数据库
 use dormitory_management;
 
 #创建超级管理员表
 drop table if exists tb_super;
 create table tb_super(
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 PRIMARY key(username)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"超级管理员表";
 
    #创建院系表
 drop table if exists tb_college;
 create table tb_college(
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 collegeName VARCHAR(10) COMMENT"院系名称",
 PRIMARY key(collegeNO)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"院系表";
 
  #创建班级表
 drop table if exists tb_class;
 create table tb_class(
 classNO VARCHAR(5) not null COMMENT"班级编号",
 className VARCHAR(10) COMMENT"班级名称",
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 PRIMARY key(classNO),
   CONSTRAINT `FK_class_college` FOREIGN KEY (`collegeNO`) REFERENCES `tb_college` (`collegeNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"班级表";
 
  #创建宿舍楼表
 drop table if exists tb_building;
 create table tb_building(
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼楼号",
 buildingName VARCHAR(10) COMMENT"宿舍楼名字",
 PRIMARY key(buildingNO)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"宿舍楼表";
 
 #创建宿舍表
 drop table if exists tb_room;
 create table tb_room(
 roomNO VARCHAR(5) not null COMMENT"宿舍号",
 roomName VARCHAR(10) COMMENT"宿舍名",
 count   int(4)  DEFAULT 100 COMMENT"电费余额",
 buildingNO VARCHAR(5) COMMENT"宿舍楼楼号",
 PRIMARY key(roomNO),
  CONSTRAINT `FK_room_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"宿舍表";
 
 #创建管理员表
 drop table if exists tb_admin;
 create table tb_admin(
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼号",
 PRIMARY key(username),
 CONSTRAINT `FK_admin_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"管理员表";
 
 #创建学生表
 drop table if exists tb_student;
 create table tb_student(
 username VARCHAR(20) not null COMMENT"编号",
 password VARCHAR(20) not null DEFAULT"123456" COMMENT"密码",
 sex varchar(2) not null COMMENT"性别",
 buildingNO VARCHAR(5) not null COMMENT"宿舍楼楼号",
 roomNO VARCHAR(5) not null COMMENT"宿舍号",
 classNO VARCHAR(5) not null COMMENT"班级编号",
 collegeNO VARCHAR(5) not null COMMENT"院系编号",
 money int(5) comment"账号余额",
 PRIMARY key(username),
 CONSTRAINT `FK_student_building` FOREIGN KEY (`buildingNO`) REFERENCES `tb_building` (`buildingNO`),
 CONSTRAINT `FK_student_room` FOREIGN KEY (`roomNO`) REFERENCES `tb_room` (`roomNO`),
 CONSTRAINT `FK_student_class` FOREIGN KEY (`classNO`) REFERENCES `tb_class` (`classNO`),
 CONSTRAINT `FK_student_college` FOREIGN KEY (`collegeNO`) REFERENCES `tb_college` (`collegeNO`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT"学生表";

#创建报修表
drop table if EXISTS tb_repair;
create table tb_repair(
repairNO varchar(10) not null COMMENT"报修编号",
repairName varchar(20) not null COMMENT"报修概要",
repairDetail varchar(150) COMMENT"报修详细信息",
dealWith varchar(2) not null COMMENT"用来判断该报修是否已经被处理",
roomNO VARCHAR(5) not null COMMENT"宿舍号",
buildingNO VARCHAR(5) not null COMMENT"宿舍楼楼号",
primary key(repairNO),
constraint `FK_repair_room` foreign key (`roomNO`) references `tb_room` (`roomNO`),
constraint `FK_repair_building` foreign key (`buildingNO`) references `tb_building` (`buildingNO`)
)engine=INNODB default charset=utf8 comment"报修表";

 
 
 
 
 
 
 

 

 
 #填充数据
 INSERT INTO `tb_super` VALUES ('super','123456');
 
 INSERT INTO `tb_building` VALUES ('20', '宿舍20栋');
 INSERT INTO `tb_building` VALUES ('21', '宿舍21栋');
 INSERT INTO `tb_building` VALUES ('22', '宿舍22栋');

 
 INSERT INTO `tb_room` VALUES ( '20102', '20栋102',100,'20');
 INSERT INTO `tb_room` VALUES ( '20103', '20栋103',100,'20');
  INSERT INTO `tb_room` VALUES ( '20104', '20栋104',100,'20');
  INSERT INTO `tb_room` VALUES ( '20202', '20栋202',100,'20');
 INSERT INTO `tb_room` VALUES ( '20203', '20栋203',100,'20');
  INSERT INTO `tb_room` VALUES ( '20204', '20栋204',100,'20');

 
  INSERT INTO `tb_room` VALUES ( '21102', '21栋102',100,'21');
 INSERT INTO `tb_room` VALUES ( '21103', '21栋103',100,'21');
  INSERT INTO `tb_room` VALUES ( '21104', '21栋104',100,'21');
  INSERT INTO `tb_room` VALUES ( '21202', '21栋202',100,'21');
 INSERT INTO `tb_room` VALUES ( '21203', '21栋203',100,'21');
  INSERT INTO `tb_room` VALUES ('21204', '21栋204',100,'21');

 
  INSERT INTO `tb_room` VALUES ( '22102', '22栋102',100,'22');
 INSERT INTO `tb_room` VALUES ( '22103', '22栋103',100,'22');
  INSERT INTO `tb_room` VALUES ( '22104', '22栋104',100,'22');
  INSERT INTO `tb_room` VALUES ('22202', '22栋202',100,'22');
 INSERT INTO `tb_room` VALUES ( '22203', '22栋203',100,'22');
  INSERT INTO `tb_room` VALUES ( '22204', '22栋204',100,'22');

 
INSERT INTO `tb_college` VALUES ('01', '计算机与信息工程院');
INSERT INTO `tb_college` VALUES ('02', '软件学院');
INSERT INTO `tb_college` VALUES ('03', '艺术学院');

INSERT INTO `tb_class` VALUES ( '01171', '计17级1班','01');
INSERT INTO `tb_class` VALUES ('01172', '计17级2班','01');
INSERT INTO `tb_class` VALUES ( '02171', '软17级1班','02');
INSERT INTO `tb_class` VALUES ( '02172', '软17级2班','02');
INSERT INTO `tb_class` VALUES ( '03171', '艺17级1班','03');
INSERT INTO `tb_class` VALUES ( '03172', '艺17级2班','03');


INSERT INTO `tb_student` VALUES ('0117101', '123456','男','20', '20102','01171','01',200);
INSERT INTO `tb_student` VALUES ( '0117201', '123456','男','20', '20102','01172','01',200);
INSERT INTO `tb_student` VALUES ( '0217101', '123456','男','20', '20103','02171','02',200);
INSERT INTO `tb_student` VALUES ( '0217201', '123456','男','20', '20104','02172','02',200);
INSERT INTO `tb_student` VALUES ( '0317101', '123456','男','20', '20104','03171','03',200);

INSERT INTO `tb_student` VALUES ('0117102', '123456','男','20', '20102','01171','01',200);
INSERT INTO `tb_student` VALUES ( '0117202', '123456','男','20', '20102','01172','01',200);
INSERT INTO `tb_student` VALUES ( '0217102', '123456','男','20', '20103','02171','02',200);
INSERT INTO `tb_student` VALUES ( '0217202', '123456','男','20', '20104','02172','02',200);
INSERT INTO `tb_student` VALUES ( '0317102', '123456','男','20', '20104','03171','03',200);

INSERT INTO `tb_student` VALUES ('0117103', '123456','男','21', '21102','01171','01',200);
INSERT INTO `tb_student` VALUES ( '0117203', '123456','男','21', '21102','01172','01',200);
INSERT INTO `tb_student` VALUES ( '0217103', '123456','男','21', '21103','02171','02',200);
INSERT INTO `tb_student` VALUES ('0217203', '123456','男','21', '21104','02172','02',200);
INSERT INTO `tb_student` VALUES ( '0317103', '123456','男','21', '21104','03171','03',200);

INSERT INTO `tb_student` VALUES ('0117104', '123456','男','21', '21102','01171','01',200);
INSERT INTO `tb_student` VALUES ('0117204', '123456','男','21', '21102','01172','01',200);
INSERT INTO `tb_student` VALUES ( '0217104', '123456','男','21', '21103','02171','02',200);
INSERT INTO `tb_student` VALUES ('0217204', '123456','男','21', '21104','02172','02',200);
INSERT INTO `tb_student` VALUES ( '0317104', '123456','男','21', '21104','03171','03',200);

INSERT INTO `tb_student` VALUES ('0117105', '123456','男','22', '22102','01171','01',200);
INSERT INTO `tb_student` VALUES ( '0117205', '123456','男','22', '22102','01172','01',200);
INSERT INTO `tb_student` VALUES ( '0217105', '123456','男','22', '22103','02171','02',200);
INSERT INTO `tb_student` VALUES ( '0217205', '123456','男','22', '22104','02172','02',200);
INSERT INTO `tb_student` VALUES ('0317105', '123456','男','21', '22104','03171','03',200);

INSERT INTO `tb_student` VALUES ( '0117106', '123456','男','22', '22102','01171','01',200);
INSERT INTO `tb_student` VALUES ( '0117206', '123456','男','22', '22102','01172','01',200);
INSERT INTO `tb_student` VALUES ('0217106', '123456','男','22', '22103','02171','02',200);
INSERT INTO `tb_student` VALUES ( '0217206', '123456','男','22', '22104','02172','02',200);
INSERT INTO `tb_student` VALUES ( '0317106', '123456','男','22', '22104','03171','03',200);



insert into tb_admin values('admin','123456','20');
insert into tb_admin values('admin2','123456','20');

insert into tb_admin values('admin3','123456','21');
insert into tb_admin values('admin4','123456','21');

insert into tb_admin values('admin5','123456','22');
insert into tb_admin values('admin6','123456','22');

insert into tb_repair values ('2010201','厕所漏水','宿舍厕所防水已坏了','否','20102','20');
insert into tb_repair values ('2010301','厕所漏水','宿舍厕所防水已坏了','否','20103','20');
insert into tb_repair values ('2010401','厕所漏水','宿舍厕所防水已坏了','否','20104','20');

insert into tb_repair values ('2110201','厕所漏水','宿舍厕所防水已坏了','否','21102','21');
insert into tb_repair values ('2110301','厕所漏水','宿舍厕所防水已坏了','否','21103','21');
insert into tb_repair values ('2110401','厕所漏水','宿舍厕所防水已坏了','否','21104','21');

insert into tb_repair values ('2210201','厕所漏水','宿舍厕所防水已坏了','否','22102','22');
insert into tb_repair values ('2210301','厕所漏水','宿舍厕所防水已坏了','否','22103','22');
insert into tb_repair values ('2210401','厕所漏水','宿舍厕所防水已坏了','否','22104','22');
