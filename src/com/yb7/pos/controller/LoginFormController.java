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

public class LoginFormController {
    public AnchorPane loginFormContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm","SignUp Form");
    }

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) loginFormContext.getScene().getWindow();
        window.setTitle(title);
        window.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml")))
        );
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        //Get User Details from the Fields

        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        //Check the details with user table
        for(User u:Database.userTable){
            if(u.getEmail().equals(userName)){
                if(u.getPassword().equals(password))
                {
                    setUi("DashBoardForm",u.getEmail());
                }
                else {
                    new Alert(Alert.AlertType.WARNING,"Incorrect Password").show();
                }
                return;

            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Username doesn't exist!").show();
            }
        }

        //Go ahead only if it matches otherwise error




    }
}
