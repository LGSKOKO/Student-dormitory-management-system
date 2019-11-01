package com.lgs.controller;

import com.jfoenix.controls.JFXButton;
import com.lgs.dao.RepairDao;
import com.lgs.dao.RoomDao;
import com.lgs.dao.StudentDao;
import com.lgs.entity.Repair;
import com.lgs.entity.Room;
import com.lgs.entity.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.*;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/17 16:32
 * @description：student界面控制器
 * @modified By：
 * @version: $
 */
public class StudentC {
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    public AnchorPane root;

    @FXML
    private Pane rightPane;

    //修改密码
    public void update_password() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/update_password.fxml"));
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

    //电费查询
    public void fee_query() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/fee_query.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }

    //电费充值
    public void fee_recharge() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/fee_recharge.fxml"));
        Parent root2 = fxmlLoader.load();
        rightPane.getChildren().add(root2);
    }

    //查询管理员
    public void admin_query() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/admin_query.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/student_query.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("查询学生");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }

    //添加报修
    public void repair_add() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/repair_add.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("宿舍管理");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }

    //报修查询
    public void repair_query() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/student/repair.fxml"));
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
        StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
        //通过登录时保存的用户名 查询得到一个Student实例
        Student student1 =studentDao.selectByStudentNo((String)StageManage.CONTROLLER.get("contextName"));
        boolean flag = student1.getPassword().equals(cpw_oldP.getText());//判断输入的原密码是否匹配
        if(flag){
            Student student2 = new Student();
            student2.setUsername((String)StageManage.CONTROLLER.get("contextName"));
            student2.setPassword(cpw_newP.getText());
            studentDao.updatePassword(student2);
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码失败");
        }
    }

    //=======================电费子模块=======================
    @FXML
    private ComboBox fCbb;//下拉框
    @FXML//余额显示框  //充值输入框
    private TextField fOver,fAdd;
    RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
    StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
    List<String> rList = new ArrayList<>();//用来保存宿舍名字
    Map<String,String> rMap = new HashMap<>();//用来进行宿舍名和宿舍号的映射
    int feeCount=0;
    //初始化事件 鼠标进入面板
    public void feeInit(){
        if(feeCount==0){
            for (Room r : roomDao.selectAll()) {
                rList.add(r.getRoomName());
                rMap.put(r.getRoomName(),r.getRoomNo());
            }
            fCbb.getItems().addAll(rList);
            fCbb.setValue(rList.get(0));//默认选中第一个
        }
        feeCount=1;
    }
    //查询余额
    public void feeQuery(){
        Room room = roomDao.selectByRoomNo(rMap.get(fCbb.getValue()));
        fOver.setText(Long.toString(room.getCount()));
    }
    //充值
    public void feeAdd(){
        //通过登录时保存的用户名 查询得到一个Student实例
        Student student =studentDao.selectByStudentNo((String)StageManage.CONTROLLER.get("contextName"));
        if (fAdd.getText().contains(" "))
            System.out.println("非法输入");
        else{
            long input= Long.parseLong(fAdd.getText());
            long count = student.getMoney()-input;
            if(count>=0){
                Student student2 = new Student();
                student2.setMoney(count);
                student2.setUsername(student.getUsername());
                studentDao.updateMoney(student2);
                Room room = roomDao.selectByRoomNo(rMap.get(fCbb.getValue()));
                 room.setCount(input+room.getCount());//更新宿舍电费余额
                 roomDao.updateMoney(room,room.getRoomNo());
                System.out.println("充值成功");
            }else{
                System.out.println("非法输入");
            }
        }

    }//end method

    //======================添加报修=======================
    @FXML
    private TextField rta;//repairTitleAdd
    @FXML
    private TextArea rda;//repairDetailAdd
    RepairDao repairDao =(RepairDao)context.getBean("repairDao");//获得bean
    Random random = new Random();
    public void repairAdd(){
      Student student =  studentDao.selectByStudentNo((String)StageManage.CONTROLLER.get("contextName"));
       String r = String.valueOf(random.nextInt(100));//生成0到99的随机数
       String repairNo = student.getRoomNo()+r;//用宿舍号加随机数生成 报修编号
        Repair repair = new Repair();
        repair.setRepairNo(repairNo);
        repair.setRepairName(rta.getText());
        repair.setRepairDetail(rda.getText());
        repair.setDealWith("否");
        repair.setBuildingNo(student.getBuildingNo());
        repair.setRoomNo(student.getRoomNo());
        repairDao.insert(repair);
        System.out.println("添加报修成功");
    }

    //重置按钮
    public void repairRest(){
        rta.setText("");
        rda.setText("");
    }





}
