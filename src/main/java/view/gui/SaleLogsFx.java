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

import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.log.*;
import model.productRelated.Product;
import model.productRelated.ProductInMenusShow;

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
    private TableColumn<SaleLogShow, Date> saleLogsDate = new TableColumn<>("Date");

    @FXML
    private TableView<SaleLogShow> saleLogsTableView = new TableView<>();

    @FXML
    private TableColumn<SaleLogShow, String> saleLogsId = new TableColumn<SaleLogShow, String>("SaleLog ID");
    private static Parent root;
    private static Parent priRoot;
    private static ArrayList<SaleLog> allSaleLogs = new ArrayList<>();

    public static ArrayList<SaleLog> getAllSaleLogs() {
        return allSaleLogs;
    }

    public static void setAllSaleLogs(ArrayList<SaleLog> allSaleLogs) {
        SaleLogsFx.allSaleLogs = allSaleLogs;
    }

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
            data.clear();
            if (!data.contains(buyLogShow)) {
                data.add(buyLogShow);
            }
        }
    }

    public static void listIni() throws FileNotFoundException {
        for (SaleLog saleLog : allSaleLogs) {
            SaleLogShow saleLogShow = new SaleLogShow();
            saleLogShow.saleLogId = saleLog.getLogId();
            saleLogShow.localDateTime = saleLog.localDateTimeForLog;
        }
    }




    @FXML
    public void initialize() throws IOException {
        saleLogsId.setCellValueFactory(new PropertyValueFactory<SaleLogShow, String>("saleLogId"));
        //  saleLogsDate.setCellValueFactory(new PropertyValueFactory<SaleLogShow, Date>("localDateTime"));
        saleLogsDate.setCellValueFactory(new PropertyValueFactory<SaleLogShow, Date>("localDateTime"));
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

    public void clickedColumn(MouseEvent mouseEvent) throws IOException {
        TablePosition tablePosition = saleLogsTableView.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        SaleLogShow item = saleLogsTableView.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();

        try {
            Parent curRoot = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
            String im = (String) tableColumn.getCellObservableValue(item).getValue();
            SaleLog saleLog = (SaleLog) Log.getLogWithId(im);
            System.out.println(saleLog.localDateTimeForLog);
            SaleLogFx.setPriRoot(curRoot);
            SaleLogFx.setCurSaleLog(saleLog);
            root = FXMLLoader.load(Objects.requireNonNull(SalesFx.class.getClassLoader().getResource("saleLogFx.fxml")));
            goToPage();

        } catch (NullPointerException e) {
            System.out.println("you cant press here");
        }
    }

}