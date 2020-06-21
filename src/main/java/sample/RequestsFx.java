package sample;

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
import model.request.*;
import model.sort.Sort;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.Objects;

public class RequestsFx {
    @FXML
    private TableView<Request> requests;
    @FXML
    private TableColumn<Request, String> requestId;
    @FXML
    private TableColumn<Request, String> requestDate;
    @FXML
    private TableColumn<Request, Account> requestSeller;

    public static final ObservableList list = FXCollections.observableArrayList();
    private static Parent root;


    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    private void makeList() throws IOException {
        list.clear();
        list.addAll(Request.getAllRequests());
    }

    private void makeTree() throws IOException {

        requestId.setCellValueFactory(new PropertyValueFactory<Request, String>("requestText"));
        requestSeller.setCellValueFactory(new PropertyValueFactory<Request, Account>("seller"));
        requestDate.setCellValueFactory(new PropertyValueFactory<Request, String>("requestDate"));

        makeList();
        requests.setEditable(true);
        requests.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        requests.getSelectionModel().setCellSelectionEnabled(true);

        requests.setItems(list);
    }


    public void showRequest(MouseEvent mouseEvent) throws IOException {
        if (requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            if (request instanceof AccountRequest) {
                ViewAccountFx.setRequest(request);
                root = FXMLLoader.load(Objects.requireNonNull(ViewAccountFx.class.getClassLoader().getResource("viewAccountFx.fxml")));
                goToPage();
            } else if (request instanceof ProductRequest) {

                root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
                goToPage();
            } else if (request instanceof CommentRequest) {

                root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
                goToPage();
            } else if (request instanceof SaleRequest) {

                root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
                goToPage();
            }

        }
    }

    public void declineRequest(MouseEvent mouseEvent) throws IOException {
        String text = null;
        if (requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            text = OutputMassageHandler.showOutputWithString(ManagerMenu.declineRequest(request.getRequestText()));
            makeTree();
        } else text = " you have to select request first";
        show(text);
    }

    public void acceptRequest(MouseEvent mouseEvent) throws IOException {
        String text = null;
        if (requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            text = OutputMassageHandler.showOutputWithString(ManagerMenu.acceptRequest(request.getRequestText()));
            makeTree();
        } else text = " you have to select request first";
        show(text);
    }

    private static void goToPage() {
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


    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void sortRequest(MouseEvent mouseEvent) {

        requestId.setCellValueFactory(new PropertyValueFactory<Request, String>("requestText"));
        requestSeller.setCellValueFactory(new PropertyValueFactory<Request, Account>("seller"));
        requestDate.setCellValueFactory(new PropertyValueFactory<Request, String>("requestDate"));

        list.clear();
        Sort.setNewArrayOfRequest(Request.getAllRequests());
        list.addAll(Sort.sortRequest());
        requests.setEditable(true);
        requests.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        requests.getSelectionModel().setCellSelectionEnabled(true);

        requests.setItems(list);
    }
}
