package sample;

import controller.menus.CustomerMenu;
import controller.menus.RegisterMenu;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Customer;
import view.OutputMassageHandler;
import view.SubMenuStatus;

import java.io.IOException;

public class PayLogFx {
    @FXML
    TextField PhoneNumberTextFieldPayLog;
    @FXML TextArea AddressTextPayLog;
    @FXML TextField hasDiscount;
    @FXML
    Label alertMessage;
    @FXML
    TextField discountPayLogTextField;
    //int output;


    SubMenuStatus subMenuStatus=SubMenuStatus.RECIVERINFORMATION;
    //public RegisterMenu registerMenu;
    int detailMenu=0;
   // public CustomerMenu customerMenu;
    public void paymentProcess(MouseEvent mouseEvent) throws IOException {
        //String ms;
        RegisterMenu.receiverInformation(PhoneNumberTextFieldPayLog.getText());
        RegisterMenu.receiverInformation(AddressTextPayLog.getText());
        //ms=OutputMassageHandler.showCustomerOutput(output);
        RegisterMenu.receiverInformation(hasDiscount.getText());
        if (hasDiscount.getText().matches("no")){
            discountPayLogTextField.setDisable(true);
        }
        else {
            CustomerMenu.checkDiscountCode(discountPayLogTextField.getText());
            if(CustomerMenu.checkDiscountCode(discountPayLogTextField.getText())){
                CustomerMenu.discountCodeValidation(discountPayLogTextField.getText());
            }
        }
        CustomerMenu.payment();
        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));

    }
   /* public void alertMessage(){
        Stage window=new Stage();
        Scene scene ;
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout=new VBox();
        TextArea textArea=new TextArea("Payment successfully");
        Button button =new Button("ok");
        layout.getChildren().addAll(button,textArea);
        layout.setAlignment(Pos.CENTER);
        button.setOnAction(e->window.close());
        scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }*/

    public void gotoAccount(MouseEvent mouseEvent) {
    }

    public void processExit(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
    }
}