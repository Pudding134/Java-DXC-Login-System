module dxc.loginsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dxc.loginsystem to javafx.fxml;
    exports dxc.loginsystem;
}