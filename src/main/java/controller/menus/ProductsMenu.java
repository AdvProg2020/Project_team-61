package controller.menus;

import model.productRelated.Category;
import model.productRelated.Product;
import model.filtar.Filter;
import model.sort.Sort;
import view.CommandProcessor;
import view.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductsMenu {

    private static int outputNo;
    private static Product product;
    private Category category;
    private static Filter filter;
    private static Sort sort;
    private static String productId;


    public static void processProducts() {
        CommandProcessor.setMenuStatus(MenuStatus.PRODUCTSMENU);
    }

    //array
    public void processViewCategories() {

    }

    //filter-------------------------------------------------------


    //done
    private static boolean checkFilter(String filterName) {
        //if (filterName.matches("(?i)(?:)")) {
            if (filter.isThereFilterWithName(filterName)) {
                return true;
            } else outputNo = 0;
       // } else inputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //done
    public static void processFiltering() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
    }

    //done
    public static void showAvailableFilters() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
        OutputHandler.showAvailableFilters(Filter.getAvailableFilters());
    }

    //firm ro che konam
    public static void filter(String filterID) throws FileNotFoundException {
        if (checkFilter(filterID)) {
            if (Filter.ifFilterAvailable(filterID)) {
                if (filterID.equals("category")) {
                    filter.categoryFilter(product.getCategory());
                } else if (filterID.equals("companyName")) {
                    filter.companiesFilter(product.getFirm().getName());
                } else if (filterID.equals("productName")) {
                    filter.productNameFilter(product.getProductName());
                }else if (filterID.equalsIgnoreCase("periodFilter")){
                    // return filter.periodFilter();
                }else if (filterID.equalsIgnoreCase("isAvailable")){
                    filter.isAvailable();
                }
            }
        }
        OutputHandler.showAllProductAfterFilter();
    }

    //done
    public static void currentFilters() {
        OutputHandler.showCurrentFilter(Filter.currentFilters());
    }

    //not done
    public static void disableFilter(String filterID) {
        if (checkFilter(filterID)) {
            Filter.disableFilter(filterID);
            outputNo =0 ;
        }else outputNo =0;
        OutputMassageHandler.showProductsOutput(outputNo);
    }


    //sort-----------------------------------------------------------------

    //done
    private static boolean checkSort(String sortName) {
        //if (sortName.matches("")) {
            if (sort.isThereSortWithName(sortName)) {
                return true;
            } else outputNo = 0;
        //} else inputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //done
    public static void processSorting() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.SORTING);
    }

    //done
    public static void showAvailableSorts() {
        OutputHandler.showAvailableSorts(Sort.getAvailableSorts());
    }

    //done
    public static void sort(String sortID) {
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

    //done
    public static void currentSorts() {
        OutputHandler.showCurrentSorts(Sort.currentSorts());
    }


    public static void disableSort() {
        sort.disableSort();
       // OutputMassageHandler.showProductsOutput();
    }

    //product--------------------------------------------------

    //done
    public void processShowProducts() throws FileNotFoundException {
        OutputHandler.showProducts(Filter.getNewArrayOfProductFilter());
    }

    //done
    public void processShowProductsID() throws FileNotFoundException {
        OutputHandler.showProductsIds();
        CommandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
         
    }

    //done
    public static String getProductId() {
        return productId;
    }
}
