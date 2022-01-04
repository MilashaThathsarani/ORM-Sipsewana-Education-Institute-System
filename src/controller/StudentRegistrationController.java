package controller;

import bo.BoFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import validation.ValidationUtil;
import view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
    public AnchorPane studentContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern addressPattern = Pattern.compile("^[A-z ]{3,30}([0-9]{1,2})?$");
    Pattern agePattern = Pattern.compile("[1-9][0-9]*$");
    Pattern phoneNumberPattern = Pattern.compile("^[0-9][-]?[0-9]*$");
    Pattern educationPattern = Pattern.compile("[A-z ]{3,30}([0-9]{1,2})?$");

    public void initialize() throws SQLException, ClassNotFoundException {
        storeValidation();

        colStudentId.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colFullName.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colAddress.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        coBirthday.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colAge.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colGender.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colPhoneNumber.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colEducation.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");


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
        
        setStudentId();

    }

    private void storeValidation() {
        map.put(txtFullName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtAge, agePattern);
        map.put(txtPhoneNumber, phoneNumberPattern);
        map.put(txtEducation, educationPattern);
    }

    private void setStudentId() throws SQLException, ClassNotFoundException {
            txtId.setText(studentBO.getStudentIds());
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

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String studentName = txtFullName.getText();
        String address = txtAddress.getText();
        String birthday =String.valueOf(dpBirthday.getValue());
        //String birthday = txtBirthDay.getText();
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
                setItemsToTable(studentBO.getAll());

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemsToTable(ArrayList<StudentTM> student) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        student.forEach(e -> {
            obList.add(
                    new StudentTM(e.getStudentId(),e.getStudentName(),e.getAddress(),e.getBirthday(),e.getAge(),e.getGender(),e.getPhoneNumber(),e.getEducation()));
        });
        tblStudent.setItems(obList);
    }


    boolean existStudent(String studentId) {
        return studentBO.ifStudentExist(studentId);
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtId.getText();
        String studentName = txtFullName.getText();
        String address = txtAddress.getText();
        String birthday =String.valueOf(dpBirthday.getValue());
        int age = Integer.parseInt(txtAge.getText());
        String gender = String.valueOf(cmbGender.getValue());
        String phoneNumber = txtPhoneNumber.getText();
        String education = txtEducation.getText();

        StudentDTO studentDTO = new StudentDTO(studentId,studentName,address,birthday,age,gender,phoneNumber,education);

        if (studentBO.updateStudent(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            setItemsToTable(studentBO.getAll());
       } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (studentBO.deleteStudent(txtId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            setItemsToTable(studentBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
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

    private void setData(StudentDTO s) {
       txtId.setText(s.getStudentId());
       txtFullName.setText(s.getStudentName());
       txtAddress.setText(s.getAddress());
       txtAge.setText(String.valueOf(s.getAge()));
       txtPhoneNumber.setText(s.getPhoneNumber());
       txtEducation.setText(s.getEducation());
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) studentContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }

    public void fullNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        dpBirthday.requestFocus();
    }

    public void ageOnAction(ActionEvent actionEvent) {
        cmbGender.requestFocus();
    }


    public void birthDayOnAction(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void feeOnAction(ActionEvent actionEvent) {
        txtEducation.requestFocus();
    }

    public void educationOnAction(ActionEvent actionEvent) {
    }

    public void genderOnAction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }

    public void Student_KeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }
        }
    }
}
