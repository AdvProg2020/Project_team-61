package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.accounts.Account;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Product;

public class ViewSaleFx {
    @FXML private Label startSaleLabel;
    @FXML private TableColumn<Product, String> saleProduct;
    @FXML private Label endSaleLabel;
    @FXML private Label saleAmountLabel;
    @FXML private TableView<Product> saleProducts;
    @FXML private Label saleIdLabel;

    private static Sale curSale;
    public static ObservableList list = FXCollections.observableArrayList();
    private static Parent root;

    public static Sale getCurSale() {
        return curSale;
    }

    public static void setCurSale(Sale curSale) {
        ViewSaleFx.curSale = curSale;
    }




    private void makeTree() {
        saleProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));

       list.clear();
       list.addAll(curSale.getAllSaleProducts());
        //  usersList.getColumns().addAll(UserId,userName,userLast,userBirth,userPhoneNo,userEmail);
        saleProducts.setEditable(true);
        saleProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        saleProducts.getSelectionModel().setCellSelectionEnabled(true);

        saleProducts.setItems(list);


    }
    @FXML
    public void initialize()  {
        makeTree();
        saleIdLabel.setText(String.valueOf(curSale.getOffId()));
        startSaleLabel.setText(String.valueOf(curSale.getStartOfSalePeriod()));
        endSaleLabel.setText(String.valueOf(curSale.getEndOfSalePeriod()));
        saleAmountLabel.setText(String.valueOf(curSale.getSaleAmount()));


    }




    public void back(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) {

    }

    public void userMenu(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {

    }

}
