package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    public AnchorPane dashboardContext;

    public void initialize(){

    }

    private void loadUi(String filename) throws IOException {
        URL resource = getClass().getResource("../view/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(load);
    }

   public void studentRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("StudentRegistration");
    }

    public void ProgrammsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("Programs");
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("Payment");
    }
}
