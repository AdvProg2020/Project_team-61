package sample;

import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.accounts.Seller;

public class ViewFirmFx {

    @FXML
    private Label firmPhoneNo;

    @FXML
    private Label fiemAddress;

    @FXML
    private Label firmName;

    @FXML
    private Label firmEmail;

    @FXML
    public void initialize()  {
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            Seller account = (Seller) LoginMenu.getLoginAccount();
            firmName.setText(account.getFirm().getName());
            firmEmail.setText(account.getFirm().getEmail());
            firmPhoneNo.setText(String.valueOf(account.getFirm().getPhoneNO()));
            fiemAddress.setText(account.getFirm().getAddress());
        }

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
