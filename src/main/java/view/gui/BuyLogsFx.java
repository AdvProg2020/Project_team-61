package view.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.log.BuyLog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class BuyLogsFx {

  //  @FXML private TableColumn<BuyLog, Seller> buyLogsSeller;
    @FXML private TableView<BuyLog> buyLogs;
  //  @FXML private TableColumn<BuyLog, DeliveryStatus> buyLogsDeliveryStatus;
    @FXML private TableColumn<BuyLog, LocalDateTime> buyLogsDate;
    //  @FXML private TableColumn<BuyLog,> buyLogsReduceAmount;
    @FXML private TableColumn<BuyLog, String> buyLogsId;
    @FXML private TableColumn<BuyLog, Double> buyLogsTotalPaidAmount;
   // @FXML private TableColumn<BuyLog, ?> buyLogsName;
    //  @FXML private TableColumn<BuyLog, ?> buyLogsRecievedAmount;
    @FXML private Button showScoreButton;
    @FXML private static Parent root;
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    public static ObservableList<BuyLog> data = FXCollections.observableArrayList();

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public static void setAllBuyLogs(ArrayList<BuyLog> allBuyLogs) {
        BuyLogsFx.allBuyLogs = allBuyLogs;
    }
   /* public void makeTree() throws IOException {
        discountId.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("discountId"));
        discountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode, Number>("discountAmount"));
        discountStart.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("startOfDiscountPeriod"));
        discountEnd.setCellValueFactory(new PropertyValueFactory<DiscountCode,String>("endOfDiscountPeriod"));
        maxDiscountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("maxDiscountAmount"));
        discountTotalTime.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("totalTimesOfUse"));


        data.clear();
        data.addAll(discounts);
        discountCodes.setEditable(true);
        discountCodes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        discountCodes.getSelectionModel().setCellSelectionEnabled(true);
        discountCodes.setItems(data);
    }
    public void edit(MouseEvent mouseEvent) {
        if(discountCodes.getSelectionModel().getSelectedItem() != null) {
            DiscountCode discountCode = discountCodes.getSelectionModel().getSelectedItem();
        }
    }*/

    public static void initializeObserverList() {
        data.clear();
        data.addAll(allBuyLogs);
    }

    @FXML
    public void initialize() throws IOException {

        buyLogsId.setCellValueFactory(new PropertyValueFactory<>("buyLogId"));
      //  buyLogsSeller.setCellValueFactory(new PropertyValueFactory<>("buyLogSeller"));
     //   buyLogsDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("buyLogDeliveryStatus"));
        buyLogsDate.setCellValueFactory(new PropertyValueFactory<>("buyLogDate"));
        buyLogsTotalPaidAmount.setCellValueFactory(new PropertyValueFactory<>("totalPaidAmount"));
        initializeObserverList();
        buyLogs.getColumns().addAll(buyLogsId,buyLogsDate);
        buyLogs.setEditable(true);
        buyLogs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        buyLogs.getSelectionModel().setCellSelectionEnabled(true);
        buyLogs.setItems(data);
    }


    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
    public void showOrderFromBuyLog(MouseEvent mouseEvent) throws IOException {
        if (buyLogs.getSelectionModel().getSelectedItem()!=null){
            BuyLog buyLog=buyLogs.getSelectionModel().getSelectedItem();
            BuyLogFx.setCurBuyLog(buyLog);
            root = FXMLLoader.load(Objects.requireNonNull(BuyLogFx.class.getClassLoader().getResource("buyLogFx.fxml")));;
            goToPage();
        }


    }
    public void rateProductFromBuyLogs(MouseEvent mouseEvent){

    }



    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void back(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void rateProduct(MouseEvent mouseEvent) {

    }

}