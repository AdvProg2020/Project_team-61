package model.log;

import model.accounts.Seller;
import model.productRelated.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SaleLog extends Log {
    private double receivedAmount = 0;
    private double price = 0;
    private double reducedAmount = 0;
    private String seller;

    public SaleLog(String logId) throws IOException {
        super(logId);
        allSellersLog.add(this);
    }

    private HashMap<Product, Integer> chosenProduct = new HashMap<>();
    //  private ArrayList<Product> allSoldProduct = new ArrayList<Product>();
    private static ArrayList<SaleLog> allSellersLog = new ArrayList<SaleLog>();


    //setterAndGetter--------------------------------------------

    public void addPrice(double p) {
        price += p;
    }

    public static void setAllSellersLog(ArrayList<SaleLog> allSellersLog) {
        SaleLog.allSellersLog = allSellersLog;
    }

    public void setReducedAmount(double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public void setReceivedAmount() throws IOException {
        receivedAmount = price - reducedAmount;
        Seller.writeInJ();


    }

    public static ArrayList<SaleLog> getAllSellersLog() {
        return allSellersLog;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public double getReducedAmount() {
        return reducedAmount;
    }


    public void addProductToSaleLog(String productId, int number) {
        Product product = Product.getProductById(productId);
        chosenProduct.put(product, number);
    }

    public static boolean idThereSeller(Seller seller) {
        for (SaleLog saleLog : allSellersLog) {
            saleLog.seller.equals(seller.getUsername());
            return true;
        }
        return false;
    }

    public static SaleLog getLogWithSeller(String seller) {
        for (SaleLog allLog : allSellersLog) {
            if (allLog.seller.equals(seller)) {
                return allLog;
            }
        }
        return null;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "SaleLog{" +
                "receivedAmount=" + receivedAmount +
                ", reducedAmount=" + reducedAmount +
                //  ", customerName='" + customerName + '\'' +
                //  ", product=" + product +
                //", localDateTimeForSaleLog=" + localDateTimeForSaleLog +
                // ", allSoldProduct=" + allSoldProduct +
                '}';
    }
}