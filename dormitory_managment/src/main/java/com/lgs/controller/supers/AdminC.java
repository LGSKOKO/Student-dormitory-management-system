package com.lgs.controller.supers;

import com.lgs.controller.StageManage;
import com.lgs.dao.AdminDao;
import com.lgs.dao.BuildingDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/23 11:07
 * @description：super包下的admin.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class AdminC {

    /*
     * super包下的admin.fxml的控件和事件
     * */
    @FXML //tableView控件
    private TableView<Admin> aTable;
    @FXML //编号 宿舍楼号 宿舍楼名 删除  修改
    private TableColumn<Admin,String> aNO,bNO,bName,aDel,aEdt;
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
    Admin admin;
    Map<String,String> bMap = new HashMap<>();//用来进行宿舍楼名和宿舍楼号的映射


    // 初始化 鼠标进入面板
    public void init() {
        showTable(getData());
        if(bMap.size()==0){//如果size等于0代表还没有赋值过
            BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
            for (Building b : buildingDao.selectAll()) {
                bMap.put(b.getBuildingNo(),b.getBuildingName());
        }
        }
    }

    //获取要添加进入tableView的数据
    public ObservableList<Admin> getData(){
        List<Admin> lists=adminDao.selectAll();//调用查询所有的方法
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        //进行遍历 并加载到admins中
        for (Admin list : lists) {
            System.out.println(list);
            admins.add(list);
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
                        String text = bMap.get(clickDel.getBuildingNo());
                        this.setText(text);
                    }
                }

            };
            return cell;
        });
        //lambda表达式
        aDel.setCellFactory((col) -> {
            TableCell<Admin, String> cell = new TableCell<Admin, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        ImageView delICON = new ImageView(getClass().getClassLoader().getResource("images/Delete_Folder.png").toString());
                        delICON.setFitHeight(15);
                        delICON.setFitWidth(15);
                        Button delBtn = new Button("删除",delICON);
                        this.setGraphic(delBtn);
                        delBtn.setOnMouseClicked((me) -> {
                            Admin clickDel = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("删除 "+clickDel.getUsername()  + " 的记录");
                           adminDao.delete(clickDel.getUsername());
                            aTable.getItems().remove(this.getIndex());//移除当前行
                        });
                    }
                }

            };
            return cell;
        });

        //lambda
        aEdt.setCellFactory((col) -> {
            TableCell<Admin, String> cell = new TableCell<Admin, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        ImageView edtICON = new ImageView(getClass().getClassLoader().getResource("images/Edit.png").toString());
                        edtICON.setFitHeight(15);
                        edtICON.setFitWidth(15);
                        Button edtBtn = new Button("修改",edtICON);
                        this.setGraphic(edtBtn);

                        edtBtn.setOnMouseClicked((me)->{
                            Admin clickEdt = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("修改 "+clickEdt.getUsername()  + " 的记录");
                            try {
                                admin = clickEdt;
                                openEdtView();//!!!!重点 打开视图
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            };
            return cell;
        });

        aTable.setItems(admins);
    }

    //弹出building_edit.fxml视图
    public void openEdtView() throws IOException {
        StageManage.CONTROLLER.put("AdminC",this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/admin_edit.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("修改管理员信息");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }
}
