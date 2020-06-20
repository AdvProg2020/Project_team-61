package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SaleLogsFx {
    
    @FXML
    private TableColumn<?, ?> saleLogsDeliveryStatus;

    @FXML
    private TableColumn<?, ?> saleLogsDate;

    @FXML
    private TableView<?> saleLogsTableView;

    @FXML
    private TableColumn<?, ?> saleLogsRecievedAmount;

    @FXML
    private TableColumn<?, ?> saleLogsId;

    @FXML
    private TableColumn<?, ?> saleLogsCustomer;

    @FXML
    private TableColumn<?, ?> saleLogsReducedAmount;

    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void viewSaleLogs(MouseEvent mouseEvent) {
    }
}
