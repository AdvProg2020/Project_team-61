package model.sort;

import model.accounts.Account;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.Sale;
import model.productRelated.Product;
import model.productRelated.Score;
import view.CommandProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class Sort {
    public Product product;
    int numberOfSort=0;
    ArrayList<Product> newArrayOfProductSort = Product.getProductList();
    ArrayList<ArrayList<Product>> listOfSorts = new ArrayList<>();
    ArrayList<String> availableSorts=new ArrayList<>();

    public Sort(ArrayList<String> availableSorts) {
        availableSorts.add("numberOfView");
        availableSorts.add("score");
    }

    //if view->1   score ->2



    //sorts----------------------------------------------------------------------

    public ArrayList<Product> numberOfViewsSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForView);
        listOfSorts.add(newArrayOfProductSort);
        availableSorts.remove(0);
        return newArrayOfProductSort;
    }

    public ArrayList<Product> scoreSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForScore);
        listOfSorts.add(newArrayOfProductSort);
        availableSorts.remove(1);
        return newArrayOfProductSort;
    }

    public ArrayList<Account> accountSortUserName(){
        ArrayList<Account> helper=Account.getAllAccounts();
        Collections.sort(helper,Account.accountComparatorForUsername);
        return helper;
    }

    public ArrayList<BuyLog> accountSortLogs(){
        ArrayList<BuyLog> helper=Account.getAllBuyLogs();
        Collections.sort(helper, new Comparator<BuyLog>() {
            public int compare(BuyLog o1, BuyLog o2) {
                return o1.getLocalDateTimeForLog().compareTo(o2.getLocalDateTimeForLog());
            }
        });
        return helper;
    }

    public ArrayList<BuyLog> buyLogSortDate(){
        ArrayList<BuyLog> helper=BuyLog.getAllCustomersLog();
        Collections.sort(helper, new Comparator<BuyLog>() {
            public int compare(BuyLog o1, BuyLog o2) {
                return o1.getLocalDateTimeForLog().compareTo(o2.getLocalDateTimeForLog());
            }
        });
        return helper;
    }

    public ArrayList<SaleLog> saleLogSortDate(){
        ArrayList<SaleLog> helper=SaleLog.getAllSellersLog();
        Collections.sort(helper, new Comparator<SaleLog>() {
            public int compare(SaleLog o1, SaleLog o2) {
                return o1.getLocalDateTimeForSaleLog().compareTo(o2.getLocalDateTimeForSaleLog());
            }
        });
        return helper;
    }

    //other---------------------------------------------------------------------------

    //finish
    public ArrayList<Product> disableSort(){
        listOfSorts.remove(listOfSorts.size()-1);
        return listOfSorts.get(listOfSorts.size()-1);
    }

    //finish
    public ArrayList<String> currentSorts() {
        ArrayList<String> current=new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (listOfSorts.get(i)!=null){
                if (i==1){
                    current.add("view");
                }
                else if (i==2){
                    current.add("score");
                }
            }
        }
        return current;
    }

    public ArrayList<String> showAvailableSort(){
        return availableSorts;
    }

    public boolean ifAvailable(String sortId){
        if (availableSorts.contains(sortId)){
            return true;
        }
        else return false;
    }

    public boolean isThereSortWithName(String sortName){
        if (sortName.matches("numberOfView")){
            return true;
        }
        else if (sortName.matches("score")){
            return true;
        }
        return false;
    }
}