package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UsersFx {

    @FXML
    private TableView<Account> usersList;

    @FXML
    private TableColumn<Account, String> UserId;

    @FXML
    private TableColumn<Account, Double> userPhoneNo;

    @FXML
    private TableColumn<Account, String> userEmail;

    @FXML
    private TableColumn<Account, String> userLast;

    @FXML
    private TableColumn<Account, Date> userBirth;

    @FXML
    private TableColumn<Account, String> userName;

    @FXML
    private TableColumn<Account, String> UserNumber;

    public static final ObservableList list = FXCollections.observableArrayList();
    private static Parent root;

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


    public void viewUser(MouseEvent mouseEvent) throws IOException {
        Account a = usersList.getSelectionModel().getSelectedItem();
        root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
        goToPage();
    }

    public void deleteUser(MouseEvent mouseEvent) {
        Account a = usersList.getSelectionModel().getSelectedItem();
    }

    public void AddManager(MouseEvent mouseEvent) throws IOException {
        Account a = usersList.getSelectionModel().getSelectedItem();
        SignUpFx.setRole("manager");
        root = FXMLLoader.load(Objects.requireNonNull(SignUpFx.class.getClassLoader().getResource("signUpFx.fxml")));
        goToPage();
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }



}
