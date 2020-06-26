package view.gui;

import controller.menus.LoginMenu;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.log.BuyLog;
import model.log.BuyLogShoo;
import model.productRelated.Product;


import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private TableView<BuyLogShoo> buyLogTable = new TableView<>();
    @FXML
    private Label buyLogIdLabel = new Label();
    @FXML
    private TableColumn<BuyLogShoo, String> productName = new TableColumn<>("productName");

    @FXML
    private TableColumn<BuyLogShoo, String> numberOfPro = new TableColumn<>("numberOfProduct");

    @FXML
    private TableColumn<BuyLogShoo,String> LogId = new TableColumn<>("logId");

    @FXML
    private TableColumn<BuyLogShoo,Double> buyLogPrice = new TableColumn<>("price");

    private TableColumn<BuyLogShoo, ImageView> proImage = new TableColumn<>("imageView");

    private TableColumn<BuyLogShoo,Double> hole = new TableColumn<>("Hole Price");

    @FXML
    private Label buyLogDate;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private static Parent root;
    private static Parent priRoot;
   // public CustomerMenu ;
    private static BuyLog curbuylog;
    public  ObservableList<BuyLogShoo> data = FXCollections.observableArrayList();

    public static BuyLog getCurbuylog() {
        return curbuylog;
    }

    public static void setCurBuylog(BuyLog curbuylog) {
        BuyLogFx.curbuylog = curbuylog;
    }

    public void initializeObserverList() throws FileNotFoundException {
        listIni();
        for (BuyLogShoo buyLogShow : BuyLogShoo.list) {
            if (!data.contains(buyLogShow)) {
                data.add(buyLogShow);
            }
        }
    }

    public static void listIni() throws FileNotFoundException {
        for (Product product : curbuylog.getChosenProduct().keySet()) {
            BuyLogShoo buyLogShoo = new BuyLogShoo();
            buyLogShoo.logId = curbuylog.getLogId();
            buyLogShoo.productName = product.getProductName();
            buyLogShoo.numberOfProduct = curbuylog.getChosenProduct().get(product);
            buyLogShoo.price = product.getPrice();
            buyLogShoo.hole = buyLogShoo.price*buyLogShoo.numberOfProduct;
            File file = new File(product.getProductImage());
            Image image = new Image(new FileInputStream(file));
            buyLogShoo.imageView.setImage(image);
            buyLogShoo.imageView.setFitWidth(200);
            buyLogShoo.imageView.setFitHeight(200);
        }
    }

    @FXML
    public void initialize() throws IOException {
        initializeObserverList();
        productName.setCellValueFactory(new PropertyValueFactory<BuyLogShoo, String>("productName"));
        numberOfPro.setCellValueFactory(new PropertyValueFactory<BuyLogShoo,String>("price"));
        LogId.setCellValueFactory(new PropertyValueFactory<BuyLogShoo,String>("logId"));
        buyLogPrice.setCellValueFactory(new PropertyValueFactory<BuyLogShoo,Double>("price"));
        hole.setCellValueFactory(new PropertyValueFactory<BuyLogShoo,Double>("hole"));
        proImage.setCellValueFactory(new PropertyValueFactory<BuyLogShoo,ImageView>("imageView"));

        buyLogTable.getColumns().addAll(productName,numberOfPro,LogId,buyLogPrice,hole,proImage);
        buyLogTable.setItems(data);
    }



    public void increaseAmount(MouseEvent mouseEvent) {
      /*  Product selectedProduct=buyLog.getSelectionModel().getSelectedItem();
       curBuyLog.increaseNumberOfProduct(selectedProduct.getProductId(), 1);
*/

    }

    public void reduceAmount(ActionEvent MouseEvent) {
      //  ObservableList<Product> selectedProduct, allProducts;
       /* Product selectedProduct;
        selectedProduct = buyLog.getSelectionModel().getSelectedItem();
        curBuyLog.reduceNumberOfProduct(selectedProduct.getProductId(), 1);
*/
    }

    public void showTotalPriceBuyLog(MouseEvent MouseClick) {
        totalPriceLabel.setText(String.valueOf(curbuylog.calculateHolePrice()));
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
        if(LoginMenu.isLogin()) {
            PayLogFx.setPreBuyLog(curbuylog);
            root = FXMLLoader.load(Objects.requireNonNull(PayLogFx.class.getClassLoader().getResource("payLogFx.fxml")));
            goToPage();
        }else{
            root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
            goToPage();
        }
    }

    public void viewProductFromBuyLog(MouseEvent mouseEvent) throws IOException {
//        if (buyLog.getSelectionModel().getSelectedItem() != null) {
//            Product selectedItem = buyLog.getSelectionModel().getSelectedItem();
////            ProductMenuFX.setCurProduct(selectedItem);
//            ProductMenuFX.productInPage = selectedItem;
//            root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenuFx.fxml")));
//            ;
//            goToPage();
//        }

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