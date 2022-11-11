package com.yb7.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yb7.pos.db.Database;
import com.yb7.pos.model.User;
import javafx.event.ActionEvent;
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
    }

    private boolean saveUser(User u){
        return Database.userTable.add(u);

    }
}
