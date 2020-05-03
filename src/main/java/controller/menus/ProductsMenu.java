package controller.menus;

import model.productRelated.Category;
import model.productRelated.Product;
import model.filtar.Filter;
import model.sort.Sort;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;

import java.util.ArrayList;

public class ProductsMenu {
    private int outputNo;
    private int inputNo;
    private Product product;
    private Category category;
    private CommandProcessor commandProcessor;
    private OutputHandler outputHandler = new OutputHandler();
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
    public ArrayList<String> showAvailableFilters() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
        return Filter.showAvailableFilters();
    }

    //finish
    public ArrayList<Product> filter(String filterID) {
        if (checkFilter(filterID)) {
            if (Filter.ifFilterAvailable(filterID)) {
                if (filterID.equals("category")) {
                    return filter.categoryFilter(product.getCategory());
                } else if (filterID.equals("companyName")) {
                    return filter.companiesFilter(product.getCompaniesName());
                } else if (filterID.equals("discount")) {
                    if (product.getHasDiscount()) {
                        return filter.discountFilter(product.getDiscountCode());
                    }
                } else if (filterID.equals("productName")) {
                    return filter.productNameFilter(product.getProductName());
                }
            }
        }
        return null;
    }

    //finish
    public ArrayList<String> currentFilters() {
        return Filter.currentFilters();
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
            if (sort.isThereFilterWithName(sortName)) {
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
    public ArrayList<String> showAvailableSorts() {
        return sort.showAvailableSort();
    }

    //finish
    public ArrayList<Product> sort(String sortID) {
        if (checkSort(sortID)) {
            if (sort.ifAvailable(sortID)) {
                if (sortID.equals("numberOfView")) {
                    return sort.scoreSort();
                } else if (sortID.equals("score")) {
                    return sort.numberOfViewsSort();
                }
            }
        }
        return null;
    }


    //finish
    public ArrayList<String> currentSorts() {
        return sort.currentSorts();
    }

    //finish
    public void disableSort(String sortName) {
        sort.disableSort(sortName);

    }

    //product--------------------------------------------------

    //finish
    public ArrayList<Product> processShowProducts() {
        return Filter.newArrayOfProductFilter;
    }

    //finish
    public ArrayList<String> processShowProductsID(String productId) {
        this.productId = productId;
        commandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
        return Product.listOfId;
    }

    public String getProductId() {
        return productId;
    }
}
