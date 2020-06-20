package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SaleLogFx {
    @FXML
    private TableView<?> saleLogs;

    @FXML
    private TableColumn<?, ?> saleLogNumber;

    @FXML
    private TableColumn<?, ?> saleLogProductNumber;

    @FXML
    private TableColumn<?, ?> saleLogReducedAmount;

    @FXML
    private TableColumn<?, ?> saleLogRecievedAmount;

    @FXML
    private TableColumn<?, ?> saleLogProduct;

    @FXML
    private TableColumn<?, ?> saleLogDate;
    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}
