package com.gym.controller.admin;

import com.gym.controller.admin.equipment.AddEquipmentController;
import com.gym.controller.admin.equipment.UpdateEquipmentController;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EquipmentService;
import com.gym.util.Navigation;
import com.gym.util.Route;
import com.gym.view.tm.EquipmentTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EquipmentFormController {


    public TextField txtSearch;
    public Button btnAddEquip;
    public Button btnUpdate;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colPrice;
    public TableColumn colEmp_Id;
    public AnchorPane paneEquip;
    public TableView<EquipmentTM> tblEquip;
    public TableColumn colStatus;

    public EquipmentService equipmentService;

    public void initialize() throws SQLException, ClassNotFoundException {

        this.equipmentService= ServiceFactory.getInstance().getService(ServiceTypes.EQUIPMENT);

        btnUpdate.setDisable(true);
        tblEquip.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Equip_Id"));
        tblEquip.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblEquip.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblEquip.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Price"));
        tblEquip.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Status"));

        List<EquipmentTM> equipmentTMList = equipmentService.findAllEquipment().stream().map(b -> new EquipmentTM(b.getEquip_Id(), b.getName(), b.getDate(), b.getPrice(),b.getStatus())).collect(Collectors.toList());

        tblEquip.setItems(FXCollections.observableArrayList(equipmentTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblEquip.getItems().clear();
                List<EquipmentTM> equipmentList = equipmentService.searchEquipmentByText(curr).stream().map(equipment -> new EquipmentTM(equipment.getEquip_Id(), equipment.getName(), equipment.getDate(), equipment.getPrice(),equipment.getStatus())).collect(Collectors.toList());
                tblEquip.setItems(FXCollections.observableArrayList(equipmentList));
            }

        } );

        tblEquip.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }




    public void btnAddEquipOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/equipment/AddEquipmentForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddEquipmentController addEquipmentController = fxmlLoader.getController();
        addEquipmentController.init(tblEquip,this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Equipment Registration Form");
        stage.centerOnScreen();
        stage.show();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/equipment/UpdateEquipmentForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateEquipmentController controller = fxmlLoader.getController();
        controller.init( tblEquip.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete equipment details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    }

