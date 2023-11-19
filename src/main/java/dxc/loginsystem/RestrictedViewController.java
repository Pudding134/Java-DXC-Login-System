package dxc.loginsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class RestrictedViewController{
    @FXML
    private Label restrictedLabel;
    @FXML
    private Button restrictedLogoutButton;

    @FXML
    protected void onLogoutButtonClick() {
        // closing the main window and reopening the login window
        Stage stage = (Stage) restrictedLogoutButton.getScene().getWindow();
        //activate log out function from Utilities class
        Utilities.logout(stage);
    }
}
