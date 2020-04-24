package model.productRelated.filtar;

import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Filter {
    Product product;
    int numberOfFilter = 0;
    String filterName;
    ArrayList<Product> newArrayOfProductFilter = new ArrayList<>();
    ArrayList<ArrayList<Product>> listOfFilters = new ArrayList<>();
    ArrayList<ArrayList<Product>> helpFilter = new ArrayList<>();

    //if category ->1    companies ->2   discount ->3   productName ->4


    //setterGetter--------------------------------------------------------------------------

    public void setNumberOfFilter(int numberOfFilter) {
        this.numberOfFilter = numberOfFilter;
    }

    public int getNumberOfFilter() {
        return numberOfFilter;
    }

    //filtering-----------------------------------------------------------------------------

    public ArrayList<Product> categoryFilter(Category category) {
        newArrayOfProductFilter = product.productList().stream().filter(product1 -> product.getCategory().equals(category)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(1, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        return product.productList();
    }

    public ArrayList<Product> companiesFilter(String companiesName) {
        newArrayOfProductFilter = product.productList().stream().filter(product1 -> product.getCompaniesName().equals(companiesName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(2, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        return newArrayOfProductFilter;
    }

    public ArrayList<Product> discountFilter(DiscountCode discountCode) {
        newArrayOfProductFilter = product.productList().stream().filter(product1 -> product.getHasDiscount()).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(3, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        return newArrayOfProductFilter;
    }

    public ArrayList<Product> productNameFilter(String productName) {
        newArrayOfProductFilter = product.productList().stream().filter(product1 -> product.getProductName().equals(productName)).collect(Collectors.toCollection(ArrayList::new));
        listOfFilters.add(4, newArrayOfProductFilter);
        helpFilter.add(newArrayOfProductFilter);
        return newArrayOfProductFilter;
    }


    //others-----------------------------------------------------------------------------

    //finish
    public ArrayList<Product> disableFilter(){
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
    public ArrayList<String> currentFilters() {
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

}
