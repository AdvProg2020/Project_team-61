package sample;

import controller.menus.ManagerMenu;
import controller.menus.RegisterMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import view.OutputMassageHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class AddDiscountFx {

    @FXML private TextField maxAmountDiscount;
    @FXML private TextField startAddDiscount;
    @FXML private TextField endAddDiscount;
    @FXML private TextField addDiscountId;
    @FXML private TextField addDiscountTimesOfUse;
    @FXML private  ListView<Account> allAccountsInfo;
    @FXML private TextField addDiscountAmount;
    @FXML private Label discountMs;

    public ObservableList list = FXCollections.observableArrayList();
    ArrayList<String> usernames = new ArrayList<>();

    @FXML
    public void initialize() throws IOException {
        initializeObserverList();
    }

    public  void initializeObserverList() {
        allAccountsInfo.setEditable(true);
        allAccountsInfo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.clear();
        for (int i = 0; i <Account.getAllAccounts().size() ; i++) {
            usernames.add(Account.getAllAccounts().get(i).getUsername());
        }
        list.addAll(usernames);
        allAccountsInfo.getItems().addAll(list);
    }


    public void addDiscount(MouseEvent mouseEvent) throws IOException, ParseException {
        String ms = null;
        if (ManagerMenu.getCreate() == 0) {
            addDiscountId.setText(OutputMassageHandler.showAccountOutput(ManagerMenu.createNewDiscountCode(addDiscountId.getText())));
        }
        if (ManagerMenu.getCreate() == 1) {
            ms =OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToDiscountCode(startAddDiscount.getText(), 0));
            if (ManagerMenu.getDetailMenu() == 1) {
                ms =OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToDiscountCode(endAddDiscount.getText(), 1));
            }
            if (ManagerMenu.getDetailMenu() == 2) {
                ms =OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToDiscountCode(maxAmountDiscount.getText(), 2));
            }
            if (ManagerMenu.getDetailMenu() == 3) {
                ms =OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToDiscountCode(addDiscountTimesOfUse.getText(), 3));
            }
            if (ManagerMenu.getDetailMenu() == 4) {
                ms =OutputMassageHandler.showAccountOutput(ManagerMenu.setDetailToDiscountCode(addDiscountAmount.getText(), 4));
            }
            if (ManagerMenu.getDetailMenu() == 5) {
                Account account = allAccountsInfo.getSelectionModel().getSelectedItem();
                ms =OutputMassageHandler.showAccountOutput(RegisterMenu.completeRegisterProcess(account.getUsername(), 5));
            }
        } discountMs.setText(ms);

    }


    public void addAccount(MouseEvent mouseEvent) {
    }

    public void removeAccount(MouseEvent mouseEvent) {
    }


    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {

    }
  /*  private DiscountCode newDiscountCode;
    @FXML
    public ImageView Exit;
    @FXML
    public ImageView Back;
    @FXML
    public ImageView Account;
    @FXML
    public TextField discountIdTextField;
    @FXML
    public Spinner timesOfUseSpinner;
    @FXML
    public Spinner discountAmountSpinner;
    @FXML
    public DatePicker EndOfDiscountDatePicker;
    @FXML
    public DatePicker StartOfDiscountDatePicker;
    @FXML
    public Label discountIdAlertLabel;
    @FXML public Label StartDiscountAlertLabel;
    @FXML public Label EndDiscountAlertLabel;
    public void setDiscountIdTextField(){
        String input=discountIdTextField.getText();
        //newDiscountCode.
    }
    public void setDiscountAmount(){
        SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,1,5);
        discountAmountSpinner=new Spinner();
        discountAmountSpinner.setValueFactory(valueFactory);
        discountAmountSpinner.setEditable(true);
        newDiscountCode.setDiscountAmount(Integer.parseInt(discountAmountSpinner.getValue().toString()));

    }
    //?annonation lazeme?
    @FXML
    public void setTimesOfUseSpinner(){
        SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30,2,1) ;
        timesOfUseSpinner=new Spinner();
        timesOfUseSpinner.setValueFactory(valueFactory);
        timesOfUseSpinner.setEditable(true);
        newDiscountCode.setTotalTimesOfUse(Integer.parseInt(timesOfUseSpinner.getValue().toString()));

    }
    @FXML
    public void setStartOfDiscountDate(){
        if (discountIdTextField.getText()==null){
            StartOfDiscountDatePicker.setDisable(true);
        }
        else {
            LocalDate inputLocalDate;
            inputLocalDate =StartOfDiscountDatePicker.getValue();
            Date inputDate;
            Date now=new Date();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            inputDate = Date.from(inputLocalDate.atStartOfDay(defaultZoneId).toInstant());
            if (now.after(inputDate)){
                StartDiscountAlertLabel.setText("Your date must be after today!");
            }else
                newDiscountCode.setStartOfDiscountPeriod(inputDate);
        }

    }
    public void setEndOfDiscountDate(){
        if (StartOfDiscountDatePicker.getValue()==null){
            EndOfDiscountDatePicker.setDisable(true);
        }
        else {
            LocalDate inputLocalDate;
            inputLocalDate =StartOfDiscountDatePicker.getValue();
            Date inputDate;
            Date now=new Date();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            inputDate = Date.from(inputLocalDate.atStartOfDay(defaultZoneId).toInstant());
            if (inputDate.before(now)||inputDate.before(newDiscountCode.getStartOfDiscountPeriod())){
                EndDiscountAlertLabel.setText("End of Discount Date period is invalid!");
            }
            else
                newDiscountCode.setEndOfDiscountPeriod(inputDate);
        }

    }

    public void processBack(MouseEvent mouseEvent) {
    }

    public void processExit(MouseEvent mouseEvent) {
    }

    public void gotoAccount(MouseEvent mouseEvent) {
    }

   /* @FXML

    @FXML
    @FXML
    @FXML
    @FXML
    @FXML
    @FXML
    @FXML*/



}

