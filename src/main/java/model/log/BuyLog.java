package model.log;

import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {

    double paidAmount;
    String buyerName;
    public BuyLog(String logId, Date date) {
        super(logId, date);
        allCustomersLog.add(this);
    }
    private static ArrayList<Product> allBoughtProduct = new ArrayList<Product>();
    ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();

    //finish
    public static boolean isBought(String productId) {
        for (Product product : allBoughtProduct) {
            if (product.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }
}
