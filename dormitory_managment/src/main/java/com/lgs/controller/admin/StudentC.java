package com.lgs.controller.admin;

import com.lgs.controller.StageManage;
import com.lgs.dao.*;
import com.lgs.entity.*;
import com.lgs.entity.Class;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：李先生
 * @date ：Created in 2019/10/24 10:27
 * @description：admin包下的student.fxml的控件和事件
 * @modified By：
 * @version: $
 */
public class StudentC {

    @FXML   //宿舍  学院 班级
    private ComboBox studentR_select,studentCo_select,studentCl_select;
    @FXML
    private TableView<Student> sTable;
    @FXML //学生编号 宿舍名 宿舍楼 班级 学院 删除  修改
    private TableColumn<Student, String> sNO,sRoom,sBuilding,sClass,sCollege,sDel,sEdt;

    //加载spring.xml配置文件
    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    StudentDao studentDao =(StudentDao)context.getBean("studentDao");//获得bean
    Student student;
    List<String> rList = new ArrayList<>();//用来保存宿舍名字
    Map<String,String> rMap = new HashMap<>();//用来进行宿舍名和宿舍号的映射
    List<String> coList = new ArrayList<>();//用来保存学院名字
    Map<String,String> coMap = new HashMap<>();//用来进行学院名和学院号的映射
    List<String> clList = new ArrayList<>();//用来保存班级名字
    Map<String,String> clMap = new HashMap<>();//用来进行班级名和班级号的映射

    AdminDao adminDao =(AdminDao)context.getBean("adminDao");//获得bean
    Admin admin =adminDao.selectByAdminNo((String)StageManage.CONTROLLER.get("contextName"));
    //通过登录时保存的用户名 查询得到一个Admin实例
    int count =0;

    public void init(){
        if(count==0){
            //===combobox 添加宿舍=======
            RoomDao roomDao =(RoomDao)context.getBean("roomDao");//获得bean
            for (Room r : roomDao.selectByBuildingNo(admin.getBuildingNo())) {
                rList.add(r.getRoomName());
                rMap.put(r.getRoomName(),r.getRoomNo());
            }
            studentR_select.getItems().addAll(rList);//添加列表
            studentR_select.setValue(rList.get(0));
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
    public void selectByRoom(){
        showTable(getData("宿舍"));
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
            case "宿舍": lists=studentDao.selectByRoomNo(rMap.get(studentR_select.getValue()));
            break;
            case "班级": lists=studentDao.selectByCLNOAndBNO(clMap.get(studentCl_select.getValue()),admin.getBuildingNo());
            break;
            case "学院": lists=studentDao.selectByCONOAndBNO(coMap.get(studentCo_select.getValue()),admin.getBuildingNo());
            break;
            case "所有": lists=studentDao.selectByBuildingNo(admin.getBuildingNo());
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
        //lambda表达式
        sDel.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {

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
                            Student clickDel = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("删除 "+clickDel.getUsername() + " 的记录");
                            studentDao.delete(clickDel.getUsername() );
                            sTable.getItems().remove(this.getIndex());//移除当前行
                        });
                    }
                }

            };
            return cell;
        });

        //lambda
        sEdt.setCellFactory((col) -> {
            TableCell<Student, String> cell = new TableCell<Student, String>() {
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
                            Student clickEdt = this.getTableView().getItems().get(this.getIndex());
                            System.out.println("修改 "+clickEdt.getUsername()+ " 的记录");
                            try {
                                student = clickEdt;
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

        sTable.setItems(students);
    }

    //弹出student_edit.fxml视图
    public void openEdtView() throws IOException {
        StageManage.CONTROLLER.put("StudentC",this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/admin/student_edit.fxml"));
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
