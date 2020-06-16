package sample;

import controller.menus.ManagerMenu;
import model.request.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestsFx {
    @FXML
    private TableView<Request> requests;
    @FXML
    private TableColumn<Request, String> requestId;

    @FXML
    private TableColumn<Request, Date> requestDate;

    @FXML
    private TableColumn<Request, Account> requestSeller;

    public static final ObservableList list = FXCollections.observableArrayList();
    private static Parent root;


    @FXML
    public void initialize() throws IOException {
        makeTree();
        makeList();

    }

    private void makeList() throws IOException {
        List<Request> allRequests = new ArrayList<>();
        allRequests.addAll(Request.getAllRequests());
        list.clear();
        for (int i = 0; i < allRequests.size() ; i++) {
            list.addAll(i, allRequests.get(i).getRequestText(),allRequests.get(i).getSeller(),allRequests.get(i).getRequestDate());
        }

    }

    private void makeTree() {
        requestId.setCellValueFactory(new PropertyValueFactory<Request, String>("ID"));
        requestSeller.setCellValueFactory(new PropertyValueFactory<Request, Account>("Seller"));
        requestDate.setCellValueFactory(new PropertyValueFactory<Request, Date>("Date"));
        requests.setItems(list);
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void showRequest(MouseEvent mouseEvent) {
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            if (request instanceof AccountRequest) {

            } else if (request instanceof ProductRequest) {

            } else if (request instanceof CommentRequest) {

            } else if (request instanceof SaleRequest) {

            }

        }
    }

    public void declineRequest(MouseEvent mouseEvent) {
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            ManagerMenu.declineRequest(request.getRequestText());
        }
    }

    public void acceptRequest(MouseEvent mouseEvent) throws IOException {
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            ManagerMenu.acceptRequest(request.getRequestText());
        }

    }
}
