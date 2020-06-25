package view.gui;

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
    private TableColumn<SaleLog,Double> saleLogsReceivedAmount;

    @FXML
    private TableColumn<SaleLog, String> saleLogsId;
    private static Parent root;
    private static Parent priRoot;

    @FXML
    private TableColumn<SaleLog, String> saleLogsCustomerName;
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
        saleLogsCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        saleLogsReceivedAmount.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        initializeObserverList();
        saleLogsTableView.getColumns().addAll(saleLogsId,saleLogsDate,saleLogsCustomerName,saleLogsReceivedAmount,saleLogsReducedAmount);
        saleLogsTableView.setItems(data);
    }
    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
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
        root= FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));;
        goToPage();

    }
}