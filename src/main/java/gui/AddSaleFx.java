package gui;

import controller.menus.LoginMenu;
import controller.menus.ManagerMenu;
import controller.menus.SellerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Seller;
import model.productRelated.Product;
import view.OutputMassageHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class AddSaleFx {

    @FXML
    private TextField saleAmount;
    @FXML
    private Label saleIdAlertLabel;
    @FXML
    private TextField startSaleDatePicker;
    @FXML
    private TableColumn<Product, String> addSaleProduct;
    @FXML
    private TextField endSaleDatePicker;
    @FXML
    private TextField saleIdTextField;
    @FXML
    private TableView<Product> addSaleProducts;
    @FXML
    private Label endSaleAlertLabel;
    @FXML
    private Label startSaleAlertLabel;
    @FXML
    private Label saleAmountAlertLabel;

    private boolean finish = false;
    private boolean oneProduct = false;
    public ObservableList list = FXCollections.observableArrayList();
    ArrayList<String> productsId = new ArrayList<>();
    private static Parent priRoot;
    private static Parent root;

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public void makeTree() {
        addSaleProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        if (LoginMenu.getLoginAccount() instanceof Seller) {
            Seller seller = (Seller) LoginMenu.getLoginAccount();
            list.clear();
            list.addAll(seller.getAllProduct());
            addSaleProducts.setEditable(true);
            addSaleProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            addSaleProducts.getSelectionModel().setCellSelectionEnabled(true);
            addSaleProducts.setItems(list);


        }
    }

        public void createSale (MouseEvent mouseEvent) throws IOException, ParseException {
            if (SellerMenu.getCreate() == 0) {
                saleIdAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(saleIdTextField.getText(), 0)));
            }
            if (SellerMenu.getCreate() == 1) {
                startSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(startSaleDatePicker.getText(), 1)));
                if (ManagerMenu.getDetailMenu() == 2) {
                    endSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(endSaleDatePicker.getText(), 2)));
                }
                if (ManagerMenu.getDetailMenu() == 3) {
                    saleAmountAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(saleAmount.getText(), 3)));
                }
                if (ManagerMenu.getDetailMenu() == 4) {
                    Product product = addSaleProducts.getSelectionModel().getSelectedItem();
                    saleAmountAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(product.getId(), 4)));
                }
            }

            // finish = false;
        }

        public void editSale (MouseEvent mouseEvent) throws IOException, ParseException {
            if (SellerMenu.getEdit() == 0) {
                saleIdAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOff(saleIdTextField.getText())));
            }
            if (SellerMenu.getEdit() == 1) {
                startSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOffField(startSaleDatePicker.getText(), "start")));
                endSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOffField(endSaleDatePicker.getText(), "end")));
                saleAmountAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOffField(saleAmount.getText(), "amount")));

            } else saleIdAlertLabel.setText("insert id first");
        }

        public void addProduct (MouseEvent mouseEvent) throws IOException, ParseException {
            if (SellerMenu.getSaleRequest() != null) {
                if (addSaleProducts.getSelectionModel().getSelectedItem() != null) {
                    Product product = addSaleProducts.getSelectionModel().getSelectedItem();
                    saleIdAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOffField(product.getId(), "add product")));
                    makeTree();
                } else saleIdAlertLabel.setText("you have to select first");
            } else saleIdAlertLabel.setText("you have to insert name and edit first");
        }
        public void removeProduct (MouseEvent mouseEvent) throws IOException, ParseException {
            if (SellerMenu.getSaleRequest() != null) {
                if (addSaleProducts.getSelectionModel().getSelectedItem() != null) {
                    Product product = addSaleProducts.getSelectionModel().getSelectedItem();
                    saleIdAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.editOffField(product.getId(), "remove product")));
                    makeTree();
                } else saleIdAlertLabel.setText("you have to select first");
            } else saleIdAlertLabel.setText("you have to insert name and edit first");

        }
        public void exit (ActionEvent actionEvent){
        }

        public void back (ActionEvent actionEvent){
        }

        public void logout (ActionEvent actionEvent){
        }

        public void userMenu (ActionEvent actionEvent){
        }


        public void finish (MouseEvent mouseEvent){
            finish = true;
        }



}
