package controller.menus;

import model.off.Sale;
import model.productRelated.Category;
import model.productRelated.Product;
import model.filtar.Filter;
import model.sort.Sort;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputMassageHandler;
import view.SubMenuStatus;

import java.util.ArrayList;

public class ProductsMenu {
    private int outputNo;
    private int inputNo;
    private Product product;
    private Category category;
    private CommandProcessor commandProcessor;
    private OutputMassageHandler outputHandler = new OutputMassageHandler();
    private Filter filter;
    private Sort sort;
    private String productId;

    public void processProducts() {
        commandProcessor.setMenuStatus(MenuStatus.PRODUCTSMENU);
    }

    //array
    public void processViewCategories() {

    }

    //filter-------------------------------------------------------

    //finish
    private boolean checkFilter(String filterName) {
        if (filterName.matches("")) {
            if (filter.isThereFilterWithName(filterName)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void processFiltering() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    //finish
    public void showAvailableFilters() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
        ArrayList<String> availableFilters=Filter.showAvailableFilters();
    }

    //finish
    public void filter(String filterID) {
        ArrayList<Product> filteredArray=null;
        if (checkFilter(filterID)) {
            if (Filter.ifFilterAvailable(filterID)) {
                if (filterID.equals("category")) {
                    filteredArray=filter.categoryFilter(product.getCategory());
                } else if (filterID.equals("companyName")) {
                    filteredArray=filter.companiesFilter(product.getCompaniesName());
                } else if (filterID.equals("discount")) {
                    if (product.getHasDiscount()) {
                        filteredArray=filter.discountFilter(product.getDiscountCode());
                    }
                } else if (filterID.equals("productName")) {
                    filteredArray=filter.productNameFilter(product.getProductName());
                }else if (filterID.equalsIgnoreCase("periodFilter")){
                    // return filter.periodFilter();
                }
            }
        }
    }

    //finish
    public void currentFilters() {
        ArrayList<String> currentFilters=Filter.currentFilters();

    }

    //finish
    public void disableFilter(String filterID) {
        if (checkFilter(filterID)) {
            Filter.disableFilter(filterID);
        }
    }


    //sort-----------------------------------------------------------------

    //finish
    private boolean checkSort(String sortName) {
        if (sortName.matches("")) {
            if (sort.isThereSortWithName(sortName)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }


    public void processSorting() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.SORTING);
    }

    //finish
    public void showAvailableSorts() {
        ArrayList<String> availableSorts=sort.showAvailableSort();
    }

    //finish
    public void sort(String sortID) {
        ArrayList<Product> sortedList=null;
        if (checkSort(sortID)) {
            if (sort.ifAvailable(sortID)) {
                if (sortID.equalsIgnoreCase("number Of View")) {
                    sortedList=sort.scoreSort();
                } else if (sortID.equalsIgnoreCase("score")) {
                    sortedList=sort.numberOfViewsSort();
                }
            }
        }
    }


    //finish
    public void currentSorts() {
        ArrayList<String> currentSorts=sort.currentSorts();
    }

    //finish
    public void disableSort() {
        sort.disableSort();

    }

    //product--------------------------------------------------

    //finish
    public void processShowProducts() {
        ArrayList<Product> listOfProductToShow= Filter.newArrayOfProductFilter;
    }

    //finish
    public void processShowProductsID(String productId) {
        this.productId = productId;
        commandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
        ArrayList<String> productsId=Product.listOfId;
    }

    public String getProductId() {
        return productId;
    }
}
