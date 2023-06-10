package com.gym.controller.admin.employee;

import com.gym.controller.admin.EmployeeFormController;
import com.gym.controller.admin.EquipmentFormController;
import com.gym.dto.EmployeeDTO;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EmployeeService;
import com.gym.service.exception.DuplicateException;
import com.gym.view.tm.EmployeeTM;
import com.gym.view.tm.EquipmentTM;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class AddEmployeeFormController {

    public Button btnRegister;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public TableView<EmployeeTM> tblEmp;
    public JFXTextField txtEmpAddress;
    public JFXTextField txtEmpNic;
    public JFXTextField txtEmpContact;
    public JFXTextField txtEmpJoinedDate;
    public JFXTextField txtEmpOccupation;
    public EmployeeService employeeService;
    public EmployeeFormController employeeFormController;
    public TableView<EmployeeTM> tblEmployee;


    public void init(TableView<EmployeeTM> tblEmployee, EmployeeFormController employeeFormController){
        this.tblEmployee=tblEmployee;
        this.employeeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
        this.employeeFormController=employeeFormController;
    }





    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (txtEmpId.getText().isBlank() || !txtEmpId.getText().matches("^[(E)0-9]{4,}$")) {
            new Alert(Alert.AlertType.ERROR, "Employee ID invalid or empty").show();
            txtEmpId.selectAll();
            txtEmpId.requestFocus();
            return;
        } else if (txtEmpName.getText().isBlank() || !txtEmpName.getText().matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Name Cannot be empty or invalid").show();
            txtEmpName.selectAll();
            txtEmpName.requestFocus();
            return;
        } else if (txtEmpAddress.getText().isBlank() || !txtEmpAddress.getText().matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.ERROR, "Address name invalid or null").show();
            txtEmpAddress.selectAll();
            txtEmpAddress.requestFocus();
            return;

      /*  } else if (txtEmpNic.getText().isBlank() || !txtEmpNic.getText().matches("^([0-9]$)")) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtEmpNic.selectAll();
            txtEmpNic.requestFocus();
            return;*/

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
        EmployeeDTO employee = new EmployeeDTO(txtEmpId.getText(), txtEmpName.getText(), txtEmpAddress.getText(), txtEmpNic.getText(), txtEmpContact.getText(), txtEmpJoinedDate.getText(), txtEmpOccupation.getText());


        try {
            if(employeeService.saveEmployee(employee)==null) {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the Employee !").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered !").show();
            tblEmp.getItems().add(new EmployeeTM(employee.getEmp_Id(), employee.getName(), employee.getAddress(), employee.getNIC(), employee.getContact(), employee.getJoined_Date(), employee.getOccupation()));
            txtEmpId.clear();
            txtEmpName.clear();
            txtEmpAddress.clear();
            txtEmpNic.clear();
            txtEmpContact.clear();
            txtEmpJoinedDate.clear();
            txtEmpOccupation.clear();
            txtEmpId.requestFocus();
        } catch (DuplicateException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
