module com.example.exam1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.exam1 to javafx.fxml;
    exports com.example.exam1;
}