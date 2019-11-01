package com.lgs.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.lgs.dao.AdminDao;
import com.lgs.dao.StudentDao;
import com.lgs.dao.SuperDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Student;
import com.lgs.entity.Super;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author ：李先生
 * @date ：Created in 2019/10/13 8:59
 * @description：login.fxml文件的控制器
 * @modified By：
 * @version: $
 */
public class LoginC {

    public JFXRadioButton student;//单选按钮 学生
    public JFXRadioButton admin;//单选按钮 管理员
    public Text tip;//提示信息
    public SplitPane root;
    public TextField username;//输入框 用户名
    public TextField password;//输入框 密码
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    //点击登录按钮的事件
    public void login() throws IOException {
        String getPassword="";//用来保存获取到的密码
        if(student.isSelected())//判断哪个单选按钮选中
        {
            System.out.println("选中学生");
            StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
            Student students = (Student)studentDao.selectByStudentNo(username.getText());//获取查询到的密码
            if (students!=null){
               getPassword = students.getPassword();
            }
            if(password.getText().equals(getPassword))//判断输入的密码和查询到的密码是否一致
            {
                StageManage.CONTROLLER.put("contextName",username.getText());//保存全局用户名
                //获取Fxml加载器
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student.fxml"));
                Parent root2 = fxmlLoader.load();//加载页面
                Stage stage =(Stage) root.getScene().getWindow();//获取当前窗口
                stage.setScene(new Scene(root2));///添加场景
//                stage.show();
            }
            tip.setText("账号或密码错误");
        }else{
            /*这里因为界面单选框只设置了 管理员和学生两个单选框的原因
            //在这里区分 超级管理员和一般的管理员
            //我这里只是进行的简易的区分 可自行更改*/
            if(username.getText().equals("super"))//超级管理员
            {
                SuperDao superDao = (SuperDao)context.getBean("superDao");//获得bean
                Super supers= superDao.selectBySuperNo(username.getText());//获取查询到的密码
                if (supers!=null){
                    getPassword = supers.getPassword();
                }
                if(password.getText().equals(getPassword))//判断输入的密码和查询到的密码是否一致
                {
                    StageManage.CONTROLLER.put("contextName",username.getText());//保存全局用户名
                    //获取Fxml加载器
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super.fxml"));
                    Parent root2 = fxmlLoader.load();//加载页面
                    Stage stage =(Stage) root.getScene().getWindow();//获取当前窗口
                    stage.setScene(new Scene(root2));///添加场景
//                    stage.show();
                }
                tip.setText("账号或密码错误");
            }else {
               AdminDao adminDao = (AdminDao) context.getBean("adminDao");//获得bean
                Admin admins= adminDao.selectByAdminNo(username.getText());//获取查询到的密码
                if (admins!=null){
                    getPassword = admins.getPassword();
                }
                if(password.getText().equals(getPassword))//判断输入的密码和查询到的密码是否一致
                {
                    StageManage.CONTROLLER.put("contextName",username.getText());//保存全局用户名
                    //获取Fxml加载器
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin.fxml"));
                    Parent root2 = fxmlLoader.load();//加载页面
                    Stage stage =(Stage) root.getScene().getWindow();//获取当前窗口
                    stage.setScene(new Scene(root2));///添加场景
//                    stage.show();
                }
                tip.setText("账号或密码错误");
            }//end is super or is admin

        }//end which radio is selected
    }//end method login


}//end Class LoginC
