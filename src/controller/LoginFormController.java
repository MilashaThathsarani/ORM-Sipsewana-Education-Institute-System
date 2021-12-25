package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane logInContext;

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
            if (txtUserName.getText().equalsIgnoreCase("a") && txtPassword.getText().equalsIgnoreCase("1")) {
                Stage window = (Stage) logInContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
                window.centerOnScreen();
            }
        }
}
