package view.gui;

import controller.menus.LoginMenu;
import controller.menus.ManagerMenu;
import controller.menus.SellerMenu;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.productRelated.Product;
import model.sort.Sort;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class ProductsFx {
    @FXML private TableColumn<Product, String> productAdditional;
    @FXML private TableColumn<Product, String> productId;
    @FXML private TableColumn<Product, String> productNumberView;
    @FXML private TableColumn<Product, Double> productNumber;
    @FXML private TableColumn<Product, Double> productScore;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Number> productPrice;
    @FXML private TableView<Product> products;
    @FXML
    private Label productsMs;
    private static Parent priRoot;

    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent root;
    private static ArrayList<Product> allProducts = new ArrayList<>();

    public static void setPriRoot(Parent priRoot) {
        ProductsFx.priRoot = priRoot;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public static void setAllProducts(ArrayList<Product> allProducts) {
        ProductsFx.allProducts = allProducts;
    }

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void initializeObserverList() {
        list.clear();
        list.addAll(allProducts);
    }

    private void makeTree() {
        productId.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        productAdditional.setCellValueFactory(new PropertyValueFactory<Product, String>("additionalDetail"));
        productScore.setCellValueFactory(new PropertyValueFactory<Product, Double>("averageScore"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productNumber.setCellValueFactory(new PropertyValueFactory<Product, Double>("numberOfProducts"));
        productNumberView.setCellValueFactory(new PropertyValueFactory<Product, String>("numberOfViews"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Number>("price"));


        initializeObserverList();
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        products.setEditable(true);
        products.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        products.getSelectionModel().setCellSelectionEnabled(true);

        products.setItems(list);


    }
    public void productsRemove(MouseEvent mouseEvent) throws IOException {
            if(products.getSelectionModel().getSelectedItem() != null) {
                Product product = (Product) products.getSelectionModel().getSelectedItem();
                if(LoginMenu.getLoginAccount() instanceof Manager) {
                    productsMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.removeProduct(product.getProductId())));
                } else if(LoginMenu.getLoginAccount() instanceof Seller) {
                    productsMs.setText(OutputMassageHandler.showAccountOutput(SellerMenu.processRemoveProduct(product.getProductId())));
                }
                makeTree();
            }else productsMs.setText("you have to select first");

    }

    public void productsEdit(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            if(products.getSelectionModel().getSelectedItem() != null) {
                Product product= products.getSelectionModel().getSelectedItem();
                //???????????????????
            root = FXMLLoader.load(Objects.requireNonNull(SignUpFx.class.getClassLoader().getResource("signUpFx.fxml")));
            goToPage();
            }else productsMs.setText("you have to select first");
        }
    }

    public void productsViewBuyers(MouseEvent mouseEvent) {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            if(products.getSelectionModel().getSelectedItem() != null) {
                Product product= products.getSelectionModel().getSelectedItem();
                list.clear();
                list.addAll(product.getListOfBuyers());
                showList();
            }else productsMs.setText("you have to select first");
        }
    }

    public void productsView(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            if(products.getSelectionModel().getSelectedItem() != null) {
                Product product = products.getSelectionModel().getSelectedItem();
                ProductMenuFX.setProductInPage(product);
                root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenu.fxml")));
                goToPage();
            }else productsMs.setText("you have to select first");
        }
    }

    public void productsSortScore(MouseEvent mouseEvent) {
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            Seller seller = (Seller) LoginMenu.getLoginAccount();
            Sort.setNewArrayOfProductSort(seller.getAllProduct());
        }else {
            Sort.setNewArrayOfProductSort(Product.getProductList());
        }
        allProducts =Sort.scoreSort();

        makeTree();


    }

    public void productsSortView(MouseEvent mouseEvent) {
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            Seller seller = (Seller) LoginMenu.getLoginAccount();
            Sort.setNewArrayOfProductSort(seller.getAllProduct());
        }else  {
            Sort.setNewArrayOfProductSort(Product.getProductList());
        }
        allProducts= Sort.numberOfViewsSort();
        makeTree();


    }
    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
    private void showList() {
        ListView listView = new ListView(list);
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Stage list = new Stage();
        list.setScene(new Scene(root, 200, 250));
        list.show();
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
