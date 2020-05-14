package model.log;

import com.google.gson.reflect.TypeToken;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class SaleLog extends Log{


    public SaleLog(String logId) {
        super(logId);
        allSellersLog.add(this);
        localDateTimeForSaleLog=LocalDateTime.now();
    }

    //detail
    double receivedAmount;
    double reducedAmount;
    String customerName;
    Product product;
    LocalDateTime localDateTimeForSaleLog;


    //list
    private static ArrayList<Product> sellersProducts=new ArrayList<>();
    private ArrayList<Product> allSoldProduct = new ArrayList<Product>();
    private static ArrayList<SaleLog> allSellersLog = new ArrayList<SaleLog>();


    //setterAndGetter--------------------------------------------
    public void setSaleLogDetail(double receivedAmount,double reducedAmount, String customerName){
        this.receivedAmount =receivedAmount;
        this.reducedAmount=reducedAmount;
        this.customerName=customerName;
        allSellersLog.add(this);
    }

    public LocalDateTime getLocalDateTimeForSaleLog() {
        return localDateTimeForSaleLog;
    }

    public static ArrayList<SaleLog> getAllSellersLog() {
        return allSellersLog;
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
    public void addProductToSaleLog(String productId){
        Product product=Product.getProductById(productId);
        allSoldProduct.add(product);
    }

    private static void sellerDeleteProduct(String productId){
        Product.deleteProduct(productId);
        Product product1=Product.getProductById(productId);
        Iterator iterator = sellersProducts.iterator();
        while (iterator.hasNext()) {
            Product product2 = (Product) iterator.next();
            if (product2.equals(product1)){
                iterator.remove();
            }
        }
    }

    public int compareTo(SaleLog saleLog) {
        return getLocalDateTimeForSaleLog().compareTo(saleLog.getLocalDateTimeForSaleLog());
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<SaleLog>>(){}.getType();
        String json= FileHandling.getGson().toJson(getAllSellersLog(),collectionType);
        FileHandling.turnToArray(json+" "+"saleLog.json");
    }
}