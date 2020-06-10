package view.GUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class ProductsMenuFX {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane topSplitPane;

        @FXML
        private AnchorPane buttomSplitPane;

        @FXML
        private TextField searchField;

        @FXML
        void Logout(ActionEvent event) {

        }

        @FXML
        void backToPreviousPage(ActionEvent event) {

        }

        @FXML
        void doTheSearch(ActionEvent event) {

        }

        @FXML
        void exitUser(ActionEvent event) {

        }

        @FXML
        void filterAscending(ActionEvent event) {

        }

        @FXML
        void filterCategory(ActionEvent event) {

        }

        @FXML
        void filterCompany(ActionEvent event) {

        }

        @FXML
        void filterIsAvailable(ActionEvent event) {

        }

        @FXML
        void goToAccount(ActionEvent event) {

        }

        @FXML
        void sortNumberOfView(ActionEvent event) {

        }

        @FXML
        void sortScore(ActionEvent event) {

        }

        @FXML
        void initialize() {
            assert topSplitPane != null : "fx:id=\"topSplitPane\" was not injected: check your FXML file 'productsMenuFX.fxml'.";
            assert buttomSplitPane != null : "fx:id=\"buttomSplitPane\" was not injected: check your FXML file 'productsMenuFX.fxml'.";
            assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'productsMenuFX.fxml'.";

        }


}
