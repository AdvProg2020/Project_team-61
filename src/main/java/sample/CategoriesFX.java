package sample;

import controller.menus.ManagerMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.productRelated.Category;
import view.OutputMassageHandler;

import java.io.IOException;

public class CategoriesFX {

    @FXML
    private TableColumn<Category, ?> categoriesName;

    @FXML
    private TableView<Category> categories;
    private static Parent root;


    public void editCategories(MouseEvent mouseEvent) {
    }

    public void removeCategories(MouseEvent mouseEvent) {
        Category cat = (Category) categories.getSelectionModel().getSelectedItem();
        OutputMassageHandler.showManagerOutput(ManagerMenu.removeCategory(cat.getName()));
    }

    public void addCategories(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(getClass().getResource("addCategory.fxml"));
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
