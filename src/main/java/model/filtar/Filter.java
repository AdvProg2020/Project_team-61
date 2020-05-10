package model.filtar;


import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Filter {
    Product product;
    public static int numberOfFilter = 0;
    String filterName;
    public static ArrayList<Product> newArrayOfProductFilter = new ArrayList<>();
    public static ArrayList<ArrayList<Product>> listOfFilters = new ArrayList<>();
    public static ArrayList<ArrayList<Product>> helpFilter = new ArrayList<>();
    public static ArrayList<String> availableFilters=new ArrayList<>();

    //if category ->1    companies ->2   discount ->3   productName ->4

    public Filter(ArrayList<String> availableFilters) {
        availableFilters.add("category");
        availableFilters.add("companyName");
        availableFilters.add("discount");
        availableFilters.add("productName");
    }


    //setterGetter--------------------------------------------------------------------------

    public void setNumberOfFilter(int numberOfFilter) {
        this.numberOfFilter = numberOfFilter;
    }

    public int getNumberOfFilter() {
        return numberOfFilter;
    }


    //filtering-----------------------------------------------------------------------------

    public ArrayList<Product> categoryFilter(Category category) {
        newArrayOfProductFilter = Product.getProductList().stream().filter(product1 -> product.getCategory().equals(category)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(1, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(0);
        return product.getProductList();
    }

    public ArrayList<Product> companiesFilter(String companiesName) {
        newArrayOfProductFilter = Product.getProductList().stream().filter(product1 -> product.getCompaniesName().equals(companiesName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(2, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(1);
        return newArrayOfProductFilter;
    }

    public ArrayList<Product> discountFilter(DiscountCode discountCode) {
        newArrayOfProductFilter = Product.getProductList().stream().filter(product1 -> product.getHasDiscount()).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(3, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(2);
        return newArrayOfProductFilter;
    }

    public ArrayList<Product> productNameFilter(String productName) {
        newArrayOfProductFilter = Product.getProductList().stream().filter(product1 -> product.getProductName().equals(productName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(4, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(3);
        return newArrayOfProductFilter;
    }


    //others-----------------------------------------------------------------------------

    //finish
    public static ArrayList<Product> disableFilter(String filterName){
        if (filterName.equals("categoryFilter")){
            numberOfFilter=1;
        }
        else if (filterName.equals("companiesFilter")){
            numberOfFilter=2;
        }
        else if (filterName.equals("discountFilter")){
            numberOfFilter=3;
        }
        else if (filterName.equals("productNameFilter")) {
            numberOfFilter = 4;
        }
        helpFilter.remove(helpFilter.indexOf(listOfFilters.get(numberOfFilter)));
        listOfFilters.remove(numberOfFilter);
        return helpFilter.get(helpFilter.size()-1);
    }

    //finish
    public static ArrayList<String> currentFilters() {
        ArrayList<String> current=new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            if (listOfFilters.get(i)!=null){
                if (i==1){
                    current.add("category");
                }
                else if (i==2){
                    current.add("companisName");
                }
                else if (i==3){
                    current.add("discount");
                }
                else if (i==4){
                    current.add("productName");
                }
            }
        }
        return current;
    }

    public static ArrayList<String> showAvailableFilters(){
        return availableFilters;
    }

    public static boolean ifFilterAvailable(String filterName){
        if (availableFilters.contains(filterName)){
            return true;
        }
        else return false;
    }

    public boolean isThereFilterWithName(String filter){
        if (filter.matches("category")){
            return true;
        }
        else if (filter.matches("companyName")){
            return true;
        }
        else if (filter.matches("discount")){
            return true;
        }
        else if (filter.matches("productName")){
            return true;
        }
        return false;
    }

}