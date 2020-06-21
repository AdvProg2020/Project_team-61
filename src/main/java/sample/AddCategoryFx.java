package sample;

import controller.menus.LoginMenu;
import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.accounts.Manager;
import model.productRelated.Category;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;

public class AddCategoryFx {


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
    @FXML
    private ListView<String> categoryTraits;

    private String catName;
    public static ObservableList list = FXCollections.observableArrayList();
    ArrayList<String> usernames = new ArrayList<>();

    @FXML
    public void initialize() throws IOException {
        if (catName != null) {
            makeTree();
        }
    }

    public void makeList() {
        list.clear();
        categoryTraits.getItems().addAll(list);
    }

    public void makeTree() {
        categoryTraits.setEditable(true);
        categoryTraits.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       makeList();
       list.addAll(Category.getCategoryWithName(catName).getTraits());
       categoryTraits.getItems().addAll(list);
    }


    public void createCategory(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Manager) {
        if (ManagerMenu.getCreate() == 0) {
            categoryIdMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.addCategory(categoryId.getText())));
            catName = categoryId.getText();
        }
        if (ManagerMenu.getCreate() == 1) {
            categoryAddTraitMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToCategory(categoryAddTrait.getText())));
            makeTree();
        }
    }else categoryTraitMs.setText("only manager have access");
    }


    public void removeTrait(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Manager) {
        if (ManagerMenu.getEditableCategory()!= null) {
            categoryAddTraitMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.removeTraitToCatEdit(categoryAddTrait.getText())));
            makeTree();
        }else categoryTraitMs.setText("you have to put name and edit first");
        }else categoryTraitMs.setText("only manager have access");
    }

    public void editCategory(MouseEvent mouseEvent) {
        categoryIdMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.editCategory(categoryId.getText())));

    }

    public void addTrait(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Manager) {
            if (ManagerMenu.getEditableCategory() != null) {
                categoryAddTraitMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.addTraitToCatEdit(categoryAddTrait.getText())));
                makeTree();
            } else categoryTraitMs.setText("you have to put name and edit first");
        }else categoryTraitMs.setText("only manager have access");
    }

     /*  public void makeTree() {
        categoryTrait.setCellValueFactory(new PropertyValueFactory<Category, String>("traits"));
        categoryTraits.setEditable(true);
        categoryTraits.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.clear();
     //   for (int i = 0; i < Category.getTraits().size() ; i++) {
       //     usernames.add(Category.getTraits().get(i).);
       // }
        list.addAll(Category.getCategoryWithName(catName).getTraits());
        categoryTraits.setItems(list);
       // categoryTraits.getItems().addAll(list);
    }
     public void removeTrait(MouseEvent mouseEvent) throws IOException {
        if(categoryTraits.getSelectionModel().getSelectedItem() != null) {
            String str = categoryTraits.getSelectionModel().getSelectedItem();
            categoryTraitMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.removeTraitToCatEdit(str)));
            makeTree();
        }else categoryTraitMs.setText("you have to select first");

    }

   */

    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {

    }
}


