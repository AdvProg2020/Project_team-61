module Project.team {

    requires javafx.controls;
    requires  javafx.fxml;
    requires com.google.gson;
    requires  javafx.base;
    requires  javafx.archetype.simple;
    requires javafx.baseEmpty;

    opens sample to javafx.fxml,javafx.controls,javafx.graphics,javafx.base;
    opens model.accounts to javafx.base;
}