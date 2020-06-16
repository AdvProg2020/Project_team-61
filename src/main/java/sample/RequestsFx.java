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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.accounts.Account;
import model.request.*;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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


///////////////////////////////////////
    public void showRequest(MouseEvent mouseEvent) throws IOException {
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
            if (request instanceof AccountRequest) {
                ViewAccountFx.setRequest(request);
                root = FXMLLoader.load(Objects.requireNonNull(SaleLogsFx.class.getClassLoader().getResource("saleLogsFx.fxml")));
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

    public void declineRequest(MouseEvent mouseEvent) {
        String text = null;
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
             text =OutputMassageHandler.showOutputWithString(ManagerMenu.declineRequest(request.getRequestText()));
        } else text = " you have to select request first";
        show(text);
    }

    public void acceptRequest(MouseEvent mouseEvent) throws IOException {
        String text = null;
        if(requests.getSelectionModel().getSelectedItem() != null) {
            Request request = requests.getSelectionModel().getSelectedItem();
           text = OutputMassageHandler.showOutputWithString(ManagerMenu.acceptRequest(request.getRequestText()));
        }else text = " you have to select request first";
        show(text);
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


    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}
