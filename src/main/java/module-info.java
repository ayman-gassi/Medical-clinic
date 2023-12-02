module com.example.cmedicale {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.cmedicale to javafx.fxml;
    exports com.example.cmedicale;
    exports com.example.cmedicale.controllers;
    opens com.example.cmedicale.controllers to javafx.fxml;
}