package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;



public class SalesFx {

    @FXML
    private TableColumn<?, ?> saleAmount;

    @FXML
    private Button EditDiscount;

    @FXML
    private TableColumn<?, ?> saleSeller;

    @FXML
    private Button removeDisoucnt;

    @FXML
    private TableColumn<?, ?> saleId;

    @FXML
    private Button addDiscount;

    @FXML
    private TableColumn<?, ?> saleEnd;

    @FXML
    private TableColumn<?, ?> saleStart;

    @FXML
    private TableColumn<?, ?> saleStatus;

    @FXML
    private TableView<?> sales;

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void add(MouseEvent mouseEvent) {
    }

    public void edit(MouseEvent mouseEvent) {
    }

    public void remove(MouseEvent mouseEvent) {
    }
}
