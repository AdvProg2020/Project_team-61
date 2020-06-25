package view.gui;

import controller.menus.LoginMenu;
import controller.menus.SellerMenu;
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

import model.accounts.Seller;
import model.productRelated.Category;
import model.productRelated.Product;
import view.OutputMassageHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    public static Scene prevScene;
    public static Stage thisStage;
    public TextField categoryNameTextField;
    public TextField sellerNameTextField;
    public TextField idTextField;
    public TextField numberOfProductTextField;
    public TextArea additionaldetailTextField;
    public boolean finish = false;
    @FXML
    public TableView<Category> categoryTableView = new TableView<>();

    @FXML
    public TableColumn<Category, String> categoryForPro = new TableColumn<>("categoryName");

    @FXML
    public TableColumn<Category, TextField> details = new TableColumn<>("specifications");

    @FXML
    public static ObservableList<Category> data = FXCollections.observableArrayList();
    public AnchorPane pane;
    public ArrayList<TextField> traitsTextFields = new ArrayList<>();
    public ArrayList<String> traits = new ArrayList<>();
    String imageId;
    List<File> files;
    Category productCategory;
    ArrayList<String> traitText = new ArrayList<>();


    public static void showProPage(Stage stage, Scene scene) throws IOException {
        thisStage = stage;
        prevScene = scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("productsMenu.fxml")));
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
        String ms = null;
        if(imageId != null) {
            if (SellerMenu.getCreate() == 0) {
                ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(idTextField.getText(), 0, imageId));
                Seller.writeInJ();
            }
            if (SellerMenu.getCreate() == 1) {
                if (SellerMenu.getDetailMenu() == 1) {
                    ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(productNameTextField.getText(), 1, null));
                    Seller.writeInJ();
                }
                if (SellerMenu.getDetailMenu() == 2) {
                    ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(priceTextField.getText(), 2, null));
                    Seller.writeInJ();
                }
                if (SellerMenu.getDetailMenu() == 3) {
                    ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(categoryNameTextField.getText(), 3, null));
                    Seller.writeInJ();
                }
                if (SellerMenu.getDetailMenu() == 4) {
                    ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(additionaldetailTextField.getText(), 4, null));
                    Seller.writeInJ();
                }
                if (SellerMenu.getDetailMenu() == 5) {
                    ms = OutputMassageHandler.showSellerOutput(SellerMenu.addProduct(numberOfProductTextField.getText(), 5, null));
                    addImageView();
                    addCategoryTrait();
                    Seller.writeInJ();
                }
                if (SellerMenu.getDetailMenu() == 6) {
                    if (finish) {
                        //  addCategoryTrait();
                        //if(changeToString()) {
                        changeToString();
                        SellerMenu.getProductRequest().setSpecialValue(traits);
                        finish = true;
                        // }else ms ="fill all field";
                    } else ms = "process add finished you should edit";
                    finish = true;
                }
            }
        }else ms= "inset image first";
        error.setVisible(true);
        error.setText(ms);
    }

    public void editProduct(MouseEvent actionEvent) throws IOException {
        String ms = null;
        error.setText("you have to put all traits value");
        if (SellerMenu.getEdit() == 0) {
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProduct(idTextField.getText()));
        }
        if (SellerMenu.getEdit() == 1) {
            addImageView();
            addCategoryTrait();
            changeToString();
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProductField(productNameTextField.getText(), "name"));
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProductField(priceTextField.getText(), "price"));
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProductField(categoryNameTextField.getText(), "category"));
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProductField(additionaldetailTextField.getText(), "additional"));
            ms = OutputMassageHandler.showSellerOutput(SellerMenu.editProductField(numberOfProductTextField.getText(), "number"));
            Product.getProductById(idTextField.getText()).setProductCategorySpecifications(traits);
            // }
        } else ms = "put id first";
        error.setVisible(true);
        error.setText(ms);
    }

    private void changeToString() {
        for (TextField traitsTextField : traitsTextFields) {
            traits.add(traitsTextField.getText());
        }

    }

    public void backToProducts(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ProductMenuFX.class.getClassLoader().getResource("productsMenu.fxml")));
        thisStage = new Stage();
        prevScene = new Scene(root);
        thisStage.setScene(prevScene);
        thisStage.show();
    }

    public static void initializeObserverList() {
        data.addAll(Category.getAllCategories());
    }


    @FXML
    public void initialize() throws IOException {
        categoryForPro.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        details.setCellValueFactory(new PropertyValueFactory<Category, TextField>("detail"));
        initializeObserverList();
        categoryTableView.getColumns().addAll(categoryForPro, details);
        categoryTableView.setItems(data);
        addImageView();
    }

    private void addCategoryTrait() throws IOException {
        traitsTextFields.clear();
        Category category1 = Category.getCategoryWithName(categoryNameTextField.getText());
        for (String trait : category1.getTraits()) {
            int n = 50;
            Label label = new Label();
            label.setText(trait);
            label.setLayoutY(n * (category1.getTraits().indexOf(trait) + 1));
            label.setLayoutX(900 + category1.getTraits().indexOf(trait));
            TextField textField = new TextField();
            textField.setMaxSize(100, 50);
            textField.setLayoutY((n) * (category1.getTraits().indexOf(trait) + 1));
            textField.setLayoutX(1000 + category1.getTraits().indexOf(trait));

            traitsTextFields.add(textField);
            pane.getChildren().addAll(label, textField);
            n += 5;
        }
        Seller.writeInJ();
    }

    private void addImageView() throws IOException {
        productImage.setVisible(true);
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
        Seller.writeInJ();
    }

    private void backToFirst(){
        SellerMenu.setCreate(0);
        SellerMenu.setEdit(0);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        backToFirst();
        LoginMenu.processLogout();
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();

    }
    /*

     @FXML
    public void addProduct(MouseEvent actionEvent) throws IOException {
        String id = idTextField.getText();
        String name = productNameTextField.getText();
        String price = priceTextField.getText();
        String category = categoryNameTextField.getText();
        //String seller = sellerNameTextField.getText();
        String seller = LoginMenu.getLoginAccount().getUsername();
        String numberOfProduct = numberOfProductTextField.getText();
        String additionalDetail = additionaldetailTextField.getText();



        if (!name.equals("")) {
            if (!price.equals("")) {
                if (!category.equals("") && Category.isThereCategoryWithName(category)) {
                    productImage.setVisible(true);
                    Product product = new Product(id);
                    product.setDetailProduct(imageId, name, Integer.parseInt(price), Category.getCategoryWithName(category), Account.getAccountWithUsername(seller), Seller.getAccountWithUsername(seller).getFirm(), Integer.parseInt(numberOfProduct));
                    product.setAdditionalDetail(additionalDetail);
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
                    Category category1 = Category.getCategoryWithName(category);
                    for (String trait : category1.getTraits()) {
                        int n = 50;
                        Label label = new Label();
                        label.setText(trait);
                        label.setLayoutY(n * (category1.getTraits().indexOf(trait) + 1));
                        label.setLayoutX(900 + category1.getTraits().indexOf(trait));
                        TextField textField = new TextField();
                        textField.setMaxSize(100, 50);
                        textField.setLayoutY((n) * (category1.getTraits().indexOf(trait) + 1));
                        textField.setLayoutX(1000 + category1.getTraits().indexOf(trait));
                        traitsTextFields.add(textField);
                        pane.getChildren().addAll(label, textField);
                        n += 5;
                    }
                } else {
                    error.setVisible(true);
                    error.setText("please inter valid category");
                }
            } else {
                error.setVisible(true);
                error.setText("please inter a valid price");
            }
        } else {
            error.setVisible(true);
            error.setText("please inter valid product name");
            System.out.println("heh");

        }
    }



     */

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