package model.log;

import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;

public class SaleLog extends Log{


    public SaleLog(String logId, Date date) {
        super(logId, date);
        allSellersLog.add(this);
    }

    //detail
    double receivedAmount;
    double reducedAmount;
    String customerName;


    //list
    ArrayList<Product> allSoldProduct = new ArrayList<Product>();
    ArrayList<SaleLog> allSellersLog = new ArrayList<SaleLog>();


    //setterAndGetter--------------------------------------------
    public void setSaleLogDetail(double receivedAmount,double reducedAmount, String customerName){
        this.receivedAmount =receivedAmount;
        this.reducedAmount=reducedAmount;
        this.customerName=customerName;
        allSellersLog.add(this);
    }

    //other-------------------------------------------------------

    //finish
    public boolean isSold (String productId){
        for (Product product : allSoldProduct) {
            if (product.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    //finish//doubt
    public void addProductToSaleLog(Product product){
        allSoldProduct.add(product);
    }


}