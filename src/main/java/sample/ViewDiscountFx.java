package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;



    public class ViewDiscountFx {
        @FXML
        private Label startDiscountInfo;

        @FXML
        private Label maxAmountInfo;

        @FXML
        private Label timesOfUseInfo;

        @FXML
        private Label discountInformationLabel;

        @FXML
        private Label discountIdInfo;

        @FXML
        private Label discountAmountInfo;

        @FXML
        private Label endDiscountInfo;

        @FXML
        private ListView<?> allAccountsInfo;


        private void remove(){

        }

        public void back(MouseEvent mouseEvent) {
            remove();
        }

        public void exit(MouseEvent mouseEvent) {
            remove();
        }

        public void userMenu(ActionEvent actionEvent) {
        }

        public void logout(ActionEvent actionEvent) {
            remove();
        }

    }
