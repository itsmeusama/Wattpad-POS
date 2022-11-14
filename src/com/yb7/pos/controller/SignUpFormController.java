package com.yb7.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yb7.pos.db.Database;
import com.yb7.pos.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class SignUpFormController {
    public JFXPasswordField txtRePassword;
    public JFXTextField txtEmail;
    public AnchorPane signupFormContext;
    public JFXTextField txtFullName;
    public JFXTextField txtContact;
    public JFXPasswordField txtPassword;

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) {
    }

    public void signUpOnAction(ActionEvent actionEvent) {

        String realPwd = txtPassword.getText().trim();
        String matchPwd = txtRePassword.getText().trim();

        if(!realPwd.equals(matchPwd)){
            new Alert(Alert.AlertType.WARNING,"Passwords not matching").show();
            return;
        }

        User u = new User(txtEmail.getText().trim(),txtFullName.getText(),txtContact.getText(),txtPassword.getText());
        if(saveUser(u)){
            new Alert(Alert.AlertType.CONFIRMATION, "User Registered").show();
            clearFields();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    private void clearFields() {
        txtEmail.clear();txtFullName.clear();txtContact.clear();txtPassword.clear();txtRePassword.clear();
    }

    private boolean saveUser(User u){
        return Database.userTable.add(u);

    }
}
