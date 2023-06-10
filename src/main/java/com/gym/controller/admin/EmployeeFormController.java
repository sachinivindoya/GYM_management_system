package com.gym.controller.admin;

import com.gym.controller.admin.employee.AddEmployeeFormController;
import com.gym.controller.admin.employee.UpdateEmployeeFormController;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EmployeeService;
import com.gym.view.tm.EmployeeTM;
import com.gym.view.tm.EquipmentTM;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeFormController {


    public TableColumn colempid;
    public TableColumn colempname;
    public TableColumn colempaddress;
    public TableColumn colempnic;
    public TableColumn colempcontact;
    public TableColumn colemjoineddate;
    public TableColumn coloccupation;
    public TextField txtSearch;
    public Button btnAddEmp;
    public Button btnUpdate;
    public EmployeeService employeeService;
    public TableView<EmployeeTM> tblEmployee;


    public void initialize() throws SQLException, ClassNotFoundException {

        this.employeeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);

        btnUpdate.setDisable(true);
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Emp_Id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Joined_Date"));
        tblEmployee.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Occupation"));

        List<EmployeeTM> employeeTMList = employeeService.findAllEmployee().stream().map(b -> new EmployeeTM(b.getEmp_Id(), b.getName(), b.getAddress(), b.getNIC(),b.getContact(),b.getJoined_Date(),b.getOccupation())).collect(Collectors.toList());

        tblEmployee.setItems(FXCollections.observableArrayList(employeeTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblEmployee.getItems().clear();
                List<EmployeeTM> employeeList = employeeService.searchEmployeeByText(curr).stream().map(employee -> new EmployeeTM(employee.getEmp_Id(), employee.getName(), employee.getAddress(), employee.getNIC(),employee.getContact(),employee.getJoined_Date(),employee.getOccupation())).collect(Collectors.toList());
                tblEmployee.setItems(FXCollections.observableArrayList(employeeList));
            }

        } );

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }







    public void btnAddEmpOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/employee/AddEmployeeForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddEmployeeFormController addEmployeeFormController = fxmlLoader.getController();
        addEmployeeFormController.init(tblEmployee,this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Employee Registration Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/admin/employee/UpdateEmployeeForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateEmployeeFormController updateEmployeeFormController = fxmlLoader.getController();
        updateEmployeeFormController.init(tblEmployee.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete Employee details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }



}
