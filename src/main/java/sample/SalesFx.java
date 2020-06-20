package sample;


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

    public static ArrayList<Sale> getAllSales() {
        return allSales;
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

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }



}