package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.productRelated.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ProductMenuFX {
    @FXML
    public AnchorPane productPagePane;
    @FXML
    public ImageView productPic;
    public static Scene prevScene;
    public static Stage thisStage = new Stage();
    public static Product productInPage;
    public AnchorPane scrollPane;
    public TextArea commentTextField;
    public TextField titleTextField;
    public Button backButtonAddComment;
    public Button sendCommentButton;
    public AnchorPane addCommentSectionPane;
    public Label nullAddCommentError;
    @FXML
    private Label productNameLabel;
    @FXML
    private Button addToCardButton;
    @FXML
    private Button commentButton;
    @FXML
    private Button scoreButton;
    @FXML
    private Label didntBuyToScoreOrProductIsFinish;


    public static void showProPage(Stage stage, Scene scene, Product product) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProductsMenuFX.class.getResource("fxFile/sample3.fxml"));
        thisStage = new Stage();
        AnchorPane pane = fxmlLoader.load();
        productInPage = product;
        prevScene = new Scene(pane);
        thisStage.setScene(prevScene);
        thisStage.show();
    }


    public void makeUpPage() throws FileNotFoundException {
        productNameLabel.setText(productInPage.getProductName());
        File file = new File(productInPage.getProductImage());
        Image image = new Image(new FileInputStream(file));
        productPic.setImage(image);
    }


    @FXML
    void popUpAddComment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("sample/fxFile/comment.fxml")));
        thisStage = new Stage();
        prevScene = new Scene(root);
        thisStage.setScene(prevScene);
        thisStage.show();
    }


    public void handleAddProductToLog(ActionEvent actionEvent) {

    }

    public void handleScore(ActionEvent actionEvent) {

    }

    public void handleBackAddCommentButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("sample/fxFile/sample3.fxml")));
        prevScene = new Scene(root);
        thisStage.setScene(prevScene);
        thisStage.show();
    }

    public void handleSendComment(ActionEvent actionEvent) {
        String title = titleTextField.getText();
        String content = commentTextField.getText();
        if (title != null){
            if (content != null){
                //add Comment Process
            }
            else{
                nullAddCommentError.setText("content is empty");
                nullAddCommentError.setVisible(true);
            }
        }
        else {
            nullAddCommentError.setText("title is empty");
            nullAddCommentError.setVisible(true);
        }
    }
}
