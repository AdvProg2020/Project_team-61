package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ViewSaleFx {
    @FXML
    private Label startSaleLabel;

    @FXML
    private TableColumn<?, ?> saleProduct;

    @FXML
    private Label endSaleLabel;

    @FXML
    private Label saleAmountLabel;

    @FXML
    private TableView<?> saleProducts;

    @FXML
    private Label saleIdLabel;
    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {

    }

}
