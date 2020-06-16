package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.Stage;
import model.productRelated.Product;

import javax.management.AttributeList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ProductsMenuFX {

        @FXML
        public  TableView<Product> tableView = new TableView<>();
        @FXML
        public TableColumn<Product, String> firstColumn = new TableColumn<>("id");

        @FXML
        public TableColumn<Product,String> productImageViewTableColumn = new TableColumn<>("proImage");

        @FXML
        public TableColumn<Product, String> secondColumn = new TableColumn<>("name");
        @FXML
        public TableColumn<Product, Double> forthColumn = new TableColumn<>("price");
        @FXML
        public TableColumn<Product, String> fifthColumn = new TableColumn<>("seller");
        @FXML
        public TableColumn<Product, String> seventh = new TableColumn<>("factory");

        @FXML
        public TableColumn<Product, Integer> eighth = new TableColumn<>("numberOfProducts");


        @FXML
        public TableColumn<Product, String> sixthColumn = new TableColumn<>("additionalDetail");
        @FXML
        public static  ObservableList<Product> data = FXCollections.observableArrayList();
        public static FilteredList<Product> filteredList = new FilteredList<>(data , b->true);



        public static Scene prevScene ;
        public static Stage thisStage;
        public TextField searchField;

        public static void showProPage(Stage stage, Scene scene) throws IOException {
                AnchorPane root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("sample/fxFile/sample.fxml")));
                initializeObserverList();
//        ImageView imageView = new ImageView();
//        imageView.setImage(new Image("sample/images/alien2 (1) - Copy.jpeg"));
//        imageView.setX(400);
//        imageView.setY(200);
//        root.getChildren().add(imageView);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


        public static void initializeObserverList() {
                data.addAll(Product.getProductList());
        }

        @FXML
        public void initialize() throws IOException {

                firstColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
                secondColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
                productImageViewTableColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("productImageId"));
                forthColumn.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
                fifthColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("category"));
                sixthColumn.setCellValueFactory(new PropertyValueFactory<Product,String>("seller"));
                seventh.setCellValueFactory(new PropertyValueFactory<Product,String>("factory"));
                eighth.setCellValueFactory(new PropertyValueFactory<Product,Integer>("numberOfProducts"));


                initializeObserverList();
                tableView.getColumns().addAll(firstColumn,secondColumn,productImageViewTableColumn,forthColumn,fifthColumn,sixthColumn,seventh,eighth);
                tableView.setItems(data);


                searchField.textProperty().addListener((observable,oldValue,newValue) -> {
                        filteredList.setPredicate(product -> {
                                if (newValue == null || newValue.isEmpty()){
                                        return true;
                                }
                                String lowerCaseFilter = newValue.toLowerCase();

                                if (product.getProductName().toLowerCase().contains(lowerCaseFilter)){
                                        return true;
                                }
                                if (product.getSeller().getName().toLowerCase().contains(lowerCaseFilter)){
                                        return true;
                                }
                                else if (product.getCategory().getName().toLowerCase().contains(lowerCaseFilter)){
                                        return true;
                                }
                                else return false;
                        });

                });
                tableView.setEditable(true);


                SortedList<Product> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tableView.comparatorProperty());
                tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                tableView.getSelectionModel().setCellSelectionEnabled(true);
                tableView.setItems(sortedList);
        }

        public static void gotoProductPage(Product product) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(ProductsMenuFX.class.getResource("fxFile/sample3.fxml"));
                thisStage = new Stage();
                AnchorPane pane = fxmlLoader.load();
                for (Product product1 : Product.getProductList()) {
                        Label label = new Label(product.getProductName());
                        label.setLayoutX(100*(Product.getProductList().indexOf(product1)+1));
                        label.setLayoutY(100*(Product.getProductList().indexOf(product1)+1));
                        TextField textField = new TextField();
                        textField.setLayoutX(300);
                        textField.setLayoutY(200);
                        pane.getChildren().addAll(textField,label);
                }

                File file = new File(product.getProductImage());
                Image image = new Image(new FileInputStream(file));
                ImageView imageView = new ImageView(image);
                pane.getChildren().add(imageView);
                imageView.prefHeight(50);
                imageView.prefWidth(50);
                imageView.setX(60);
                imageView.setY(60);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                prevScene = new Scene(pane);
                thisStage.setScene(prevScene);
                thisStage.show();

        }

        public void clickedColumn(MouseEvent mouseEvent) throws IOException {
                TablePosition tablePosition = tableView.getSelectionModel().getSelectedCells().get(0);
                int row = tablePosition.getRow();
                Product item = tableView.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
//        ImageView imageView = (ImageView) tableColumn.getCellObservableValue(item).getValue();
                String im = (String) tableColumn.getCellObservableValue(item).getValue();
//        imageView.setVisible(true);
                gotoProductPage(Product.getProductWithImage(im));
//        imageView.setVisible(true);
        }
}
