package com.lgs.controller.student;

import com.lgs.controller.StageManage;
import com.lgs.dao.StudentDao;
import com.lgs.dao.RepairDao;
import com.lgs.entity.Repair;
import com.lgs.entity.Student;
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
 * @date ：Created in 2019/10/26 16:51
 * @description：student包下的repair.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class RepairC {

        @FXML
        private TableView<Repair> rTable;
        @FXML //报修编号 报修概述 状态 确认  详情
        private TableColumn<Repair, String> rNO,rName,rStatus,rDel,rDetail;
        @FXML //详细信息显示页面
        private TextArea detail_information;

        //加载spring.xml配置文件
        private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        RepairDao repairDao =(RepairDao)context.getBean("repairDao");//获得bean
        int count = 0;

        //查询所有报修记录
        public void selectAll(){
            if(count==0)
            {
                showTable(getData());
                System.out.println("初始化成功");
            }

            count = 1;
        }

        //获取要添加进入tableView的数据 注意这里进行了传参！！！！
        public ObservableList<Repair> getData(){
            StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
            Student student= studentDao.selectByStudentNo((String) StageManage.CONTROLLER.get("contextName"));
            List<Repair> lists=repairDao.selectByRoomNo(student.getRoomNo());//调用查询所有的方法
            ObservableList<Repair> repairs = FXCollections.observableArrayList();
            //进行遍历 并加载到repairs中
            for (Repair list : lists)
                repairs.add(list);
            return repairs;
        }

        //将数据与视图进行绑定
        public void showTable(ObservableList<Repair> repairs){
            rNO.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairNo"));
            rName.setCellValueFactory(new PropertyValueFactory<Repair, String>("repairName"));
            rStatus.setCellValueFactory(new PropertyValueFactory<Repair, String>("dealWith"));

                //lambda表达式
            rDel.setCellFactory((col) -> {
                    TableCell<Repair, String> cell = new TableCell<Repair, String>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            this.setText(null);
                            this.setGraphic(null);
                            if (!empty) {
                                ImageView delICON = new ImageView(getClass().getClassLoader().getResource("images/Delete_Folder.png").toString());
                                delICON.setFitHeight(15);
                                delICON.setFitWidth(15);
                                Button delBtn = new Button("删除", delICON);
                                this.setGraphic(delBtn);
                                delBtn.setOnMouseClicked((me) -> {
                                    Repair clickDel = this.getTableView().getItems().get(this.getIndex());
                                    System.out.println("删除 " + clickDel.getRepairNo() + " 的记录");
                                    repairDao.delete(clickDel.getRepairNo());
                                    rTable.getItems().remove(this.getIndex());//移除当前行
                                });
                            }
                        }

                    };
                    return cell;
            });

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
