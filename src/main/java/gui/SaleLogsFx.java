package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.log.DeliveryStatus;
import model.log.SaleLog;

import java.io.IOException;
import java.time.LocalDateTime;

public class SaleLogsFx {

    @FXML
    private TableColumn<SaleLog, DeliveryStatus> saleLogsDeliveryStatus;

    @FXML
    private TableColumn<SaleLog, LocalDateTime> saleLogsDate;

    @FXML
    private TableView<SaleLog> saleLogsTableView;

    @FXML
    private TableColumn<SaleLog,Double> saleLogsRecievedAmount;

    @FXML
    private TableColumn<SaleLog, String> saleLogsId;

    @FXML
    private TableColumn<SaleLog, String> saleLogsCustomerName;
    public  static ObservableList<SaleLog> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SaleLog, Double> saleLogsReducedAmount;
    public  void initializeObserverList() {
        data.addAll(SaleLog.getAllSellersLog());
    }

   /* TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));*/


    @FXML
    public void initialize()throws IOException {
        saleLogsDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        saleLogsDate.setCellValueFactory(new PropertyValueFactory<>("number"));
        saleLogsReducedAmount.setCellValueFactory(new PropertyValueFactory<>("reducedAmount"));
        saleLogsCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        saleLogsId.setCellValueFactory(new  PropertyValueFactory<>("saleLogId"));
        saleLogsRecievedAmount.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        initializeObserverList();
        saleLogsTableView.getColumns().addAll(saleLogsId,saleLogsDate,saleLogsDeliveryStatus,saleLogsCustomerName,saleLogsRecievedAmount,saleLogsReducedAmount);
        saleLogsTableView.setItems(data);
    }
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