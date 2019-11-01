package com.lgs.controller.supers;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.BuildingDao;
import com.lgs.entity.Building;
import javafx.fxml.FXML;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/22 17:45
 * @description：super包下的building_edit.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class BuildingEditC {
    @FXML //修改模块的输入框
    private JFXTextField buildingNO_edit,buildingName_edit;
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
    BuildingC c=(BuildingC)StageManage.CONTROLLER.get("BuildingC");//获得原先加载的控制器

    //初始化方法  这里是鼠标进入面板
    public void init(){
        buildingNO_edit.setText(c.building.getBuildingNo());
        buildingName_edit.setText(c.building.getBuildingName());
    }
    //修改宿舍楼 提交按钮的点击事件
    public void building_edit(){
        Building building= new Building();//创建一个对象
        building.setBuildingNo(buildingNO_edit.getText());//获取输入的编号
        building.setBuildingName(buildingName_edit.getText());//获取输入的名字
        buildingDao.update(building,c.building.getBuildingNo());//执行更新方法
        StageManage.CONTROLLER.remove("BuildingC");//移除之前添加的主键
        System.out.println("修改成功");
    }
}
