package view.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.productRelated.Category;
import model.productRelated.Product;

import java.io.IOException;

public class ViewCategoryFx {
    private static Category curCat;
    @FXML
    private TableView<Category> viewCategoryTable;
    @FXML
    private TableColumn<Category, String> traitCatColumn;
    @FXML
    private TableColumn<Category, Product> productCatColumn;
    @FXML
    private Label catNameLabel;
    @FXML
    private Text catNameText;
    private static Parent priRoot;
    public static ObservableList<Category> data = FXCollections.observableArrayList();

    public static void setCurCat(Category curCat) {
        ViewCategoryFx.curCat = curCat;
    }

    public static void setPriRoot(Parent priRoot) {
        ViewCategoryFx.priRoot = priRoot;
    }

    public void initializeObserverList() {
        data.clear();
        data.addAll(curCat);
    }

    @FXML
    public void initialize() throws IOException {
        catNameLabel.setText(curCat.getName());
        productCatColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        traitCatColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        initializeObserverList();
        viewCategoryTable.setEditable(true);

        //  viewCategoryTable.getColumns().addAll(productCatColumn, traitCatColumn);
        viewCategoryTable.setItems(data);
    }
}