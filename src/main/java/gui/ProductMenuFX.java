package gui;

import controller.ProductMenu;
import controller.menus.CustomerMenu;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.OutputMassageHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    public TextArea productCategoryDetail;
    public TextArea productDetail;
    public TableView commentTableView = new TableView();
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
    @FXML
    private Label scoreMs;
    @FXML
    public TableColumn<Product, ArrayList<Comment>> titleColumn = new TableColumn<>("title");

    @FXML
    public TableColumn<Product, ArrayList<Comment>> contentColumn = new TableColumn<>("content");
    @FXML
    public static ObservableList<Comment> data = FXCollections.observableArrayList();
    private int score =0;
    ///////////////////////
    private Product product;
    private static Product curProduct;

    public Product getCurProduct() {
        return curProduct;
    }

    public static void setCurProduct(Product curProduct) {
        ProductMenuFX.curProduct = curProduct;
    }

    public static void showProPage(Stage stage, Scene scene, Product product) throws IOException {
        productInPage = product;
    }


    public void makeUpPage() throws FileNotFoundException {
        productNameLabel.setText(productInPage.getProductName());
        File file = new File(productInPage.getProductImage());
        Image image = new Image(new FileInputStream(file));
        productPic.setImage(image);
    }


    @FXML
    void popUpAddComment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("comment.fxml")));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("gui/fxFile/sample3.fxml")));
        prevScene = new Scene(root);
        thisStage.setScene(prevScene);
        thisStage.show();
    }

    public void handleSendComment(ActionEvent actionEvent) throws IOException {
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.addComments()));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.contentOfComment(commentTextField.getText())));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.titleOfComment(titleTextField.getText())));
    }

    @FXML
    public void initialize() throws IOException {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Product, ArrayList<Comment>>("title"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<Product, ArrayList<Comment>>("content"));
        initializeObserverList();
        commentTableView.getColumns().addAll(titleColumn, contentColumn);
        commentTableView.setItems(data);

    }

    private void initializeObserverList() {
//        if (productInPage.getAllCommentsOnProduct().size() != 0) {
//            data.addAll(productInPage.getAllCommentsOnProduct());
//        } else {
//            System.out.println("no");
//        }
    }

    public void score5(MouseEvent mouseEvent) {
        score = 4;
    }

    public void score4(MouseEvent mouseEvent) {
        score = 4;
    }

    public void score3(MouseEvent mouseEvent) {
        score = 3;
    }

    public void score1(MouseEvent mouseEvent) {
        score = 1;
    }

    public void score(MouseEvent mouseEvent) throws IOException {
        if(score != 0){
         scoreMs.setText(OutputMassageHandler.showCustomerOutput( CustomerMenu.rateProduct( product.getProductId(), score)));
        }else scoreMs.setText("you have to select first");
    }

    public void score2(MouseEvent mouseEvent) {
        score = 2;
    }
    /*
        public void handleSendComment(ActionEvent actionEvent) throws IOException {
        String title = titleTextField.getText();
        String content = commentTextField.getText();

        if (title != null) {
            if (content != null) {
//                productInPage.com(title, content);
            } else {
                nullAddCommentError.setText("content is empty");
                nullAddCommentError.setVisible(true);
            }
        } else {
            nullAddCommentError.setText("title is empty");
            nullAddCommentError.setVisible(true);
        }
    }
     */
}