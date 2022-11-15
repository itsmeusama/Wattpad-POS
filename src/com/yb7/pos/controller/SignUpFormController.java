package com.yb7.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yb7.pos.db.Database;
import com.yb7.pos.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpFormController {
    public JFXPasswordField txtRePassword;
    public JFXTextField txtEmail;
    public AnchorPane signupFormContext;
    public JFXTextField txtFullName;
    public JFXTextField txtContact;
    public JFXPasswordField txtPassword;

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm","Login Form");
    }

    public void signUpOnAction(ActionEvent actionEvent) throws InterruptedException, IOException {

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
            Thread.sleep(2000);
            //setUi("LoginForm");
           // setUi("DashBoardForm",u.getEmail());

        }
        else{
            new Alert(Alert.AlertType.WARNING,"Already Exist!, Try Again").show();
        }
    }

    private void clearFields() {
        txtEmail.clear();txtFullName.clear();txtContact.clear();txtPassword.clear();txtRePassword.clear();
    }

    private boolean saveUser(User u){
        for(User tempUser:Database.userTable){
            if(tempUser.getEmail().equals(u.getEmail())){
                return false;
            }
        }
        return Database.userTable.add(u);

    }

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) signupFormContext.getScene().getWindow();
        window.setTitle(title);
        window.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml")))
        );
    }
}
