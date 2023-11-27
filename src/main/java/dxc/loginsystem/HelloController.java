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

    private UserDAO userDAO = new UserDAO();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userDAO.getUser(username);
        if (user == null) {
            notificationMessage.setText("Login failed: User not found!");
        } else {
            if (BCrypt.checkpw(password, user.getPassword())) {
                //notificationMessage.setText("Login successful! Welcome to JavaFX Application!");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
                    root = loader.load();

                    // Set user information on the main view
                    MainViewController mainViewController = loader.getController();
                    mainViewController.setUserInformation(user);

                    // Switch to main application view
                    stage = (Stage) (usernameField.getScene().getWindow());
                    scene = new Scene(root, 500, 400);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    notificationMessage.setText("Failed to load the main view.");
                }
            } else {
                notificationMessage.setText("Invalid username or password.");
            }
        }
    }
}