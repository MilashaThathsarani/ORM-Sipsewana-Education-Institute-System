package controller;

import bo.BoFactory;
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
import view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewStudentDetailsController {
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    public AnchorPane viewContext;
    public TableView tblStudent;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colBirthDay;
    public TableColumn colAge;
    public TableColumn colGender;
    public TableColumn colPhoneNumber;
    public TableColumn colEducation;
    public JFXButton btnBack;

    public void initialize() throws SQLException, ClassNotFoundException {
        setItemsToTable(studentBO.getAll());

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBirthDay.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEducation.setCellValueFactory(new PropertyValueFactory<>("education"));
    }

    private void setItemsToTable(ArrayList<StudentTM> student) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        student.forEach(e -> {
            obList.add(
                    new StudentTM(e.getStudentId(),e.getStudentName(),e.getAddress(),e.getBirthday(),e.getAge(),e.getGender(),e.getPhoneNumber(),e.getEducation()));
        });
        tblStudent.setItems(obList);
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) viewContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }
}
