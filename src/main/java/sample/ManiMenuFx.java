package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ManiMenuFx {



        public void users(MouseEvent mouseEvent) throws IOException {
                goToPage(mouseEvent," ");
        }

        public void products(MouseEvent mouseEvent) throws IOException {
                goToPage(mouseEvent," ");
        }

        public void sales(MouseEvent mouseEvent) throws IOException {
                goToPage(mouseEvent," ");
        }

        public void exit(MouseEvent mouseEvent) {
                System.exit(0);
        }

        private void goToPage(MouseEvent mouseEvent, String name) throws IOException {
                Parent page2 = FXMLLoader.load(getClass().getResource(name));
                Scene pageTwoScene = new Scene(page2);
                Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                window.setScene(pageTwoScene);
                window.show();
        }


}
