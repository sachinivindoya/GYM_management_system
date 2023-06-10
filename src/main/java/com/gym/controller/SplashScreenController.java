package com.gym.controller;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;


public class SplashScreenController {

    public Rectangle loadRec1;
    public Rectangle loadRec2;
    public Label lblLoading;

    public void initialize () {
        Timeline tm = new Timeline();

        KeyFrame kf1 = new KeyFrame(Duration.millis(500), actionEvent -> {
            lblLoading.setText("Initializing the application...");
            loadRec2.setWidth(loadRec1.getWidth()*0.3);
        });

        KeyFrame kf2 = new KeyFrame(Duration.millis(1000),actionEvent -> {
            lblLoading.setText("Loading internal resources...");
            loadRec2.setWidth(loadRec1.getWidth()*0.5);
        });

        KeyFrame kf3= new KeyFrame(Duration.millis(1500),actionEvent ->{
            lblLoading.setText("Loading Images....");
            loadRec2.setWidth(loadRec1.getWidth()*0.6);
        });

        KeyFrame kf4= new KeyFrame(Duration.millis(2000),actionEvent ->{
            lblLoading.setText("Loading UIs....");
            loadRec2.setWidth(loadRec1.getWidth()*0.8);
        });

        KeyFrame kf5= new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblLoading.setText("Welcome to management system");
            loadRec2.setWidth(loadRec1.getWidth());
        });

        KeyFrame kf6 = new KeyFrame(Duration.millis(3000),actionEvent -> {
            try {
                Stage window = (Stage) lblLoading.getScene().getWindow();
                window.close();
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.centerOnScreen();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoadingForm.fxml"))));
                stage.show();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        tm.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6);
        tm.playFromStart();


    }
}
