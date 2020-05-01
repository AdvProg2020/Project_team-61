package model.log;

import model.off.DiscountCode;
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
    int numberOfChosenPro;
    private static boolean ifItsFinal;
    boolean isBought;

    //list
    private static ArrayList<Product> listOfOneProduct = new ArrayList<Product>();
    private static ArrayList<ArrayList<Product>> allBoughtProduct = new ArrayList<>();
    ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();

    //setterAndGetter----------------------------------------------------

    public void setBuyLogDetail(String buyerName , double paidAmount){
        this.buyerName=buyerName;
        this.paidAmount=paidAmount;
        allCustomersLog.add(this);
    }
    public void setIsBought(boolean bought) {
        isBought = bought;
    }
    public boolean getIsBought(){
        return isBought;
    }

    //other--------------------------------------------------------------

    //finish
    public static boolean isBought(String productId) {
        return ifItsFinal;
    }


    public void addProductToBuyLog(String productId,int amount){
        Product product=Product.getProductById(productId);
        for (int i = 0; i < amount; i++) {
            assert product != null;
            if (product.getNumberOfProducts()!=0){
                listOfOneProduct.add(product);
                product.setNumberOfProducts(product.getNumberOfProducts()-1);
                numberOfChosenPro++;
            }
        }
        allBoughtProduct.add(listOfOneProduct);
        product.setTotalNumberOfBuyers(product.getTotalNumberOfBuyers()+1);
        ifItsFinal=true;
    }

//    public void amountOfProductUncounteble(String productId,String amount){
//        Product product=Product.getProductById(productId);
//    }

    public double holePriceWithOutDiscount(){
        double price=0;
        for (ArrayList<Product> productArrayList : allBoughtProduct) {
            for (Product product1 : productArrayList) {
                 price=+product1.getPrice();
            }
        }
        return price;
    }

    public double holePriceWithDiscount(){
        double price=0;
        for (ArrayList<Product> productArrayList : allBoughtProduct) {
            for (Product product1 : productArrayList) {
                if (product1.getHasDiscount()){

                }
            }
        }
        return price;
    }


    public double payThePrice(){
        double price=0;
        if (ifItsFinal){
            for (ArrayList<Product> productArrayList : allBoughtProduct) {
                for (Product product1 : productArrayList) {
                     price=+holePriceWithDiscount()+holePriceWithOutDiscount();
                }
            }
        }
        setIsBought(true);
        return price;
    }



}
