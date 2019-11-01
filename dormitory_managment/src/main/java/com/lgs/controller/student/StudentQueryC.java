package com.lgs.controller.student;

import com.lgs.controller.StageManage;
import com.lgs.dao.*;
import com.lgs.entity.Class;
import com.lgs.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/28 21:42
 * @description：student包下的student_query.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class StudentQueryC {
    @FXML   //宿舍  学院 班级
    private ComboBox studentB_select,studentCo_select,studentCl_select;
    @FXML
    private TableView<Student> sTable;
    @FXML //学生编号 宿舍名 宿舍楼 班级 学院
    private TableColumn<Student, String> sNO,sRoom,sBuilding,sClass,sCollege;

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
    Student student;
    List<String> bList = new ArrayList<>();//用来保存宿舍楼名字
    Map<String,String> bMap = new HashMap<>();//用来进行宿舍楼名和宿舍楼号的映射
    List<String> coList = new ArrayList<>();//用来保存学院名字
    Map<String,String> coMap = new HashMap<>();//用来进行学院名和学院号的映射
    List<String> clList = new ArrayList<>();//用来保存班级名字
    Map<String,String> clMap = new HashMap<>();//用来进行班级名和班级号的映射

    int count =0;

    public void init(){
        if(count==0){
            //===combobox 添加宿舍楼=======
            BuildingDao buildingDao =(BuildingDao)context.getBean("buildingDao");//获得bean
            for (Building b : buildingDao.selectAll()) {
                bList.add(b.getBuildingName());
                bMap.put(b.getBuildingName(),b.getBuildingNo());
            }
            studentB_select.getItems().addAll(bList);//添加列表
            studentB_select.setValue(bList.get(0));
            //===combobox 添加学院=======
            CollegeDao collegeDao =(CollegeDao)context.getBean("collegeDao");//获得bean
            for (College co : collegeDao.selectAll()) {
                coList.add(co.getCollegeName());
                coMap.put(co.getCollegeName(),co.getCollegeNo());
            }
            studentCo_select.getItems().addAll(coList);//添加列表
            studentCo_select.setValue(coList.get(0));
            //===combobox 添加班级=======
            ClassDao classDao =(ClassDao)context.getBean("classDao");//获得bean
            for (Class cl : classDao.selectAll()) {
                clList.add(cl.getClassName());
                clMap.put(cl.getClassName(),cl.getClassNo());
            }
            studentCl_select.getItems().addAll(clList);//添加列表
            studentCl_select.setValue(clList.get(0));
        }//end if
        count=1;
    }

    //根据宿舍查询
    public void selectByBuilding(){
        showTable(getData("宿舍楼"));
    }
    //根据班级查询
    public void selectByClass(){
        showTable(getData("班级"));
    }
    //根据学院查询
    public void selectByCollege(){
        showTable(getData("学院"));
    }
    //查询所有
    public void selectAll(){
        showTable(getData("所有"));
    }

    //获取要添加进入tableView的数据
    public ObservableList<Student> getData(String flag){
        List<Student> lists = new ArrayList<>();
        switch (flag){
            case "宿舍楼": lists=studentDao.selectByBuildingNo(bMap.get(studentB_select.getValue()));
                break;
            case "班级": lists=studentDao.selectByClassNo(clMap.get(studentCl_select.getValue()));
                break;
            case "学院": lists=studentDao.selectByCollegeNo(coMap.get(studentCo_select.getValue()));
                break;
            case "所有": lists=studentDao.selectAll();
                break;
        }
        ObservableList<Student> students = FXCollections.observableArrayList();
        //进行遍历 并加载到students中
        for (Student list : lists) {
            System.out.println(list);
            students.add(list);
        }
        return students;
    }

    //将数据与视图进行绑定
    public void showTable(ObservableList<Student> students){
        sNO.setCellValueFactory(new PropertyValueFactory<Student, String>("username"));
//        rName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));

        //lambda表达式
        sBuilding.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Student student = this.getTableView().getItems().get(this.getIndex());
                        String text = student.getBuilding().getBuildingName();
                        this.setText(text);
                    }
                }
            };
            return cell;
        });
        //lambda表达式
        sRoom.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Student student = this.getTableView().getItems().get(this.getIndex());
                        String text = student.getRoom().getRoomName();
                        this.setText(text);
                    }
                }
            };
            return cell;
        });
        //lambda表达式
        sClass.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Student student = this.getTableView().getItems().get(this.getIndex());
                        String text = student.getClasses().getClassName();
                        this.setText(text);
                    }
                }
            };
            return cell;
        });
        //lambda表达式
        sCollege.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Student student = this.getTableView().getItems().get(this.getIndex());
                        String text = student.getCollege().getCollegeName();
                        this.setText(text);
                    }
                }
            };
            return cell;
        });

        sTable.setItems(students);
    }
}
