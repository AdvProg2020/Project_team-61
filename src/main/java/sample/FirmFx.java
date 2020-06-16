package sample;

import controller.menus.RegisterMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.OutputMassageHandler;

import java.io.IOException;

public class FirmFx {
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

    public void addFirm(MouseEvent mouseEvent) throws IOException {
        if(type != null) {
            String firmName = firmNameCr.getText();
            String firmEmail = firmEmailCr.getText();
            String firmAddress = firmAddressCr.getText();
            String firmPhone = firmPhoneCr.getText();
            firmNameCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmName)));
            firmPhoneNoCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmPhone)));
            firmAddressCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmAddress)));
            RegisterMenu.createFirm(type);
            firmEmailCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmEmail)));
            type = null;
        }
    }

    public void userMenu(ActionEvent actionEvent) throws IOException {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }



    public void company(MouseEvent mouseEvent) {
        type = "company";
    }

    public void factory(MouseEvent mouseEvent) {
        type = "factory";
    }

    public void workshop(MouseEvent mouseEvent) {
        type = "workshop";
    }
}
