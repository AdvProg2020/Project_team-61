package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.firms.Firm;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Category;
import model.productRelated.Product;


import java.util.ArrayList;

public class OutputHandler {


    public static void showAvailableFilters(ArrayList<String> listOfAvailableFilters) {

    }

    public static void showAllProductAfterFilter(ArrayList<Product> filteredProduct) {

    }

    public static void showCurrentFilter(ArrayList<String> currentFilters) {

    }

    public static void showAvailableSorts(ArrayList<String> availableSort) {

    }

    public static void showAllProductAfterSort(ArrayList<Product> productArrayList) {

    }

    public static void showCurrentSorts(ArrayList<String> currentSorts) {

    }

    public static void showProducts(ArrayList<Product> productArrayList) {

    }

    public static void showProductsIds(Product product) {

    }

    public static void compareProducts(Product first, Product second) {

    }

    public static void showAllSellersForOneProduct(ArrayList<Account> sellerArrayList){

    }
    //..................................................................

    public static void showAccountInformation(Account accountInfo){

    }

    public static void showAccounts(ArrayList<Account> accounts){

    }

    public static void showDiscountCodes(ArrayList<DiscountCode> discountCodes){

    }

    public static void showDiscountCode(DiscountCode discountInfo){

    }

    public static void showRequest(Request requestInfo){

    }

    public static void showRequests(ArrayList<Request> requests){

    }

    public static void showCategories(ArrayList<Category> categories){

    }

    public static void showOff(Sale saleInfo){

    }

    public static void showOffs(ArrayList<Sale> sales){

    }

  //  public static void showBalance(){

  //  }

    public static void showSalesHistory(ArrayList<SaleLog> saleLogs){

    }

    public static void showFirmInformation(Firm firmInfo){

    }

    public static void showCustomerLog(BuyLog buyLog){

    }

    public static void showOrders(ArrayList<BuyLog> buyLogs){


    }

    public static void showOrder(BuyLog buyLog){

    }

    public static void showProductShow(ArrayList<Seller> sellers){

    }

    public static void showProductBuyers(ArrayList<Customer> customers){

    }

    public static void showProduct(Product product){

    }

    public static void showTotalPrice(double price){

    }

    public static void showBalance(double balance){

    }


}
