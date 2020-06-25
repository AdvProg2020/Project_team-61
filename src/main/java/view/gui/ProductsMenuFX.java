package view.gui;


import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.productRelated.Category;
import model.productRelated.Product;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;



public class ProductsMenuFX {

        @FXML
        public TableView<Product> tableView = new TableView<>();
        @FXML
        public TableColumn<Product, String> firstColumn = new TableColumn<>("id");

        @FXML
        public TableColumn<Product, String> productImageViewTableColumn = new TableColumn<>("proImage");

        @FXML
        public TableColumn<Product, String> secondColumn = new TableColumn<>("name");
        @FXML
        public TableColumn<Product, String> forthColumn = new TableColumn<>("price");
        @FXML
        public TableColumn<Product, Seller> fifthColumn = new TableColumn<>("seller");

        @FXML
        public TableColumn<Product, String> sixthColumn = new TableColumn<>("Specifications");

        public TableColumn<Product ,Category> seventh = new TableColumn<>("category");

        @FXML
        public static ObservableList<Product> data = FXCollections.observableArrayList();

        public static ImageView productPic = new ImageView();

        public static FilteredList<Product> filteredList = new FilteredList<>(data, b -> true);

        @FXML
        public TableColumn<Category, String> catName = new TableColumn<>("name");

        @FXML
        public TableColumn<Category, ArrayList<String>> traits = new TableColumn<>("traits");


        private static Parent priRoot;
        private static Parent root;

        public static Scene prevScene;
        public static Stage thisStage;
        public TextField searchField;
        public AnchorPane topSplitPane;
        public TableView categoriesListView = new TableView();
        public CheckBox filterAscending;
        public CheckBox filterIsAvailable;
        public AnchorPane FilterCategoryPane;
        public ObservableList<Category> dataCat = FXCollections.observableArrayList();
        public ArrayList<CheckBox> filterCatCheck = new ArrayList<>();

        public static void setPriRoot(Parent priRoot) {
                ProductsMenuFX.priRoot = priRoot;
        }

        public static void initializeObserverList() {
                for (Product product : Product.getProductList()) {
                        if (!data.contains(product)){
                                System.out.println(product.getSeller());
                                System.out.println(product.getCategory());
                                data.add(product);
                        }
                }
        }
        @FXML
        public void initialize() throws IOException {
                dataInFilterCheck();
                firstColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
                secondColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
                productImageViewTableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productImage"));
                forthColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
                fifthColumn.setCellValueFactory(new PropertyValueFactory<Product, Seller>("seller"));
                sixthColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("additionalDetail"));
                seventh.setCellValueFactory(new PropertyValueFactory<Product,Category>("category"));

                initializeObserverList();
                tableView.getColumns().addAll(firstColumn, secondColumn, productImageViewTableColumn, forthColumn, fifthColumn, sixthColumn,seventh);
                tableView.setItems(data);


                searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                        filteredList.setPredicate(product -> {
                                if (newValue == null || newValue.isEmpty()) {
                                        return true;
                                }
                                String lowerCaseFilter = newValue.toLowerCase();

                                if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                                        return true;
                                }
                                else if (product.getCategory().getName().toLowerCase().contains(lowerCaseFilter)) {
                                        return true;
                                }
                                else return false;
                        });

                });


                tableView.setEditable(true);

                catName.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
                traits.setCellValueFactory(new PropertyValueFactory<Category, ArrayList<String>>("traits"));
                dataInListView();
                categoriesListView.getColumns().addAll(catName,traits);
                categoriesListView.setItems(dataCat);


                SortedList<Product> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tableView.comparatorProperty());
                tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                tableView.getSelectionModel().setCellSelectionEnabled(true);
                tableView.setItems(sortedList);

        }

        public static void gotoProductPage(Product product) throws IOException {
                AnchorPane root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenu.fxml")));
                prevScene = new Scene(root);
                thisStage = new Stage();
                thisStage.setScene(prevScene);
                ProductMenuFX.productInPage = product;
                thisStage.show();
        }

        public void clickedColumn(MouseEvent mouseEvent) throws IOException {
                TablePosition tablePosition = tableView.getSelectionModel().getSelectedCells().get(0);
                int row = tablePosition.getRow();
                Product item = tableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();

                try {
                        System.out.println(tableColumn.getCellObservableValue(item).getValue() + "1***");
                        String im = (String) tableColumn.getCellObservableValue(item).getValue();
                        System.out.println(im+"heh");
                        ProductMenuFX.productInPage = Product.getProductWithImage(im);
                        System.out.println(ProductMenuFX.productInPage.getProductImage()+"fksflksdfsdlf");
                        gotoProductPage(ProductMenuFX.productInPage);

                } catch (NullPointerException e) {
                        System.out.println("you cant press here");
                }
        }

        public void dataInListView(){
                for (Category category : Category.getAllCategories()) {
                        if (!dataCat.contains(category)){
                                dataCat.add(category);
                        }
                }
        }


        public void dataInFilterCheck(){
                for (Category category : Category.getAllCategories()) {
                        int n=30;
                        CheckBox checkBox = new CheckBox(category.getName());
                        checkBox.setLayoutY((n)*(Category.getAllCategories().indexOf(category)+1));
                        checkBox.setLayoutX(10+Category.getAllCategories().indexOf(category));
                        filterCatCheck.add(checkBox);
                        FilterCategoryPane.getChildren().add(checkBox);
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

        private static void goToPage() {
                Scene pageTwoScene = new Scene(root);
                //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Main.primStage.setScene(pageTwoScene);
                Main.primStage.show();
        }

        public void login(ActionEvent actionEvent)throws IOException {
                root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
                goToPage();
        }

        public void userMenu(ActionEvent actionEvent) throws IOException{
                Parent curRoot  = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
                if(LoginMenu.getLoginAccount() instanceof Manager) {
                        ManagerMenuFx.setPriRoot(curRoot);
                        root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
                }else  if(LoginMenu.getLoginAccount() instanceof Seller) {
                        SellerMenuFx.setPriRoot(curRoot);
                        root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
                }else  if(LoginMenu.getLoginAccount() instanceof Customer) {
                        CustomerMenuFx.setPriRoot(curRoot);
                        root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
                }

                goToPage();
        }
}
