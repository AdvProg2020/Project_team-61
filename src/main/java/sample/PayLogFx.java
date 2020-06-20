package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PayLogFx {
    public void paymentProcess(MouseEvent mouseEvent) {
    }
    public void alertMessage(){
        Stage window=new Stage();
        Scene scene ;
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout=new VBox();
        TextArea textArea=new TextArea("Payment successfully");
        Button button =new Button("ok");
        layout.getChildren().addAll(button,textArea);
        layout.setAlignment(Pos.CENTER);
        button.setOnAction(e->window.close());
        scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void gotoAccount(MouseEvent mouseEvent) {
    }

    public void processExit(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
    }
}