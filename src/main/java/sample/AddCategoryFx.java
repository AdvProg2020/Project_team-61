package sample;

import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.productRelated.Category;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategoryFx {

    @FXML
    private TableColumn<String, String> categoryTrait;

    @FXML
    private TableView<String> categoryTraits;

    @FXML
    private Label categoryIdMs;

    @FXML
    private TextField categoryId;
    @FXML
    private Label categoryTraitMs;

    @FXML
    private Label categoryAddTraitMs;
    @FXML
    private TextField categoryAddTrait;
    private String catName;

    public static ObservableList list = FXCollections.observableArrayList();
    ArrayList<String> usernames = new ArrayList<>();

    @FXML
    public void initialize() throws IOException {
        if (catName != null) {
            makeTree();
        }
    }

    public void makeTree() {
        categoryTraits.setEditable(true);
        categoryTraits.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.clear();
     //   for (int i = 0; i < Category.getTraits().size() ; i++) {
       //     usernames.add(Category.getTraits().get(i).);
       // }
        list.addAll(Category.getCategoryWithName(catName).getTraits());
        categoryTraits.getItems().addAll(list);
    }


    public void createCategory(MouseEvent mouseEvent) throws IOException {

        if (ManagerMenu.getCreate() == 0) {
            categoryIdMs.setText(OutputMassageHandler.showAccountOutput(ManagerMenu.addCategory(categoryId.getText())));
            catName = categoryId.getText();

        }
        if (ManagerMenu.getCreate() == 1) {
            //String trait = categoryTraits.getSelectionModel().getSelectedItem();
            categoryAddTraitMs.setText(OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToCategory(categoryAddTrait.getText())));
            makeTree();
        }
    }


    public void removeTrait(MouseEvent mouseEvent) {
    }

    public void editCategory(MouseEvent mouseEvent) {
    }

    public void addTrait(MouseEvent mouseEvent) {
    }


    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {

    }
}


