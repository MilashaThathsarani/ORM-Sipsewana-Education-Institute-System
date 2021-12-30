package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import entity.Student;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public TableView<StudentTM> tblStudent;
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
    public JFXTextField txtEducation;
    public JFXTextField txtBirthDay;

    public void initialize() throws SQLException, ClassNotFoundException {

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEducation.setCellValueFactory(new PropertyValueFactory<>("education"));

        loadDateAndTime();

        cmbGender.getItems().addAll(
                "Male",
                "Female");
        
        setItemsToTable(studentBO.getAll());

    }

    private void setItemsToTable(ArrayList<StudentTM> student) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        student.forEach(e -> {
            obList.add(
                    new StudentTM(e.getStudentId(),e.getStudentName(),e.getAddress(),e.getBirthday(),e.getAge(),e.getGender(),e.getPhoneNumber(),e.getEducation()));
        });
        tblStudent.setItems(obList);
    }

    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();

        StudentDTO studentDTO = studentBO.searchStudent(studentId);
        if (studentDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(studentDTO);
        }
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
                studentBO.add(studentDTO);

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

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String studentName = txtFullName.getText();
        String address = txtAddress.getText();
        //String birthday =String.valueOf(dpBirthday.getValue());
        String birthday = txtBirthDay.getText();
        int age = Integer.parseInt(txtAge.getText());
        String gender = String.valueOf(cmbGender.getValue());
        String phoneNumber = txtPhoneNumber.getText();
        String education = txtEducation.getText();

        StudentDTO studentDTO = new StudentDTO(studentId,studentName,address,birthday,age,gender,phoneNumber,education);

        if (studentBO.update(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
       } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }
    private void setData(StudentDTO s) {
       txtId.setText(s.getStudentId());
       txtFullName.setText(s.getStudentName());
       txtAddress.setText(s.getAddress());
       txtBirthDay.setText(s.getBirthday());
       txtAge.setText(String.valueOf(s.getAge()));
       //cmbGender.setValue(s.getGender());
       txtPhoneNumber.setText(s.getPhoneNumber());
       txtEducation.setText(s.getEducation());
    }

    public void deleteOnAction(ActionEvent actionEvent) {

    }

    public void feeOnAction(ActionEvent actionEvent) {
        txtEducation.requestFocus();
    }

    public void educationOnAction(ActionEvent actionEvent) {
    }
}
