module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;
    requires java.net.http;
    requires jdk.internal.le;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}