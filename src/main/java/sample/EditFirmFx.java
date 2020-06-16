package sample;

import controller.menus.LoginMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.OutputMassageHandler;

import java.io.IOException;

public class EditFirmFx {
    @FXML
    private Label firmPhoneNoCrMs;
    @FXML
    private Label firmEmailCrMs;
    @FXML
    private Label firmNameCrMs;
    @FXML
    private TextField firmAddressCr;
    @FXML
    private Label firmAddressCrMs;
    @FXML
    private TextField firmEmailCr;
    @FXML
    private TextField firmNameCr;
    @FXML
    private TextField firmPhoneCr;
    private String type;
    private static Parent root;

    public void exit(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void addFirm(MouseEvent mouseEvent) throws IOException {
        String firmName = firmNameCr.getText();
        String firmEmail = firmEmailCr.getText();
        String firmNAddress = firmAddressCr.getText();
        String firmPhone = firmPhoneCr.getText();
        firmNameCrMs.setText(OutputMassageHandler.showFirmOutput(LoginMenu.firmName(firmName)));
        firmEmailCrMs.setText(OutputMassageHandler.showFirmOutput(LoginMenu.editFirm(firmEmail,"email")));
        firmAddressCrMs.setText(OutputMassageHandler.showFirmOutput(LoginMenu.editFirm(firmNAddress,"address")));
        firmPhoneNoCrMs.setText(OutputMassageHandler.showFirmOutput(LoginMenu.editFirm(firmPhone,"phone number")));

    }
}
