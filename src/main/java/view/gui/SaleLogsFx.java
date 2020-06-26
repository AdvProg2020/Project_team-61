package view.gui;

import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.log.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SaleLogsFx {

    //@FXML
  //  private TableColumn<SaleLog, DeliveryStatus> saleLogsDeliveryStatus;

    @FXML
    private TableColumn<SaleLogShow, Date> saleLogsDate;

    @FXML
    private TableView<SaleLogShow> saleLogsTableView = new TableView<>();

    @FXML
    private TableColumn<SaleLogShow, String> saleLogsId = new TableColumn<SaleLogShow, String>();
    private static Parent root;
    private static Parent priRoot;

    @FXML
    private TableColumn<SaleLogShow, String> getSaleLogsId = new TableColumn<>();
    public  static ObservableList<SaleLogShow> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SaleLogShow, LocalDateTime> date = new TableColumn<>();



   /* TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));*/


    public static void initializeObserverList() throws FileNotFoundException {
        listIni();
        for (SaleLogShow buyLogShow : SaleLogShow.list) {
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

    public static void setSaleLogs(ArrayList<SaleLog> saleLogsHistory) {

    }


    @FXML
    public void initialize() throws IOException {
        saleLogsId.setCellValueFactory(new PropertyValueFactory<SaleLogShow, String>("saleLogId"));
      //  saleLogsDate.setCellValueFactory(new PropertyValueFactory<SaleLogShow, Date>("localDateTime"));
        saleLogsDate.setCellValueFactory(new PropertyValueFactory<SaleLogShow, Date>("date"));
        initializeObserverList();
        saleLogsTableView.getColumns().addAll(saleLogsId,saleLogsDate);
        saleLogsTableView.setItems(data);

    }



    public void logout(ActionEvent actionEvent) throws IOException {
        LoginMenu.processLogout();
        root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        goToPage();
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

    public static void setPriRoot(Parent priRoot) {
        SaleLogsFx.priRoot = priRoot;
    }

    public void back(ActionEvent actionEvent) {
        root=priRoot;
        goToPage();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
    public void viewSaleLogFromAllSaleLogs(MouseEvent mouseEvent) throws IOException {
        SaleLogFx.setPriRoot(root);
//        SaleLogFx.setCurSaleLog(saleLogsTableView.getSelectionModel().getSelectedItem());
        root= FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));;
        goToPage();

    }

}