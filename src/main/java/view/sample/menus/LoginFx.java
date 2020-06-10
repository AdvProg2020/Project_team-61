package view.sample.menus;

import controller.menus.LoginMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.OutputMassageHandler;

public class LoginFx {
    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField passLogin;

    @FXML
    private Label passLoginMs;

    @FXML
    private Label userLoginMs;

    public void login(MouseEvent mouseEvent) {
        String username = userLogin.getText();
        String password = passLogin.getText();
        int user =  LoginMenu.processLogin(username);
        int pass =  LoginMenu.checkPassword(password);
        userLoginMs.setText(OutputMassageHandler.showAccountOutput(user));
        passLoginMs.setText(OutputMassageHandler.showAccountOutput(pass));
    }

    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {
    }

    public void signUpMenu(MouseEvent mouseEvent) {
    }
}
