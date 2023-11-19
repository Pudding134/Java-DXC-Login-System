package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label notificationMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAuthenticated = authenticate(username, password);
        if (isAuthenticated) {
            notificationMessage.setText("Login successful!");
            // Here you would transition to the main application view
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
                root = loader.load();

                // Set user information on the main view
                MainViewController mainViewController = loader.getController();
                mainViewController.setUserInformation(username);

                // Switch to main application view
                stage = (Stage) (usernameField.getScene().getWindow());
                scene = new Scene(root, 500, 400);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                notificationMessage.setText("Failed to load the main view.");
            }
        }else{
            notificationMessage.setText("Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
        // Placeholder for authentication logic
        if((username.equals("admin") && password.equals("admin")) || (username.equals("willie") && password.equals("willie"))) {
            //notificationMessage.setText("Login successful! Welcome to JavaFX Application!");
            return true;
        }
        return false;
    }
}