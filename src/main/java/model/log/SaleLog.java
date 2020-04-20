package model.log;

import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;

public class SaleLog extends Log{


    public SaleLog(String logId, Date date) {
        super(logId, date);
        allSellersLog.add(this);
    }
    double recievedAmount;
    double reducedAmount;
    String customerName;
    ArrayList<Product> allSoldProduct = new ArrayList<Product>();
    ArrayList<SaleLog> allSellersLog = new ArrayList<SaleLog>();

    //finish
    public boolean isSold (String productId){
        for (Product product : allSoldProduct) {
            if (product.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }
}
