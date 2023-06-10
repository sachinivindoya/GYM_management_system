package com.gym.controller.admin.employee;



import com.gym.controller.admin.EmployeeFormController;
import com.gym.controller.admin.EquipmentFormController;
import com.gym.dto.EmployeeDTO;
import com.gym.dto.EquipmentDTO;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EmployeeService;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;
import com.gym.view.tm.EmployeeTM;
import com.gym.view.tm.EquipmentTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class UpdateEmployeeFormController {
    public JFXTextField txtempName;
    public JFXTextField txtEmpAddress;
    public JFXTextField txtEmpNic;
    public JFXTextField txtEmpOccupation;
    public JFXTextField txtEmpContact;
    public JFXTextField txtEmpJoinedDate;
    public Button btnUpdateEmployee;
    public Button btnDltEmployee;
    public Label lblEmpId;
    public EmployeeService employeeService;
    public EmployeeFormController employeeFormController;
    public EmployeeTM employeeTM;

    public void init(EmployeeTM employeeTM, EmployeeFormController employeeFormController) {
        this.employeeTM=employeeTM;
        this.employeeFormController =employeeFormController;
        fillAllFields(employeeTM);
        employeeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
    }

    private void fillAllFields(EmployeeTM employeeTM) {
        lblEmpId.setText(employeeTM.getEmp_Id());
        txtempName.setText(employeeTM.getName());
        txtEmpAddress.setText(employeeTM.getAddress());
        txtEmpNic.setText(employeeTM.getNIC());
        txtEmpContact.setText(employeeTM.getContact());
        txtEmpJoinedDate.setText(employeeTM.getJoined_Date());
        txtEmpOccupation.setText(employeeTM.getOccupation());

    }


    public void btnDltEmployeeOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the employee", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.YES) {

            try {
                employeeService.deleteEmployee(employeeTM.getEmp_Id());
                new Alert(Alert.AlertType.INFORMATION, "employee delete successful").show();
                employeeFormController.tblEmployee.getItems().
                        removeAll(employeeFormController.tblEmployee.getSelectionModel().getSelectedItem());
                btnDltEmployee.getScene().getWindow().hide();


            } catch (NotFoundException e) {
                new Alert(Alert.AlertType.WARNING, "No employee found for given id!").show();
            }
        }

    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        //Data Validations

        if (txtempName.getText().isBlank() || !txtempName.getText().matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid EmployeeDTO name !").show();
            txtempName.selectAll();
            txtempName.requestFocus();
            return;
        } else if (txtEmpAddress.getText().isBlank() || !txtEmpAddress.getText().matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid address").show();
            txtEmpAddress.selectAll();
            txtEmpAddress.requestFocus();
            return;
        } else if (txtEmpNic.getText().isBlank() || !txtEmpNic.getText().matches("^([0-9]{9})(X|V)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtEmpNic.selectAll();
            txtEmpNic.requestFocus();
            return;
        } else if (txtEmpContact.getText().isBlank() || !txtEmpContact.getText().matches("^(075|077|078|074|070|071|072|076)([0-9]{7})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtEmpContact.selectAll();
            txtEmpContact.requestFocus();
            return;

        } else if (txtEmpOccupation.getText().isBlank() || !txtEmpOccupation.getText().matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Text").show();
            txtEmpOccupation.selectAll();
            txtEmpOccupation.requestFocus();
            return;
        }
        EmployeeDTO updatedEmployee= new EmployeeDTO(employeeTM.getEmp_Id(), txtempName.getText(),  txtEmpAddress.getText(),txtEmpNic.getText(),txtEmpContact.getText(),txtEmpJoinedDate.getText(),txtEmpOccupation.getText());
        try {
            if(employeeService.updateEmployee(updatedEmployee)!=null){
                int selectedIndex = employeeFormController.tblEmployee.getSelectionModel()
                        .getSelectedIndex();
                employeeFormController.tblEmployee.getItems()
                        .add(selectedIndex,new EmployeeTM(updatedEmployee.getEmp_Id(), updatedEmployee.getName(),  updatedEmployee.getAddress(),updatedEmployee.getNIC(),updatedEmployee.getContact(),updatedEmployee.getJoined_Date(),updatedEmployee.getOccupation()));

                employeeFormController.tblEmployee.getItems().remove(selectedIndex+1);
                new Alert(Alert.AlertType.INFORMATION,"employee has been successfully updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to update the employee details,try again!").show();
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.WARNING,"employee not found for given id!").show();
        }



    }
}
