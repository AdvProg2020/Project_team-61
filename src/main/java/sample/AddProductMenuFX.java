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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import model.accounts.Account;
import model.accounts.Seller;
import model.productRelated.Category;
import model.productRelated.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductMenuFX {

    private static int outputNo = 0;
    private Product product;
    public ImageView productImage;
    public TextField priceTextField;
    public TextField productNameTextField;
    public Button addProductButton;
    public Label error;
    public static Scene prevScene;
    public static Stage thisStage;
    public TextField categoryNameTextField;
    public TextField sellerNameTextField;
    public TextField idTextField;
    public TextField numberOfProductTextField;
    public AnchorPane pane;
    public static ArrayList<TextField> traitsTextFields = new ArrayList<>();
    public TextArea additionaldetailTextField;
    String imageId;
    List<File> files;
    Category productCategory;
    ArrayList<String> traitText = new ArrayList<>();


    public static void showProPage(Stage stage, Scene scene) throws IOException {
        thisStage = stage;
        prevScene = scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("sample/fxFile/sample2.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addProductToCard(ActionEvent actionEvent) {
        System.out.println("hah");
    }

    @FXML
    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void handleDrop(DragEvent dragEvent) throws FileNotFoundException {
        files = dragEvent.getDragboard().getFiles();
        System.out.println(files.get(0).getAbsolutePath());
        System.out.println(files.get(0).getPath());
        imageId = files.get(0).getPath();
        File file = new File(imageId);
        Image image = new Image(new FileInputStream(file));
        productImage.setImage(image);
    }

    @FXML
    public void addProduct(MouseEvent actionEvent) throws IOException {
        String id = idTextField.getText();
        String name = productNameTextField.getText();
        String price = priceTextField.getText();
        String category = categoryNameTextField.getText();
        String seller = sellerNameTextField.getText();
        String numberOfProduct = numberOfProductTextField.getText();
        String additionalDetail = additionaldetailTextField.getText();
        if (!name.equals("")) {
            if (!price.equals("")) {
                if (!category.equals("") && Category.isThereCategoryWithName(category)) {
                    if (Category.getCategoryWithName(category).getTraits() != null){
                        productImage.setVisible(true);
                        productImage.setVisible(true);
                        Product product = new Product(id);
                        product.setDetailProduct(imageId, name, Integer.parseInt(price), Category.getCategoryWithName(category), Account.getAccountWithUsername(seller), Seller.getAccountWithUsername(seller).getFirm(), Integer.parseInt(numberOfProduct));
                        product.setAdditionalDetail(additionalDetail);
                        productImage = new ImageView();
                        productImage.setFitHeight(216.0);
                        productImage.setFitWidth(267.0);
                        productImage.setLayoutX(137.0);
                        productImage.setLayoutY(76.0);
                        handleTraits();
                        product.productCategorySpecifications.addAll(traitText);
                        productCategory = Category.getCategoryWithName(category);
                        imageId = files.get(0).getPath();
                        File file = new File(imageId);
                        Image image = new Image(new FileInputStream(file));
                        productImage = new ImageView();
                        productImage.setImage(image);
                        productImage.setFitHeight(216.0);
                        productImage.setFitWidth(267.0);
                        productImage.setLayoutX(137.0);
                        productImage.setLayoutY(76.0);
                        productImage.setOnDragDropped(e -> {
                            try {
                                handleDrop(e);
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        });
                        productImage.setOnDragOver(this::handleDragOver);
                    }

                }
                else {
                    error.setVisible(true);
                    error.setText("please inter valid category");
                }
            } else {
                error.setVisible(true);
                error.setText("please inter valid price");
                System.out.println("heh");

            }
        } else {
            error.setVisible(true);
            error.setText("please inter a valid product name");
        }

    }

    public void backToProducts(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxFile/sample.fxml"));
//        thisStage = new Stage();
        prevScene = new Scene(fxmlLoader.load());
        thisStage.setScene(prevScene);
        thisStage.show();
    }

    public void addTraitsButton(ActionEvent actionEvent) {
        String category = categoryNameTextField.getText();
        if (category != null && Category.isThereCategoryWithName(category)){
            productCategory = Category.getCategoryWithName(category);
            if (productCategory != null){
                for (String trait : productCategory.getTraits()) {
                    int n =50;
                    Label label = new Label();
                    label.setText(trait);
                    label.setLayoutY(n*(productCategory.getTraits().indexOf(trait)+1));
                    label.setLayoutX(900+productCategory.getTraits().indexOf(trait));
                    TextField textField = new TextField();
                    textField.setMaxSize(100,50);
                    textField.setLayoutY((n)*(productCategory.getTraits().indexOf(trait)+1));
                    textField.setLayoutX(1000+productCategory.getTraits().indexOf(trait));
                    traitsTextFields.add(textField);
                    pane.getChildren().addAll(label,textField);
                    n+=5;
                }
            }
        }
        else {
            error.setVisible(true);
            error.setText("please enter category name first");
        }
    }

    private void handleTraits() {

        for (TextField traitsTextField : traitsTextFields) {
            traitText.add(traitsTextField.getText());
        }

    }


}