package com.gym.controller;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadingFormController {


    public JFXPasswordField txtLoginPassword;
    public JFXTextField txtLoginUsername;
    public JFXButton btnLogin;
    public AnchorPane paneLogin;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
      // if (txtLoginUsername.getText() == "Admin" && txtLoginPassword.getText() == "1234") {

          /*  Stage login = (Stage) paneLogin.getScene().getWindow();
            login.close();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RecForm.fxml"))));
            stage.show();
        } else if (txtLoginUsername.getText() == "Rec" && txtLoginPassword.getText() == "1234"){*/

            Stage login = (Stage) paneLogin.getScene().getWindow();
            login.close();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminForm.fxml"))));
            stage.show();
      //  } else{
           // new Alert(Alert.AlertType.ERROR,"Enter a valid username or password!").show();



    }
}
