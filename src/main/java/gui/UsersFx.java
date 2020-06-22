package gui;

import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.accounts.Account;
import model.sort.Sort;
import view.OutputMassageHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class UsersFx {

    @FXML private TableView<Account> usersList;
    @FXML private TableColumn<Account, String> UserId;
    @FXML private TableColumn<Account, Double> userPhoneNo;
    @FXML private TableColumn<Account, String> userEmail;
    @FXML private TableColumn<Account, String> userLast;
    @FXML private TableColumn<Account, Date> userBirth;
    @FXML private TableColumn<Account, String> userName;
    @FXML private Label usersMs;

    public static  ObservableList list = FXCollections.observableArrayList();
    private static Parent root;
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Parent priRoot;

    public static void setPriRoot(Parent priRoot) {
        UsersFx.priRoot = priRoot;
    }

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void makeList() {
        list.clear();
        list.addAll(Account.getAllAccounts());
    }

    private void makeTree() {
        UserId.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        userName.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        userLast.setCellValueFactory(new PropertyValueFactory<Account, String>("lastname"));
        userBirth.setCellValueFactory(new PropertyValueFactory<Account, Date>("birthdayDate"));
        userPhoneNo.setCellValueFactory(new PropertyValueFactory<Account, Double>("phoneNo"));
        userEmail.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));

        makeList();
      //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        usersList.setEditable(true);
        usersList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        usersList.getSelectionModel().setCellSelectionEnabled(true);

        usersList.setItems(list);
    }


    public void viewUser(MouseEvent mouseEvent) throws IOException {
        if(usersList.getSelectionModel().getSelectedItem() != null) {
            Account a = usersList.getSelectionModel().getSelectedItem();
            ViewAccountFx.setAccount(a);
            root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
            goToPage();
        }else usersMs.setText("you have to select first");
    }

    public void deleteUser(MouseEvent mouseEvent) {
        if(usersList.getSelectionModel().getSelectedItem() != null) {
            Account a = usersList.getSelectionModel().getSelectedItem();
            usersMs.setText( OutputMassageHandler.showAccountOutput( ManagerMenu.deleteUser(a.getUsername())));
            makeTree();
        }else usersMs.setText("you have to select first");
    }

    public void AddManager(MouseEvent mouseEvent) throws IOException {
            SignUpFx.setRole("manager");
            root = FXMLLoader.load(Objects.requireNonNull(SignUpFx.class.getClassLoader().getResource("signUpFx.fxml")));
            goToPage();
    }


    public void sortUser(MouseEvent mouseEvent) throws FileNotFoundException {
        UserId.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        userName.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        userLast.setCellValueFactory(new PropertyValueFactory<Account, String>("lastname"));
        userBirth.setCellValueFactory(new PropertyValueFactory<Account, Date>("birthdayDate"));
        userPhoneNo.setCellValueFactory(new PropertyValueFactory<Account, Double>("phoneNo"));
        userEmail.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));

        list.clear();
        Sort.setNewArrayOfAccountSort(Account.getAllAccounts());
        list.addAll(Sort.accountSortUserName());

        usersList.setEditable(true);
        usersList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        usersList.getSelectionModel().setCellSelectionEnabled(true);

        usersList.setItems(list);

    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    private void show(String text) {
        Label label = new Label();
        StackPane root = new StackPane();
        root.getChildren().add(label);
        label.setText(text);
        Stage massage = new Stage();
        massage.setScene(new Scene(root, 500, 500));
        massage.show();
    }

//    private void makeList() throws IOException {
//        List<Account> allAccounts = new ArrayList<>();
//        allAccounts.addAll(Account.getAllAccounts());
//        list.clear();
//        for (int i = 0; i < allAccounts.size() ; i++) {
//            list.addAll(i, allAccounts.get(i).getUsername(),allAccounts.get(i).getName(),allAccounts.get(i).getLastname(),allAccounts.get(i).getBirthdayDate(),allAccounts.get(i).getPhoneNo(),allAccounts.get(i).getEmail());
//        }
//
//    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

}
