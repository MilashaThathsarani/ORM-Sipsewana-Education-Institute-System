package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

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
