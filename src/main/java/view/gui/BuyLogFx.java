package view.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.log.BuyLog;
import model.productRelated.Product;


import java.io.IOException;

import java.util.Objects;

public class BuyLogFx {
    private static String buyLogId;

    public static void setBuyLogId(String buyLogId) {
        BuyLogFx.buyLogId = buyLogId;
    }

    public static void setPriRoot(Parent priRoot) {
        BuyLogFx.priRoot = priRoot;
    }

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

    private static BuyLog curBuyLog;
    public static ObservableList<Product> data = FXCollections.observableArrayList();

    public static BuyLog getCurBuyLog() {
        return curBuyLog;
    }


    public static void setCurBuyLog(BuyLog curBuyLog) {
        BuyLogFx.curBuyLog = curBuyLog;
    }

    public void initializeObserverList() {
        data.addAll(curBuyLog.getChosenProduct().keySet());
    }

    @FXML
    public void initialize() throws IOException {
        buyLogIdLabel.setText(curBuyLog.getId());
        buyLogDate.setText(BuyLog.getLocalDateTimeForLog().toString());
        buyLogProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        buyLogProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        buyLogProductNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        //buyLogDate.setCellValueFactory(curBuyLog,);
        buyLogPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        initializeObserverList();
        buyLog.getColumns().addAll(buyLogProductId, buyLogProduct, buyLogProductNumber, buyLogPrice);
        buyLog.setItems(data);
    }

    public void increaseAmount(MouseEvent mouseEvent) {
        ObservableList<Product> selectedProduct, allProducts;
        allProducts = buyLog.getItems();
        selectedProduct = buyLog.getSelectionModel().getSelectedItems();
        for (Product product1 : selectedProduct) {
            curBuyLog.increaseNumberOfProduct(product1.getProductId(), 1);
        }


    }

    public void reduceAmount(ActionEvent MouseEvent) {
        ObservableList<Product> selectedProduct, allProducts;
        selectedProduct = buyLog.getSelectionModel().getSelectedItems();
        for (Product product1 : selectedProduct) {
            curBuyLog.reduceNumberOfProduct(product1.getProductId(), 1);
        }

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
        buyLogDate.setText(BuyLog.getLocalDateTimeForLog().toString());
    }

    public void purchase(MouseEvent mouseEvent) throws IOException {
        PayLogFx.setPreBuyLog(curBuyLog);
        root = FXMLLoader.load(Objects.requireNonNull(BuyLogFx.class.getClassLoader().getResource("payLogFx.fxml")));
        goToPage();
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


    public void viewProduct(MouseEvent mouseEvent) {
    }

}