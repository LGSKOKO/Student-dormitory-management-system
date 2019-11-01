package com.lgs.controller.admin;

import com.jfoenix.controls.JFXTextField;
import com.lgs.controller.StageManage;
import com.lgs.dao.AdminDao;
import com.lgs.dao.RoomDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Room;
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
 * @date ：Created in 2019/10/24 7:36
 * @description：admin包下的room.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class RoomC {


    @FXML
    private TableView<Room> rTable;
    @FXML //宿舍编号 宿舍名 删除  修改
    private TableColumn<Room, String> rNO,rName,rDel,rEdt;

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
    Room room;

    //查询所有
    public void selectAll(){
        showTable(getData());
    }

    //获取要添加进入tableView的数据
    public ObservableList<Room> getData(){
        AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
       Admin admin= adminDao.selectByAdminNo((String)StageManage.CONTROLLER.get("contextName"));
        List<Room> lists=roomDao.selectByBuildingNo(admin.getBuildingNo());//调用查询所有的方法
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        //进行遍历 并加载到rooms中
        for (Room list : lists) {
            System.out.println(list);
            rooms.add(list);
        }
        return rooms;
    }

    //将数据与视图进行绑定
    public void showTable(ObservableList<Room> rooms){
        rNO.setCellValueFactory(new PropertyValueFactory<Room, String>("roomNo"));
        rName.setCellValueFactory(new PropertyValueFactory<Room, String>("roomName"));

        //lambda表达式
        rDel.setCellFactory((col) -> {
            TableCell<Room, String> cell = new TableCell<Room, String>() {

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
                            Room clickDel = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("删除 "+clickDel.getRoomNo()  + " 的记录");
                            roomDao.delete(clickDel.getRoomNo());
                            rTable.getItems().remove(this.getIndex());//移除当前行
                        });
                    }
                }

            };
            return cell;
        });

        //lambda
        rEdt.setCellFactory((col) -> {
            TableCell<Room, String> cell = new TableCell<Room, String>() {
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
                            Room clickEdt = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("修改 "+clickEdt.getRoomNo()  + " 的记录");
                            try {
                                room = clickEdt;
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

        rTable.setItems(rooms);
    }

    //弹出room_edit.fxml视图
    public void openEdtView() throws IOException {
        StageManage.CONTROLLER.put("RoomC",this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/room_edit.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("修改宿舍信息");//设置标题
        stage.getIcons().add(new Image("images/Lock_Rotation.png"));//设置图标
        stage.setScene(scene);//设置场景
        stage.showAndWait();
    }
}
