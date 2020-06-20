package sample;

import controller.menus.ManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.off.DiscountCode;
import view.OutputMassageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DiscountCodesFx {
    private static ArrayList<DiscountCode> discounts;
    @FXML
    private Button removeDisoucnt;
    @FXML
    private Button addDiscount;
    @FXML
    private Button EditDiscount;


    @FXML
    private Label discountCodesMs;
    @FXML
    private TableView<DiscountCode> discountCodes;

    @FXML
    private TableColumn<DiscountCode, String> discountStart;

    @FXML
    private TableColumn<DiscountCode, Number> maxDiscountAmount;

    @FXML
    private TableColumn<DiscountCode, Number> discountAmount;

    @FXML
    private TableColumn<DiscountCode, Number> discountTotalTime;

    @FXML
    private TableColumn<DiscountCode, String> discountId;

    @FXML
    private TableColumn<DiscountCode, String> discountEnd;

    public static ObservableList<DiscountCode> data = FXCollections.observableArrayList();
    private static Parent root;

    @FXML
    public void initialize() throws IOException {
        makeTree();
    }

    public static void initializeObserverList() throws IOException {
        data.clear();
      //  data.addAll(discounts);
        data.addAll(new DiscountCode("ssss"),new DiscountCode("sss"));
    }


    public void makeTree() throws IOException {
        discountId.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("discountId"));
        discountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode, Number>("discountAmount"));
        discountStart.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("startOfDiscountPeriod"));
        discountEnd.setCellValueFactory(new PropertyValueFactory<DiscountCode,String>("endOfDiscountPeriod"));
        maxDiscountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("maxDiscountAmount"));
        discountTotalTime.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("totalTimesOfUse"));


        initializeObserverList();
       // discountCodes.getColumns().addAll(discountId,discountAmount,discountStart,discountEnd,maxDiscountAmount,discountTotalTime);
        discountCodes.setEditable(true);
        discountCodes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        discountCodes.getSelectionModel().setCellSelectionEnabled(true);
        discountCodes.setItems(data);
    }
    public void edit(MouseEvent mouseEvent) {
        if(discountCodes.getSelectionModel().getSelectedItem() != null) {
            DiscountCode discountCode = discountCodes.getSelectionModel().getSelectedItem();
        }
    }

    public void remove(MouseEvent mouseEvent) throws IOException {
        if(discountCodes.getSelectionModel().getSelectedItem() != null) {
            DiscountCode discountCode = discountCodes.getSelectionModel().getSelectedItem();
            discountCodesMs.setText(OutputMassageHandler.showManagerOutput(ManagerMenu.removeDiscountCode(discountCode.getDiscountId())));
            makeTree();
        }

    }

    public void viewDiscount(MouseEvent mouseEvent) throws IOException {
        if(discountCodes.getSelectionModel().getSelectedItem() != null) {
            DiscountCode discountCode = discountCodes.getSelectionModel().getSelectedItem();
            ViewDiscountFx.setCurDiscountCode(discountCode);
            root = FXMLLoader.load(Objects.requireNonNull(ViewDiscountFx.class.getClassLoader().getResource("viewDiscountFx.fxml")));
            goToPage();
        }
    }

    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }

    public static void setDiscounts(ArrayList<DiscountCode> discounts) {
        DiscountCodesFx.discounts = discounts;
    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }


}