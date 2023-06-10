package com.gym.controller.admin.equipment;

import com.gym.controller.admin.EquipmentFormController;
import com.gym.dto.EquipmentDTO;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EquipmentService;
import com.gym.service.exception.NotFoundException;
import com.gym.view.tm.EquipmentTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import java.util.Optional;


public class UpdateEquipmentController {
    public JFXTextField txtDate;
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public Button btnUpdateEquip;
    public Label lblEquip_Id;
    public JFXTextField txtStatus;
    public EquipmentTM equipmentTM;
    public EquipmentService equipmentService;
    public EquipmentFormController equipmentFormController;
    public Button btnEquipDlt;

    public void btnUpdateEquipOnAction(ActionEvent actionEvent) {

        EquipmentDTO updatedEquipment = new EquipmentDTO(equipmentTM.getEquip_Id(), txtName.getText(), txtDate.getText(), Double.parseDouble(txtPrice.getText()),txtStatus.getText());
        try {
            if(equipmentService.updateEquipment(updatedEquipment)!=null){
                int selectedIndex = equipmentFormController.tblEquip.getSelectionModel()
                        .getSelectedIndex();
                equipmentFormController.tblEquip.getItems()
                        .add(selectedIndex,new EquipmentTM(updatedEquipment.getEquip_Id(), updatedEquipment.getName(), updatedEquipment.getDate(), updatedEquipment.getPrice(),updatedEquipment.getStatus()));
                equipmentFormController.tblEquip.getItems().remove(selectedIndex+1);
                new Alert(Alert.AlertType.INFORMATION,"Equipment has been successfully updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to update the Equipment details,try again!").show();
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.WARNING,"Equipment not found for given id!").show();
        }
        }


    public void btnEquipDltOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the equipment", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES){

            try {
                equipmentService.deleteEquipment(equipmentTM.getEquip_Id());
                new Alert(Alert.AlertType.INFORMATION, "equipment delete successful").show();
                equipmentFormController.tblEquip.getItems().
                        removeAll(equipmentFormController.tblEquip.getSelectionModel().getSelectedItem());
                btnEquipDlt.getScene().getWindow().hide();


            }catch (NotFoundException e){
                new Alert(Alert.AlertType.WARNING,"No equipment found for given id!").show();
            }

        }
    }

    public void init(EquipmentTM equipmentTM, EquipmentFormController equipmentFormController) {
        this.equipmentTM=equipmentTM;
        this.equipmentFormController =equipmentFormController;
        fillAllFields(equipmentTM);
        equipmentService= ServiceFactory.getInstance().getService(ServiceTypes.EQUIPMENT);
    }




    private void fillAllFields(EquipmentTM equipmentTM) {
        lblEquip_Id.setText(equipmentTM.getEquip_Id());
        txtName.setText(equipmentTM.getName());
        txtDate.setAccessibleText(equipmentTM.getDate());
        txtPrice.setText(equipmentTM.getPrice()+"");
        txtStatus.setText(equipmentTM.getStatus());
    }


}
