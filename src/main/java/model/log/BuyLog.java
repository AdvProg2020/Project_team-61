package model.log;


import com.google.gson.reflect.TypeToken;
import controller.menus.CustomerMenu;
import model.accounts.Account;
import model.accounts.Customer;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BuyLog extends Log {



    public BuyLog(String logId) throws IOException {
        super(logId);
        id=logId;
        localDateTimeForLog=LocalDateTime.now();
        allCustomersLog.add(this);
    }




    //detail
    private String id;
    public static LocalDateTime localDateTimeForLog;
    public double paidAmount;
    private double discountOnPrice;

    int numberOfChosenPro;
    private static boolean ifItsFinal;
    boolean isBought;
    private static boolean firstProduct=true;
    double amountAfterDis;
    public Account buyerLog;

    //list
    private  HashMap<Product,Integer> allBoughtProduct = new HashMap<>();
    private  static HashMap<Product,Integer> chosenProduct = new HashMap<>();
    public static ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();
    public static HashMap<ArrayList<Product>, Double> productWithPriceWithSale=new HashMap<>();
    public static HashMap<ArrayList<Product>, Double> productWithPriceWithoutSale=new HashMap<>();

    //setterAndGetter----------------------------------------------------

    public void setBuyLogDetail() throws IOException {
        allCustomersLog.add(this);
        writeInJ();
    }

    public void setBuyerLog(Account account){
        this.buyerLog=buyerLog;
    }
    public String getId() {
        return id;
    }

    public void setIsBought(boolean bought) {
        isBought = bought;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public static LocalDateTime getLocalDateTimeForLog() {
        return localDateTimeForLog;
    }

    public static ArrayList<BuyLog> getAllCustomersLog() {
        return allCustomersLog;
    }

    public static boolean getFirstProduct(){
        return firstProduct;
    }
    //other--------------------------------------------------------------

    //done
    public static BuyLog getBuyLogWithName(String buyLogId){
        for (BuyLog log : allCustomersLog) {
            if (log.getId().equals(buyLogId)){
                return log;
            }
        }
        return null;
    }


    public  boolean isBought(String productId) {
        return ifItsFinal;
    }

    //done
    public void addProductToBuyLog(String productId, int amount) {
       Product product=Product.getProductById(productId);
       if (product.getNumberOfProducts()!=0){
           product.setNumberOfProducts(product.getNumberOfProducts()-amount);
           chosenProduct.put(product,amount);
       }
       else {
           Product.deleteProduct(productId);
       }
    }

    //done
    public double holePriceWithOutDiscount() {
        double price=0;
        if (CustomerMenu.isHasDiscount()){
            for (Product p : chosenProduct.keySet()){
                price=+chosenProduct.get(p)* buyerLog.getDiscount().calculate(chosenProduct);
            }
        }
        else {
            for (Product p : chosenProduct.keySet()){
                price=+chosenProduct.get(p)*p.getPrice();
            }
        }
        return price;
    }


    //done
    public void reduceNumberOfProduct(String productId , int amount) {
        Product product = Product.getProductById(productId);
        int numberOfPro = chosenProduct.get(product);
        if (amount<numberOfPro){
            chosenProduct.put(product, chosenProduct.get(product) - amount);
            product.setNumberOfProducts(product.getNumberOfProducts()+amount);
        }
        else if (amount == numberOfPro){
            chosenProduct.remove(product);
            product.setNumberOfProducts(product.getNumberOfProducts()+amount);
        }
        else if (amount>chosenProduct.get(product)){
            product.setNumberOfProducts(chosenProduct.get(product));
            chosenProduct.remove(product);
        }

    }

    //done
    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<BuyLog>>(){}.getType();
        String json= FileHandling.getGson().toJson(BuyLog.getAllCustomersLog(),collectionType);
        FileHandling.turnToArray(json+" "+"buyLog.json");
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "id='" + id + '\'' +
                ", amountAfterDis=" + amountAfterDis +
                ", numberOfChosenPro=" + numberOfChosenPro +
                ", isBought=" + isBought +
                '}';
    }
}
