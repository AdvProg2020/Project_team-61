package view.gui;

import controller.menus.CustomerMenu;
import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.log.BuyLog;
import model.off.Sale;
import model.productRelated.Product;


import java.io.IOException;

import java.util.Objects;

public class ViewBuyLogFx {
    private static String buyLogId;

    public static void setBuyLogId(String buyLogId) {
        ViewBuyLogFx.buyLogId = buyLogId;
    }

    public static void setPriRoot(Parent priRoot) {
        ViewBuyLogFx.priRoot = priRoot;
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
        Parent curRoot = FXMLLoader.load(Objects.requireNonNull(ViewBuyLogFx.class.getClassLoader().getResource("viewBuyLog.fxml")));
        if (LoginMenu.getLoginAccount() instanceof Seller) {
            SellerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
        } else if (LoginMenu.getLoginAccount() instanceof Manager) {
            ManagerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
        } else if (LoginMenu.getLoginAccount() instanceof Customer) {
            CustomerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        }
        goToPage();
    }

    public void back(ActionEvent actionEvent) {
        root = priRoot;
        goToPage();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        LoginMenu.processLogout();
        root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        goToPage();
    }
}