module com.heshanthenura.cocktails {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.heshanthenura.cocktails to javafx.fxml;
    exports com.heshanthenura.cocktails;
}