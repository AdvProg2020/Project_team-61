package gui;

import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.accounts.Customer;
import view.OutputMassageHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class AddDiscountFx {

    @FXML
    private TextField maxAmountDiscount;
    @FXML
    private TextField startAddDiscount;
    @FXML
    private TextField endAddDiscount;
    @FXML
    private TextField addDiscountId;
    @FXML
    private TextField addDiscountTimesOfUse;
    @FXML
    private ListView<Account> allAccountsInfo;
    @FXML
    private TextField addDiscountAmount;
    @FXML
    private Label discountMs;
    @FXML
    private TableColumn<Account, String> discountUser;
    @FXML
    private TableView<Account> discountAccounts;

    public static ObservableList list = FXCollections.observableArrayList();
    ArrayList<String> usernames = new ArrayList<>();
    private static Parent priRoot;

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void makeList() {
        list.clear();
        list.addAll(Customer.getAllCustomers());
    }

    private void makeTree() {
        discountUser.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));

        makeList();
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        discountAccounts.setEditable(true);
        discountAccounts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        discountAccounts.getSelectionModel().setCellSelectionEnabled(true);

        discountAccounts.setItems(list);
    }


    public void addDiscount(MouseEvent mouseEvent) throws IOException, ParseException {
        String ms = null;
        if (discountAccounts.getSelectionModel().getSelectedItem() != null) {
            if (ManagerMenu.getCreate() == 0) {
                addDiscountId.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.createNewDiscountCode(addDiscountId.getText())));
            }
            if (ManagerMenu.getCreate() == 1) {
                ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(startAddDiscount.getText(), 0));
                if (ManagerMenu.getDetailMenu() == 1) {
                    ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(endAddDiscount.getText(), 1));
                }
                if (ManagerMenu.getDetailMenu() == 2) {
                    ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(maxAmountDiscount.getText(), 2));
                }
                if (ManagerMenu.getDetailMenu() == 3) {
                    ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(addDiscountTimesOfUse.getText(), 3));
                }
                if (ManagerMenu.getDetailMenu() == 4) {
                    ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(addDiscountAmount.getText(), 4));
                }
                if (ManagerMenu.getDetailMenu() == 5) {
                    Account account = discountAccounts.getSelectionModel().getSelectedItem();
                    ms = OutputMassageHandler.showManagerOutput(ManagerMenu.setDetailToDiscountCode(account.getUsername(), 5));

                }
            }
        } else ms = "you have to select account first";
        discountMs.setText(ms);

    }

    public void editDiscount(MouseEvent mouseEvent) throws IOException, ParseException {
        String ms = null;
        if (ManagerMenu.getEdit() == 0) {
            ms = (OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCode(addDiscountId.getText())));
        }
        //if (ManagerMenu.getCreate() == 1) {
        if (ManagerMenu.getEdit() == 1) {
            ms = OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(startAddDiscount.getText(), "start of discount"));
            //  if (ManagerMenu.getDetailMenu() == 1) {
            ms = OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(endAddDiscount.getText(), "end of discount"));
            // }
            //  if (ManagerMenu.getDetailMenu() == 2) {
            ms = OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(maxAmountDiscount.getText(), "max"));
            // }
            // if (ManagerMenu.getDetailMenu() == 3) {
            ms = OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(addDiscountTimesOfUse.getText(), "time of use"));
            // }
            // if (ManagerMenu.getDetailMenu() == 4) {
            ms = OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(addDiscountAmount.getText(), "amount"));
            // }
        }else ms = "you have to put id first";

        // }
        discountMs.setText(ms);
    }

    public void addAccount(MouseEvent mouseEvent) throws IOException, ParseException {
        if (ManagerMenu.getEditableDiscountCode() != null) {
            if (discountAccounts.getSelectionModel().getSelectedItem() != null) {
                Account a = discountAccounts.getSelectionModel().getSelectedItem();
                discountMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(a.getUsername(), "add account")));
                makeTree();
            } else discountMs.setText("you have to select first");
        } else discountMs.setText("you have to put name and edit first");
    }

    public void removeAccount(MouseEvent mouseEvent) throws IOException, ParseException {
        if (ManagerMenu.getEditableDiscountCode() != null) {
            if (discountAccounts.getSelectionModel().getSelectedItem() != null) {
                Account a = discountAccounts.getSelectionModel().getSelectedItem();
                discountMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.editDiscountCodeField(a.getUsername(), "remove account")));
                makeTree();
            } else discountMs.setText("you have to select first");
        } else discountMs.setText("you have to put name and edit first");
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


  /*  public  void initializeObserverList() {
        allAccountsInfo.setEditable(true);
        allAccountsInfo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.clear();
        for (int i = 0; i <Account.getAllAccounts().size() ; i++) {
            usernames.add(Account.getAllAccounts().get(i).getUsername());
        }
        list.addAll(usernames);
        allAccountsInfo.getItems().addAll(list);
    }

   */


}

