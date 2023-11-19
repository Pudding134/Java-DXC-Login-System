package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void initialize() {
        // You can initialize anything you need when the main view loads here
    }

    @FXML
    protected void onLogoutButtonClick() {
        // This method would handle the logout process
        // For example, closing the main window and reopening the login window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            root = loader.load();

            // Switch back to application login view
            stage = (Stage) logoutButton.getScene().getWindow();
            scene = new Scene(root, 500, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserInformation(String username) {
        // This method can be used to set user information on the welcome label
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}
