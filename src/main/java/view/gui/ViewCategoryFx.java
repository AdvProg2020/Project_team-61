package view.gui;

import controller.menus.LoginMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.productRelated.Category;
import model.productRelated.Product;

import java.io.IOException;
import java.util.Objects;

public class ViewCategoryFx {
    private static Category curCat;
   /* @FXML
    private TableView<Category> viewCategoryTable;
    @FXML
    private TableColumn<Category, String> traitCatColumn;
    @FXML
    private TableColumn<Category, Product> productCatColumn;*/
    @FXML
    private Label catNameLabel;
  //  @FXML
   // private Text catNameText;
    @FXML private ListView<Product> products;
    private static Parent priRoot;
    private static Parent root;

    public static ObservableList<Product> data = FXCollections.observableArrayList();

    public static void setCurCat(Category curCat) {
        ViewCategoryFx.curCat = curCat;
    }

    public static void setPriRoot(Parent priRoot) {
        ViewCategoryFx.priRoot = priRoot;
    }

    public void initializeObserverList() {
        data.clear();
        data.addAll(curCat.getAllProducts());
    }

    @FXML
    public void initialize() throws IOException {
        catNameLabel.setText(curCat.getName());
      //  productCatColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        //traitCatColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        initializeObserverList();
        products.setEditable(true);

        //  viewCategoryTable.getColumns().addAll(productCatColumn, traitCatColumn);
        products.setItems(data);
    }
    public void back(ActionEvent actionEvent){
        root=priRoot;
        goToPage();
    }
    public void userMenu(ActionEvent actionEvent) throws IOException {
        LoginMenu.processLogout();
        root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        goToPage();

    }
    public  void logout(ActionEvent actionEvent) throws IOException {
        if(LoginMenu.getLoginAccount() instanceof Seller){
            root = FXMLLoader.load(Objects.requireNonNull(SellerMenuFx.class.getClassLoader().getResource("sellerMenuFx.fxml")));
        } else if(LoginMenu.getLoginAccount() instanceof Manager){
            root = FXMLLoader.load(Objects.requireNonNull(ManagerMenuFx.class.getClassLoader().getResource("managerMenuFx.fxml")));
        }else if(LoginMenu.getLoginAccount() instanceof Customer){
            root = FXMLLoader.load(Objects.requireNonNull(CustomerMenuFx.class.getClassLoader().getResource("customerMenuFx.fxml")));
        }
        goToPage();

    }
    private static void goToPage(){
        Scene pageTwoScene = new Scene(root);
        //Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Main.primStage.setScene(pageTwoScene);
        Main.primStage.show();
    }
}
