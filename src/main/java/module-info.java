module com.example.net_storage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires io.netty.transport;
    requires io.netty.buffer;


    opens com.example.net_storage to javafx.fxml;
    exports com.example.net_storage;
}