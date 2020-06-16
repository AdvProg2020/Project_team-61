package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.off.DiscountCode;

import java.io.IOException;
import java.util.ArrayList;

public class DiscountCodesFx {
    private static ArrayList<DiscountCode> discounts;
    @FXML
    private Button removeDisoucnt;
    @FXML
    private Button addDiscount;
    @FXML
    private Button EditDiscount;

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

    public static void initializeObserverList() {
        data.clear();
        data.addAll(discounts);
    }

    @FXML
    public void initialize() throws IOException {

        discountId.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("discountId"));
        discountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode, Number>("discountAmount"));
        discountStart.setCellValueFactory(new PropertyValueFactory<DiscountCode, String>("startOfDiscountPeriod"));
        discountEnd.setCellValueFactory(new PropertyValueFactory<DiscountCode,String>("endOfDiscountPeriod"));
        maxDiscountAmount.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("maxDiscountAmount"));
        discountTotalTime.setCellValueFactory(new PropertyValueFactory<DiscountCode,Number>("totalTimesOfUse"));


        initializeObserverList();
        discountCodes.getColumns().addAll(discountId,discountAmount,discountStart,discountEnd,maxDiscountAmount,discountTotalTime);
        discountCodes.setEditable(true);
        discountCodes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        discountCodes.getSelectionModel().setCellSelectionEnabled(true);
        discountCodes.setItems(data);
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

    public void add(MouseEvent mouseEvent) {
    }

    public void edit(MouseEvent mouseEvent) {
    }

    public void remove(MouseEvent mouseEvent) {
    }
}
