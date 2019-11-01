package com.lgs.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lgs.dao.AdminDao;
import com.lgs.dao.BuildingDao;
import com.lgs.dao.SuperDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Building;
import com.lgs.entity.Super;
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
 * @date ：Created in 2019/10/17 16:01
 * @description：super界面的控制器
 * @modified By：
 * @version: $
 */
public class SuperC {

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    public Pane rightPane;//右侧空白面板
    public JFXButton building;//宿舍楼管理按钮
    public AnchorPane root;


    //修改密码 点击事件
    public void update_password() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/update_password.fxml"));
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

    //分配工作
    public void add() throws IOException {
        System.out.println("分配工作");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/admin.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }



    //手动添加人员
    public void addAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/admin_add.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }


    //宿舍楼管理
    public void building() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/building.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);

    }


    //=======================修改密码子模块=======================
    @FXML //修改密码页面的 提交 重置按钮
   private JFXButton cpw_edit,cpw_reset;
    @FXML //修改密码页面的 原密码 新密码输入框
    private PasswordField cpw_oldP,cpw_newP;
    //点击重置按钮 事件
    public void cpw_reset(){//将两个输入框置空
        cpw_oldP.setText("");
        cpw_newP.setText("");
    }
    //点击提交按钮 事件
    public void cpw_edit(){
        SuperDao superDao =(SuperDao)context.getBean("superDao");//获得bean
        //通过登录时保存的用户名 查询得到一个Super实例
        Super super1 =superDao.selectBySuperNo((String)StageManage.CONTROLLER.get("contextName"));
       boolean flag = super1.getPassword().equals(cpw_oldP.getText());//判断输入的原密码是否匹配
        if(flag){
            Super super2 = new Super();
            super2.setUsername((String)StageManage.CONTROLLER.get("contextName"));
            super2.setPassword(cpw_newP.getText());
            superDao.updatePassword(super2);
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码失败");
        }
    }

    //=======================添加管理员子模块=======================
    public JFXTextField adminNO_add,adminPassword_add;
    public ComboBox adminB_add;//下拉框 宿舍楼
    List<String> bList = new ArrayList<>();//用来保存宿舍楼名字
    Map<String,String> bMap = new HashMap<>();//用来进行宿舍楼名和宿舍楼号的映射

    //初始化 鼠标进入面板
    public void addAdmin_init(){
//        System.out.println(bList.size());
        if(bList.size()==0){//如果size等于0代表还没有赋值过
            BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
            for (Building b : buildingDao.selectAll()) {
                bList.add(b.getBuildingName());
                bMap.put(b.getBuildingName(),b.getBuildingNo());
            }
            adminB_add.getItems().addAll(bList);
            adminB_add.setValue(bList.get(0));//默认选中第一个
        }
    }

    //提交按钮
    public void admin_add(){
        Admin admin = new Admin();
        admin.setUsername(adminNO_add.getText());
        admin.setPassword(adminPassword_add.getText());
        String buildingNo = bMap.get(adminB_add.getValue()); //获得选择的值
        admin.setBuildingNo(buildingNo);
        AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
        adminDao.insert(admin);
        System.out.println("添加成功");

    }


}
