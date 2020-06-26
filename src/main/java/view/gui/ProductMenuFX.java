package view.gui;

import controller.ProductMenu;
import controller.ProductsMenu;
import controller.menus.CustomerMenu;
import controller.menus.LoginMenu;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.Product;
import model.request.ProductRequest;
import model.request.Request;
import view.OutputMassageHandler;


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

    public TextArea commentTextField;
    public TextField titleTextField;
    public Button backButtonAddComment;
    public Button sendCommentButton;
    public AnchorPane addCommentSectionPane = new AnchorPane();
    public Label nullAddCommentError = new Label();
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
    public TableColumn<Comment, String> personWhoCommented = new TableColumn<>("person commented on this");

    @FXML
    public static ObservableList<Comment> data = FXCollections.observableArrayList();
    @FXML
    private Label scoreMs;
    private int score = 0;
    private static Parent priRoot;
    private static Parent root;
    private static Request request;

    public static void setRequest(Request request) {
        ProductMenuFX.request = request;
    }

    public static void setPriRoot(Parent priRoot) {
        ProductMenuFX.priRoot = priRoot;
    }

    public static void setProductInPage(Product productInPage) {
        ProductMenuFX.productInPage = productInPage;
    }

    public void makeUpPage() throws IOException {

        if (request == null) {
            productNameLabel.setText(productInPage.getProductName());
            File file = new File(productInPage.getProductImage());
            Image image = new Image(new FileInputStream(file));
            productPic.setImage(image);
            productDetail.setText("Id : " + productInPage.getId() + "\n" +
                    "Name : " + productInPage.getProductName() + "\n" +
                    "Price : " + productInPage.getPrice() + "\n" +
                    "Seller : " + productInPage.getSeller() + "\n" +
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
        } else makeRequest();
    }

    private void makeRequest() throws FileNotFoundException {
        if (request instanceof ProductRequest) {
            ProductRequest productRequest = (ProductRequest) request;
            productNameLabel.setText(productRequest.getProductName());
            File file = new File(productRequest.getImg());
            Image image = new Image(new FileInputStream(file));
            productPic.setImage(image);
            productDetail.setText("Id : " + productRequest.getProductId() + "\n" +
                    "Name : " + productRequest.getProductName() + "\n" +
                    "Price : " + productRequest.getPrice() + "\n" +
                    "Seller : " + productRequest.getSeller() + "\n" +
                    "Category : " + productRequest.getCategoryName() + "\n"
            );
            productDetail.setEditable(false);
            for (String productCategorySpecification : productRequest.getSpecialValue()) {
                System.out.println(productCategorySpecification);
            }
            for (String specification : productRequest.getSpecialValue()) {

                if (specification != null && !specification.equals("")) {
                    for (String productCategorySpecification : productRequest.getSpecialValue()) {
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
    }

    @FXML
    void popUpAddComment(MouseEvent mouseEvent) throws IOException {
        if (LoginMenu.isLogin()) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("comment.fxml")));
            prevScene = new Scene(root);
            thisStage = new Stage();
            thisStage.setScene(prevScene);
            thisStage.show();
        } else {
            nullAddCommentError.setVisible(true);
            nullAddCommentError.setText("sign In first");
            nullAddCommentError.setVisible(true);

        }
    }

    public void handleAddProductToLog(ActionEvent actionEvent) throws IOException {
        // if (LoginMenu.isLogin()){
        try {
            ProductMenu.setProductId(productInPage.getId());
            ProductMenu.addToCart();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BuyLogFx.setPriRoot(productPagePane);
        BuyLogFx.setCurBuylog(ProductMenu.getBuyLog());
        Parent cur = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenu.fxml")));
        BuyLogFx.setPriRoot(cur);

        //  BuyLogFx.getCurBuyLog().setBuyLogCustomer(LoginMenu.getLoginAccount());
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(BuyLogFx.class.getClassLoader().getResource("buyLogFx.fxml")));
        Scene scene = new Scene(root);
        Main.primStage.setScene(scene);
        Main.primStage.show();

        // }
//        else {
//            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("LoginFx.fxml")));
//            Scene scene = new Scene(root);
//            Main.primStage.setScene(scene);
//            Main.primStage.show();
//        }
    }

    public void handleBackAddCommentButton(ActionEvent actionEvent) throws IOException {
        thisStage.close();
    }

    public void handleSendComment(ActionEvent actionEvent) throws IOException {
        ProductMenu.setSelectedProduct(productInPage);
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.addComments()));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.contentOfComment(commentTextField.getText())));
        nullAddCommentError.setText(OutputMassageHandler.showProductOutput(ProductMenu.titleOfComment(titleTextField.getText())));
    }

    public String handleProCatDetail() {

        String out = "";
        if (request == null) {
            for (String specification : productInPage.productCategorySpecifications) {
                out += specification + "\n";
            }
        } else {
            if (request instanceof ProductRequest) {
                ProductRequest productRequest = (ProductRequest) request;
                for (String specification : productRequest.getSpecialValue()) {
                    out += specification + "\n";
                }
            }
        }
        return out;

    }

    @FXML
    public void initialize() throws IOException {

        if (request == null) {
            titleColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("title"));
            contentColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("content"));
            personWhoCommented.setCellValueFactory(new PropertyValueFactory<Comment, String>("personName"));

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
            data.clear();
        }
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
        score = 2;
    }

    public void handleScore(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("ScoreFx.fxml")));
        Scene pageTwoScene = new Scene(root);
        Stage stage= new Stage();
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(pageTwoScene);
        stage.show();
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
        Parent curRoot = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productMenu.fxml")));
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
