package com.lgs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * @author ：李先生
 * @date ：Created in 2019/10/12 21:58
 * @description：  启动程序的入口
 * @modified By：
 * @version: $ 1.0
 */
public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //这条语句作用等于下面两条语句
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/login.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("学生宿舍管理系统");//设置窗口标题
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/start.png").toString()));//设置图标
        primaryStage.setScene(new Scene(root));//添加场景
        primaryStage.setResizable(false);//窗口不可改变高度 宽度 这样就不用调节自适应了
        primaryStage.show();//显示窗口
    }


    public static void main(String[] args) {
        launch(args);
    }
}
