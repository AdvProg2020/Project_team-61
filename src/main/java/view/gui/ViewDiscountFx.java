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
import javafx.scene.control.ListView;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.off.DiscountCode;

import java.io.IOException;
import java.util.Objects;


public class ViewDiscountFx {
    @FXML private Label startDiscountInfo;
    @FXML private Label maxAmountInfo;
    @FXML private Label timesOfUseInfo;
    @FXML private Label discountIdInfo;
    @FXML private Label discountAmountInfo;
    @FXML private Label endDiscountInfo;
    @FXML private ListView<Account> allAccountsInfo;

    private static DiscountCode curDiscountCode;
    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent root;
    private static Parent priRoot;

    public static void setPriRoot(Parent priRoot) {
        ViewDiscountFx.priRoot = priRoot;
    }

    public static DiscountCode getCurDiscountCode() {
        return curDiscountCode;
    }

    public static void setCurDiscountCode(DiscountCode curDiscountCode) {
        ViewDiscountFx.curDiscountCode = curDiscountCode;
    }


    private void makeTree() {
        allAccountsInfo.setEditable(true);
        list.clear();
        list.addAll(curDiscountCode.getAllCustomersWithDiscountCode());
        allAccountsInfo.getItems().addAll(list);

    }

    @FXML
    public void initialize()  {
        makeTree();
        startDiscountInfo.setText(String.valueOf(curDiscountCode.getStartOfDiscountPeriod()));
        maxAmountInfo.setText(String.valueOf(curDiscountCode.getMaxDiscountAmount()));
        timesOfUseInfo.setText(String.valueOf(curDiscountCode.getTotalTimesOfUse()));
        discountIdInfo.setText(String.valueOf(curDiscountCode.getDiscountId()));
        discountAmountInfo.setText(String.valueOf(curDiscountCode.getDiscountAmount()));
        endDiscountInfo.setText(String.valueOf(curDiscountCode.getEndOfDiscountPeriod()));

    }

    private void remove() {

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
    private static void goToPage() {
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

}
