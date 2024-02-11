module com.heshanthenura.cocktails {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.heshanthenura.cocktails to javafx.fxml;
    exports com.heshanthenura.cocktails;
    exports com.heshanthenura.cocktails.controllers;
    opens com.heshanthenura.cocktails.controllers to javafx.fxml;
}