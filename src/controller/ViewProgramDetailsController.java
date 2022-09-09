package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewProgramDetailsController {
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    public JFXButton btnBack;
    public TableView tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public AnchorPane viewContext;

    public void initialize() throws SQLException, ClassNotFoundException {

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        colProgramId.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colProgramName.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colDuration.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colFee.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");


        setItemsToTable(programBO.getAll());
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
        Stage window = (Stage) viewContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }
}
