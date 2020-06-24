package view.gui;

import controller.ProductMenu;
import controller.menus.CustomerMenu;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

    public TextArea commentTextField;
    public TextField titleTextField;
    public Button backButtonAddComment;
    public Button sendCommentButton;
    public AnchorPane addCommentSectionPane;
    public Label nullAddCommentError;
    public TextArea productCategoryDetail;
    public TextArea productDetail;
    public TableView commentTableView = new TableView();
    public Button scoreButton;
    @FXML
    private Label productNameLabel;
    @FXML
    private Button addToCardButton;
    @FXML
    private Button commentButton;
    @FXML
    private Label didntBuyToScoreOrProductIsFinish;
    @FXML
    public TableColumn<Comment, String> titleColumn = new TableColumn<>("title");
    @FXML
    public TableColumn<Comment, String> contentColumn = new TableColumn<>("content");
    @FXML
    public static ObservableList<Comment> data = FXCollections.observableArrayList();
    @FXML
    private Label scoreMs;
    private int score =0;

    public void makeUpPage() throws IOException {
        productNameLabel.setText(productInPage.getProductName());
        File file = new File(productInPage.getProductImage());
        Image image = new Image(new FileInputStream(file));
        productPic.setImage(image);
        productDetail.setText("Id : " + productInPage.getId() + "\n" +
                "Name : " + productInPage.getProductName() + "\n" +
                "Price : " + productInPage.getPrice() + "\n" +
                "Seller : " + productInPage.getSeller().getName() + "\n" +
                "Category : " + productInPage.getCategory().getName() + "\n" +
                "Number : " + productInPage.getNumberOfProducts() + "\n" +
                "Average Score : " + productInPage.getScore() + "\n"
        );
        productDetail.setEditable(false);
        for (String productCategorySpecification : productInPage.productCategorySpecifications) {
            System.out.println(productCategorySpecification);
        }
        for (String specification : productInPage.productCategorySpecifications) {

            if (specification != null && !specification.equals("")) {
                for (String productCategorySpecification : productInPage.productCategorySpecifications) {
                    if (!productCategorySpecification.equals(null)) {
                        if (!productCategoryDetail.getText().equals(handleProCatDetail())) {
                            productCategoryDetail.appendText(productCategorySpecification + "\n");
                        }
                    }
                }
            } else {
                System.out.println("trait is empty");
            }
        }
        productCategoryDetail.setEditable(false);

    }

    @FXML
    void popUpAddComment(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("sample/fxFile/comment.fxml")));
        prevScene = new Scene(root);
        thisStage = new Stage();
        thisStage.setScene(prevScene);
        thisStage.show();
    }

    public void handleAddProductToLog(ActionEvent actionEvent) {

    }

    public void handleBackAddCommentButton(ActionEvent actionEvent) throws IOException {
        thisStage.close();
    }

    public void handleSendComment(ActionEvent actionEvent) throws IOException {
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.addComments()));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.contentOfComment(commentTextField.getText())));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.titleOfComment(titleTextField.getText())));
    }

    public String handleProCatDetail() {
        String out = "";
        for (String specification : productInPage.productCategorySpecifications) {
            out += specification + "\n";
        }
        return out;
    }

    @FXML
    public void initialize() throws IOException {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("title"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("content"));
        for (Comment comment : Comment.getCommentsOfPro(productInPage.getId())) {
            if (!data.contains(comment)) {
                data.add(comment);
            }
        }
        commentTableView.getColumns().addAll(titleColumn, contentColumn);
        commentTableView.setItems(data);
        for (Comment comment : Comment.getCommentsOfPro(productInPage.getId())) {
            System.out.println(comment.getTitle());
        }
        data.removeAll();
    }

    public void backToProductsMenu(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("sample/fxFile/sample.fxml")));
        Scene scene = new Scene(root);
        thisStage.setScene(scene);
        thisStage.show();
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
            scoreMs.setText(OutputMassageHandler.showCustomerOutput( CustomerMenu.rateProduct( productInPage.getProductId(), score)));
        }else scoreMs.setText("you have to select first");
    }

    public void score2(MouseEvent mouseEvent) {

    }

    public void handleScore(ActionEvent actionEvent) {

    }


}
