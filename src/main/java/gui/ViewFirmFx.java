package gui;

import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import model.accounts.Account;
import model.accounts.Seller;
import model.request.AccountRequest;
import model.request.Request;

public class ViewFirmFx {

    @FXML
    private Label firmPhoneNo;

    @FXML
    private Label firmAddress;

    @FXML
    private Label firmName;

    @FXML
    private Label firmEmail;

    private static Parent priRoot;
    private static Account account;
    private static Request request;

    public static void setPriRoot(Parent priRoot) {
        ViewFirmFx.priRoot = priRoot;
    }

    public static void setRequest(Request request) {
        ViewFirmFx.request = request;
    }

    public static void setAccount(Account account) {
        ViewFirmFx.account = account;
    }

    @FXML
    public void initialize()  {
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            Seller seller = (Seller) account;
            firmName.setText(seller.getFirm().getName());
            firmEmail.setText(seller.getFirm().getEmail());
            firmPhoneNo.setText(String.valueOf(seller.getFirm().getPhoneNO()));
            firmAddress.setText(seller.getFirm().getAddress());
        }else firmRequest();
    }

    private void firmRequest() {
        AccountRequest accountRequest = null;
        if (request instanceof AccountRequest){
            accountRequest = (AccountRequest) request;
        }
        firmName.setText(accountRequest.getFirmName());
        firmEmail.setText(accountRequest.getFirmEmail());
        firmPhoneNo.setText(String.valueOf(accountRequest.getFirmPhoneNO()));
        firmAddress.setText(accountRequest.getFirmAddress());
    }

    private void remove(){
        account = null;
        request = null;
    }

    public void userMenu(ActionEvent actionEvent) {
        remove();
    }

    public void logout(ActionEvent actionEvent) {
        remove();
    }

    public void back(ActionEvent actionEvent) {
        remove();
    }

    public void exit(ActionEvent actionEvent) {

    }
}
