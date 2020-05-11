package view;

import com.google.gson.Gson;
import model.accounts.Seller;
import model.productRelated.Product;

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

    public static void showInput(int input) {
    }

    public static void showObjectInput(Object object, int size, int number) {

    }
}
