package model.log;

import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {

    public BuyLog(String logId, Date date) {
        super(logId, date);
        allCustomersLog.add(this);
    }


    //detail
    double paidAmount;
    String buyerName;

    //list
    private static ArrayList<Product> allBoughtProduct = new ArrayList<Product>();
    ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();

    //setterAndGetter----------------------------------------------------

    public void setBuyLogDetail(String buyerName , double paidAmount){
        this.buyerName=buyerName;
        this.paidAmount=paidAmount;
        allCustomersLog.add(this);
    }

    //other--------------------------------------------------------------

    //finish
    public static boolean isBought(String productId) {
        for (Product product : allBoughtProduct) {
            if (product.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    //finish//doubt
    public void addProductToBuyLog(Product product){
        allBoughtProduct.add(product);
        product.setNumberOfProducts(product.getNumberOfProducts()-1);
        product.addTotalNumberOfBuyers();
    }


}
