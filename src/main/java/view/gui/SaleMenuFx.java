package view.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductInMenusShow;
import model.productRelated.ProductInSaleShow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SaleMenuFx {
    public TextField searchProInSale;
    public TableView ProductsInOffSearch = new TableView();

    @FXML
    public TableColumn<ProductInSaleShow, String> firstColumn = new TableColumn<>("id");

    @FXML
    public TableColumn<ProductInSaleShow, ImageView> productImageViewTableColumn = new TableColumn<>("productImage");

    @FXML
    public TableColumn<ProductInSaleShow, String> secondColumn = new TableColumn<>("name");
    @FXML
    public TableColumn<ProductInSaleShow, String> forthColumn = new TableColumn<>("price");
    @FXML
    public TableColumn<ProductInSaleShow, String> fifthColumn = new TableColumn<>("seller");

    @FXML
    public TableColumn<ProductInSaleShow, String> sixthColumn = new TableColumn<>("Specifications");

    public TableColumn<ProductInSaleShow , Category> seventh = new TableColumn<>("category");
    @FXML
    public static ObservableList<ProductInSaleShow> data = FXCollections.observableArrayList();

    public static FilteredList<ProductInSaleShow> filteredList = new FilteredList<>(data, b -> true);


//    public static void setPriRoot(Parent priRoot) {
//        ProductsMenuFX.priRoot = priRoot;
//    }

    public static void initializeObserverList() throws FileNotFoundException {
        listIni();
        for (ProductInSaleShow saleShow : ProductInSaleShow.list) {
            if (!data.contains(saleShow)){
                data.add(saleShow);
            }
        }
    }


    public static void listIni() throws FileNotFoundException {
        for (Product product : Product.getProductList()) {
            ProductInSaleShow show = new ProductInSaleShow(product.getId());
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

        }
    }



    @FXML
    public void initialize() throws IOException {
        firstColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, String>("id"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, String>("name"));
        productImageViewTableColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, ImageView>("productImage"));
        forthColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, String>("price"));
        fifthColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, String>("seller"));
        sixthColumn.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow, String>("additionalDetail"));
        seventh.setCellValueFactory(new PropertyValueFactory<ProductInSaleShow,Category>("category"));

        initializeObserverList();
        ProductsInOffSearch.getColumns().addAll(firstColumn, secondColumn, productImageViewTableColumn, forthColumn, fifthColumn, sixthColumn,seventh);
        ProductsInOffSearch.setItems(data);


        searchProInSale.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (product.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else return false;
            });

        });


        ProductsInOffSearch.setEditable(true);



        SortedList<ProductInSaleShow> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(ProductsInOffSearch.comparatorProperty());
        ProductsInOffSearch.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ProductsInOffSearch.getSelectionModel().setCellSelectionEnabled(true);
        ProductsInOffSearch.setItems(sortedList);

    }



}
