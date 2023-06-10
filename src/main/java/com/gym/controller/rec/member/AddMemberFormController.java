package com.gym.controller.rec.member;


import com.gym.controller.rec.MembersFormController;
import com.gym.dto.MemberDTO;
import com.gym.service.ServiceFactory;
import com.gym.service.ServiceTypes;
import com.gym.service.custom.MemberService;
import com.gym.service.exception.DuplicateException;
import com.gym.view.tm.MemberTM;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.SQLException;


public class AddMemberFormController {

    public JFXTextField txtMemberName;
    public JFXTextField txtMemberAddress;
    public JFXTextField txtAge;
    public JFXTextField txtMembership;
    public JFXTextField txtMemberNIC;
    public JFXTextField txtContact;
    public JFXTextField txtJoinedDate;
    public JFXTextField txtDescription;
    public JFXTextField txtAmount;
    public Button btnMemberRegister;
    public MemberService memberService;
    public static TableView<MemberTM> tblMember;
    public MembersFormController membersFormController;
    public JFXTextField txtMemberId;


    public void btnMemberRegisterOnAction(ActionEvent actionEvent) {
        if (txtMemberId.getText().isBlank() || !txtMemberId.getText().matches("^[0-9]$")) {
        new Alert(Alert.AlertType.ERROR, "Member ID invalid or empty").show();
        txtMemberId.selectAll();
        txtMemberId.requestFocus();
        return;
    } else if (txtMemberName.getText().isBlank() || !txtMemberName.getText().matches("^[A-Za-z ]+$")) {
        new Alert(Alert.AlertType.ERROR, "Name Cannot be empty or invalid").show();
            txtMemberName.selectAll();
            txtMemberName.requestFocus();
        return;
    } else if (txtMemberAddress.getText().isBlank() || !txtMemberAddress.getText().matches("^[A-Za-z ]+$")) {
        new Alert(Alert.AlertType.ERROR, "Address name invalid or null").show();
            txtMemberAddress.selectAll();
            txtMemberAddress.requestFocus();
        return;

    } else if (txtMemberNIC.getText().isBlank() || !txtMemberNIC.getText().matches("^([0-9]{9})(X|V)$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtMemberNIC.selectAll();
            txtMemberNIC.requestFocus();
        return;

    } else if (txtContact.getText().isBlank() || !txtMemberNIC.getText().matches("^(075|077|078|074|070|071|072|076)([0-9]{7})$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtMemberNIC.selectAll();
            txtMemberNIC.requestFocus();
        return;


    }
        MemberDTO member = new MemberDTO(Integer.parseInt(txtMemberId.getText()), txtMemberName.getText(), txtMemberAddress.getText(), txtAge.getText(), txtMembership.getText(), txtMemberNIC.getText(), txtContact.getText(),txtJoinedDate.getText());


        try {
            if(memberService.saveMember(member)==null) {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the Member !").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered !").show();
            tblMember.getItems().add(new MemberTM(Integer.parseInt(member.getID()), member.getFullName(), member.getAddress(), member.getAge(), member.getMembership(), member.getNIC(), member.getContact(),member.getJoinedDate()));
            txtMemberId.clear();
            txtMemberName.clear();
            txtMemberAddress.clear();
            txtAge.clear();
            txtMembership.clear();
            txtMemberNIC.clear();
            txtContact.clear();
            txtJoinedDate.clear();
            txtMemberId.requestFocus();
        } catch (DuplicateException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void init(TableView<MemberTM> tblMember, MembersFormController membersFormController){
        this.tblMember=tblMember;
        this.memberService= ServiceFactory.getInstance().getService(ServiceTypes.EQUIPMENT);
        this.membersFormController=membersFormController;
    }
}
