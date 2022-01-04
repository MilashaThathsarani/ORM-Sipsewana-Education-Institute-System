package controller;

import bo.BoFactory;
import bo.custom.RegisterBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.RegisterDetailDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Program;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.CartTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentController {
    private final RegisterBO registerBO = (RegisterBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER);
    public AnchorPane paymentContext;
    public Label txtDate;
    public Label txtTime;
    public JFXButton btnBack;
    public TableView <CartTM> tblList;
    public Label lblTotal;
    public TextField txtRegisterId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtBirthday;
    public JFXTextField txtAge;
    public JFXTextField txtGender;
    public JFXTextField txtPhoneNumber;
    public JFXTextField txtEducation;
    public JFXComboBox cmbProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public JFXComboBox cmbStudentId;
    public TableColumn colRegisterId;
    public TableColumn colStudentId;
    public TableColumn colProgramId;
    public TableColumn colStudentName;
    public TableColumn colProgramName;
    public TableColumn colPayment;
    private String registerId;
    public String tempStudentId;
    public String tempProgramId;

    public void initialize() {

        try {
            setRegisterId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadDateAndTime();
        try {
            loadStudentIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colRegisterId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            loadProgramIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                tempStudentId=String.valueOf(newValue);
                setStudentData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                tempProgramId=String.valueOf(newValue);
                setProgramData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        try {
            registerId = setRegisterId();
            txtRegisterId.setText(registerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String setRegisterId() throws SQLException, ClassNotFoundException {
        return registerBO.getRegisterIds();
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

    private void setProgramData(String programId) throws SQLException, ClassNotFoundException {
        ProgramDTO programDTO = registerBO.searchPrograms(programId);
        if (programDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtProgramName.setText(programDTO.getProgramName());
            txtDuration.setText(programDTO.getDuration());
            txtFee.setText(String.valueOf(programDTO.getFee()));
        }
    }

    private void setStudentData(String studentId) throws SQLException, ClassNotFoundException {
        StudentDTO studentDTO = registerBO.searchStudents(studentId);
        if (studentDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtStudentName.setText(studentDTO.getStudentName());
            txtAddress.setText(studentDTO.getAddress());
            txtBirthday.setText(studentDTO.getBirthday());
            txtAge.setText(String.valueOf(studentDTO.getAge()));
            txtGender.setText(studentDTO.getGender());
            txtPhoneNumber.setText(studentDTO.getPhoneNumber());
            txtEducation.setText(studentDTO.getEducation());
        }
    }

    private void loadProgramIds() throws SQLException, ClassNotFoundException {
        List<String> programIds = registerBO.getAllProgramIds();
        cmbProgramId.getItems().addAll(programIds);
    }

    private void loadStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = registerBO.getAllStudentIds();
        cmbStudentId.getItems().addAll(studentIds);
    }

    ObservableList<CartTM> obList = FXCollections.observableArrayList();

    public void addToTableOnAction(ActionEvent actionEvent) {
        String registerId = txtRegisterId.getText();
        String studentId = String.valueOf(cmbStudentId.getValue());
        String programId = String.valueOf(cmbProgramId.getValue());
        String studentName = txtStudentName.getText();
        String programName = txtProgramName.getText();
        double total = Double.parseDouble(txtFee.getText());

        CartTM tm = new CartTM(
                registerId,
                studentId,
                programId,
                studentName,
                programName,
                total
        );

        int rowNumber = isExists(tm);

        if (rowNumber == -1) {
            obList.add(tm);
        } else {
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getRegisterId(),
                    temp.getStudentId(),
                    temp.getProgramId(),
                    temp.getStudentName(),
                    temp.getProgramName(),
                    total + temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblList.setItems(obList);

        calculate();
    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getProgramId().equals(obList.get(i).getProgramId())) {
                return i;
            }
        }
        return -1;
    }

    void calculate() {
        double ttl = 0;
        for (CartTM tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblTotal.setText(ttl + "/=");
    }

    public void confirmOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RegistrationDTO registrationDTO = new RegistrationDTO(
                txtRegisterId.getText(),
                tempStudentId,
                //String.valueOf(cmbProgramId.getValue()),
                tempProgramId,
                txtDate.getText(),
                txtTime.getText(),
                lblTotal.getText() ,
                (ArrayList<RegisterDetailDTO>) tblList.getItems().stream().map(tm ->
                        new RegisterDetailDTO(tm.getProgramId(),tm.getStudentId())).collect(Collectors.toList())
        );
        if(registerBO.purchaseRegister(registrationDTO)){
        new Alert(Alert.AlertType.CONFIRMATION,"Registered Successfully").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again").show();
        }
        registerId = setRegisterId();
        txtRegisterId.setText(setRegisterId());
        tblList.getItems().clear();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) paymentContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }

}
