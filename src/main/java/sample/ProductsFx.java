package sample;

import controller.menus.ManagerMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.productRelated.Product;
import view.OutputMassageHandler;



public class ProductsFx {
    @FXML
    private Button removeSale;

    @FXML
    private TableColumn<?, ?> productId;

    @FXML
    private TableColumn<?, ?> productNumberView;

    @FXML
    private TableColumn<?, ?> productStatus;

    @FXML
    private TableColumn<?, ?> productNumber;

    @FXML
    private TableColumn<?, ?> productBuyerNo;

    @FXML
    private TableColumn<?, ?> productName;

    @FXML
    private TableColumn<?, ?> productPrice;

    @FXML
    private TableView<Product> products;

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void remove(MouseEvent mouseEvent) {
        Product product = products.getSelectionModel().getSelectedItem();
        OutputMassageHandler.showManagerOutput(ManagerMenu.removeProduct(product.getProductId()));
    }
}
