package view.sample.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersFx {

    @FXML
    private TableView<Account> usersList;

    @FXML
    private TableColumn<Account, String> UserNumber;

    @FXML
    private  TableColumn<Account, String> UserId;

    @FXML
    private  TableColumn<Account, String> userEmail;

    @FXML
    private  TableColumn<Account, String> userLast;

    @FXML
    private TableColumn<Account, Date> userBirth;

    @FXML
    private TableColumn<Account, String> userName;

    @FXML
    private TableColumn<Account, Double> userPhoneNo;
    public static final ObservableList list = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        makeTree();
        makeList();

    }

    private void makeList() throws IOException {
        List<Account> allAccounts = new ArrayList<>();
        allAccounts.addAll(Account.getAllAccounts());
        list.clear();
        for (int i = 0; i < allAccounts.size() ; i++) {
        list.addAll(i, allAccounts.get(i).getUsername(),allAccounts.get(i).getName(),allAccounts.get(i).getLastname(),allAccounts.get(i).getBirthdayDate(),allAccounts.get(i).getPhoneNo(),allAccounts.get(i).getEmail());
        }
        Account a = usersList.getSelectionModel().getSelectedItem();
    }

    private void makeTree() {
        UserNumber.setCellValueFactory(new PropertyValueFactory<Account, String>("Number"));
        UserId.setCellValueFactory(new PropertyValueFactory<Account, String>("Username"));
        userName.setCellValueFactory(new PropertyValueFactory<Account, String>("Name"));
        userLast.setCellValueFactory(new PropertyValueFactory<Account, String>("Last name"));
        userBirth.setCellValueFactory(new PropertyValueFactory<Account, Date>("Birthday date"));
        userPhoneNo.setCellValueFactory(new PropertyValueFactory<Account, Double>("Phone number"));
        userEmail.setCellValueFactory(new PropertyValueFactory<Account, String>("Email"));
        usersList.setItems(list);
    }


    public void viewUser(MouseEvent mouseEvent) {
    }

    public void deleteUser(MouseEvent mouseEvent) {
    }

    public void AddManager(MouseEvent mouseEvent) {
    }
}
