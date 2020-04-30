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
        if (ifItsFinal){
            return true;
        }
        else return false;
    }


    public void amountOfProductCountabale(String productId,int amount){
        Product product=Product.getProductById(productId);
        for (int i = 0; i < amount; i++) {
            if (product.getNumberOfProducts()!=0){
                listOfOneProduct.add(product);
                product.setNumberOfProducts(product.getNumberOfProducts()-1);
                numberOfChosenPro++;
            }
        }

    }

//    public void amountOfProductUncounteble(String productId,String amount){
//        Product product=Product.getProductById(productId);
//    }

    //finish//doubt
    public void addProductToBuyLog(Product product){
        allBoughtProduct.add(listOfOneProduct);
        product.setTotalNumberOfBuyers(product.getTotalNumberOfBuyers()+1);
        ifItsFinal=true;
    }


    public double finalPrice(String productId){
        double price=0;
        Product product=Product.getProductById(productId);
        assert product != null;
        if (product.getHasDiscount()){
            DiscountCode discountCode=product.getDiscountCode();
        }
        price=+product.getPrice();
        return price;
    }


    public void payThePrice(){
        if (ifItsFinal){
            for (ArrayList<Product> productArrayList : allBoughtProduct) {
                for (Product product1 : productArrayList) {
                    double price=+finalPrice(product1.getId());
                }
            }
        }
        setIsBought(true);
    }



}
