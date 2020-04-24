package controller.menus;

import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.filtar.Filter;
import view.CommandProcessor;
import view.OutputHandler;
import view.SubMenuStatus;

public class ProductsMenu {
    private int outputNo;
    private int inputNo;
    private Product product;
    private Category category;
    private CommandProcessor commandProcessor;
    private Filter filter;

    public void processProducts() {
        OutputHandler.showObjectOutput(product.listProduct(), product.li(), 1);
    }

    public void processViewCategories() {
        OutputHandler.showObjectOutput(category.ca(), category.getCategoryListSize(), 1);
    }

    public void processFiltering() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    public void showAvailableFilters() {
        OutputHandler.showObjectOutput(filter.list(),filter., 1);
    }

    public void filter(String filterID) {
        OutputHandler.showObjectOutput(filter.getFileterWithID(filterID), 1, 1);
    }

    public void currentFilters() {

    }

    public void disableFilter(String filterID) {

    }


    public void processSorting() {

    }

    public void showAvailableSorts() {

    }

    public void sort(String sortID) {

    }


    public void currentSorts() {

    }

    public void disableSort() {

    }


    public void processShowProducts() {
        OutputHandler.showObjectOutput(product.listProduct(), product.li(), 1);
    }

    public void processShowProductsID(String id) {
        OutputHandler.showObjectOutput(product.getProductById(id), 1, 1);
    }
}
