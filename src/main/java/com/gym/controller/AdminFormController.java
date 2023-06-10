package com.gym.controller;



import com.gym.util.Navigation;
import com.gym.util.Route;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminFormController {

    public Label lblDateTime;
    public JFXButton btnEmployees;
    public JFXButton btnPackages;
    public JFXButton btnAccount;
    public JFXButton btnEquipments;
    public JFXButton btnReports;
    public JFXButton btnLogOut;
    public  AnchorPane paneNavigate;
    public AnchorPane AdminHome;
    public JFXButton btnBack;


    public void initialize () throws SQLException, ClassNotFoundException {
        //loadpiechart();
       // loadpiechart1();
        setDateAndTime();
    }
    private void setDateAndTime(){
        // setTime
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblDateTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void btnEmployeesOnAction(ActionEvent actionEvent) throws IOException {
        AdminHome.setVisible(false);
        paneNavigate.setVisible(true);
        Navigation.navigate(Route.EMPLOYEE,paneNavigate);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnEquipmentsOnAction(ActionEvent actionEvent) throws IOException {
        AdminHome.setVisible(false);
        paneNavigate.setVisible(true);
        Navigation.navigate(Route.EQUIPMENT,paneNavigate);
    }

    public void btnPackagesOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        paneNavigate.setVisible(false);
        AdminHome.setVisible(true);
        Navigation.navigate(Route.ADMIN_HOME,paneNavigate);
    }
}
