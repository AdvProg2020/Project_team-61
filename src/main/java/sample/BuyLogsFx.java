package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.log.BuyLog;

public class BuyLogsFx {

    @FXML
    private TableColumn<BuyLog, ?> buyLogsSeller;

    @FXML
    private TableView<BuyLog> buyLogs;

    @FXML
    private TableColumn<BuyLog, ?> buyLogsDeliveryStatus;

    @FXML
    private TableColumn<BuyLog, ?> buyLogsDate;

    @FXML
    private TableColumn<BuyLog, ?> buyLogsReduceAmount;

    @FXML
    private TableColumn<BuyLog, ?> buyLogsId;

    @FXML
    private TableColumn<BuyLog, ?> buyLogsRecievedAmount;
    public static ObservableList<BuyLog> data = FXCollections.observableArrayList();

    public static void initializeObserverList() {
        data.clear();
        data.addAll(BuyLog.getAllCustomersLog());
    }

 /*   @FXML
    public void initialize() throws IOException {

        buyLogsId.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("discountId"));
        buyLogsSeller.setCellValueFactory(new PropertyValueFactory<DiscountCode, Number>("discountAmount"));
        buyLogsDeliveryStatus.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("startOfDiscountPeriod"));
        buyLogsDate.setCellValueFactory(new PropertyValueFactory<DiscountCode,String>("endOfDiscountPeriod"));
        buyLogsReduceAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("maxDiscountAmount"));
        buyLogsRecievedAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("totalTimesOfUse"));


        initializeObserverList();
        buyLogs.getColumns().addAll(buyLogsId,buyLogsSeller,buyLogsDeliveryStatus,buyLogsDate,buyLogsReduceAmount,buyLogsRecievedAmount);
        buyLogs.setEditable(true);
      //  buyLogs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      //  buyLogs.getSelectionModel().setCellSelectionEnabled(true);
        buyLogs.setItems(data);
    }



  */
    public void exit(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }


}
