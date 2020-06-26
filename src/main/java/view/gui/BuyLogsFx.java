package view.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.log.BuyLog;
import model.log.BuyLogShow;
import model.productRelated.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BuyLogsFx {

    @FXML private TableView<BuyLogShow> buyLogs = new TableView<>();
    @FXML private TableColumn<BuyLogShow, LocalDateTime> buyLogsDate = new TableColumn<>();
    @FXML private TableColumn<BuyLogShow, String> buyLogsId = new TableColumn<>("id");
    @FXML private TableColumn<BuyLogShow, Double> buyLogsTotalPaidAmount = new TableColumn<>("price");
    @FXML private Button showScoreButton;
    @FXML private static Parent root;
    @FXML private static Parent priRoot;
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();

    public static ObservableList<BuyLogShow> data = FXCollections.observableArrayList();

    public static Parent getPriRoot() {
        return priRoot;
    }

    public static void setPriRoot(Parent priRoot) {
        BuyLogsFx.priRoot = priRoot;
    }

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public static void setAllBuyLogs(ArrayList<BuyLog> allBuyLogs) {
        BuyLogsFx.allBuyLogs = allBuyLogs;
    }


    public static void initializeObserverList() throws FileNotFoundException {
        listIni();
        for (BuyLogShow buyLogShow : BuyLogShow.list) {
            if (!data.contains(buyLogShow)) {
                data.add(buyLogShow);
            }
        }
    }

    public static void listIni() throws FileNotFoundException {
        for (BuyLog buyLog : BuyLog.getAllCustomersLog()) {
            BuyLogShow buyLogShow = new BuyLogShow();
            buyLogShow.holePrice = buyLog.holePrice;
            buyLogShow.price = buyLog.price;
            buyLogShow.buyLogId = buyLog.getLogId();

        }

    }


    @FXML
    public void initialize() throws IOException {
        buyLogsId.setCellValueFactory(new PropertyValueFactory<BuyLogShow, String>("buyLogId"));
        buyLogsTotalPaidAmount.setCellValueFactory(new PropertyValueFactory<BuyLogShow,Double>("price"));


        initializeObserverList();
        buyLogs.getColumns().addAll(buyLogsId,buyLogsTotalPaidAmount);
        buyLogs.setItems(data);
    }


    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
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

    public void showOrdersFromBuyLog(MouseEvent mouseEvent) throws IOException {
        if (buyLogs.getSelectionModel().getSelectedItem()!=null){
//            BuyLog buyLog=buyLogs.getSelectionModel().getSelectedItem();

            root = FXMLLoader.load(Objects.requireNonNull(BuyLogFx.class.getClassLoader().getResource("buyLogFx.fxml")));;
            goToPage();
        }
    }

    public void clickedColumn(MouseEvent mouseEvent) throws IOException {
        TablePosition tablePosition = buyLogs.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        BuyLogShow item = buyLogs.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();

        try {
            String im = (String) tableColumn.getCellObservableValue(item).getValue();
            if (BuyLog.getLogWithId(im) instanceof BuyLog){
                BuyLog buyLog = (BuyLog) BuyLog.getLogWithId(im);
                HashMap<Product, Integer> boughtPro = buyLog.getChosenProduct();
                BuyLogFx.setCurBuylog(buyLog);
                root = FXMLLoader.load(Objects.requireNonNull(BuyLogFx.class.getClassLoader().getResource("buyLogFx.fxml")));
                goToPage();

            }

        } catch (NullPointerException e) {
            System.out.println("you cant press here");
        }
    }
}