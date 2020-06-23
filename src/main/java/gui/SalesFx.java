package gui;


import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.off.Sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class SalesFx {

    @FXML private TableColumn<Sale, Number> saleAmount;
    @FXML private TableColumn<Sale, Seller> saleSeller;
    @FXML private TableColumn<Sale, String> saleId;
    @FXML private TableColumn<Sale, Date> saleEnd;
    @FXML private TableColumn<Sale, Date> saleStart;
    @FXML private TableColumn<Sale, String> saleStatus;
    @FXML private TableView<Sale> sales;
    @FXML private Label salesMs;


    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent root;
    private static ArrayList<Sale> allSales = new ArrayList<>();
    private static Parent priRoot;
    public static ArrayList<Sale> getAllSales() {
        return allSales;
    }

    public static void setPriRoot(Parent priRoot) {
        SalesFx.priRoot = priRoot;
    }

    public static void setAllSales(ArrayList<Sale> allSales) {
        SalesFx.allSales = allSales;
    }

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void makeList() {
        list.clear();
        list.addAll(allSales);
    }

    private void makeTree() {
        saleId.setCellValueFactory(new PropertyValueFactory<Sale, String>("username"));
        saleStart.setCellValueFactory(new PropertyValueFactory<Sale, Date>("name"));
        saleEnd.setCellValueFactory(new PropertyValueFactory<Sale, Date>("lastname"));
        saleStatus.setCellValueFactory(new PropertyValueFactory<Sale, String>("birthdayDate"));
        saleSeller.setCellValueFactory(new PropertyValueFactory<Sale, Seller>("phoneNo"));
        saleAmount.setCellValueFactory(new PropertyValueFactory<Sale, Number>("email"));

        makeList();
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        sales.setEditable(true);
        sales.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        sales.getSelectionModel().setCellSelectionEnabled(true);

        sales.setItems(list);
    }


    public void add(MouseEvent mouseEvent) throws IOException {
      //  if(sales.getSelectionModel().getSelectedItem() != null) {
          //  Sale sale = sales.getSelectionModel().getSelectedItem();
            root = FXMLLoader.load(Objects.requireNonNull(AddSaleFx.class.getClassLoader().getResource("addSaleFx.fxml")));
            goToPage();
      //  }else salesMs.setText("you have to select first");
    }

    public void edit(MouseEvent mouseEvent) throws IOException {
        //if(sales.getSelectionModel().getSelectedItem() != null) {
         //   Sale sale = sales.getSelectionModel().getSelectedItem();
            root = FXMLLoader.load(Objects.requireNonNull(AddSaleFx.class.getClassLoader().getResource("addSale.fxml")));
            goToPage();
       // }else salesMs.setText("you have to select first");
    }

    public void viewSale(MouseEvent mouseEvent) throws IOException {
        if(sales.getSelectionModel().getSelectedItem() != null) {
           Sale sale = sales.getSelectionModel().getSelectedItem();

        root = FXMLLoader.load(Objects.requireNonNull(AddSaleFx.class.getClassLoader().getResource("addSale.fxml")));
        goToPage();
         }else salesMs.setText("you have to select first");

    }
    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }


    public void userMenu(ActionEvent actionEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
        } else if(LoginMenu.getLoginAccount() instanceof Manager){
            root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
        }else if(LoginMenu.getLoginAccount() instanceof Customer){
            root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        }
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
