package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    public AnchorPane dashboardContext;
    public JFXButton btnBack;

    public void initialize(){

    }

    private void loadUi(String filename) throws IOException {
        URL resource = getClass().getResource("../view/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(load);
    }

   public void studentRegistrationOnAction(ActionEvent actionEvent) throws IOException {
       Stage window = (Stage) dashboardContext.getScene().getWindow();
       window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/StudentRegistration.fxml"))));
       window.centerOnScreen();
    }

    public void ProgrammsOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Programs.fxml"))));
        window.centerOnScreen();
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Payment.fxml"))));
        window.centerOnScreen();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void viewStudentOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) dashboardContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ViewStudentDetails.fxml"))));
        window.centerOnScreen();
    }
}
