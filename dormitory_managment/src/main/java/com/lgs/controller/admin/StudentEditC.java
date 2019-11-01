package com.lgs.controller.admin;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.*;
import com.lgs.entity.*;
import com.lgs.entity.Class;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/24 10:28
 * @description：student包下的student_edit.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class StudentEditC {

    @FXML   //学生编号 学生密码
    private JFXTextField studentNO_edit,studentPassword_edit;
    @FXML   //宿舍  学院 班级
    private ComboBox studentR_edit,studentCo_edit,studentCl_edit;

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
    StudentC c=(StudentC) StageManage.CONTROLLER.get("StudentC");//获得原先加载的控制器
    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
    //通过登录时保存的用户名 查询得到一个Admin实例
    Admin admin =adminDao.selectByAdminNo((String)StageManage.CONTROLLER.get("contextName"));

    List<String> rList = new ArrayList<>();//用来保存宿舍名字
    Map<String,String> rMap = new HashMap<>();//用来进行宿舍名和宿舍号的映射
    List<String> coList = new ArrayList<>();//用来保存学院名字
    Map<String,String> coMap = new HashMap<>();//用来进行学院名和学院号的映射
    List<String> clList = new ArrayList<>();//用来保存班级名字
    Map<String,String> clMap = new HashMap<>();//用来进行班级名和班级号的映射
    int count=0;

    //初始化方法  这里是鼠标进入面板
    public void editStudent_init(){
        if(count==0){
            studentNO_edit.setText(c.student.getUsername());//设置原来的编号
            studentPassword_edit.setText(c.student.getPassword());//设置原来的密码
            //===combobox 添加宿舍=======
            RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
            for (Room r : roomDao.selectByBuildingNo(admin.getBuildingNo())) {
                rList.add(r.getRoomName());
                rMap.put(r.getRoomName(),r.getRoomNo());
            }
            studentR_edit.getItems().addAll(rList);//添加列表
            studentR_edit.setValue(c.student.getRoom().getRoomName());
            //===combobox 添加学院=======
            CollegeDao collegeDao =(CollegeDao)context.getBean("collegeDao");//获得bean
            for (College co : collegeDao.selectAll()) {
                coList.add(co.getCollegeName());
                coMap.put(co.getCollegeName(),co.getCollegeNo());
            }
            studentCo_edit.getItems().addAll(coList);//添加列表
            studentCo_edit.setValue(c.student.getCollege().getCollegeName());
            //===combobox 添加班级=======
            ClassDao classDao =(ClassDao)context.getBean("classDao");//获得bean
            for (Class cl : classDao.selectAll()) {
                clList.add(cl.getClassName());
                clMap.put(cl.getClassName(),cl.getClassNo());
            }
            studentCl_edit.getItems().addAll(clList);//添加列表
            studentCl_edit.setValue(c.student.getClasses().getClassName());
        }//end if
        count=1;
    }

    //重置按钮
    public void student_reset(){
        studentNO_edit.setText("");
        studentPassword_edit.setText("");
    }

    //提交按钮
    public void student_edit(){
        Student student= new Student();//创建一个对象
        student.setUsername(studentNO_edit.getText());//获取输入的编号
        student.setPassword(studentPassword_edit.getText());//获取输入的名字
        student.setSex("男");//这里暂且设置为固定的 后面要修改
        student.setRoomNo(rMap.get(studentR_edit.getValue()));
        student.setBuildingNo(c.student.getBuildingNo());
        student.setClassNo(clMap.get(studentCl_edit.getValue()));
        student.setCollegeNo(coMap.get(studentCo_edit.getValue()));
        studentDao.update(student,c.student.getUsername());//执行更新方法
        StageManage.CONTROLLER.remove("StudentC");//移除之前添加的主键
        System.out.println("修改成功");
    }
}
