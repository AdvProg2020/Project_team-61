package model.off;
import model.accounts.Account;
import model.accounts.Customer;
import model.off.SaleStatus;
import model.productRelated.Product;
import model.accounts.Seller;
import java.util.ArrayList;
import java.util.*;
public class Sale  implements Comparable{
    private String offId;
    private SaleStatus saleStatus;
    private Date startOfSalePeriod;
    private Date endOfSalePeriod;
    private int saleAmount;
    private Seller seller;
    private ArrayList <Product>allProducts;
    private static ArrayList <Sale> allSales;

    public Sale(String offId) {
        this.offId = offId;
        allProducts=new ArrayList<>();
        allSales.add(this);

    }
    public void setSaleDetails(SaleStatus saleStatus, Date startOfSalePeriod, Date endOfSalePeriod, int saleAmount, Seller seller){
        this.saleStatus = saleStatus;
        this.startOfSalePeriod = startOfSalePeriod;
        this.endOfSalePeriod = endOfSalePeriod;
        this.saleAmount = saleAmount;
        this.seller = seller;
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

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public  Sale getSaleWithId(String id){
        for (Sale sale : allSales) {
            if (sale.getOffId().equals(id)) {
                return sale;
            }
        }
        return null;
    }
    public boolean isThereSaleWithId(String id){
       return allSales.contains(getSaleWithId(id));
    }


     public int getSaleListSize(){
            return allSales.size();
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
             for (Product products : sale.getAllProducts()) {
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

    public boolean isTheProductInAnotherSale(Product product) {
        for (Sale sale : allSales) {
            if (sale.getAllProducts().contains(product)) {
                return true;}
        }
        return false;

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public ArrayList<Sale> listSales(){
        return allSales;
    }
    public ArrayList<Sale> viewSalesList(){
        return allSales;
    }
    @Override
    public String toString() {
        return "Sale{" +
                "offId='" + offId + '\'' +
                ", saleStatus=" + saleStatus +
                ", startOfSalePeriod=" + startOfSalePeriod +
                ", endOfSalePeriod=" + endOfSalePeriod +
                ", saleAmount=" + saleAmount +
                ", seller=" + seller +
                ", allProducts=" + allProducts +
                '}';
    }
}
