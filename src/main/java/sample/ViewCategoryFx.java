package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.log.BuyLog;
import model.productRelated.Category;
import model.productRelated.Product;

import java.io.IOException;

public class ViewCategoryFx {
    Category curCat;
    @FXML private TableView<Category> viewCategoryTable;
    @FXML private TableColumn<Category,String> traitCatColumn;
    @FXML private TableColumn<Category, Product> productCatColumn;
    @FXML private Label catNameLabel;
    @FXML private Text catNameText;
    public  static ObservableList<Category> data = FXCollections.observableArrayList();
    public  void initializeObserverList() {
        data.addAll(curCat);
    }
    @FXML
    public void initialize()throws IOException {
        productCatColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        traitCatColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        initializeObserverList();
        viewCategoryTable.getColumns().addAll(productCatColumn,traitCatColumn);
        viewCategoryTable.setItems(data);
    }
}