package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
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
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
    public AnchorPane programContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern durationPattern = Pattern.compile("^[0-9 A-z]{2,}$");
    Pattern feePattern = Pattern.compile("[1-9][0-9]*([.][0-9]{2})?$");

    public void initialize() throws SQLException, ClassNotFoundException {

        storeValidation();

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        colProgramId.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colProgramName.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colDuration.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colFee.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");

        loadDateAndTime();

        setItemsToTable(programBO.getAll());
        //setProgramId();
    }

    private void clearText() {
        txtId.setText("");
        txtProgramName.setText("");
        txtDuration.setText("");
        txtFee.setText("");
    }

    private void storeValidation() {
        map.put(txtProgramName, namePattern);
        map.put(txtDuration, durationPattern);
        map.put(txtFee,feePattern);
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
        String programId =  txtId.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        ProgramDTO programDTO = new ProgramDTO(programId,programName,duration,fee);

        if (programBO.add(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            clearText();
            setItemsToTable(programBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String programId =  txtId.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        ProgramDTO programDTO = new ProgramDTO(programId,programName,duration,fee);

        if (programBO.update(programDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            setItemsToTable(programBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (programBO.delete(txtId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            setItemsToTable(programBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void setItemsToTable(ArrayList<ProgramTM> program) {
        ObservableList<ProgramTM> obList = FXCollections.observableArrayList();
        program.forEach(e -> {
            obList.add(
                    new ProgramTM(e.getProgramId(),e.getProgramName(),e.getDuration(),e.getFee()));
        });
        tblProgram.setItems(obList);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) programContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }

    public void idOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String programId = txtId.getText();

        ProgramDTO programDTO = programBO.searchProgram(programId);
        if (programDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(programDTO);
        }
    }
    private void setData(ProgramDTO p) {
        txtId.setText(p.getProgramId());
        txtProgramName.setText(p.getProgramName());
        txtDuration.setText(p.getDuration());
        txtFee.setText(String.valueOf(p.getFee()));
    }

    public void programNameOnAction(ActionEvent actionEvent) {
        txtDuration.requestFocus();
    }

    public void durationOnAction(ActionEvent actionEvent) {
        txtFee.requestFocus();
    }

    public void feeOnAction(ActionEvent actionEvent) {
    }

    public void Program_KeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }
        }
    }
}
