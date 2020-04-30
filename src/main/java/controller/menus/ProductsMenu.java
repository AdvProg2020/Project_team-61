package controller.menus;

import model.productRelated.Category;
import model.productRelated.Product;
import model.filtar.Filter;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;

public class ProductsMenu {
    private int outputNo;
    private int inputNo;
    private Product product;
    private Category category;
    private CommandProcessor commandProcessor;
    private OutputHandler outputHandler = new OutputHandler();
    private Filter filter;
    private String productId;

    public void processProducts() {
        commandProcessor.setMenuStatus(MenuStatus.PRODUCTSMENU);
    }

    //array
    public void processViewCategories() {

    }

    //filter-------------------------------------------------------
    private boolean checkFilter(String filter) {
        if (filter.matches("")) {
            // if (filter.isThereFilterWithName(filter)) {
            return true;
            // } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void processFiltering() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    //array
    public void showAvailableFilters() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    public void filter(String filterID) {
        if (checkFilter(filterID)) {

        }

    }

    //array
    public void currentFilters() {

    }

    public void disableFilter(String filterID) {
        if (checkFilter(filterID)) {

        }
    }


    //sort-----------------------------------------------------------------
    private boolean checkSort(String sort) {
        if (sort.matches("")) {
            //if (sort.isThereFilterWithName(sort)) {
            return true;
            // } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void processSorting() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.SORTING);

    }

    public void showAvailableSorts() {

    }

    public void sort(String sortID) {
        if (checkSort(sortID)) {

        }

    }


    //array
    public void currentSorts() {

    }

    public void disableSort() {
        //in dastoore bayad badesh esm bgire :D?

    }

    //product--------------------------------------------------
    //array
    public void processShowProducts() {
        //tozih dre
    }

    public void processShowProductsID(String productId) {
        this.productId = productId;
        commandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
    }

    public String getProductId() {
        return productId;
    }
}
