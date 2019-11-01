package com.lgs.controller.admin;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.RoomDao;
import com.lgs.entity.Room;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/24 8:09
 * @description：admin包下的room_edit.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class RoomEditC {


    @FXML //修改模块的输入框
    private JFXTextField roomNO_edit,roomName_edit;
    @FXML //修改模块下拉框
    private ComboBox roomB_edit;
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
    RoomC c=(RoomC) StageManage.CONTROLLER.get("RoomC");//获得原先加载的控制器
    int count=0;

    //初始化方法  这里是鼠标进入面板
    public void editRoom_init(){
        if(count==0){
            roomNO_edit.setText(c.room.getRoomNo());
            roomName_edit.setText(c.room.getRoomName());
            roomB_edit.setValue(c.room.getBuilding().getBuildingName());
        }
        count=1;
    }
    
    //重置按钮
    public void room_reset(){
        roomName_edit.setText("");
        roomNO_edit.setText("");
    }

    //提交按钮
    public void room_edit(){
        Room room= new Room();//创建一个对象
        room.setRoomNo(roomNO_edit.getText());//获取输入的编号
        room.setRoomName(roomName_edit.getText());//获取输入的名字
        room.setBuildingNo(c.room.getBuildingNo());
        roomDao.update(room,c.room.getRoomNo());//执行更新方法
        StageManage.CONTROLLER.remove("RoomC");//移除之前添加的主键
        System.out.println("修改成功");
    }
}
