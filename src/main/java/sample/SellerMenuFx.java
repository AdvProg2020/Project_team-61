package sample;

import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SellerMenuFx {
    public static final ObservableList data = FXCollections.observableArrayList();
    private static Parent root;
    

    public void viewPersonalInfo(MouseEvent mouseEvent) throws IOException {
        ViewAccountFx.setAccount(LoginMenu.getLoginAccount());
         root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
        goToPage();
    }

    public void viewCompanyInfo(MouseEvent mouseEvent) throws IOException {
        ViewFirmFx.setAccount(LoginMenu.getLoginAccount());
         root = FXMLLoader.load(Objects.requireNonNull(ViewFirmFx.class.getClassLoader().getResource("viewFirmFx.fxml")));
        goToPage();
    }

    public void viewSalesHistory(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
        goToPage();
    }

    public void addProduct(MouseEvent mouseEvent) throws IOException {
        //esmeshoon fargh dare
         root = FXMLLoader.load(Objects.requireNonNull(AddProductMenuFX.class.getClassLoader().getResource("AddProductMenuFX.fxml")));
        goToPage();
    }


    public void removeProduct(MouseEvent mouseEvent) throws IOException {
        //?????????????????????????????????
       // Parent root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
       // goToPage();
    }

    public void viewBalance(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
//        goToPage();
        String balance = String.valueOf(LoginMenu.getLoginAccount().getCredit());
        show("balance: "+balance);
    }

    public void viewOffs(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
        goToPage();
    }

    public void showCategories(MouseEvent mouseEvent) throws IOException {
       root = FXMLLoader.load(Objects.requireNonNull(CategoriesFX.class.getClassLoader().getResource("categoriesFX.fxml")));
        goToPage();
    }

    public void manageProducts(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(ManageProductsFx.class.getClassLoader().getResource("manageProductsFx.fxml")));
        goToPage();
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    private void showList() {
        ListView listView = new ListView(data);
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Stage list = new Stage();
        list.setScene(new Scene(root, 200, 250));
        list.show();
    }

    private void show(String text) {
        Label label = new Label();
        StackPane rot = new StackPane();
        rot.getChildren().add(label);
        label.setText(text);
        Stage massage = new Stage();
        massage.setScene(new Scene(rot, 500, 500));
        massage.show();
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void UserMenu(ActionEvent actionEvent) {
    }
}
