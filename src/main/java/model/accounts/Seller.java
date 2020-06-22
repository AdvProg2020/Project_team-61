package model.accounts;

import com.google.gson.reflect.TypeToken;
import model.firms.Firm;
import model.log.SaleLog;
import model.off.Sale;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Seller extends Account {
    //private Firm firm;
    private  ArrayList<SaleLog> saleLogsHistory = new ArrayList<>();
    private static ArrayList<Seller> allSellers = new ArrayList<>();
    private  ArrayList<Product> allProduct = new ArrayList<>();
    private  ArrayList<Sale> allSales = new ArrayList<>();
    public static Type SellerType = new TypeToken<ArrayList<Seller>>() {
    }.getType();

    public Seller(String username) throws IOException {
        super(username);
        role = "seller";
        allSellers.add(this);
        writeInJ();
    }

    public ArrayList<Sale> getAllSales() {
        return allSales;
    }

    public void setAllSales(ArrayList<Sale> allSales) {
        this.allSales = allSales;
    }

    public void setSaleLogsHistory(ArrayList<SaleLog> saleLogsHistory) {
        this.saleLogsHistory = saleLogsHistory;
    }

    public void addProduct(Product product){
        allProduct.add(product);
    }

    public ArrayList<Product> getAllProduct() {
        return allProduct;
    }

    public void addLog(SaleLog saleLog) throws IOException {
        saleLogsHistory.add(saleLog);
        writeInJ();

    }



    public static void setAllSellers(ArrayList<Seller> allSellers) {
        Seller.allSellers = allSellers;
    }

    public ArrayList<SaleLog> getSaleLogsHistory() {
        return saleLogsHistory;
    }

    public Firm getFirm() {
        return firm;
    }

    public static ArrayList<Seller> getAllSellers() {
        return allSellers;
    }

    public static void writeInJ() throws IOException {

        String json = FileHandling.getGson().toJson(Seller.allSellers, SellerType);
        FileHandling.writeInFile(json, "seller.json");

    }



    @Override
    public String toString() {
        return "Seller{" +
                "firm=" + firm +
                ", saleLogsHistory=" + saleLogsHistory +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", credit=" + credit +
                ", role='" + role + '\'' +
                ", currentPhoneNo=" + currentPhoneNo +
                ", address='" + address + '\'' +
                ", birthdayDate=" + birthdayDate +
             //   ", allDiscountCodes=" + allDiscountCodes +
                '}';
    }
}