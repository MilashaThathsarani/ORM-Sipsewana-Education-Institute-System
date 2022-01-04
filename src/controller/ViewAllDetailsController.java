package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.RegisterTM;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAllDetailsController {
    public TableView tblRegisterList;
    public TableColumn colRegisterId;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableColumn colPhoneNumber;
    public JFXButton btnBack;
    public AnchorPane registerContext;

    public void initialize(){
        try {
            loadAllData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblRegisterList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                RegisterTM temp= (RegisterTM) newValue;
                System.out.println(temp.getRegisterId());
                loadDetailUi(temp.getRegisterId());
            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void loadAllData() throws SQLException, ClassNotFoundException {
       /* ObservableList<RegisterTM> tmList = FXCollections.observableArrayList();
        QueryDAO queryDAO = new QueryDAOImpl();
        for (RegisterTM temp:queryDAO.getAllRegisters()
        ) {
            tmList.add(
                    new RegisterTM(temp.getRegisterId(),
                            temp.getStudentId(),
                            temp.getStudentName(),
                            temp.getAddress(),
                            temp.getAge(),
                            temp.getPhoneNumber()
                    ));

        }
        tblRegisterList.setItems(tmList);*/

    }

    private void loadDetailUi(String registerId) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewRegisterDetails.fxml"));
        Parent load = loader.load();
        ViewRegisterDetailsController controller = loader.getController();
        controller.loadAllData(registerId);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
        stage.centerOnScreen();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) registerContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        window.centerOnScreen();
    }
}
