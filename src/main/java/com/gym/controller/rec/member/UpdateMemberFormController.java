package com.gym.controller.rec.member;


import com.gym.controller.rec.MembersFormController;
import com.gym.dto.MemberDTO;
import com.gym.service.custom.MemberService;
import com.gym.service.exception.NotFoundException;
import com.gym.view.tm.MemberTM;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UpdateMemberFormController {
    public JFXTextField txtMemberName;
    public JFXTextField txtMemberAddress;
    public JFXTextField txtAge;
    public JFXTextField txtMembership;
    public JFXTextField txtMemberNIC;
    public JFXTextField txtContact;
    public JFXTextField txtJoinedDate;
    public Button btnUpdate;
    public Button btnDlt;
    public Label lblID;
    public MemberTM memberTM;
    public MemberService memberService;
    public MembersFormController membersFormController;

    public void btnDltOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        MemberDTO updatedMember = new MemberDTO(memberTM.getID(), txtMemberName.getText(), txtMemberAddress.getText(), txtAge.getText(),txtMembership.getText(),txtMemberNIC.getText(),txtContact.getText(),txtJoinedDate.getText());
        try {
            if(memberService.updateMember(updatedMember)!=null){
                int selectedIndex = membersFormController.tblMember.getSelectionModel()
                        .getSelectedIndex();
                membersFormController.tblMember.getItems()
                        .add(selectedIndex,new MemberTM(Integer.parseInt(updatedMember.getID()), updatedMember.getFullName(), updatedMember.getAddress(), updatedMember.getAge(),updatedMember.getMembership(),updatedMember.getNIC(),updatedMember.getContact(),updatedMember.getJoinedDate()));
                membersFormController.tblMember.getItems().remove(selectedIndex+1);
                new Alert(Alert.AlertType.INFORMATION,"Member has been successfully updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to update the member details,try again!").show();
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.WARNING,"member not found for given id!").show();
        }
    }
}
