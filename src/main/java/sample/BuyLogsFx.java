package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Seller;
import model.log.BuyLog;
import model.log.DeliveryStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BuyLogsFx {

    @FXML private TableColumn<BuyLog, Seller> buyLogsSeller;
    @FXML private TableView<BuyLog> buyLogs;
    @FXML private TableColumn<BuyLog, DeliveryStatus> buyLogsDeliveryStatus;
    @FXML private TableColumn<BuyLog, LocalDateTime> buyLogsDate;
    //  @FXML private TableColumn<BuyLog,> buyLogsReduceAmount;
    @FXML private TableColumn<BuyLog, ?> buyLogsId;
    //  @FXML private TableColumn<BuyLog, ?> buyLogsRecievedAmount;
    @FXML private Button showScoreButton;

    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    public static ObservableList<BuyLog> data = FXCollections.observableArrayList();

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public static void setAllBuyLogs(ArrayList<BuyLog> allBuyLogs) {
        BuyLogsFx.allBuyLogs = allBuyLogs;
    }

    public static void initializeObserverList() {
        data.clear();
        data.addAll(BuyLog.getAllCustomersLog());
    }

    @FXML
    public void initialize() throws IOException {

        buyLogsId.setCellValueFactory(new PropertyValueFactory<>("buyLogId"));
        buyLogsSeller.setCellValueFactory(new PropertyValueFactory<>("buyLogSeller"));
        buyLogsDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("buyLogDeliveryStatus"));
        buyLogsDate.setCellValueFactory(new PropertyValueFactory<>("buyLogDate"));
        //  buyLogsReduceAmount.setCellValueFactory(new PropertyValueFactory<>("maxDiscountAmount"));
        // buyLogsRecievedAmount.setCellValueFactory(new PropertyValueFactory<>("totalTimesOfUse"));


        initializeObserverList();
        buyLogs.getColumns().addAll(buyLogsId,buyLogsSeller,buyLogsDeliveryStatus,buyLogsDate);
        buyLogs.setEditable(true);
        //  buyLogs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //  buyLogs.getSelectionModel().setCellSelectionEnabled(true);
        buyLogs.setItems(data);
    }




    public void exit(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void viewBuyLogs(MouseEvent mouseEvent) {
    }
}