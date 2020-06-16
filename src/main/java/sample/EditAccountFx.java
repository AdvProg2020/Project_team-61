package sample;

import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import model.accounts.Seller;
import view.OutputMassageHandler;

import java.io.IOException;

public class EditAccountFx {

    @FXML
    private TextField userSign;

    @FXML
    private TextField lastNameSign;

    @FXML
    private Label nameLoginMs;

    @FXML
    private Label phoneLoginMs;

    @FXML
    private PasswordField passSign;

    @FXML
    private TextField phoneNoSign;

    @FXML
    private Label emailLoginMs;

    @FXML
    private Label passLoginMs;

    @FXML
    private TextField emailSign;

    @FXML
    private TextField nameSign;

    @FXML
    private Label userLoginMs;

    @FXML
    private Label lastNameLoginMs;



    public void picOver(DragEvent dragEvent) {
    }

    public void picDrop(DragEvent dragEvent) {
    }

    public void editAccount(MouseEvent mouseEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            LoginMenu.edit();
            passLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(passSign.getText(), "password")));
            userLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(userSign.getText(), "username")));
            lastNameLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(lastNameSign.getText(), "last name")));
            emailLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(emailSign.getText(), "email")));
            phoneLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(phoneNoSign.getText(), "phone number")));
            nameLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editSellerField(nameSign.getText(), "name")));
        }
        passLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(passSign.getText(), "password")));
        userLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(userSign.getText(), "username")));
        lastNameLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(lastNameSign.getText(), "last name")));
        emailLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(emailSign.getText(), "email")));
        phoneLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(phoneNoSign.getText(), "phone number")));
        nameLoginMs.setText(OutputMassageHandler.showAccountOutput(LoginMenu.editAccount(nameSign.getText(), "name")));

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
    }
}
