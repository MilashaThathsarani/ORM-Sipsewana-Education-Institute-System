package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class ProgramController {
    public Label txtDate;
    public Label txtTime;
    public JFXButton btnBack;
    public JFXTextField txtId;
    public JFXTextField txtSearch;
    public TableView tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtProgramName;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
        String programId =  txtProgramName.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        /*if (studentBO.addStudent(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }*/
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void programNameOnAction(ActionEvent actionEvent) {
        txtDuration.requestFocus();
    }

    public void durationOnAction(ActionEvent actionEvent) {
        txtFee.requestFocus();
    }

    public void feeOnAction(ActionEvent actionEvent) {
    }
}
