package sample;

import controller.menus.RegisterMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import view.OutputMassageHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class SignUpFx {

    @FXML
    private TextField phoneNoSign;
    @FXML
    private TextField userSign;
    @FXML
    private TextField lastNameSign;
    @FXML
    private PasswordField passSign;
    @FXML
    private TextField emailSign;
    @FXML
    private TextField nameSign;
    @FXML
    private TextField birthdaySign;
    @FXML
    private Label emailLoginMs;
    @FXML
    private Label userLoginMs;
    @FXML
    private Label birthLoginMs;
    @FXML
    private Label nameLoginMs;
    @FXML
    private Label phoneLoginMs;
    @FXML
    private Label passLoginMs;
    @FXML
    private Label lastNameLoginMs;

    private static String role;
    private static  Parent root;

    public static void setRole(String role) {
        SignUpFx.role = role;
    }

    public void picDrop(DragEvent dragEvent) {

    }

    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void picOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasContent(DataFormat.IMAGE)) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
        dragEvent.consume();
    }

    /*  String username = userSign.getText();
   String name = nameSign.getText();
   String lastName = lastNameSign.getText();
   String phone = phoneNoSign.getText();
   String password = passSign.getText();
   String email = emailSign.getText();
   String birthday = birthdaySign.getText();

  */
    public void signUp(MouseEvent mouseEvent) throws IOException, ParseException {
        userLoginMs.setText(OutputMassageHandler.showAccountOutput(RegisterMenu.processRegister(role,userSign.getText())));
        passLoginMs.setText(OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(passSign.getText())));
        nameLoginMs.setText( OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(nameSign.getText())));
        lastNameLoginMs.setText( OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(lastNameSign.getText())));
        emailLoginMs.setText(OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(emailSign.getText())));
        phoneLoginMs.setText(OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(phoneNoSign.getText())));
        birthLoginMs.setText( OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(birthdaySign.getText())));
        goToMenu();
    }

    private void goToMenu() throws IOException {
        if(role == "seller") {
            root = FXMLLoader.load(Objects.requireNonNull(FirmFx.class.getClassLoader().getResource("firmFx.fxml")));
        }else {
            root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
        }
        Scene pageTwoScene = new Scene(root);
        // Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();

    }


    public void sellerRole(MouseEvent mouseEvent) {
        role="seller";
    }

    public void customerRole(MouseEvent mouseEvent) {
        role = "customer";
    }
}
