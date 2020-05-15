package model.log;


import  model.productRelated.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class BuyLog extends Log {



    public BuyLog(String logId) {
        super(logId);
        localDateTimeForLog=LocalDateTime.now();
        allCustomersLog.add(this);
    }


    //detail
    double paidAmount;
    String buyerName;
    int numberOfChosenPro;
    private static boolean ifItsFinal;
    boolean isBought;
    private static boolean firstProduct=true;
    public static LocalDateTime localDateTimeForLog;

    //list
    private static ArrayList<Product> listOfOneProduct = new ArrayList<Product>();
    private static ArrayList<ArrayList<Product>> allBoughtProduct = new ArrayList<>();
    public static ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();

    //setterAndGetter----------------------------------------------------

    public void setBuyLogDetail(String buyerName, double paidAmount) {
        this.buyerName = buyerName;
        this.paidAmount = paidAmount;
        allCustomersLog.add(this);
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


    //finish
    public static boolean isBought(String productId) {
        return ifItsFinal;
    }


    public void addProductToBuyLog(String productId, int amount) {
        Product product = Product.getProductById(productId);
        for (int i = 0; i < amount; i++) {
            assert product != null;
            if (product.getNumberOfProducts() != 0) {
                listOfOneProduct.add(product);
                numberOfChosenPro++;
            }
        }
        allBoughtProduct.add(listOfOneProduct);
        product.setTotalNumberOfBuyers(product.getTotalNumberOfBuyers() + 1);
        firstProduct=false;
        ifItsFinal = true;
    }

    public double holePriceWithOutDiscount() {
        double price = 0;
        for (ArrayList<Product> productArrayList : allBoughtProduct) {
            for (Product product1 : productArrayList) {
                price = +product1.getPrice();
            }
        }
        return price;
    }

    public double holePriceWithDiscount() {
        double price = 0;
        for (ArrayList<Product> productArrayList : allBoughtProduct) {
            for (Product product1 : productArrayList) {
//                if () {
//
//                }
            }
        }
        return price;
    }


    public void reduceNumberOfProduct() {
        for (ArrayList<Product> productArrayList : allBoughtProduct) {
            for (Product product1 : listOfOneProduct) {
                product1.setNumberOfProducts(product1.getNumberOfProducts() - 1);
            }
        }
    }

    public static void deleteProductFromBuyLog(String productId) {
        Product product = Product.getProductById(productId);
        ArrayList<Product> help = null;
        Iterator iterator = listOfOneProduct.iterator();
        while (iterator.hasNext()) {
            Product product1 = (Product) iterator.next();
            if (product1.equals(product)) {
                help.add(product1);
                iterator.remove();
            }
        }
        allBoughtProduct.remove(help);
    }


    public  int compareTo(BuyLog buyLog) {
        return getLocalDateTimeForLog().compareTo(buyLog.getLocalDateTimeForLog());
    }

}
