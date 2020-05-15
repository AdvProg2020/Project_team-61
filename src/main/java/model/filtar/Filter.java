package model.filtar;


import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;
import view.FileHandling;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Filter {


    Product product;
    public static int numberOfFilter = 0;
    String filterName;
    public static ArrayList<Product> newArrayOfProductFilter;

    static {
        try {
            newArrayOfProductFilter = getListOfProductFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<Product>> listOfFilters = new ArrayList<>();
    public static ArrayList<ArrayList<Product>> helpFilter = new ArrayList<>();
    public static ArrayList<String> availableFilters = new ArrayList<>();

    //if category ->1    companies ->2   discount ->3   productName ->4

    public Filter(ArrayList<String> availableFilters) {
        availableFilters.add("category");
        availableFilters.add("companyName");
        availableFilters.add("discount");
        availableFilters.add("productName");
        availableFilters.add("periodFilter");
        availableFilters.add("isAvailable");
    }


    //setterGetter--------------------------------------------------------------------------

    public void setNumberOfFilter(int numberOfFilter) {
        this.numberOfFilter = numberOfFilter;
    }

    public int getNumberOfFilter() {
        return numberOfFilter;
    }

    public static ArrayList<Product> getListOfProductFromFile() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader= FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        return data;
    }


    //filtering-----------------------------------------------------------------------------



    public static ArrayList<Product> getNewArrayOfProductFilter() {
        return newArrayOfProductFilter;
    }

    public static ArrayList<String> getAvailableFilters() {
        return availableFilters;
    }

    public void categoryFilter(Category category) throws FileNotFoundException {
        newArrayOfProductFilter = newArrayOfProductFilter.stream().filter(product1 -> product.getCategory().equals(category)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(1, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(0);

    }

    public void companiesFilter(String companiesName) throws FileNotFoundException {
        newArrayOfProductFilter = newArrayOfProductFilter.stream().filter(product1 -> product.getCompaniesName().equals(companiesName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(2, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(1);

    }

    public void productNameFilter(String productName) throws FileNotFoundException {
        newArrayOfProductFilter = newArrayOfProductFilter.stream().filter(product1 -> product.getProductName().equals(productName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(4, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(3);

    }

    public void periodFilter(int n, int m) {
        ArrayList<Product> helper=null;
        for (Product product1 : newArrayOfProductFilter) {
            if (product1.getPrice() > n && product1.getPrice() < m) {
                helper.add(product1);
            }
        }
        newArrayOfProductFilter.removeAll(helper);
        listOfFilters.add(5,newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(4);
    }

    public void isAvailable(){
        ArrayList<Product> delete=new ArrayList<>();
        for (Product product1 : Product.getProductList()) {
            if (product1.getNumberOfProducts()==0){
                delete.add(product1);
            }
        }
        newArrayOfProductFilter.removeAll(delete);
        listOfFilters.add(6,newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        availableFilters.remove(5);

    }

    //others-----------------------------------------------------------------------------

    //finish
    public static ArrayList<Product> disableFilter(String filterName) {
        if (filterName.equals("categoryFilter")) {
            numberOfFilter = 1;
        } else if (filterName.equals("companiesFilter")) {
            numberOfFilter = 2;
        } else if (filterName.equals("discountFilter")) {
            numberOfFilter = 3;
        } else if (filterName.equals("productNameFilter")) {
            numberOfFilter = 4;
        }else if (filterName.equals("periodFilter")){
            numberOfFilter=5;
        }
        helpFilter.remove(helpFilter.indexOf(listOfFilters.get(numberOfFilter)));
        listOfFilters.remove(numberOfFilter);
        return helpFilter.get(helpFilter.size() - 1);
    }
    //finish
    public static ArrayList<String> currentFilters() {
        ArrayList<String> current = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            if (listOfFilters.get(i) != null) {
                if (i == 1) {
                    current.add("category");
                } else if (i == 2) {
                    current.add("companisName");
                } else if (i == 3) {
                    current.add("discount");
                } else if (i == 4) {
                    current.add("productName");
                }else if (i==5){
                    current.add("periodFilter");
                }
            }
        }
        return current;
    }

    public static ArrayList<String> showAvailableFilters() {
        return availableFilters;
    }

    public static boolean ifFilterAvailable(String filterName) {
        if (availableFilters.contains(filterName)) {
            return true;
        } else return false;
    }

    public boolean isThereFilterWithName(String filter) {
        if (filter.matches("category")) {
            return true;
        } else if (filter.matches("companyName")) {
            return true;
        } else if (filter.matches("discount")) {
            return true;
        } else if (filter.matches("productName")) {
            return true;
        }else if (filter.matches("periodFilter")){
            return true;
        }
        return false;
    }

}
