package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.log.BuyLog;
import model.productRelated.Product;

public class BuyLogFx {
    @FXML
    private TableView<BuyLog> buyLog;

    @FXML
    private TableColumn<BuyLog, ?> buyLogProductNumber;

    @FXML
    private TableColumn<BuyLog, Product> buyLogProduct;

    @FXML
    private TableColumn<BuyLog, ?> buyLogDate;

    @FXML
    private TableColumn<BuyLog, ?> buyLogRecievedAmount;

    @FXML
    private TableColumn<BuyLog, ?> buyLogReduceAmount;
    private static BuyLog curBuyLog;
    public static ObservableList<BuyLog> data = FXCollections.observableArrayList();

    public static BuyLog getCurBuyLog() {
        return curBuyLog;
    }

    public static void setCurBuyLog(BuyLog curBuyLog) {
        BuyLogFx.curBuyLog = curBuyLog;
    }

    /*  public static void initializeObserverList() {
        data.clear();
        data.addAll(ProductMenu.getBuyLog());
    }

    @FXML
    public void initialize() throws IOException {

        buyLogProduct.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("discountId"));
        buyLogProductNumber.setCellValueFactory(new PropertyValueFactory<DiscountCode, Number>("discountAmount"));
        buyLogDate.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("startOfDiscountPeriod"));
        buyLogReduceAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,String>("endOfDiscountPeriod"));
        buyLogRecievedAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("maxDiscountAmount"));


        initializeObserverList();
        buyLog.getColumns().addAll(buyLogProduct,buyLogProductNumber,buyLogDate,buyLogReduceAmount,buyLogRecievedAmount);
        buyLog.setEditable(true);
       // buyLog.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      //  buyLog.getSelectionModel().setCellSelectionEnabled(true);
        buyLog.setItems(data);
    }


   */

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }


}
