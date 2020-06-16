package sample;

import controller.ProductsMenu;
import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;
import model.request.CommentRequest;
import model.request.Request;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ProductMenuFX {

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

    private static int outputNo =0;
    private static Product selectedProduct;
    private static BuyLog buyLog;
    private static CommentRequest commentRequest;
    public Button addCommentButton;
    public Button addScoreButton;


    public static void showProPage(Stage stage , Scene scene) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("productMenu.fxml")));
        //  Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenuFX.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addProductToCard(ActionEvent actionEvent) {
        System.out.println("hah");
    }

    @FXML
    public void goToAccount(ActionEvent actionEvent) {
    }

    @FXML
    public void backToPreviousPage(ActionEvent actionEvent) {
    }

    @FXML
    public void Logout(ActionEvent actionEvent) {
    }

    @FXML
    public void exitUser(ActionEvent actionEvent) {
    }

    @FXML
    public void addCommnet(ActionEvent actionEvent) throws IOException {
        addComments();
    }

    @FXML
    public void scoreOnProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void addScore(ActionEvent actionEvent) {
    }


    public  BuyLog getBuyLog() {
        return buyLog;
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public  void processDigest() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(ProductsMenu.getProductId());
        OutputHandler.digest(selectedProduct.getId());
    }

    public  void addToCart() throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        if (BuyLog.getFirstProduct()) {
            buyLog = new BuyLog(uniqueID);
        }
        buyLog.addProductToBuyLog(ProductsMenu.getProductId(), 1);
    }


    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
    public  void processAttributes() throws FileNotFoundException {
        OutputHandler.attributes(selectedProduct.getId());
    }

    public  void processCompare(String productID) throws FileNotFoundException {
        if (Product.isThereProductWithId(productID)) {
            OutputHandler.compareProducts(selectedProduct.getId(), productID);
        } else OutputMassageHandler.showProductOutput(4);
    }

    //comment--------------------------------------------------------------------
    public  void processComments() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        OutputHandler.showCommentOnOneProduct(selectedProduct.getId());
    }


    public  void addComments() throws IOException {
        String commentId = LoginMenu.getLoginAccount() + " comment on " + selectedProduct;
        if(!Comment.isThereCommentWithId(commentId)) {
            String id= LoginMenu.getLoginAccount()+ "comment";
            if (!Request.isThereRequestFromID(id)) {
                Comment comment = new Comment(id);
                comment.setCommentStatus(CommentStatus.WAITINGFORAPPROVAL);
                commentRequest = new CommentRequest(id);
                commentRequest.setPersonToVote(LoginMenu.getLoginAccount());
                commentRequest.setProduct(selectedProduct);
                commentRequest.setId(commentId);
            }else commentRequest = (CommentRequest) Request.getRequestFromID(id);
            outputNo = 1;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
            CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        }else outputNo= 3;
        OutputMassageHandler.showProductsOutput(outputNo);
    }


    public void titleOfComment(String title) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        commentRequest.setTitle(commentTitleField.getText());
        OutputMassageHandler.showProductsOutput(2);
    }


    public  void contentOfComment(String content) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        commentRequest.setContent(commnetContentField.getText());
    }


}
