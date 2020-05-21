package model.off;

import model.accounts.Account;
import model.accounts.Seller;
import model.productRelated.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Sale{
    private String offId;
    private SaleStatus saleStatus;
    private Date startOfSalePeriod;
    private Date endOfSalePeriod;
    private int saleAmount;
    private static Account seller;
    private ArrayList <Product> allSaleProducts = new ArrayList<>();
    private static ArrayList <Sale> allSales = new ArrayList<>();

    public Sale(String offId) {
        this.offId = offId;
        allSales.add(this);

    }
    public void setSaleDetails(SaleStatus saleStatus, Date startOfSalePeriod, Date endOfSalePeriod, int saleAmount, Account seller) throws IOException {
        this.saleStatus = saleStatus;
        this.startOfSalePeriod = startOfSalePeriod;
        this.endOfSalePeriod = endOfSalePeriod;
        this.saleAmount = saleAmount;
        this.seller = seller;
//        writeInJ();
    }

    public static ArrayList<Sale> getAllSales() {
        return allSales;
    }

    public String getOffId() {
        return offId;
    }
    public SaleStatus getSaleStatus() {
        return saleStatus;
    }
    public Date getStartOfSalePeriod() {
        return startOfSalePeriod;
    }
    public Date getEndOfSalePeriod() {
        return endOfSalePeriod;
    }
    public int getSaleAmount() {
        return saleAmount;
    }
    public static Account getSeller() {
        return seller;
    }
    public ArrayList<Product> getAllSaleProducts() {
        return allSaleProducts;
    }
    public ArrayList<Sale> listSales(){
        return allSales;
    }


    public static Sale getSaleWithId(String id){
        for (Sale sale : allSales) {
            if (sale.getOffId().equals(id)) {
                return sale;
            }
        }
        return null;
    }
    public static boolean isThereSaleWithId(String id){
        return allSales.contains(getSaleWithId(id));
    }

    public Sale getSaleWithSeller(Seller seller){
        for (Sale sale:allSales){
            if ((sale.getSeller())==(seller)){
                return sale;
            }

        }
        return null;
    }

    public Sale getSaleWithProduct(Product product){
        for (Sale sale : allSales) {
            for (Product products : sale.getAllSaleProducts()) {
                if (product.equals(products)){
                    return sale;
                }
            }
        }
        return null;
    }

    public void setStartOfSalePeriod(Date startOfSalePeriod) {
        this.startOfSalePeriod = startOfSalePeriod;
    }

    public void setEndOfSalePeriod(Date endOfSalePeriod) {
        this.endOfSalePeriod = endOfSalePeriod;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }

    public void setAllSaleProducts(ArrayList<Product> allSaleProducts) {
        this.allSaleProducts = allSaleProducts;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public static void deleteSale(String id){
        allSales.remove(id);
    }

    public boolean isTheProductInAnotherSale(Product product) {
        for (Sale sale : allSales) {
            if (sale.getAllSaleProducts().contains(product)) {
                return true;}
        }
        return false;

    }

//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Sale>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Sale.allSales,collectionType);
//        FileHandling.turnToArray(json+" "+"sale.json");
//    }

    @Override
    public String toString() {
        return "Sale{" +
                "offId='" + offId + '\'' +
                ", saleStatus=" + saleStatus +
                ", startOfSalePeriod=" + startOfSalePeriod +
                ", endOfSalePeriod=" + endOfSalePeriod +
                ", saleAmount=" + saleAmount +
                ", seller=" + seller +
                ", allProducts=" + allSaleProducts +
                '}';
    }

}