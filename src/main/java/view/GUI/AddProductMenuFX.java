package view.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProductMenuFX {
    public TextField idTextField;
    public TextField priceTextField;
    public TextField productNameTextField;
    public TextField categoryNameTextField;
    public TextField sellerNameTextField;
    public Button addProductButton;
    public Label idError;
    public Label nameError;
    public Button backButton;
    public Label cateforyNameError;
    public Label sellerNameError;
    public Label priceError;

    @FXML
    public void addNewProduct(ActionEvent actionEvent) {
    }
    @FXML
    public void backToPreviosPage(ActionEvent actionEvent) {
    }
}
