package view.gui;


import controller.menus.LoginMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductInMenusShow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class ProductsMenuFX {

    @FXML
    public TableView<ProductInMenusShow> tableView = new TableView<>();
    @FXML
    public TableColumn<ProductInMenusShow, String> firstColumn = new TableColumn<>("id");

    @FXML
    public TableColumn<ProductInMenusShow, ImageView> productImageViewTableColumn = new TableColumn<>("productImage");

    @FXML
    public TableColumn<ProductInMenusShow, String> secondColumn = new TableColumn<>("name");
    @FXML
    public TableColumn<ProductInMenusShow, String> forthColumn = new TableColumn<>("price");
    @FXML
    public TableColumn<ProductInMenusShow, String> fifthColumn = new TableColumn<>("seller");

    @FXML
    public TableColumn<ProductInMenusShow, String> sixthColumn = new TableColumn<>("Specifications");

    public TableColumn<ProductInMenusShow, Category> seventh = new TableColumn<>("category");

    public TableColumn<ProductInMenusShow, String> eleventh = new TableColumn<>("firm");

    public TableColumn<ProductInMenusShow, String> tenth = new TableColumn<>("numberOfProduct");

    @FXML
    public static ObservableList<ProductInMenusShow> data = FXCollections.observableArrayList();


    public static ImageView productPic = new ImageView();

    public static FilteredList<ProductInMenusShow> filteredList = new FilteredList<>(data, b -> true);

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
    public SortedList<ProductInMenusShow> sortedList = new SortedList<>(filteredList);
    public AnchorPane companyNamePaneFilter;
    public TextField maxPriceTextField;
    public TextField minPriceTextField;

    public static void setPriRoot(Parent priRoot) {
        ProductsMenuFX.priRoot = priRoot;
    }

    public static void initializeObserverList() throws FileNotFoundException {
        listIni();
        for (ProductInMenusShow show : ProductInMenusShow.list) {
            if (!data.contains(show)) {
                data.add(show);
            }
        }
    }

    public static void listIni() throws FileNotFoundException {
        for (Product product : Product.getProductList()) {
            ProductInMenusShow show = new ProductInMenusShow(product.getId());
            if(product.getProductStatus().equals("CONFIRMED")) {
                show.name = product.getProductName();
                show.additionalDetail = product.getAdditionalDetail();
                show.category = product.getCategory().getName();
                show.id = product.getId();
                show.price = product.getPrice();
                show.comment = product.getComment();
                show.seller = product.getSeller();
                File file = new File(product.getProductImage());
                Image image = new Image(new FileInputStream(file));
                show.productImage = new ImageView();
                show.productImage.setFitWidth(100);
                show.productImage.setFitHeight(100);
                show.productImage.setImage(image);
                show.firm = Seller.getAccountWithUsername(product.getSeller()).getFirm().getName();
                show.numberOfProduct = product.getNumberOfProducts();
            }
        }
    }

    @FXML
    public void initialize() throws IOException {
        dataInFilterCheck();
        firstColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, String>("id"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, String>("name"));
        productImageViewTableColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, ImageView>("productImage"));
        forthColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, String>("price"));
        fifthColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, String>("seller"));
        sixthColumn.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, String>("additionalDetail"));
        seventh.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow, Category>("category"));
        tenth.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow,String>("numberOfProduct"));

        eleventh.setCellValueFactory(new PropertyValueFactory<ProductInMenusShow,String>("firm"));


        initializeObserverList();
        tableView.getColumns().addAll(firstColumn, secondColumn, productImageViewTableColumn, forthColumn, fifthColumn, sixthColumn, seventh,eleventh,tenth);
        tableView.setItems(data);


        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else return false;
            });

        });

        minPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (product.getPrice() > Integer.parseInt(minPriceTextField.getText())) {
                    return true;
                }
                else return false;
            });

        });

        maxPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (product.getPrice() < Integer.parseInt(minPriceTextField.getText())) {
                    return true;
                }
                else return false;
            });

        });

        for (CheckBox checkBox : filterCatCheck) {
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    filteredList.setPredicate(obj -> {
                        if (!checkBox.isSelected()) {
                            return true;
                        }
                        if (checkBox.isSelected() && obj.getCategory().equals(checkBox.getText())){
                            return true;
                        }
                        if (checkBox.isSelected() && obj.getNumberOfProduct() != 0){
                            return true;
                        }
                        if (checkBox.isSelected() && obj.getFirm().equals(checkBox.getText())){
                            return true;
                        }
                        else return false;
                    });
                }
            });
        }


        tableView.setEditable(true);

        catName.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        traits.setCellValueFactory(new PropertyValueFactory<Category, ArrayList<String>>("traits"));
        dataInListView();
        categoriesListView.getColumns().addAll(catName, traits);
        categoriesListView.setItems(dataCat);


        sortedList = new SortedList<>(filteredList);
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
        ProductInMenusShow item = tableView.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();

        try {

            ImageView im = (ImageView) tableColumn.getCellObservableValue(item).getValue();
            String id = ProductInMenusShow.getIdWithImage(im);
            ProductMenuFX.productInPage = Product.getProductById(id);
            gotoProductPage(ProductMenuFX.productInPage);

        } catch (NullPointerException e) {
            System.out.println("you cant press here");
        }
    }

    public void dataInListView() {
        for (Category category : Category.getAllCategories()) {
            if (!dataCat.contains(category)) {
                dataCat.add(category);
            }
        }
    }


    public void dataInFilterCheck() {
        for (Category category : Category.getAllCategories()) {
            int n = 30;
            CheckBox checkBox = new CheckBox(category.getName());
            checkBox.setLayoutY((n) * (Category.getAllCategories().indexOf(category) + 1));
            checkBox.setLayoutX(10 + Category.getAllCategories().indexOf(category));
            filterCatCheck.add(checkBox);
            FilterCategoryPane.getChildren().add(checkBox);
        }

        for (Firm firm : Firm.getAllFirms()) {
            int n=30;
            CheckBox checkBox = new CheckBox(firm.getName());
            checkBox.setLayoutY((n) * (Firm.getAllFirms().indexOf(firm) + 1));
            checkBox.setLayoutX(10 + Firm.getAllFirms().indexOf(firm));
            filterCatCheck.add(checkBox);
            companyNamePaneFilter.getChildren().add(checkBox);
        }
        filterCatCheck.add(filterIsAvailable);
        filterCatCheck.add(filterAscending);
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

    public void login(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
        goToPage();
    }

    public void userMenu(ActionEvent actionEvent) throws IOException {
        Parent curRoot = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
        if (LoginMenu.getLoginAccount() instanceof Manager) {
            ManagerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
        } else if (LoginMenu.getLoginAccount() instanceof Seller) {
            SellerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
        } else if (LoginMenu.getLoginAccount() instanceof Customer) {
            CustomerMenuFx.setPriRoot(curRoot);
            root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        }

        goToPage();
    }
}
