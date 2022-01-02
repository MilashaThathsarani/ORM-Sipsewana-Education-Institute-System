package controller;

import bo.BoFactory;
import bo.custom.RegisterBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Program;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

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
    
    public void initialize(){
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
    }

    private void setProgramData(String programId) throws SQLException, ClassNotFoundException {
        ProgramDTO programDTO = registerBO.searchPrograms(programId);
        if (programDTO == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        }else {
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

    public void addToTableOnAction(ActionEvent actionEvent) {
    }

    public void confirmOnAction(ActionEvent actionEvent) {
    }
}
