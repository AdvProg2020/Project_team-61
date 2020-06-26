package view.gui;

import controller.menus.CustomerMenu;
import controller.menus.RegisterMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Customer;
import model.log.BuyLog;
import view.OutputMassageHandler;


import java.io.IOException;
import java.util.BitSet;
import java.util.Objects;

public class PayLogFx {
    @FXML
     private TextField phoneNumberTextFieldPayLog=new TextField();
    @FXML private TextArea addressTextPayLog=new TextArea();
   // @FXML TextField hasDiscount;
    @FXML
    Label alertMessage=new Label();
    @FXML
    private TextField discountPayLogTextField=new TextField();
    @FXML Button yesDiscountButton=new Button();
    @FXML Button noDisocuntButton=new Button();
    @FXML Button yesFastButton=new Button();
    @FXML Button noFastButton=new Button();
    @FXML Button payButton=new Button();
    //int output;
    static Parent root;
    private static BuyLog preBuyLog;

    private static Parent priRoot;

    public static Parent getPriRoot() {
        return priRoot;
    }

    public static void setPriRoot(Parent priRoot) {
        PayLogFx.priRoot = priRoot;
    }

    public static void setPreBuyLog(BuyLog preBuyLog) {
        PayLogFx.preBuyLog = preBuyLog;
    }

    //SubMenuStatus subMenuStatus=SubMenuStatus.RECIVERINFORMATION;
    //public RegisterMenu registerMenu;
    int detailMenu=0;
   // public CustomerMenu customerMenu;
    @FXML
    public void initialize(){
        addingInfo();
    }
    public void paymentProcess(MouseEvent mouseEvent) throws IOException {
        //String ms;


        //CustomerMenu.
        CustomerMenu.payment();

      //  alertMessage.setText();
       alertMessage();

    }
   public void getPhoneNumber(MouseEvent mouseEvent) throws IOException {
        addressTextPayLog.setDisable(false);
       RegisterMenu.receiverInformation(phoneNumberTextFieldPayLog.getText(),0);
        alertMessage.setText(OutputMassageHandler.showReceiverInfo(RegisterMenu.getOutputNo()));
   }
   public void getAddress() throws IOException {
      // addressTextPayLog.setDisable(false);
     //  RegisterMenu.receiverInformation(phoneNumberTextFieldPayLog.getText(),0);
       RegisterMenu.receiverInformation(addressTextPayLog.getText(),1);
       alertMessage.setText(OutputMassageHandler.showReceiverInfo(RegisterMenu.getOutputNo()));
   }
    public  void addingInfo(){
       // addressTextPayLog.setDisable(true);

        yesDiscountButton.setDisable(true);
        noDisocuntButton.setDisable(true);
        payButton.setDisable(true);
        discountPayLogTextField.setDisable(true);
        phoneNumberTextFieldPayLog.disableProperty();
        //phoneNumberTextFieldPayLog.
      /*  if (phoneNumberTextFieldPayLog.getText()!=null){
            addressTextPayLog.setDisable(false);
        }*/
      if (!addressTextPayLog.isDisable()){
            yesDiscountButton.setDisable(false);
            noDisocuntButton.setDisable(false);
        }
        else if (!noDisocuntButton.isDisabled()||!yesDiscountButton.isDisabled()){
            discountPayLogTextField.setDisable(false);
        }
    }
    public void alertMessage() throws IOException {
        Stage window=new Stage();
        Scene scene ;
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout=new VBox();
        TextArea textArea=new TextArea("Payment successfully");
        Text text=new Text(String.valueOf(preBuyLog.totalPrice()));
        Button button =new Button("go to account");
      //  button.setOnMouseClicked(this::gotoAccount);
        layout.getChildren().addAll(button,textArea);
        layout.setAlignment(Pos.CENTER);
        button.setOnAction(e->window.close());
        scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        root=FXMLLoader.load(Objects.requireNonNull(PayLogFx.class.getClassLoader().getResource("CustomerMenuFx.fxml")));
        goToPage();
    }

    public void setYesDiscount(MouseEvent mouseEvent){
        discountPayLogTextField.setDisable(false);
        CustomerMenu.haveDiscount("yes");
        CustomerMenu.checkDiscountCode(discountPayLogTextField.getText());
        CustomerMenu.discountCodeValidation(discountPayLogTextField.getText());
        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));
        payButton.setDisable(false);
    }
    public void setNoDiscount(MouseEvent mouseEvent){
        discountPayLogTextField.setDisable(true);
        CustomerMenu.haveDiscount("no");
        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));
        payButton.setDisable(false);
    }
    public void setYesFast(MouseEvent mouseEvent) throws IOException {
        //RegisterMenu.receiverInformation("yes",2);
        alertMessage.setText(OutputMassageHandler.showReceiverInfo(RegisterMenu.receiverInformation("yes",2)));
        payButton.setDisable(true);
    }
    public void setNoFast(MouseEvent mouseEvent) throws IOException {
       // RegisterMenu.receiverInformation("no",2);
        //discountPayLogTextField.setDisable(true);
        alertMessage.setText(OutputMassageHandler.showReceiverInfo(RegisterMenu.receiverInformation("no",2)));
        payButton.setDisable(true);
    }
    public void gotoAccount(MouseEvent mouseEvent) throws IOException {
        root=FXMLLoader.load(Objects.requireNonNull(PayLogFx.class.getClassLoader().getResource("CustomerMenuFx.fxml")));
        goToPage();

    }

    public void processExit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(PayLogFx.class.getClassLoader().getResource("buyLogFx.fxml")));
        goToPage();
    }
    public  void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
}