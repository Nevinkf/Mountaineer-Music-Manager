/*
 * File Name: Main.java
 * Author: Nevin Fullerton
 * Date: 1/7/2025
 * Description: Where the program starts TODO
 */

package com.mountaineermusicmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new ProgramController().getGUI()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Get Song metadata and display it in table, then button to play song

}