package com.mygaienko.rt_system.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by enda1n on 23.05.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        initPage(stage);

    }

    private void initPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WorkingArea.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Client");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
