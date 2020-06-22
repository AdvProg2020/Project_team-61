package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.log.BuyLog;
import model.productRelated.Product;


import java.io.IOException;

import java.util.Objects;

public class BuyLogFx {
    @FXML
    private TableView<Product> buyLog = new TableView<>();

    @FXML
    private TableColumn<BuyLog, Double> buyLogProductId = new TableColumn<>();

    @FXML
    private TableColumn<BuyLog, Product> buyLogProduct = new TableColumn<>();
    @FXML
    private TableColumn<BuyLog, Double> buyLogTotalPrice = new TableColumn<>();

    @FXML
    private TableColumn<BuyLog, Integer> buyLogProductNumber = new TableColumn<>();

    @FXML
    private TableColumn<BuyLog, Double> buyLogPrice = new TableColumn<>();
    @FXML
    private static Label buyLogDate;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Button addButton = new Button("+");
    @FXML
    private Button reduceButton = new Button("-");
    @FXML
    private static Parent root;

    private BuyLog curBuyLog;
    public static ObservableList<Product> data = FXCollections.observableArrayList();

    public BuyLog getCurBuyLog() {
        return curBuyLog;
    }

    public void setCurBuyLog(BuyLog curBuyLog) {
        this.curBuyLog = curBuyLog;
    }

    public void initializeObserverList() {
        data.addAll(curBuyLog.getChosenProduct().keySet());
    }

    @FXML
    public void initialize() throws IOException {
        buyLogProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        buyLogProductNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        //buyLogDate.setCellValueFactory(curBuyLog,);
        buyLogPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyLogTotalPrice.setCellValueFactory(new PropertyValueFactory<>("total Price"));
        buyLogProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        initializeObserverList();
        // buyLog.getColumns().addAll(buyLogTotalPrice,buyLogProduct,buyLogProductNumber,buyLogPrice,buyLogTotalPrice);
        buyLog.setItems(data);
    }

    public void increaseAmount(MouseEvent mouseEvent, Product product) {
        ObservableList<Product> selectedProduct, allProducts;
        selectedProduct = buyLog.getSelectionModel().getSelectedItems();
        for (Product product1 : selectedProduct) {
            curBuyLog.increaseNumberOfProduct(product1.getProductId(), 1);
        }


    }

    public static void showPage(Stage stage, Scene scene) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("gui/fxFile/sample.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reduceAmount(ActionEvent MouseEvent, Product product) {
        ObservableList<Product> selectedProduct, allProducts;
        selectedProduct = buyLog.getSelectionModel().getSelectedItems();
        for (Product product1 : selectedProduct) {
            curBuyLog.reduceNumberOfProduct(product1.getProductId(), 1);
        }

    }

    public void showTotalPrice() {
        totalPriceLabel.setText(String.valueOf(curBuyLog.calculateHolePrice()));
    }


    private static void goToPage() {
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    public static void showLogLocalDate() {
        buyLogDate.setText(BuyLog.getLocalDateTimeForLog().toString());
    }

    public void pay(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SignUpFx.class.getClassLoader().getResource("payLogFx.fxml")));
        goToPage();
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
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


}