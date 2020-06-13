package sample;

import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;

import java.io.IOException;
import java.util.Objects;

public class ViewAccountFx {
    @FXML
    private Label birthday;

    @FXML
    private Label lastName;

    @FXML
    private Label role;

    @FXML
    private Label name;

    @FXML
    private ImageView accountImg;

    @FXML
    private Label credit;

    @FXML
    private Label phoneNo;

    @FXML
    private Label email;

    @FXML
    private Label username;

    public static final ObservableList data = FXCollections.observableArrayList();
    private static Parent root;

    @FXML
    public void initialize()  {
        Account account = LoginMenu.getLoginAccount();
        username.setText(account.getUsername());
        name.setText(account.getName());
        lastName.setText(account.getLastname());
        role.setText(account.getRole());
        phoneNo.setText(String.valueOf(account.getPhoneNo()));
        email.setText(account.getEmail());
        credit.setText(String.valueOf(account.getCredit()));
        birthday.setText(String.valueOf(account.getBirthdayDate()));

    }

    public void edit(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
    }

    public void viewCart(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SaleLogFx.class.getClassLoader().getResource("saleLogFx.fxml")));
        goToPage();
    }

    public void viewCustomerDiscount(MouseEvent mouseEvent) {
    }

    public void viewBougts(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
        goToPage();
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
}
