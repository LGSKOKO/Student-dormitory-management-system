package com.lgs.controller.admin;

import com.lgs.controller.StageManage;
import com.lgs.dao.AdminDao;
import com.lgs.dao.RepairDao;
import com.lgs.entity.Admin;
import com.lgs.entity.Repair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/25 12:10
 * @description：这个控制类对应的界面是admin包下 repair_ndw.fxml和repair_adw.fxml
 * 因为两个界面大部分代码是一样的 所以这里都放在这个控制器里
 * @modified By：
 * @version: $
 */
public class RepairC {

    @FXML
    private TableView<Repair> rTable;
    @FXML //报修编号 报修概述 宿舍号 确认  详情
    private TableColumn<Repair, String> rNO,rName,roomNO,rConfirm,rDetail;
    @FXML //详细信息显示页面
    private TextArea detail_information;

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    RepairDao repairDao =(RepairDao)context.getBean("repairDao");//获得bean
    int count = 0;

    //查询所有未处理的报修记录
    public void selectNdw(){
        if(count==0)
            showTable(getData(false),false);
        count = 1;
    }
    //查询所有已处理的报修记录
    public void selectAdw(){
        if(count==0)
            showTable(getData(true),true);
        count = 1;
    }

    //获取要添加进入tableView的数据 注意这里进行了传参！！！！
    public ObservableList<Repair> getData(boolean flag){
        AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
        Admin admin= adminDao.selectByAdminNo((String) StageManage.CONTROLLER.get("contextName"));
        List<Repair> lists=repairDao.selectByBuildingNo(admin.getBuildingNo());//调用查询所有的方法
        ObservableList<Repair> repairs = FXCollections.observableArrayList();
        //进行遍历 并加载到repairs中
        if (flag){
            for (Repair list : lists) {
                if(list.getDealWith().equals("是"))
                    repairs.add(list);
            }
        }else{
            for (Repair list : lists) {
                if(list.getDealWith().equals("否"))
                    repairs.add(list);
            }
        }

        return repairs;
    }

    //将数据与视图进行绑定
    public void showTable(ObservableList<Repair> repairs,boolean flag){
        rNO.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairNo"));
        rName.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairName"));
        roomNO.setCellValueFactory(new PropertyValueFactory<Repair, String>("roomNo"));

        //注意这里进行了已处理和未处理的
        // 区分 ！！！
        if(!flag) {
            //lambda表达式
            rConfirm.setCellFactory((col) -> {
                TableCell<Repair, String> cell = new TableCell<Repair, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setText(null);
                        this.setGraphic(null);
                        if (!empty) {
                            ImageView confirmICON = new ImageView(getClass().getClassLoader().getResource("images/Confirm.png").toString());
                            confirmICON.setFitHeight(15);
                            confirmICON.setFitWidth(15);
                            Button confirmBtn = new Button("确认", confirmICON);
                            this.setGraphic(confirmBtn);
                            confirmBtn.setOnMouseClicked((me) -> {
                                Repair clickConfirm = this.getTableView().getItems().get(this.getIndex());
                                System.out.println("确认 " + clickConfirm.getRepairNo() + " 的记录");
                                repairDao.updateStatus("是", clickConfirm.getRepairNo());
                                rTable.getItems().remove(this.getIndex());//移除当前行
                            });
                        }
                    }

                };
                return cell;
            });
        }

        //lambda
        rDetail.setCellFactory((col) -> {
            TableCell<Repair, String> cell = new TableCell<Repair, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        ImageView detailICON = new ImageView(getClass().getClassLoader().getResource("images/Detail.png").toString());
                        detailICON.setFitHeight(15);
                        detailICON.setFitWidth(15);
                        Button detailBtn = new Button("详情",detailICON);
                        this.setGraphic(detailBtn);

                        detailBtn.setOnMouseClicked((me)->{
                            Repair clickDetail = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("查看了"+clickDetail.getRepairNo()  + " 的记录");
                            detail_information.setText(clickDetail.getRepairDetail());
                        });
                    }
                }
            };
            return cell;
        });

        rTable.setItems(repairs);
    }
}
