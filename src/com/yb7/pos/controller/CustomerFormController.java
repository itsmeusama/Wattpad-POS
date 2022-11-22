package com.yb7.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.yb7.pos.db.Database;
import com.yb7.pos.model.Customer;
import com.yb7.pos.view.tm.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TableView<CustomerTM> tblCustomer;
    public JFXButton btnSaveUpdate;
    public TextField txtSearch;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtId;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;

    public void initialize(){
        setTableDate();
        setCustomerId();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        /////////////////// Listeners ///////////////////////
        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(null!=newValue){
                        setCustomerData(newValue);
                    }
                });


        /////////////////// Listeners ///////////////////////

    }
    private void setCustomerData(CustomerTM tm){
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));

        btnSaveUpdate.setText("Update Customer");

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm", "Dashboard");
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void saveUpdateOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        if(btnSaveUpdate.getText().equalsIgnoreCase("Save Customer")){
            //Save
            if(Database.customerTable.add(customer)){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
                setTableDate();
                setCustomerId();
                clear();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        }
        else
        {
            for(Customer c: Database.customerTable){
                if(txtId.getText().equalsIgnoreCase(c.getId())){
                    c.setName(txtName.getText());
                    c.setAddress(txtAddress.getText());
                    c.setSalary(Double.parseDouble(txtSalary.getText()));
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated").show();
                    setTableDate();
                    clear();
                }

            }
        }
    }

    private void clear(){
        btnSaveUpdate.setText("Save Customer");

        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
        setCustomerId();

    }

    private void setUi(String location, String title) throws IOException {
        Stage window = (Stage) customerFormContext.getScene().getWindow();
        window.setTitle(title);
        window.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml")))
        );
    }

    private void setTableDate(){
        ArrayList<Customer> customerList = Database.customerTable;
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        for(Customer c: customerList){
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(c.getId(),c.getName(),c.getAddress(),c.getSalary(),btn);
            obList.add(tm);

            btn.setOnAction(e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you Sure?",ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> val = alert.showAndWait();
                if(val.get()==ButtonType.YES)
                {
                    Database.customerTable.remove(c);
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Removed").show();
                    setTableDate();
                }
            });


        }
        tblCustomer.setItems(obList);

    }
    private void setCustomerId(){
        //Get last saved Customer
        //Catch the id(C-001)
        //Seperate the numbers from the character
        //increment the seperated number
        //Concat the character again to the incremented number (C-002)
        //setCustomerId
        if(!Database.customerTable.isEmpty()){
            Customer c = Database.customerTable.get(Database.customerTable.size()-1);
            String id = c.getId(); //C-002
            String dataArray[] = id.split("-"); //[C,002]
            id=dataArray[1];
            int oldNumber = Integer.parseInt(id);
            oldNumber++;

            if(oldNumber<9){
                txtId.setText("C-00"+oldNumber);
            }
            else if(oldNumber < 99){
                txtId.setText("C-0"+oldNumber);
            }
            else{
                txtId.setText("C-"+oldNumber);
            }
        }
        else{
            txtId.setText("C-001");
        }
         
    }


}
