package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.RegisterBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import dto.RegistrationDTO;
import entity.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.ProgramTM;
import view.tm.RegisterTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewAllDetailsController {
    private final RegisterBO registerBO = (RegisterBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER);
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    public TableView tblRegisterList;
    public TableColumn colRegisterId;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableColumn colPhoneNumber;
    public JFXButton btnBack;
    public AnchorPane registerContext;
    public TableColumn colPayment;

    public void initialize() {

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRegisterId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        colAddress.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colRegisterId.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colPayment.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colStudentId.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        colStudentName.setStyle("-fx-border-color: #860a0a;-fx-table-cell-border-color:#860a0a;");
        try {
            loadAllDetails();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllDetails() throws SQLException, ClassNotFoundException {
        tblRegisterList.getItems().clear();
        ObservableList<RegisterTM> observableList= FXCollections.observableArrayList();
        List<Registration> allRegisters = registerBO.getAllForAll();

        for (Registration temp : allRegisters){
            observableList.add(new RegisterTM(
                    temp.getRegisterId(),
                    temp.getStudent().getStudentId(),
                    temp.getStudent().getStudentName(),
                    temp.getStudent().getAddress(),
                    temp.getPayment()
                    ));
        }
        tblRegisterList.setItems(observableList);
    }
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) registerContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }
}
