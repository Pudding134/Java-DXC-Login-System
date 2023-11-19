package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainViewController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutButton;

    public void initialize() {
        // You can initialize anything you need when the main view loads here
    }

    @FXML
    protected void onLogoutButtonClick() {
        // This method would handle the logout process
        // For example, closing the main window and reopening the login window
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        // You can place logic here to switch back to the login view
    }

    public void setUserInformation(String username) {
        // This method can be used to set user information on the welcome label
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}
