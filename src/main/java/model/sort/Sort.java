package model.sort;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.accounts.Account;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.Sale;
import model.productRelated.Product;
import model.productRelated.Score;
import view.CommandProcessor;
import view.FileHandling;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class Sort {
    public Product product;
    int numberOfSort=0;
    private static ArrayList<Product> newArrayOfProductSort = new ArrayList<>();

    static {
        try {
            newArrayOfProductSort = getProductFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<ArrayList<Product>> listOfSorts = new ArrayList<>();
    private static ArrayList<String> availableSorts=new ArrayList<>();

    public Sort(ArrayList<String> availableSorts) throws FileNotFoundException {
        availableSorts.add("numberOfView");
        availableSorts.add("score");
    }

    //if view->1   score ->2


    public static ArrayList<Product> getProductFromFile() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader= FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        return data;
    }

    //sorts----------------------------------------------------------------------


    public static ArrayList<Product> getNewArrayOfProductSort() {
        return newArrayOfProductSort;
    }

    public static ArrayList<String> getAvailableSorts() {
        return availableSorts;
    }

    public static ArrayList<Product> numberOfViewsSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForView);
        listOfSorts.add(newArrayOfProductSort);
        availableSorts.remove(0);
        return newArrayOfProductSort;
    }

    public static ArrayList<Product> scoreSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForScore);
        listOfSorts.add(newArrayOfProductSort);
        availableSorts.remove(1);
        return newArrayOfProductSort;
    }

    public ArrayList<Account> accountSortUserName() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Account>>() {
        }.getType();
        JsonReader sellerReader=FileHandling.readFile("account.json");
        ArrayList<Account> data = FileHandling.getGson().fromJson(sellerReader, REVIEW_TYPE);
        Collections.sort(data,Account.accountComparatorForUsername);
        return data;
    }

    public ArrayList<BuyLog> accountSortLogs() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        Collections.sort(data, new Comparator<BuyLog>() {
            public int compare(BuyLog o1, BuyLog o2) {
                return o1.getLocalDateTimeForLog().compareTo(o2.getLocalDateTimeForLog());
            }
        });
        return data;
    }

    public ArrayList<BuyLog> buyLogSortDate() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        Collections.sort(data, new Comparator<BuyLog>() {
            public int compare(BuyLog o1, BuyLog o2) {
                return o1.getLocalDateTimeForLog().compareTo(o2.getLocalDateTimeForLog());
            }
        });
        return data;
    }

    public ArrayList<SaleLog> saleLogSortDate() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<SaleLog>>() {
        }.getType();
        JsonReader SaleLogReader=FileHandling.readFile("saleLog.json");
        ArrayList<SaleLog> data = FileHandling.getGson().fromJson(SaleLogReader, REVIEW_TYPE);
        Collections.sort(data, new Comparator<SaleLog>() {
            public int compare(SaleLog o1, SaleLog o2) {
                return o1.getLocalDateTimeForSaleLog().compareTo(o2.getLocalDateTimeForSaleLog());
            }
        });
        return data;
    }

    //other---------------------------------------------------------------------------

    //finish
    public static ArrayList<Product> disableSort(){
        listOfSorts.remove(listOfSorts.size()-1);
        return listOfSorts.get(listOfSorts.size()-1);
    }

    //finish
    public static ArrayList<String> currentSorts() {
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

    public static ArrayList<String> showAvailableSort(){
        return availableSorts;
    }

    public static boolean ifAvailable(String sortId){
        if (availableSorts.contains(sortId)){
            return true;
        }
        else return false;
    }

    public static boolean isThereSortWithName(String sortName){
        if (sortName.matches("numberOfView")){
            return true;
        }
        else if (sortName.matches("score")){
            return true;
        }
        return false;
    }
}