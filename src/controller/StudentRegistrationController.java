package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class StudentRegistrationController {
    public Label txtDate;
    public Label txtTime;
    public JFXButton btnBack;
    public TextField txtFullName;
    public TextField txtAddress;
    public DatePicker dpBirthday;
    public TextField txtAge;
    public TextField txtPhoneNumber;
    public TextField txtxEducation;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colFullName;
    public TableColumn colAddress;
    public TableColumn coBirthday;
    public TableColumn colAge;
    public TableColumn colGender;
    public TableColumn colPhoneNumber;
    public TableColumn colEducation;
    public ComboBox cmbGender;
    public JFXTextField txtId;
    public JFXTextField txtSearch;

    public void initialize(){

        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void fullNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        dpBirthday.requestFocus();
    }

    public void birthdayOnAction(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void ageOnAction(ActionEvent actionEvent) {
        cmbGender.requestFocus();
    }

    public void phoneNumberOnAction(ActionEvent actionEvent) {
        txtxEducation.requestFocus();
    }

    public void genderOnaction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }

    public void addOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}
