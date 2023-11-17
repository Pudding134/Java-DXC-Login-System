module dxc.loginsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens dxc.loginsystem to javafx.fxml;
    exports dxc.loginsystem;
}