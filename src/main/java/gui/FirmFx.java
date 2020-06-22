package gui;

import controller.menus.RegisterMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.Objects;

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
    private static Parent priRoot;

    public static void setPriRoot(Parent priRoot) {
        FirmFx.priRoot = priRoot;
    }

    public void addFirm(MouseEvent mouseEvent) throws IOException {
        if (type != null) {
            if (RegisterMenu.getDetailMenu() == 0)
                firmNameCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmNameCr.getText(), 0)));
            if (RegisterMenu.getDetailMenu() == 1)
                firmPhoneNoCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmPhoneCr.getText(), 1)));
            if (RegisterMenu.getDetailMenu() == 2)
                firmAddressCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmAddressCr.getText(), 2)));
            if (RegisterMenu.getDetailMenu() == 3)
                RegisterMenu.createFirm(type, 3);
            if (RegisterMenu.getDetailMenu() == 4) {
                firmEmailCrMs.setText(OutputMassageHandler.showFirmOutput(RegisterMenu.createFirm(firmEmailCr.getText(), 4)));
                RegisterMenu.setDetailMenu(0);
                root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
                goToPage();
            }
            //type = null;
        }else firmNameCrMs.setText("please enter type first");
    }

    public void userMenu(ActionEvent actionEvent) throws IOException {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    private static void goToPage() {
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
