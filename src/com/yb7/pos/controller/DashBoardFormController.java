package com.yb7.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;
    
    public void initialize(){
        setDateAndTime();
    }

    private void setDateAndTime() {
        //Set Date
       /* Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        lblDate.setText(formattedDate);*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        //Set Time
        final DateFormat format = DateFormat.getDateInstance();
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
           /* Calendar cal = Calendar.getInstance();
            lblTime.setText(format.format(cal.getTime()));*/
            lblTime.setText(LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("HH:mm:ss")
            ));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void openOrderDetailsOnAction(MouseEvent mouseEvent) {
    }

    public void openPlaceOrderFormOnAction(MouseEvent mouseEvent) {
    }

    public void openCustomerFormOnAction(MouseEvent mouseEvent) {
    }

    public void openStatisticsOnAction(MouseEvent mouseEvent) {
    }

    public void openProductManagementOnAction(MouseEvent mouseEvent) {
    }
}
