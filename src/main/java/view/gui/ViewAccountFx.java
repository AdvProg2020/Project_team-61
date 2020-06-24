package view.gui;

import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.request.AccountRequest;
import model.request.Request;

import java.io.IOException;
import java.util.Objects;

public class ViewAccountFx {
    @FXML private Label birthday;
    @FXML private Label lastName;
    @FXML private Label role;
    @FXML private Label name;
    @FXML private ImageView accountImg;
    @FXML private Label credit;
    @FXML private Label phoneNo;
    @FXML private Label email;
    @FXML private Label username;

    public static final ObservableList data = FXCollections.observableArrayList();
    private static Parent root;
    private static Account account;
    private static Request request;
    private static Parent priRoot;

    public static void setPriRoot(Parent priRoot) {
        ViewAccountFx.priRoot = priRoot;
    }

    public static void setAccount(Account account) {
        ViewAccountFx.account = account;
    }

    public static void setRequest(Request request) {
        ViewAccountFx.request = request;
    }

    @FXML
    public void initialize()  {
        if (request == null) {
 //           Account curAccount = null;
//            if (account != null) {
//                curAccount = account;
//            }
            Account curAccount = account;
            username.setText(curAccount.getUsername());
            name.setText(curAccount.getName());
            lastName.setText(curAccount.getLastname());
            role.setText(curAccount.getRole());
            phoneNo.setText(String.valueOf(curAccount.getPhoneNo()));
            email.setText(curAccount.getEmail());
            credit.setText(String.valueOf(curAccount.getCredit()));
            birthday.setText(String.valueOf(curAccount.getBirthdayDate()));
        }else showRequest();
    }

    private void showRequest() {
        AccountRequest accountRequest = null;
        if (request instanceof AccountRequest){
             accountRequest = (AccountRequest) request;
        }
        username.setText(accountRequest.getUsername());
        name.setText(accountRequest.getName());
        lastName.setText(accountRequest.getLastname());
        role.setText("seller");
        phoneNo.setText(String.valueOf(accountRequest.getPhoneNo()));
        email.setText(accountRequest.getEmail());
        credit.setText(null);
        birthday.setText(String.valueOf(accountRequest.getBirthdayDate()));
    }



    public void viewCart(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Customer) {
            root = FXMLLoader.load(Objects.requireNonNull(SaleLogFx.class.getClassLoader().getResource("saleLogFx.fxml")));
            goToPage();
        }else show("you can't see cart");
    }

    ///////////////////////
    public void viewCustomerDiscount(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Customer) {
            Customer customer = (Customer) LoginMenu.getLoginAccount();
//            DiscountCodesFx.setDiscounts(customer.getAllDiscountCodes());
        } else if(LoginMenu.getLoginAccount() instanceof Manager) {
            Manager customer = (Manager) LoginMenu.getLoginAccount();
            DiscountCodesFx.setDiscounts(customer.getAllDiscountCodes());
        }  else show("you can't see discounts");
            root = FXMLLoader.load(Objects.requireNonNull(DiscountCodesFx.class.getClassLoader().getResource("DiscountCodesFx.fxml")));
            goToPage();

    }


    public void viewLogs(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Customer) {
            root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
            goToPage();
        }else if(LoginMenu.getLoginAccount() instanceof Seller){
            root = FXMLLoader.load(Objects.requireNonNull(BuyLogsFx.class.getClassLoader().getResource("vuyLogsFx.fxml")));
            goToPage();
        } else show("you can't see logs");
    }


    private static void goToPage(){
        remove();
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
    private static void remove(){
        account = null;
        request = null;
    }

    public void viewFirm(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            ViewFirmFx.setAccount(LoginMenu.getLoginAccount());
        }else if(LoginMenu.getLoginAccount() instanceof Manager){
            ViewFirmFx.setRequest(request);
        }
        root = FXMLLoader.load(Objects.requireNonNull(ViewFirmFx.class.getClassLoader().getResource("viewFirmFx.fxml")));
        goToPage();
    }

    public void edit(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(EditAccountFx.class.getClassLoader().getResource("editAccountFx.fxml")));
        goToPage();
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

}
