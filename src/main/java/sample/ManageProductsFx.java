package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.firms.Company;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ManageProductsFx {
  /*  @FXML private TreeTableColumn<Product, Number> productsNoView;
    @FXML private TreeTableColumn<Product, String> productId;
    @FXML private TreeTableColumn<Product, String> productsAditionalDetail;
    @FXML private TreeTableColumn<Product, Number> productsScore;
    @FXML private TreeTableColumn<Product, String> productName;
    @FXML private TreeTableColumn<Product, Double> productNo;
    @FXML private TreeTableColumn<Product, Number> productPrice;
    @FXML private TreeTableView<Product> manageProducts;

    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent root;
    private static ArrayList<Product> allProducts = new ArrayList<>();

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void makeList() {
        list.clear();
        list.addAll(allProducts);
    }

    private void makeTree() {

        productId.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        productsAditionalDetail.setCellValueFactory(new PropertyValueFactory<Product, String>("additionalDetail"));
        productsScore.setCellValueFactory(new PropertyValueFactory<Product, Double>("averageScore"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productNo.setCellValueFactory(new PropertyValueFactory<Product, Double>("numberOfProducts"));
        productsNoView.setCellValueFactory(new PropertyValueFactory<Product, String>("numberOfViews"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Number>("price"));

        makeList();
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        manageProducts.setEditable(true);
        manageProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        manageProducts.getSelectionModel().setCellSelectionEnabled(true);

    }

   */

    public void productsRemove(MouseEvent mouseEvent) {
    }

    public void productsEdit(MouseEvent mouseEvent) {
    }

    public void productsViewBuyers(MouseEvent mouseEvent) {
    }

    public void productsView(MouseEvent mouseEvent) {
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
