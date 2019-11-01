package com.lgs.controller;

import com.jfoenix.controls.JFXTextField;
import com.lgs.dao.*;
import com.lgs.entity.*;
import com.lgs.entity.Class;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/17 16:16
 * @description：admin界面的控制器
 * @modified By：
 * @version: $
 */
public class AdminC {
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    public AnchorPane root;
    @FXML //右侧面板
    private Pane rightPane;


    //修改密码
    public void update_password() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/update_password.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("修改密码");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }

    //安全退出
    public void exist() throws IOException {
        StageManage.CONTROLLER.remove("contextName");//清除全局用户名
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/login.fxml"));
        Parent root2 = fxmlLoader.load();//加载页面
        Stage stage =(Stage) root.getScene().getWindow();//获取当前窗口
        stage.setScene(new Scene(root2));///添加场景

    }


    //添加学生
    public void addStudent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/student_add.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }

    //学生管理
    public void student() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/student.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("学生管理");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.show();
    }
    //查询管理员
    public void admin_query() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/admin_query.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("查询管理员");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }

    //查询学生
    public void student_query() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/student_query.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("查询学生");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }

    //添加宿舍
    public void addRoom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/room_add.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }

    //宿舍管理
    public void room() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/room.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("宿舍管理");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.show();
    }

    //======报修管理--- 未处理(not deal with)=====
     public void ndw() throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/repair_ndw.fxml"));
         Parent root2 = fxmlLoader.load();
         Scene scene = new Scene(root2);
         Stage stage = new Stage();
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.setTitle("宿舍管理");//设置标题
         stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
         stage.setScene(scene);//设置场景
         stage.showAndWait();
     }
    //======报修管理--- 已处理(already deal with)=====
    public void adw() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/repair_adw.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("宿舍管理");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }



    //=======================修改密码子模块=======================
    @FXML //修改密码页面的 原密码 新密码输入框
    private PasswordField cpw_oldP,cpw_newP;
    //点击重置按钮 事件
    public void cpw_reset(){//将两个输入框置空
        cpw_oldP.setText("");
        cpw_newP.setText("");
    }

    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
    //通过登录时保存的用户名 查询得到一个Admin实例
    Admin admin1 =adminDao.selectByAdminNo((String)StageManage.CONTROLLER.get("contextName"));

    public void cpw_edit(){//点击提交按钮 事件
        boolean flag = admin1.getPassword().equals(cpw_oldP.getText());//判断输入的原密码是否匹配
        if(flag){
            Admin admin2 = new Admin();
            admin2.setUsername((String)StageManage.CONTROLLER.get("contextName"));
            admin2.setPassword(cpw_newP.getText());
            adminDao.updatePassword(admin2);
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码失败");
        }
    }

    //=======================添加学生子模块=======================
    @FXML   //学生编号 学生密码
    private JFXTextField studentNO_add,studentPassword_add;
    @FXML   //宿舍  学院 班级
    private ComboBox studentR_add,studentCo_add,studentCl_add;
    List<String> rList = new ArrayList<>();//用来保存宿舍名字
    Map<String,String> rMap = new HashMap<>();//用来进行宿舍名和宿舍号的映射
    List<String> coList = new ArrayList<>();//用来保存学院名字
    Map<String,String> coMap = new HashMap<>();//用来进行学院名和学院号的映射
    List<String> clList = new ArrayList<>();//用来保存班级名字
    Map<String,String> clMap = new HashMap<>();//用来进行班级名和班级号的映射

    int addStudent = 0;

    //初始化 鼠标进入面板
    public void addStudent_init(){
        if(addStudent==0){//如果count等于0代表还没有赋值过
            //===combobox 添加宿舍=======
            RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
            for (Room r : roomDao.selectByBuildingNo(admin1.getBuildingNo())) {
                rList.add(r.getRoomName());
                rMap.put(r.getRoomName(),r.getRoomNo());
            }
            studentR_add.getItems().addAll(rList);
            studentR_add.setValue(rList.get(0));//默认选中第一个
            //===combobox 添加学院=======
            CollegeDao collegeDao =(CollegeDao)context.getBean("collegeDao");//获得bean
            for (College co : collegeDao.selectAll()) {
                coList.add(co.getCollegeName());
                coMap.put(co.getCollegeName(),co.getCollegeNo());
            }
            studentCo_add.getItems().addAll(coList);
            studentCo_add.setValue(coList.get(0));//默认选中第一个
            //===combobox 添加班级=======
            ClassDao classDao =(ClassDao)context.getBean("classDao");//获得bean
            for (Class cl : classDao.selectAll()) {
                clList.add(cl.getClassName());
                clMap.put(cl.getClassName(),cl.getClassNo());
            }
            studentCl_add.getItems().addAll(clList);
            studentCl_add.setValue(clList.get(0));//默认选中第一个
        }//end if

        addStudent=1;
    }

    //提交按钮
    public void student_add(){
        Student student = new Student();
        student.setUsername(studentNO_add.getText());//获取用户名
        student.setPassword(studentPassword_add.getText());//获取密码
        String buildingNo = admin1.getBuildingNo(); //获得选择的值
        student.setBuildingNo(buildingNo);//获取宿舍楼号
        student.setRoomNo(rMap.get(studentR_add.getValue()));//获取宿舍号
        student.setClassNo(clMap.get(studentCl_add.getValue()));//获取班级号
        student.setCollegeNo(coMap.get(studentCo_add.getValue()));//获取学院号
        student.setSex("男");//默认设置为男生
        student.setMoney(200);//默认金额为200
        StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
        studentDao.insert(student);
        System.out.println("添加成功");
    }



    //=======================添加宿舍子模块=======================
    @FXML   //宿舍编号 宿舍名
    private JFXTextField roomNO_add,roomName_add;
    @FXML   //宿舍楼
    private ComboBox roomB_add;
//    List<String> bList = new ArrayList<>();//用来保存宿舍楼名字
    int addRoom = 0;

    //初始化 鼠标进入面板
    public void addRoom_init(){
        if(addRoom==0){//如果count等于0代表还没有赋值过
            //设置当前管理员所管理的宿舍
            roomB_add.setValue(admin1.getBuilding().getBuildingName());
        }
        addRoom=1;
    }
    //提交按钮
    public void room_add(){
        Room room = new Room();
        room.setRoomNo(roomNO_add.getText());
        room.setRoomName(roomName_add.getText());
        String buildingNo = admin1.getBuildingNo(); //获得选择的值
        room.setBuildingNo(buildingNo);
        RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
        roomDao.insert(room);
        System.out.println("添加成功");

    }



}
