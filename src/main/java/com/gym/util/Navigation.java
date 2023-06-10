package com.gym.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    public static AnchorPane paneNavigate;

    public static void navigate(Route route, AnchorPane paneNavigate) throws IOException {
        Navigation.paneNavigate = paneNavigate;
        Navigation.paneNavigate.getChildren().clear();


        switch (route) {
            case ADMIN_HOME:
                setUi("/view/admin/AdminHome.fxml");
                break;

            case EMPLOYEE:
                setUi("/view/admin/EmployeeForm.fxml");
                break;

            case MEMBER:
                setUi("/view/receptionist/MembersForm.fxml");

            case PAYMENT:
                setUi("/view/receptionist/PaymentForm.fxml");

            case REC_HOME:
                setUi("/view/receptionist/RecHome.fxml");
            case EQUIPMENT:
                setUi("/view/admin/EquipmentForm.fxml");

        }
    }
    private static void setUi(String location) throws IOException {
        Navigation.paneNavigate.getChildren().add(FXMLLoader.load(Route.class.getResource("" +location)));
    }

}

