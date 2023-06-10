package com.gym.controller.admin.equipment;

import com.gym.controller.admin.EquipmentFormController;
import com.gym.dto.EquipmentDTO;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EquipmentService;
import com.gym.service.exception.DuplicateException;
import com.gym.view.tm.EquipmentTM;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class AddEquipmentController {

    public Button btnEquipRegister;
    public JFXTextField txtDate;
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtStatus;
    public JFXTextField txtEquip_Id;
    public TableView<EquipmentTM> tblEquip;

    public EquipmentService equipmentService;

    public EquipmentFormController equipmentFormController;


    public void init(TableView<EquipmentTM> tblEquip, EquipmentFormController equipmentFormController){
        this.tblEquip=tblEquip;
        this.equipmentService= ServiceFactory.getInstance().getService(ServiceTypes.EQUIPMENT);
        this.equipmentFormController=equipmentFormController;
    }


    public void btnEquipRegisterOnAction(ActionEvent actionEvent) {
        EquipmentDTO equipment=new EquipmentDTO(txtEquip_Id.getText(),txtName.getText(),txtDate.getText(),Double.parseDouble(txtPrice.getText()),txtStatus.getText());

        try {
            EquipmentDTO equipmentDTO = equipmentService.saveEquipment(equipment);
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Registered !").show();
            tblEquip.getItems().add(new EquipmentTM(equipmentDTO.getEquip_Id(), equipmentDTO.getName(), equipmentDTO.getDate(), equipmentDTO.getPrice(),equipmentDTO.getStatus()));
            txtEquip_Id.clear();
            txtName.clear();
            txtName.clear();
            txtDate.clear();
            txtPrice.clear();
            txtStatus.clear();
            txtEquip_Id.requestFocus();
        }catch (DuplicateException | SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
