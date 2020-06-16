package sample;

import controller.menus.SellerMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.OutputMassageHandler;

import java.io.IOException;
import java.text.ParseException;

public class AddSaleFx {

    @FXML
    private TextField saleAmount;

    @FXML
    private Label saleIdAlertLabel;

    @FXML
    private TextField startSaleDatePicker;

    @FXML
    private TableColumn<?, ?> addSaleProduct;

    @FXML
    private TextField endSaleDatePicker;

    @FXML
    private TextField saleIdTextField;

    @FXML
    private ImageView discountBackground;

    @FXML
    private TableView<?> addSaleProducts;

    @FXML
    private Label endSaleAlertLabel;

    @FXML
    private Label startSaleAlertLabel;

    @FXML
    private Label saleAmountAlertLabel;
    private boolean finish = false;
    private boolean oneProduct = false;



    public void createSale(MouseEvent mouseEvent) throws IOException, ParseException {
            saleIdAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(saleIdTextField.getText())));
            startSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(startSaleDatePicker.getText())));
            endSaleAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(endSaleDatePicker.getText())));
            saleAmountAlertLabel.setText(OutputMassageHandler.showSaleOutput(SellerMenu.setDetailsToSale(saleAmount.getText())));

            finish = false;

    }

    public void addProduct(MouseEvent mouseEvent) throws IOException, ParseException {
        if (!finish) {
           // TreeItem<Product> a = saleProducts.getSelectionModel().getSelectedItem();
            SellerMenu.setDetailsToSale(saleAmount.getText());
        }
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void userMenu(ActionEvent actionEvent) {
    }


    public void finish(MouseEvent mouseEvent) {
        finish =true;
    }
}
