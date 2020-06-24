package view.gui;

import controller.menus.LoginMenu;
import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.productRelated.Category;
import model.sort.Sort;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.Objects;

public class CategoriesFX {

    @FXML private TableColumn<Category, String> categoriesName;
    @FXML private TableView<Category> categories;


    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent priRoot;
    private static Parent root;

    public static void setPriRoot(Parent priRoot) {
        CategoriesFX.priRoot = priRoot;
    }

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void makeList() {
        list.clear();
        list.addAll(Category.getAllCategories());
    }

    private void makeTree() {
        categoriesName.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));


        makeList();
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        categories.setEditable(true);
        categories.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        categories.getSelectionModel().setCellSelectionEnabled(true);

        categories.setItems(list);
    }


    public void editCategories(MouseEvent mouseEvent) throws IOException {
       // if(categories.getSelectionModel().getSelectedItem() != null) {
           // Category curCat = categories.getSelectionModel().getSelectedItem();

        root = FXMLLoader.load(Objects.requireNonNull(AddCategoryFx.class.getClassLoader().getResource("addCategoryFx.fxml")));
            goToPage();
       // }else usersMs.setText("you have to select first");
    }

    public void removeCategories(MouseEvent mouseEvent) {
        if(categories.getSelectionModel().getSelectedItem() != null) {
            Category curCat = categories.getSelectionModel().getSelectedItem();
            OutputMassageHandler.showManagerOutput(ManagerMenu.removeCategory(curCat.getName()));
            makeTree();
        }
    }


    public void sortCategories(MouseEvent mouseEvent) {
        categoriesName.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));

        list.clear();
        Sort.setNewArrayOfCategory(Category.getAllCategories());
        list.addAll( Sort.categoryNameSort());

        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        categories.setEditable(true);
        categories.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        categories.getSelectionModel().setCellSelectionEnabled(true);

        categories.setItems(list);
    }

    public void addCategories(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(AddCategoryFx.class.getClassLoader().getResource("addCategoryFx.fxml")));
        goToPage();
    }


    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    private void show(String text) {
        Label label = new Label();
        StackPane root = new StackPane();
        root.getChildren().add(label);
        label.setText(text);
        Stage massage = new Stage();
        massage.setScene(new Scene(root, 500, 500));
        massage.show();
    }



    public void userMenu(ActionEvent actionEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
        } else if(LoginMenu.getLoginAccount() instanceof Manager){
            root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
        }else if(LoginMenu.getLoginAccount() instanceof Customer){
            root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        }
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

    public void viewCategories(MouseEvent mouseEvent) throws IOException {
        if(categories.getSelectionModel().getSelectedItem() != null) {
            Category curCat = categories.getSelectionModel().getSelectedItem();
            ViewCategoryFx.setCurCat(curCat);
            root = FXMLLoader.load(Objects.requireNonNull(ViewCategoryFx.class.getClassLoader().getResource("ViewCategoryFx.fxml")));

        }
    }
}
