package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramController {
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    public Label txtDate;
    public Label txtTime;
    public JFXButton btnBack;
    public JFXTextField txtId;
    public JFXTextField txtSearch;
    public TableView<ProgramTM> tblProgram;
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

    public void initialize() throws SQLException, ClassNotFoundException {

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        setItemsToTable(programBO.getAll());
    }


    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String programId =  txtId.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        ProgramDTO programDTO = new ProgramDTO(programId,programName,duration,fee);

        if (programBO.add(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            setItemsToTable(programBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    private void setItemsToTable(ArrayList<ProgramTM> program) {
        ObservableList<ProgramTM> obList = FXCollections.observableArrayList();
        program.forEach(e -> {
            obList.add(
                    new ProgramTM(e.getProgramId(),e.getProgramName(),e.getDuration(),e.getFee()));
        });
        tblProgram.setItems(obList);
    }

    public void backOnAction(ActionEvent actionEvent) {
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
