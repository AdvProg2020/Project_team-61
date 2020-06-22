package gui;

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
import model.log.DeliveryStatus;
import model.log.SaleLog;

import java.io.IOException;
import java.time.LocalDateTime;
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

    @FXML
    private TableColumn<SaleLog, String> saleLogsCustomerName;
    public  static ObservableList<SaleLog> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SaleLog, Double> saleLogsReducedAmount;
    public  void initializeObserverList() {
        data.addAll(SaleLog.getAllSellersLog());
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
        saleLogsRecievedAmount.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        initializeObserverList();
        saleLogsTableView.getColumns().addAll(saleLogsId,saleLogsDate,saleLogsCustomerName,saleLogsRecievedAmount,saleLogsReducedAmount);
        saleLogsTableView.setItems(data);
    }
    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
    public void viewSaleLogFromAllSaleLogs(MouseEvent mouseEvent) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));;
        goToPage();

    }
}