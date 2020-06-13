/**
 *
 */
module module {
    requires com.google.gson;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens sample to javafx.fxml, javafx.graphics;

}