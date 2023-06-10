package com.gym.controller;

import com.gym.util.Navigation;
import com.gym.util.Route;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecFormController {


    public AnchorPane paneNavigateRec;
    public AnchorPane RecHome;
    public Button btnBack;
    public AnchorPane paneRecep;
    public Button btnMember;
    public Button btnPayment;
    public Label lblDate;
    public AnchorPane paneNavigate;
    public Button btnLogOut;

    public void initialize () throws SQLException, ClassNotFoundException {
        setDateAndTime();
    }
    private void setDateAndTime(){
        // setTime
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }



    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage rec = (Stage) paneRecep.getScene().getWindow();
        rec.close();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoadingForm.fxml"))));
        stage.show();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        RecHome.setVisible(true);
        paneNavigate.setVisible(false);
        Navigation.navigate(Route.REC_HOME,paneNavigate);
    }


    public void btnMemberOnAction(ActionEvent actionEvent) throws IOException {
        RecHome.setVisible(false);
        paneNavigate.setVisible(true);
        Navigation.navigate(Route.MEMBER,paneNavigate);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        RecHome.setVisible(false);
        paneNavigate.setVisible(true);
        Navigation.navigate(Route.PAYMENT,paneNavigate);
    }
}
