package com.cqut.atao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello Atao");
        primaryStage.setScene(new Scene(root, 840, 840));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
