package com.lgs.controller.supers;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.BuildingDao;
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
import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/19 22:39
 * @description：super包下的building.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class BuildingC {

    /*
     * super包下的building.fxml的控件和事件
     * */
    @FXML //添加模块的输入框
    private JFXTextField buildingNO_add,buildingName_add;
    @FXML
    private TableView<Building> bTable;
    @FXML //宿舍楼编号 宿舍楼名 删除  修改
    private TableColumn<Building, String> bNO,bName,bDel,bEdt;
    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
    Building building;

    //添加宿舍楼 提交按钮的点击事件
    public void building_add(){
        building= new Building();
        building.setBuildingNo(buildingNO_add.getText());//获取输入的编号
        building.setBuildingName(buildingName_add.getText());//获取输入的名字
        buildingDao.insert(building);
        System.out.println("添加成功");
    }


    // 初始化  鼠标进入面板
    public void init() {
        showTable(getData());
    }

    //获取要添加进入tableView的数据
    public ObservableList<Building> getData(){
        List<Building> lists=buildingDao.selectAll();//调用查询所有的方法
        ObservableList<Building> buildings = FXCollections.observableArrayList();
        //进行遍历 并加载到buildings中
        for (Building list : lists) {
            System.out.println(list);
            buildings.add(list);
        }
        return buildings;
    }

    //将数据与视图进行绑定
    public void showTable(ObservableList<Building> buildings){
        bNO.setCellValueFactory(new PropertyValueFactory<Building, String>("buildingNo"));
        bName.setCellValueFactory(new PropertyValueFactory<Building, String>("buildingName"));

        //lambda表达式
        bDel.setCellFactory((col) -> {
            TableCell<Building, String> cell = new TableCell<Building, String>() {

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
                           Building clickDel = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("删除 "+clickDel.getBuildingNo()  + " 的记录");
                            buildingDao.delete(clickDel.getBuildingNo());
                            bTable.getItems().remove(this.getIndex());//移除当前行
                        });
                    }
                }

            };
            return cell;
        });

        //lambda
        bEdt.setCellFactory((col) -> {
            TableCell<Building, String> cell = new TableCell<Building, String>() {
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
                            Building clickEdt = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("修改 "+clickEdt.getBuildingNo()  + " 的记录");
                            try {
                                building = clickEdt;
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

        bTable.setItems(buildings);
    }

    //弹出building_edit.fxml视图
    public void openEdtView() throws IOException {
        StageManage.CONTROLLER.put("BuildingC",this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/super/building_edit.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("修改宿舍楼信息");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }


}
