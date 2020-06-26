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
import model.log.SaleLog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class SaleLogsFx {

    //@FXML
  //  private TableColumn<SaleLog, DeliveryStatus> saleLogsDeliveryStatus;

    @FXML
    private TableColumn<SaleLog, LocalDateTime> saleLogsDate;

    @FXML
    private TableView<SaleLog> saleLogsTableView;

    @FXML
    private TableColumn<SaleLog,Double> saleLogsRecievedAmount;

    @FXML
    private TableColumn<SaleLog, String> saleLogsId;
    private static Parent root;
    private static Parent priRoot;

    @FXML
    private TableColumn<SaleLog, String> saleLogsCustomer;
    public  static ObservableList<SaleLog> data = FXCollections.observableArrayList();
    private static ArrayList<SaleLog> saleLogs = new ArrayList<>();
    @FXML
    private TableColumn<SaleLog, Double> saleLogsReducedAmount;


    public static ArrayList<SaleLog> getSaleLogs() {
        return saleLogs;
    }

    public static void setSaleLogs(ArrayList<SaleLog> saleLogs) {
        SaleLogsFx.saleLogs = saleLogs;
    }


    public  void initializeObserverList() {
        data.addAll(saleLogs);
    }

   /* TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));*/



    @FXML
    public void initialize()throws IOException {
      //  saleLogsDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        saleLogsId.setCellValueFactory(new  PropertyValueFactory<>("saleLogId"));
        saleLogsDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        saleLogsReducedAmount.setCellValueFactory(new PropertyValueFactory<>("reducedAmount"));
        saleLogsCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        saleLogsRecievedAmount.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        initializeObserverList();
        saleLogsTableView.getColumns().addAll(saleLogsId,saleLogsDate,saleLogsCustomer,saleLogsRecievedAmount,saleLogsReducedAmount);
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
        SaleLogFx.setCurSaleLog(saleLogsTableView.getSelectionModel().getSelectedItem());
        root= FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));;
        goToPage();

    }

}