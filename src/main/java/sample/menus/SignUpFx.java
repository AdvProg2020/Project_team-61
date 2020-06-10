package sample.menus;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignUpFx {

    @FXML
    private TextField phoneNoSign;

    @FXML
    private TextField roleSign;

    @FXML
    private Label emailLoginMs;

    @FXML
    private Label userLoginMs;

    @FXML
    private Label birthLoginMs;

    @FXML
    private TextField userSign;

    @FXML
    private TextField lastNameSign;

    @FXML
    private Label nameLoginMs;

    @FXML
    private Label phoneLoginMs;

    @FXML
    private PasswordField passSign;

    @FXML
    private Label passLoginMs;

    @FXML
    private TextField emailSign;

    @FXML
    private TextField nameSign;

    @FXML
    private Label roleLoginMs;

    @FXML
    private TextField birthdaySign;

    @FXML
    private Label lastNameLoginMs;

    public void picDrop(DragEvent dragEvent) {
        Image image = new Image("");
        File outputFile = new File("C:/JavaFX/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void back(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
    }

    public void picOver(DragEvent dragEvent) {
    }

    public void signUp(MouseEvent mouseEvent) {
    }
}