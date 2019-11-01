package com.lgs.controller.student;

import com.lgs.dao.AdminDao;
import com.lgs.dao.BuildingDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/27 22:03
 * @description：student包下的admin_query.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class AdminQueryC {
    public ComboBox adminB;
    @FXML //tableView控件
    private TableView<Admin> aTable;
    @FXML //编号 宿舍楼号 宿舍楼名
    private TableColumn<Admin,String> aNO,bNO,bName;
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
    List<String> bList = new ArrayList<>();//用来保存宿舍楼名字
    Map<String,String> bMap = new HashMap<>();//用来进行宿舍楼名和宿舍楼号的映射

    public void init(){
        if(bList.size()==0){//如果size等于0代表还没有赋值过
            BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
            for (Building b : buildingDao.selectAll()) {
                bList.add(b.getBuildingName());
                bMap.put(b.getBuildingName(),b.getBuildingNo());
            }
            adminB.getItems().addAll(bList);
            adminB.setValue(bList.get(0));//默认选中第一个
        }
    }

    //  查询所有
    public void selectAll() {
        showTable(getData(true));
    }

    public void selectSome(){
        showTable(getData(false));
    }

    //获取要添加进入tableView的数据
    public ObservableList<Admin> getData(boolean flag){
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        if(flag){
            List<Admin> lists=adminDao.selectAll();//调用查询所有的方法
            //进行遍历 并加载到admins中
            for (Admin list : lists) {
                System.out.println(list);
                admins.add(list);
            }
        }else {
            List<Admin> lists=adminDao.selectByBuildingNo(bMap.get(adminB.getValue()));//调用查询所有的方法
            //进行遍历 并加载到admins中
            for (Admin list : lists) {
                System.out.println(list);
                admins.add(list);
            }
        }

        System.out.println("成功");
        return admins;
    }

    //将数据与视图进行绑定
    public void showTable(ObservableList<Admin> admins){
        aNO.setCellValueFactory(new PropertyValueFactory<Admin,String>("username"));
        bNO.setCellValueFactory(new PropertyValueFactory<Admin,String>("buildingNo"));

        //lambda表达式
        bName.setCellFactory((col) -> {
            TableCell<Admin, String> cell = new TableCell<Admin, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Admin clickDel = this.getTableView().getItems().get(this.getIndex());
                        String text = clickDel.getBuilding().getBuildingName();
                        this.setText(text);
                    }
                }

            };
            return cell;
        });
        aTable.setItems(admins);
    }
}
