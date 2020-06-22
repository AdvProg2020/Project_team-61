package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.off.DiscountCode;


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

    public void back(MouseEvent mouseEvent) {
        remove();
    }

    public void exit(MouseEvent mouseEvent) {
        remove();
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
        remove();
    }

}
