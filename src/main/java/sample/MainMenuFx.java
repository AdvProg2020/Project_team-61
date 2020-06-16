package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;

public class MainMenuFx {
        private Parent root;

        public void users(MouseEvent mouseEvent) throws IOException {
                 root = FXMLLoader.load(Objects.requireNonNull(LoginFx.class.getClassLoader().getResource("loginFx.fxml")));
                goToPage();
        }



        public void products(MouseEvent mouseEvent) throws IOException {
                 root = FXMLLoader.load(Objects.requireNonNull(ProductsMenuFX.class.getClassLoader().getResource("productsMenuFX.fxml")));
                goToPage();

        }

        public void sales(MouseEvent mouseEvent) throws IOException {
//               Parent root = FXMLLoader.load(Objects.requireNonNull(className.class.getClassLoader().getResource(name)));
                goToPage();

        }

        public void exit(MouseEvent mouseEvent) {
                System.exit(0);
        }

        private void goToPage(){
                Scene pageTwoScene = new Scene(root);
                //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                Main.primStage.setScene(pageTwoScene);
                Main.primStage.show();
        }



}
