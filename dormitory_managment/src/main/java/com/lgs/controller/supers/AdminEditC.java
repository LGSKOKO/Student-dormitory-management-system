package com.lgs.controller.supers;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.AdminDao;
import com.lgs.dao.BuildingDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Building;
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
 * @date ：Created in 2019/10/23 12:34
 * @description：super包下的admin_edit.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class AdminEditC {
    @FXML //下拉框
    private ComboBox adminB_edit;
    @FXML   //用户编号 密码
    private JFXTextField adminNO_edit,adminPassword_edit;

    AdminC c=(AdminC) StageManage.CONTROLLER.get("AdminC");//获得原先加载的控制器
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean

    Map<String,String> bMap = new HashMap<>();//用来进行宿舍楼名和宿舍楼号的映射
    List<String> bList = new ArrayList<>();//用来保存宿舍楼名字

    //初始化 鼠标进入面板
    public void init(){
        if(bMap.size()==0) {//如果size等于0代表还没有赋值过
            BuildingDao buildingDao = (BuildingDao) context.getBean("buildingDao");//获得bean
            for (Building b : buildingDao.selectAll()) {//循环遍历 添加数据
                bList.add(b.getBuildingName());
                bMap.put(b.getBuildingName(),b.getBuildingNo());
            }
            adminB_edit.getItems().addAll(bList);
           adminNO_edit.setText(c.admin.getUsername());//设置原来的编号
            adminPassword_edit.setText(c.admin.getPassword());//设置原来的密码
            adminB_edit.setValue(c.admin.getBuilding().getBuildingName());//设置原来的宿舍楼
        }

    }
    //重置按钮
    public void admin_reset(){
       adminNO_edit.setText("");
        adminPassword_edit.setText("");
    }

    //提交按钮
    public void admin_add(){
        Admin admin= new Admin();//创建一个对象
        admin.setUsername(adminNO_edit.getText());//获取输入的编号
        admin.setPassword(adminPassword_edit.getText());//获取输入的名字
        admin.setBuildingNo(bMap.get(adminB_edit.getValue()));
        adminDao.update(admin,c.admin.getUsername());//执行更新方法
        StageManage.CONTROLLER.remove("AdminC");//移除之前添加的主键
        System.out.println("修改成功");
    }
}
