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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.tm.CartTM;

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
    public TableView tblList;
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

    public void initialize() {

        loadDateAndTime();
        try {
            loadStudentIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadProgramIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudentData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProgramData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        colRegisterId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
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

    public void backOnAction(ActionEvent actionEvent) {
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

    public void confirmOnAction(ActionEvent actionEvent) {
       /* RegistrationDTO registrationDTO = new RegistrationDTO(
                txtRegisterId.getText(),
                cmbStudentId.getValue(),
                cmbProgramId.getValue(),
                txtDate.getText(),
                txtTime.getText(),
                tblList.getItems().stream().map(tm ->
                        new RegisterDetailDTO(tm.getProgramId(),tm.getStudentId())).collect(Collectors.toList())
        );

        if(registerBO.confirmRegister(registrationDTO)){
        new Alert(Alert.AlertType.CONFIRMATION,"Registered Successfully").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again").show();
        }
        tblList.getItems().clear();
    }*/
        ArrayList<RegisterDetailDTO> registerDetailDTOS = new ArrayList<>();
        for (CartTM tm : obList
        ) {
            registerDetailDTOS.add(new RegisterDetailDTO(txtRegisterId.getText(), tm.getProgramId(), tm.getStudentId()));
        }

        boolean b = saveRegister(txtRegisterId.getText(),(String)cmbStudentId.getValue(),(String) cmbProgramId.getValue(),txtDate.getText(),txtTime.getText(),lblTotal.getText(),registerDetailDTOS);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            /*showInvoice();
            setOrderId();
            clearText();*/
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

    }

    public boolean saveRegister(String registerId, String studentId, String programId, String registerDate, String time, String payment, ArrayList<RegisterDetailDTO>registerDetail) {
        try {
            RegistrationDTO registrationDTO = new RegistrationDTO(registerId,studentId,programId,registerDate,time,payment,registerDetail);
            return registerBO.purchaseRegister(registrationDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
