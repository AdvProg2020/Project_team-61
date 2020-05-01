package model.log;

import model.productRelated.Category;
import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    HashMap<Category,ArrayList<Product>> allSellersProduct=new HashMap<>();
    ArrayList<Product> allProductOfOne=null;

    //setterAndGetter--------------------------------------------
    public void setSaleLogDetail(double receivedAmount,double reducedAmount, String customerName){
        this.receivedAmount =receivedAmount;
        this.reducedAmount=reducedAmount;
        this.customerName=customerName;
        allSellersLog.add(this);
    }

    public void setAllProductOfOne(ArrayList<Product> allProductOfOne) {
        for (Category category : allSellersProduct.keySet()) {
            allProductOfOne.add(allSellersProduct.get(category));
        }
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

    public void addProductToSaleLog(Product product,int amount){
        allSoldProduct.add(product);

    }
    //finish//doubt
    public void addProductToSaleLog(Product product,int amount){
        allSoldProduct.add(product);

    }


}
