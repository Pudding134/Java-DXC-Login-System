package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;

public class MainViewController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button restrictedButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize() {
        // You can initialize anything you need when the main view loads here
    }

    @FXML
    protected void onLogoutButtonClick() {
        // closing the main window and reopening the login window
        stage = (Stage) logoutButton.getScene().getWindow();
        //activate log out function from Utilities class
        Utilities.logout(stage);
    }

    public void setUserInformation(String username) {
        // This method can be used to set user information on the welcome label
        welcomeLabel.setText("Welcome, " + username + "!");

        // Show the restricted page button only if the user is "admin"
        restrictedButton.setVisible("admin".equals(username));
    }

    @FXML
    private void onRestrictedButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("restricted-view.fxml"));
            root = loader.load();

            stage = (Stage) restrictedButton.getScene().getWindow();
            scene = new Scene(root, 500, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
