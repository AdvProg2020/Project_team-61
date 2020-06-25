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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.log.SaleLog;
import model.productRelated.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class SaleLogFx {
    @FXML private Label saleLogId;
    @FXML private Label saleLogReceived;
    @FXML private Label saleLogReduced;
    @FXML private Label saleLogCustomerName;
    @FXML private Label saleLogDate;


    @FXML
    private ListView<Product> products;
    public  static ObservableList<Product> data = FXCollections.observableArrayList();
    public static SaleLog curSaleLog;

    public static void setPriRoot(Parent priRoot) {
        SaleLogFx.priRoot = priRoot;
    }

    private static Parent  priRoot;
    private static Parent root;

    public  void initializeObserverList() {
        data.addAll(curSaleLog.getAllSoldProduct());
    }

    public static void setCurSaleLog(SaleLog curSaleLog) {
        SaleLogFx.curSaleLog = curSaleLog;
    }

    @FXML
    public void initialize()throws IOException {

        saleLogId.setText(curSaleLog.getId());
        saleLogCustomerName.setText(curSaleLog.getCustomerName());
        saleLogDate.setText(curSaleLog.getLocalDateTimeForSaleLog().toString());
        saleLogReceived.setText(String.valueOf(curSaleLog.getReceivedAmount()));
        saleLogReduced.setText(String.valueOf(curSaleLog.getReducedAmount()));
        initializeObserverList();
        products.setItems(data);

        //buyLogDate.setCellValueFactory(curBuyLog,)
        //  saleLogReducedAmount.setCellValueFactory(new PropertyValueFactory<>("id"));

        //saleLogs.getColumns().addAll(saleLogNumber,saleLogDate,saleLogProduct,saleLogRecievedAmount,saleLogReducedAmount,saleLogProductNumber);

    }

    public void userMenu(ActionEvent actionEvent) throws IOException {

            if(LoginMenu.getLoginAccount() instanceof Seller){
                root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
            } else if(LoginMenu.getLoginAccount() instanceof Manager){
                root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
            }else if(LoginMenu.getLoginAccount() instanceof Customer){
                root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
            }
            goToPage();

    }

    public void logout(ActionEvent actionEvent) throws IOException {
        LoginMenu.processLogout();
        root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        goToPage();
    }

    public void back(ActionEvent actionEvent) throws IOException {

        root= FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));;
        goToPage();
    }
    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}