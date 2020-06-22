package gui;

import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import model.off.DiscountCode;

import java.io.IOException;
import java.util.Objects;

public class ManagerMenuFx {
    private static Parent root;
    private static Parent priRoot;

    public static void setPriRoot(Parent priRoot) {
        ManagerMenuFx.priRoot = priRoot;
    }

    public void back(ActionEvent actionEvent) {
        root = priRoot;
        goToPage();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logout(ActionEvent actionEvent) {
        LoginMenu.processLogout();
    }

    public void viewPersonalInfo(MouseEvent mouseEvent) throws IOException {
        ViewAccountFx.setAccount(LoginMenu.getLoginAccount());
         root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
        goToPage();
    }

    public void manageAllProducts(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(ManageProductsFx.class.getClassLoader().getResource("manageProductsFx.fxml")));
        goToPage();
    }

    public void viewDiscount(MouseEvent mouseEvent) throws IOException {
        DiscountCodesFx.setDiscounts(DiscountCode.getAllDiscountCodes());
         root = FXMLLoader.load(Objects.requireNonNull(ViewDiscountFx.class.getClassLoader().getResource("viewDiscountFx.fxml")));
        goToPage();
    }

    public void createDiscount(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(AddDiscountFx.class.getClassLoader().getResource("addDiscountFx.fxml")));
        goToPage();
    }

    public void manageCategories(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(CategoriesFX.class.getClassLoader().getResource("categoriesFX.fxml")));
        goToPage();
    }

    public void manageRequests(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(RequestsFx.class.getClassLoader().getResource("requestsFx.fxml")));
        goToPage();
    }

    public void manageUsers(MouseEvent mouseEvent) throws IOException {
         root = FXMLLoader.load(Objects.requireNonNull(UsersFx.class.getClassLoader().getResource("usersFx.fxml")));
        goToPage();
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

}
