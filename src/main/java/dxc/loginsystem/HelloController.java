package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label notificationMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAuthenticated = authenticate(username, password);
        if (isAuthenticated) {
            notificationMessage.setText("Login successful!");
            // Here you would transition to the main application view
        } else {
            notificationMessage.setText("Invalid username or password.");
        }    }

    private boolean authenticate(String username, String password) {
        // Placeholder for authentication logic
        if(username.equals("admin") && password.equals("admin")) {
            notificationMessage.setText("Login successful! Welcome to JavaFX Application!");
            return true;
        }
        return false;
    }
}