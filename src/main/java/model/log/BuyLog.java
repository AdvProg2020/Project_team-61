package model.log;


import com.google.gson.reflect.TypeToken;
import controller.menus.CustomerMenu;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.off.DiscountCode;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class BuyLog extends Log {


    public BuyLog(String logId) throws IOException {
        super(logId);
        id = logId;
        localDateTimeForLog = LocalDateTime.now();
        allCustomersLog.add(this);
   //     writeInJ();
    }


    //detail
    private String id;
    public static LocalDateTime localDateTimeForLog;
    public double paidAmount;

    int numberOfChosenPro;
    private static boolean firstProduct = true;
    double amountAfterDis;
    public double holePrice;
    public double price;


    //list
    private HashMap<Product, Integer> allBoughtProduct = new HashMap<>();
    private  HashMap<Product, Integer> chosenProduct = new HashMap<>();
    public static ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();


    //setterAndGetter----------------------------------------------------

    public String getId() {
        return id;
    }

    public static LocalDateTime getLocalDateTimeForLog() {
        return localDateTimeForLog;
    }

    public static ArrayList<BuyLog> getAllCustomersLog() {
        return allCustomersLog;
    }

    public static boolean getFirstProduct() {
        return firstProduct;
    }

    public  HashMap<Product, Integer> getChosenProduct() {
        return chosenProduct;
    }

    public double calculateHolePrice(){
        if (CustomerMenu.isHasDiscount()) {
            DiscountCode discountCode= DiscountCode.getDiscountWithId(CustomerMenu.getDiscountID());
            holePrice = discountCode.calculate(totalPrice());
            discountCode.setTotalTimesOfUse(discountCode.getTotalTimesOfUse() - 1);
        } else {
            holePrice = totalPrice();
        }
        return holePrice;
    }

    public void setAllBoughtProduct(HashMap<Product, Integer> allBoughtProduct) {
        this.allBoughtProduct = allBoughtProduct;
    }

    public HashMap<Product, Integer> getAllBoughtProduct() {
        return allBoughtProduct;
    }

    //other--------------------------------------------------------------

    //done
    public static BuyLog getBuyLogWithName(String buyLogId) {
        for (BuyLog log : allCustomersLog) {
            if (log.getId().equals(buyLogId)) {
                return log;
            }
        }
        return null;
    }

    //done
    public void addProductToBuyLog(String productId, int amount) {
        Product product = Product.getProductById(productId);
        if (product.getNumberOfProducts() > amount) {
            numberOfChosenPro = +numberOfChosenPro + amount;
            product.setNumberOfProducts(product.getNumberOfProducts() - amount);
            chosenProduct.put(product, amount);
        }

        else if (product.getNumberOfProducts() == amount) {
            numberOfChosenPro = +numberOfChosenPro + amount;
            product.setNumberOfProducts(0);
            product.setIsBought(true);
            chosenProduct.put(product, amount);
        }
        else if (product.getNumberOfProducts() < amount) {
            numberOfChosenPro = +numberOfChosenPro + product.getNumberOfProducts();
            product.setNumberOfProducts(0);
            chosenProduct.put(product, product.getNumberOfProducts());
            product.setIsBought(true);
        }
    }

    public ArrayList<Seller> getSellers(){
        ArrayList<Seller> sellers=null;
        for (Product p : allBoughtProduct.keySet()){
            if (!sellers.contains(p.getSeller())){
                sellers.add((Seller) p.getSeller());
            }
        }
        return sellers;
    }

    //done
    public double totalPrice() {
        for (Product p : chosenProduct.keySet()) {
            price = +chosenProduct.get(p) * p.getPrice();
        }
        return price;
    }


    public boolean checkIfProductIsBought(String productId) {
        for (Product p : allBoughtProduct.keySet()) {
            if (p.getId().equals(productId)) {
                return true;
            }
        }
        return false;
    }


    public void increaseNumberOfProduct(String productId, int amount) {
        Product product = Product.getProductById(productId);
        int numberOfPro = chosenProduct.get(product);
        if (product.getNumberOfProducts() > amount) {
            chosenProduct.put(product, chosenProduct.get(product) + amount);
            product.setNumberOfProducts(product.getNumberOfProducts() - amount);
        } else if (product.getNumberOfProducts() == amount) {
            chosenProduct.put(product, chosenProduct.get(product)+amount);
            product.setNumberOfProducts(0);
            product.setIsBought(true);
        } else if (product.getNumberOfProducts() < amount) {
            chosenProduct.put(product, chosenProduct.get(product)+product.getNumberOfProducts());
            product.setNumberOfProducts(0);
            product.setIsBought(true);
        }
    }

    //done
    public void reduceNumberOfProduct(String productId, int amount) {
        Product product = Product.getProductById(productId);
        int numberOfPro = chosenProduct.get(product);
        if (amount < numberOfPro) {
            chosenProduct.put(product, chosenProduct.get(product) - amount);
            product.setNumberOfProducts(product.getNumberOfProducts() + amount);
        } else if (amount == numberOfPro) {
            chosenProduct.remove(product);
            product.setNumberOfProducts(product.getNumberOfProducts() + amount);
        } else if (amount > chosenProduct.get(product)) {
            product.setNumberOfProducts(chosenProduct.get(product));
            chosenProduct.remove(product);
        }

    }

    //done
//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<BuyLog>>() {
//        }.getType();
//        String json = FileHandling.getGson().toJson(BuyLog.getAllCustomersLog(), collectionType);
//        FileHandling.turnToArray(json + " " + "buyLog.json");
//    }


    public static Comparator<BuyLog> productComparatorForScore = new Comparator<BuyLog>() {

        public int compare(BuyLog o1, BuyLog o2) {
            return o1.getLocalDateTimeForLog().compareTo(o2.getLocalDateTimeForLog());
        }
    };



    @Override
    public String toString() {
        return "BuyLog{" +
                "id='" + id + '\'' +
                ", paidAmount=" + paidAmount +
                ", numberOfChosenPro=" + numberOfChosenPro +
                ", amountAfterDis=" + amountAfterDis +
                ", holePrice=" + holePrice +
                ", price=" + price +
                ", allBoughtProduct=" + allBoughtProduct +
                ", chosenProduct=" + chosenProduct +
                '}';
    }
}