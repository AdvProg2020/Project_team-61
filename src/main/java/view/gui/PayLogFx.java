package view.gui;

import controller.menus.CustomerMenu;
import controller.menus.RegisterMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    @FXML Button yesButton=new Button();
    @FXML Button noButton=new Button();
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
        RegisterMenu.receiverInformation(phoneNumberTextFieldPayLog.getText(),0);
        RegisterMenu.receiverInformation(addressTextPayLog.getText(),1);
        CustomerMenu.payment();

        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));
        alertMessage();

    }
   // @FXML
    //public void initialize(){


    //}
   public void addressEnable(){
        addressTextPayLog.setDisable(false);
   }
    public  void addingInfo(){
       // addressTextPayLog.setDisable(true);
        yesButton.setDisable(true);
        noButton.setDisable(true);
        payButton.setDisable(true);
        discountPayLogTextField.setDisable(true);
        phoneNumberTextFieldPayLog.disableProperty();
        //phoneNumberTextFieldPayLog.
      /*  if (phoneNumberTextFieldPayLog.getText()!=null){
            addressTextPayLog.setDisable(false);
        }*/
      if (!addressTextPayLog.isDisable()){
            yesButton.setDisable(false);
            noButton.setDisable(false);
        }
        else if (!noButton.isDisabled()||!yesButton.isDisabled()){
            discountPayLogTextField.setDisable(false);
        }
    }
    public void alertMessage(){
        Stage window=new Stage();
        Scene scene ;
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout=new VBox();
        TextArea textArea=new TextArea("Payment successfully");
        Button button =new Button("go to account");
        button.setOnMouseClicked(this::gotoAccount);
        layout.getChildren().addAll(button,textArea);
        layout.setAlignment(Pos.CENTER);
        button.setOnAction(e->window.close());
        scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void setYes(MouseEvent mouseEvent){
        discountPayLogTextField.setDisable(false);
        CustomerMenu.haveDiscount("yes");
        CustomerMenu.checkDiscountCode(discountPayLogTextField.getText());
        CustomerMenu.discountCodeValidation(discountPayLogTextField.getText());
        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));
    }
    public void setNo(MouseEvent mouseEvent){
        discountPayLogTextField.setDisable(true);
        CustomerMenu.haveDiscount("no");
        alertMessage.setText(OutputMassageHandler.showCustomerOutput(CustomerMenu.getOutputNo()));
    }
    public void gotoAccount(MouseEvent mouseEvent) {
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