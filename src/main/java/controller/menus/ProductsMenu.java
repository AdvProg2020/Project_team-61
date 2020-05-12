package controller.menus;

import model.productRelated.Category;
import model.productRelated.Product;
import model.filtar.Filter;
import model.sort.Sort;
import view.CommandProcessor;
import view.*;

import java.util.ArrayList;

public class ProductsMenu {

    private int outputNo;
    private Product product;
    private Category category;
    private Filter filter;
    private Sort sort;
    private String productId;


    public void processProducts() {
        CommandProcessor.setMenuStatus(MenuStatus.PRODUCTSMENU);
    }

    //array
    public void processViewCategories() {

    }

    //filter-------------------------------------------------------


    private boolean checkFilter(String filterName) {
        //if (filterName.matches("(?i)(?:)")) {
            if (filter.isThereFilterWithName(filterName)) {
                return true;
            } else outputNo = 0;
       // } else inputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    public void processFiltering() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    //finish//json
    public void showAvailableFilters() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
        OutputHandler.showAvailableFilters(Filter.showAvailableFilters());
    }

    //finish//json
    public void filter(String filterID) {
        ArrayList<Product> filteredArray = null;
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
        if (filteredArray != null){
            OutputHandler.showAllProductAfterFilter(filteredArray);
        }
    }

    //finish//json
    public void currentFilters() {
        OutputHandler.showCurrentFilter(Filter.currentFilters());
    }

    //finish
    public void disableFilter(String filterID) {
        if (checkFilter(filterID)) {
            Filter.disableFilter(filterID);
            outputNo =0 ;
        }else outputNo =0;
        OutputMassageHandler.showProductsOutput(outputNo);
    }


    //sort-----------------------------------------------------------------

    //finish
    private boolean checkSort(String sortName) {
        //if (sortName.matches("")) {
            if (sort.isThereSortWithName(sortName)) {
                return true;
            } else outputNo = 0;
        //} else inputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }


    public void processSorting() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.SORTING);
    }

    //finish//json
    public void showAvailableSorts() {
        OutputHandler.showAvailableSorts(sort.showAvailableSort());
    }

    //finish//json
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
        if (sortedList!=null){
            OutputHandler.showAllProductAfterSort(sortedList);
        }
    }


    //finish//json
    public void currentSorts() {
        OutputHandler.showCurrentSorts(sort.currentSorts());
    }

    //finish
    public void disableSort() {
        sort.disableSort();
        OutputMassageHandler.showProductsOutput();

    }

    //product--------------------------------------------------

    //finish//json
    public void processShowProducts() {
        OutputHandler.showProducts(Filter.newArrayOfProductFilter);
    }


    //GSON
    public void processShowProductsID(String productId) {
        this.productId = productId;
        OutputHandler.showProductsIds();
        CommandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
         
    }


    public String getProductId() {
        return productId;
    }
}
