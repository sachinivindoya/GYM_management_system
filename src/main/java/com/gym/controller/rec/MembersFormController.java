package com.gym.controller.rec;

import com.gym.controller.admin.equipment.AddEquipmentController;
import com.gym.controller.rec.member.AddMemberFormController;
import com.gym.entity.Member;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.EquipmentService;
import com.gym.service.custom.MemberService;
import com.gym.view.tm.EquipmentTM;
import com.gym.view.tm.MemberTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MembersFormController {




    public TableColumn colId;
    public TableColumn colFullName;
    public TableColumn colAddress;
    public TableColumn colMemship;
    public TableColumn colNic;
    public TableColumn colContact;
    public TableColumn colJoinedDate;
    public TableColumn colAge;
    public Button btnUpdate;
    public Button btnAddMember;
    public Button btnPrint;
    public TextField txtSearch;
    public TableView<MemberTM> tblMember;


    public MemberService memberService;



    public void initialize() throws SQLException, ClassNotFoundException {

        this.memberService= ServiceFactory.getInstance().getService(ServiceTypes.MEMBER);

       btnUpdate.setDisable(true);
        tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Full Name"));
        tblMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblMember.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Age"));
        tblMember.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Membership"));
        tblMember.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tblMember.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblMember.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Joined Date"));



        List<MemberTM> memberTMList = memberService.findAllMembers().stream().map(b -> new MemberTM(Integer.parseInt(b.getID()), b.getFullName(), b.getAddress(), b.getAge(),b.getMembership(),b.getNIC(),b.getContact(),b.getJoinedDate())).collect(Collectors.toList());

        tblMember.setItems(FXCollections.observableArrayList(memberTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblMember.getItems().clear();
                List<MemberTM> memberList = memberService.searchMemberByText(curr).stream().map(member -> new MemberTM(Integer.parseInt(member.getID()), member.getFullName(), member.getAddress(), member.getAge(),member.getMembership(),member.getNIC(),member.getContact(),member.getJoinedDate())).collect(Collectors.toList());
                tblMember.setItems(FXCollections.observableArrayList(memberList));
            }

        } );

        tblMember.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }






    public void btnPrintOnAction(ActionEvent actionEvent) {
    }

    public void tblMemberOnAction(MouseEvent mouseEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnAddMemberOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/receptionist/member/AddMemberForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddMemberFormController addMemberFormController = fxmlLoader.getController();
        addMemberFormController.init(tblMember,this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Equipment Registration Form");
        stage.centerOnScreen();
        stage.show();
    }
}
