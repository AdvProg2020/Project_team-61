package sample;

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
import model.accounts.Account;
import model.productRelated.Category;
import model.sort.Sort;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class CategoriesFX {

    @FXML private TableColumn<Category, String> categoriesName;
    @FXML private TableView<Category> categories;
    private static Parent root;

    public static ObservableList list = FXCollections.observableArrayList();


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


    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }


}
