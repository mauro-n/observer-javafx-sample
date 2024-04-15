module org.example.observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.observer to javafx.fxml;
    exports org.example.observer;
    exports org.example.observer.ui;
    opens org.example.observer.ui to javafx.fxml;
    exports org.example.observer.controller;
    opens org.example.observer.controller to javafx.fxml;
}