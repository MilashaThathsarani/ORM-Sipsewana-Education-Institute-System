package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    public AnchorPane dashboardContext;

    private void loadUi(String filename) throws IOException {
        URL resource = getClass().getResource("../view/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(load);
    }

   /* public void studentRegistrationOnAction(ActionEvent actionEvent) {
        loadUi("");
    }

    public void ProgrammsOnAction(ActionEvent actionEvent) {
        loadUi();
    }

    public void PaymentOnAction(ActionEvent actionEvent) {
        loadUi();
    }*/
}
