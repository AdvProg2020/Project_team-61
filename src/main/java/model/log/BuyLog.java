package model.log;


import com.google.gson.reflect.TypeToken;
import controller.menus.CustomerMenu;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BuyLog extends Log {


    public BuyLog(String logId) throws IOException {
        super(logId);
        id = logId;
        localDateTimeForLog = LocalDateTime.now();
        allCustomersLog.add(this);
    }


    //detail
    private String id;
    public static LocalDateTime localDateTimeForLog;
    public double paidAmount;

    int numberOfChosenPro;
    private static boolean ItsFinal;
    boolean isBought;
    private static boolean firstProduct = true;
    double amountAfterDis;
    public Account buyerLog;

    //list
    private HashMap<Product, Integer> allBoughtProduct = new HashMap<>();
    private static HashMap<Product, Integer> chosenProduct = new HashMap<>();
    public static ArrayList<BuyLog> allCustomersLog = new ArrayList<BuyLog>();


    //setterAndGetter----------------------------------------------------

    public void setBuyLogDetail() throws IOException {
        allCustomersLog.add(this);
        writeInJ();
    }

    public void setBuyerLog(Account account) {
        this.buyerLog = buyerLog;
    }

    public String getId() {
        return id;
    }

    public void setIsBought(boolean bought) {
        isBought = bought;
    }

    public boolean getIsBought() {
        return isBought;
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

    public boolean isBought(String productId) {
        return ifItsFinal;
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
//        if (!product.getIsBought()) {
        if (product.getNumberOfProducts() > amount) {
            product.setNumberOfProducts(product.getNumberOfProducts() - amount);
            chosenProduct.put(product, amount);
        } else if (product.getNumberOfProducts() == amount) {
            product.setNumberOfProducts(0);
            product.setIsBought(true);
            chosenProduct.put(product, amount);
        } else if (product.getNumberOfProducts() < amount) {
            product.setNumberOfProducts(0);
            chosenProduct.put(product, product.getNumberOfProducts());
            product.setIsBought(true);
        }
        numberOfChosenPro = +numberOfChosenPro + amount;
//        } else {
//            Product.deleteProduct(productId);
//        }
    }

    //done
    public double totalPrice() {
        double price = 0;
        for (Product p : chosenProduct.keySet()) {
            price = +chosenProduct.get(p) * p.getPrice();
        }
        return price;
    }

    public void whenFinal() throws IOException {
        double price = 0;
        if (CustomerMenu.isHasDiscount()) {
            price = buyerLog.getDiscount().calculate(totalPrice());
            buyerLog.getDiscount().setTotalTimesOfUse(buyerLog.getDiscount() - 1);
        } else {
            price = totalPrice();
        }
        for (Product p : chosenProduct.keySet()) {
            //faghat price bedoon discount be seller eafe
            p.getSeller().setCredit(p.getSeller().getCredit() + p.getPrice());
            //price ba discount be az customer kam
            paidAmount = buyerLog.getCredit() - price;
            buyerLog.setCredit(paidAmount);
        }
        allBoughtProduct = chosenProduct;
        itsDone = true;
        changesForSaleLogAfterFinish();
    }

    public void changesForSaleLogAfterFinish() throws IOException {
        //product ha be sold ezafe
        //gheimat daryafti
        for (Product p : allBoughtProduct.keySet()) {
            SaleLog saleLog = new SaleLog(id);
            saleLog.setSaleLogDetail(p.getPrice(), buyerLog.getName());
            saleLog.addProductToSaleLog(p.getId());
            if (p.getInSale()) {
                saleLog.setReducedAmount(p.getSale().getSaleAmount());
            } else saleLog.setReducedAmount(0);
        }

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
            chosenProduct.put(product, amount);
            product.setNumberOfProducts(0);
            product.setIsBought(true);
        } else if (product.getNumberOfProducts() < amount) {
            chosenProduct.put(product, product.getNumberOfProducts());
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
    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        String json = FileHandling.getGson().toJson(BuyLog.getAllCustomersLog(), collectionType);
        FileHandling.turnToArray(json + " " + "buyLog.json");
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "id='" + id + '\'' +
                ", amountAfterDis=" + amountAfterDis +
                ", numberOfChosenPro=" + numberOfChosenPro +
                ", isBought=" + isBought +
                '}';
    }
}