package dxc.loginsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Utilities {

    public static void logout(Stage stage) {
        // This method would handle the logout process
        // Create a confirmation dialog
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout Confirmation");
        logoutAlert.setHeaderText("Logging Out");
        logoutAlert.setContentText("Do you want to proceed with logout and return to the login screen?");

        // Show the dialog and capture the user's response
        Optional<ButtonType> result = logoutAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicked OK, then logout
            // closing the main window and reopening the login window
            try {
                FXMLLoader loader = new FXMLLoader(Utilities.class.getResource("hello-view.fxml"));
                Parent root = loader.load();

                // Switch back to application login view
                Scene scene = new Scene(root, 500, 400);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
