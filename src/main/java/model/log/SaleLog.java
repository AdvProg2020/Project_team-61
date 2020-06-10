package model.log;

import model.productRelated.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class SaleLog extends Log {




    public SaleLog(String logId) throws IOException {
        super(logId);
        id=logId;
        allSellersLog.add(this);
        localDateTimeForSaleLog=LocalDateTime.now();
     //   writeInJ();
    }

    //detail
    private String id;
    private double receivedAmount;
    private double reducedAmount;
    private String customerName;
    private Product product;
    LocalDateTime localDateTimeForSaleLog;


    //list

    private ArrayList<Product> allSoldProduct = new ArrayList<Product>();
    private static ArrayList<SaleLog> allSellersLog = new ArrayList<SaleLog>();


    //setterAndGetter--------------------------------------------


    public void setReducedAmount(double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public ArrayList<Product> getAllSoldProduct() {
        return allSoldProduct;
    }

    public void setSaleLogDetail(double receivedAmount, String customerName){
        this.receivedAmount =receivedAmount;
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




    //finish//doubt
    public void addProductToSaleLog(String productId){
        Product product= Product.getProductById(productId);
        allSoldProduct.add(product);
    }


    public static Comparator<SaleLog> productComparatorForScore = new Comparator<SaleLog>() {

        public int compare(SaleLog o1, SaleLog o2) {
            return o1.getLocalDateTimeForSaleLog().compareTo(o2.getLocalDateTimeForSaleLog());
        }
    };

//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<SaleLog>>(){}.getType();
//        String json= FileHandling.getGson().toJson(getAllSellersLog(),collectionType);
//        FileHandling.turnToArray(json+" "+"saleLog.json");
//    }

    @Override
    public String toString() {
        return "SaleLog{" +
                "receivedAmount=" + receivedAmount +
                ", reducedAmount=" + reducedAmount +
                ", customerName='" + customerName + '\'' +
                ", product=" + product +
                ", localDateTimeForSaleLog=" + localDateTimeForSaleLog +
                ", allSoldProduct=" + allSoldProduct +
                '}';
    }
}