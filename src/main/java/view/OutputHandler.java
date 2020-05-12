package view;

import com.google.gson.Gson;
import model.accounts.Seller;
import model.productRelated.Product;

import java.security.PublicKey;
import java.util.ArrayList;

public class OutputHandler {
    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }


    public static String makeJsonFromStringArrayList(ArrayList<String> arrayList) {
        return OutputHandler.getGson().toJson(arrayList);
    }

    public static String makeJsonFromProductArrayList(ArrayList<Product> productArrayList) {
        return OutputHandler.getGson().toJson(productArrayList);
    }

    public static String makeJsonFromSellerArrayList(ArrayList<Seller> sellerArrayList){
        return OutputHandler.getGson().toJson(sellerArrayList);
    }

    public static String showAvailableFilters(ArrayList<String> listOfAvailableFilters) {
        return makeJsonFromStringArrayList(listOfAvailableFilters);
    }

    public static String showAllProductAfterFilter(ArrayList<Product> filteredProduct) {
        return makeJsonFromProductArrayList(filteredProduct);
    }

    public static String showCurrentFilter(ArrayList<String> currentFilters) {
        return makeJsonFromStringArrayList(currentFilters);
    }

    public static String showAvailableSorts(ArrayList<String> availableSort) {
        return makeJsonFromStringArrayList(availableSort);
    }

    public static String showAllProductAfterSort(ArrayList<Product> productArrayList) {
        return makeJsonFromProductArrayList(productArrayList);
    }

    public static String showCurrentSorts(ArrayList<String> currentSorts) {
        return makeJsonFromStringArrayList(currentSorts);
    }

    public static String showProducts(ArrayList<Product> productArrayList) {
        return makeJsonFromProductArrayList(productArrayList);
    }

    public static String showProductsIds(ArrayList<String> id) {
        return makeJsonFromStringArrayList(id);
    }

    public static String compareProducts(ArrayList<String> first, ArrayList<String> second) {
        return makeJsonFromStringArrayList(first) + makeJsonFromStringArrayList(second);
    }

    public static String showAllSellersForOneProduct(ArrayList<Seller> sellerArrayList){
        return makeJsonFromSellerArrayList(sellerArrayList);
    }
    //..................................................................
    public static void showAccountInformation(){
        System.out.println();
    }

    public static void showAccounts(){

    }

    public static void showDiscountCodes(){

    }

    public static void showDiscountCode(){

    }

    public static void showRequest(){

    }

    public static void showRequests(){

    }

    public static void showCategories(){

    }

    public static void showOff(){

    }

    public static void showOffs(){

    }

  //  public static void showBalance(){

  //  }

    public static void showSalesHistory(){
        //loginaccount sellere
    }

    public static void showFirmInformation(){
        //loginaccount sellere
    }

    public static void showCustomerLog(){

    }

    public static void showOrders(){
        //sabeghe kharid

    }

    public static void showOrder(){

    }

    public static void showProductBuyers(){

    }

    public static void showProduct(){

    }

    public static void showTotalPrice(){
        //poole log bedoone discount

    }

    public static void showBalance(){
        //credite har account
    }


}
