package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.log.SaleLog;
import model.productRelated.Product;

import java.io.IOException;
import java.time.LocalDateTime;

public class SaleLogFx {
    @FXML
    private TableView<Product> saleLogs;

    @FXML
    private TableColumn<Product, Integer> saleLogNumber ;

    @FXML
    private TableColumn<Product, Integer> saleLogProductNumber;

    @FXML
    private TableColumn<Product,Double> saleLogReducedAmount;

    @FXML
    private TableColumn<Product, Double> saleLogRecievedAmount;

    @FXML
    private TableColumn<Product, Product> saleLogProduct;
    public  static ObservableList<Product> data = FXCollections.observableArrayList();
    SaleLog curSaleLog;

    @FXML
    private TableColumn<Product, LocalDateTime> saleLogDate;
    public  void initializeObserverList() {
        data.addAll(curSaleLog.getAllSoldProduct());
    }
    @FXML
    public void initialize()throws IOException {
        saleLogNumber.setCellValueFactory(new PropertyValueFactory<>("saleLogNumber"));
        saleLogDate.setCellValueFactory(new PropertyValueFactory<>("saleLogDate"));
        //buyLogDate.setCellValueFactory(curBuyLog,);
        saleLogProduct.setCellValueFactory(new PropertyValueFactory<>("SaleLogProduct"));
        saleLogRecievedAmount.setCellValueFactory(new  PropertyValueFactory<>("saleLogReceivedAmount"));
        saleLogReducedAmount.setCellValueFactory(new PropertyValueFactory<>("saleLogReducedAmount"));
        saleLogProductNumber.setCellValueFactory(new PropertyValueFactory<>("saleLogProductNumber"));
        //  saleLogReducedAmount.setCellValueFactory(new PropertyValueFactory<>("id"));
        initializeObserverList();
        saleLogs.getColumns().addAll(saleLogNumber,saleLogDate,saleLogProduct,saleLogRecievedAmount,saleLogReducedAmount,saleLogProductNumber);
        saleLogs.setItems(data);
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}