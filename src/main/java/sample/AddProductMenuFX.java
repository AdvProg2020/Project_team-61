package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.List;
import java.util.Objects;

public class AddProductMenuFX {

    public Button addToCardButton;
    public AnchorPane productMenuPane;
    public AnchorPane topSplitPane;
    public TextArea productImageRectangle;
    public TextArea productProperties;
    public Tab specificationsTab;
    public Rectangle specificationPane;
    public TextArea specificatonTextbox;
    public AnchorPane commentsPane;
    public Tab averageScoreTab;
    public AnchorPane averageScorePane;
    public Label productFinishedError;
    public Tab commentTab;
    public MenuItem account;
    public MenuItem back;
    public MenuItem logout;
    public MenuItem exit;
    public MenuItem deleteProduct;
    public MenuItem editProduct;
    public TextArea commentTitleField;
    public TextArea commnetContentField;
    public Button addCommentButton;
    public Button addScoreButton;

    private static int outputNo = 0;
    private static Product selectedProduct;
    public ImageView productImage;
    public TextField priceTextField;
    public TextField productNameTextField;
    public Button addProductButton;
    public Label error;
    public static Scene prevScene ;
    public static Stage thisStage;
    public TextField categoryNameTextField;
    public TextField sellerNameTextField;
    public TextField idTextField;
    public TextField numberOfProductTextField;


    public static void showProPage(Stage stage, Scene scene) throws IOException {
        thisStage=stage;
        prevScene = scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("sample/fxFile/sample2.fxml")));
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
        List<File> files = dragEvent.getDragboard().getFiles();
        Image image = new Image(new FileInputStream(files.get(0)));
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

        if (!name.equals("")) {
            if (!price.equals("")) {
                productImage.setVisible(true);
                Product product = new Product(id);
                product.setDetailProduct(productImage,name,Integer.parseInt(price), Category.getCategoryWithName(category), Account.getAccountWithUsername(seller),Seller.getAccountWithUsername(seller).getFirm(),Integer.parseInt(numberOfProduct));
                productImage = new ImageView();
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
                System.out.println("haha");

            } else {
                error.setVisible(true);
                error.setText("please inter valid product name");
                System.out.println("heh");

            }
        } else {
            error.setVisible(true);
            error.setText("please inter a valid price");
        }
    }

    public void backToProducts(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/productsMenu.fxml"));
//        thisStage = new Stage();
        prevScene = new Scene(fxmlLoader.load());
        thisStage.setScene(prevScene);
        thisStage.show();
    }


}
