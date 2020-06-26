package view.gui;

import controller.menus.CustomerMenu;
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
import javafx.stage.Stage;
import model.accounts.Customer;
import model.log.BuyLog;
import model.off.Sale;
import model.productRelated.Product;
import model.request.Request;
import view.OutputMassageHandler;


import java.io.IOException;

import java.util.Objects;

public class ViewBuyLogFx {
    private static String buyLogId;
    public TableView buyLogTable;
    public Button viewProductB;

    public static void setBuyLogId(String buyLogId) {
        ViewBuyLogFx.buyLogId = buyLogId;
    }

    public static void setPriRoot(Parent priRoot) {
        ViewBuyLogFx.priRoot = priRoot;
    }

    private int score =0;

    @FXML
    private Label scoreMs;

    @FXML
    private TableView<Product> buyLog = new TableView<>();
    @FXML
    private Label buyLogIdLabel;
    @FXML
    private TableColumn<Product, ?> buyLogProductId = new TableColumn<>("id");

    @FXML
    private TableColumn<Product, Product> buyLogProduct = new TableColumn<>("product");

    //private TableColumn<Product, Double> buyLogTotalPrice=new TableColumn<>("totalPrice");

    @FXML
    private TableColumn<Product, Integer> buyLogProductNumber = new TableColumn<>("number");

    @FXML
    private TableColumn<Product, Double> buyLogPrice = new TableColumn<>("price");
    @FXML
    private Label buyLogDate;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private static Parent root;
    private static Parent priRoot;
    // public CustomerMenu ;


    private static BuyLog curBuyLog;
    public static ObservableList<Product> data = FXCollections.observableArrayList();

    public static BuyLog getCurBuyLog() {
        return curBuyLog;
    }


    public static void setCurBuyLog(BuyLog curBuyLog) {
        ViewBuyLogFx.curBuyLog = curBuyLog;
    }

    public void initializeObserverList() {
        data.addAll(curBuyLog.getChosenProduct().keySet());
    }

    @FXML
    public void initialize() throws IOException {
        buyLogIdLabel.setText(curBuyLog.getLogId());
        buyLogDate.setText(curBuyLog.getLocalDateTimeForLog().toString());
        buyLogProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        buyLogProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        buyLogProductNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        //buyLogDate.setCellValueFactory(curBuyLog,);
        buyLogPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        initializeObserverList();
        buyLog.setEditable(true);
        buyLog.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        buyLog.getSelectionModel().setCellSelectionEnabled(true);
        buyLog.getColumns().addAll(buyLogProductId, buyLogProduct, buyLogProductNumber, buyLogPrice);
        buyLog.setItems(data);
    }


    public void showTotalPriceBuyLog(MouseEvent MouseClick) {
        totalPriceLabel.setText(String.valueOf(curBuyLog.calculateHolePrice()));
    }


    private static void goToPage() {
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    public void showLogLocalDate() {
        buyLogDate.setText(curBuyLog.getLocalDateTimeForLog().toString());
    }


    public void viewProductFromBuyLog(MouseEvent mouseEvent) throws IOException {
        if (buyLog.getSelectionModel().getSelectedItem() != null) {
            Product selectedItem = buyLog.getSelectionModel().getSelectedItem();
//            ProductMenuFX.setCurProduct(selectedItem);
            ProductMenuFX.productInPage = selectedItem;
            root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenuFx.fxml")));
            ;
            goToPage();
        }

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


    public void userMenu(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SignUpFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        goToPage();
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
        root = priRoot;
        goToPage();

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void score5(MouseEvent mouseEvent) {
        score = 4;
    }

    public void score4(MouseEvent mouseEvent) {
        score = 4;
    }

    public void score3(MouseEvent mouseEvent) {
        score = 3;
    }

    public void score1(MouseEvent mouseEvent) {
        score = 1;
    }


    public void score(MouseEvent mouseEvent) throws IOException {
        String im = "";
        TablePosition tablePosition = buyLog.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        Product item = buyLog.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        try{
            im = (String) tableColumn.getCellObservableValue(item).getValue();
        }catch (NullPointerException e){
            System.out.println("you Cant Press Here");
        }
        if(score != 0){
            scoreMs.setText(OutputMassageHandler.showCustomerOutput( CustomerMenu.rateProduct( im, score)));
        }else scoreMs.setText("you have to select first");
    }

    public void score2(MouseEvent mouseEvent) {

    }

    public void handleScore(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("ScoreFx.fxml")));
        Scene pageTwoScene = new Scene(root);
        Stage stage= new Stage();
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(pageTwoScene);
        stage.show();
    }


}