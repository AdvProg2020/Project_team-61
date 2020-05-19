package model.sort;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import controller.request.Request;
import model.accounts.Account;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Category;
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
    private static ArrayList<Product> newArrayOfProductSort = Product.getProductList();
    private static ArrayList<Account> newArrayOfAccountSort = Account.getAllAccounts();
    private static ArrayList<DiscountCode> newArrayOfDiscountCodeSort = DiscountCode.getAllDiscountCodes();
    private static ArrayList<Category> newArrayOfCategory = Category.getAllCategories();
    private static ArrayList<Request> newArrayOfRequest = Request.getAllRequests();
    private static ArrayList<BuyLog> newArrayOfBuyLog = BuyLog.getAllCustomersLog();
    private static ArrayList<SaleLog> newArrayOfSalelog = SaleLog.getAllSellersLog();


//    static {
//        try {
//            newArrayOfProductSort = getProductFromFile();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


    public static void setNewArrayOfAccountSort(ArrayList<Account> newArrayOfAccountSort) {
        Sort.newArrayOfAccountSort = newArrayOfAccountSort;
    }

    public static void setNewArrayOfBuyLog(ArrayList<BuyLog> newArrayOfBuyLog) {
        Sort.newArrayOfBuyLog = newArrayOfBuyLog;
    }

    public static void setNewArrayOfCategory(ArrayList<Category> newArrayOfCategory) {
        Sort.newArrayOfCategory = newArrayOfCategory;
    }

    public static void setNewArrayOfDiscountCodeSort(ArrayList<DiscountCode> newArrayOfDiscountCodeSort) {
        Sort.newArrayOfDiscountCodeSort = newArrayOfDiscountCodeSort;
    }

    public static void setNewArrayOfRequest(ArrayList<Request> newArrayOfRequest) {
        Sort.newArrayOfRequest = newArrayOfRequest;
    }

    public static void setNewArrayOfSalelog(ArrayList<SaleLog> newArrayOfSalelog) {
        Sort.newArrayOfSalelog = newArrayOfSalelog;
    }

    private static ArrayList<ArrayList<Product>> listOfProSorts = new ArrayList<>();
    private static ArrayList<String> availableSorts=new ArrayList<>();

    public Sort(ArrayList<String> availableSorts) {
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

    public static void setNewArrayOfProductSort(ArrayList<Product> newArrayOfProductSort) {
        Sort.newArrayOfProductSort = newArrayOfProductSort;
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
        listOfProSorts.add(newArrayOfProductSort);
        availableSorts.remove(0);
        return newArrayOfProductSort;
    }

    public static ArrayList<Product> scoreSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForScore);
        listOfProSorts.add(newArrayOfProductSort);
        availableSorts.remove(1);
        return newArrayOfProductSort;
    }

    public static ArrayList<Account> accountSortUserName() throws FileNotFoundException {
        Collections.sort(newArrayOfAccountSort,Account.accountComparatorForUsername);
//        listOfAccountfSorts.add(newArrayOfAccountSort);
//        availableSorts.remove(2);
        return newArrayOfAccountSort;
    }

    public static ArrayList<Category> categoryNameSort(){
        Collections.sort(newArrayOfCategory,Category.productComparatorForView);
//        availableSorts.remove(3);
//        listOfCatSort.add(newArrayOfCategory);
        return newArrayOfCategory;
    }

    public ArrayList<BuyLog> buyLogSortDate() throws FileNotFoundException {
        Collections.sort(newArrayOfBuyLog,BuyLog.productComparatorForScore);
//        availableSorts.remove(4);
//        listOfBuyLogSort.add(newArrayOfBuyLog);
        return newArrayOfBuyLog;
    }

    public ArrayList<SaleLog> saleLogSortDate() throws FileNotFoundException {
        Collections.sort(newArrayOfSalelog,SaleLog.productComparatorForScore);
//        availableSorts.remove(5);
//        listOfSaleLogtSort.add(newArrayOfSalelog);
        return newArrayOfSalelog;
    }

    public static ArrayList<DiscountCode> sortDiscountCodes(){
        Collections.sort(newArrayOfDiscountCodeSort,DiscountCode.productComparatorForView);
//        availableSorts.remove(6);
//        listOfDiSort.add(newArrayOfDiscountCodeSort);
        return newArrayOfDiscountCodeSort;
    }

    public static ArrayList<Request> sortRequest(){
        Collections.sort(newArrayOfRequest,Request.productComparatorForScore);
//        availableSorts.remove(7);
//        listOfRequestSort.add(newArrayOfRequest);
        return newArrayOfRequest;
    }

    //other---------------------------------------------------------------------------

    //finish
    public static ArrayList<Product> disableSort(){
        listOfProSorts.remove(listOfProSorts.size()-1);
        return listOfProSorts.get(listOfProSorts.size()-1);
    }

    //finish
    public static ArrayList<String> currentSorts() {
        ArrayList<String> current=new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (listOfProSorts.get(i)!=null){
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