package controller;

import bo.BoFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class StudentRegistrationController {
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
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
    public JFXTextField txtFee;
    public JFXTextField txtEducation;
    public JFXTextField txtBirthDay;

    public void initialize(){

        loadDateAndTime();

        cmbGender.getItems().addAll(
                "Male",
                "Female");
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

    public void genderOnaction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String studentName = txtFullName.getText();
        String address = txtAddress.getText();
        //String birthday =String.valueOf(dpBirthday.getValue());
        String birthday = txtBirthDay.getText();
        int age = Integer.parseInt(txtAge.getText());
        String gender = String.valueOf(cmbGender.getValue());
        String phoneNumber = txtPhoneNumber.getText();
        String education = txtEducation.getText();

        try {
            if (existStudent(studentId)) {
                new Alert(Alert.AlertType.ERROR, studentId + " already exists").show();

            } else {
                StudentDTO studentDTO = new StudentDTO(studentId,studentName,address,birthday,age,gender,phoneNumber,education);
                studentBO.addStudent(studentDTO);

                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*if (studentBO.addStudent(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }*/
    }

    boolean existStudent(String studentId) {
        return studentBO.ifStudentExist(studentId);
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void idOnAction(ActionEvent actionEvent) {
        txtFullName.requestFocus();
    }

    public void feeOnAction(ActionEvent actionEvent) {
        txtEducation.requestFocus();
    }

    public void educationOnAction(ActionEvent actionEvent) {
    }
}
