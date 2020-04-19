package main.java.model.off;
import java.util.ArrayList;
import java.util.*;
public class Sale {
    private String offId;
    private model.off.SaleStatus saleStatus;
    private Date startOfSalePeriod;
    private Date endOfSalePeriod;
    private int saleAmount;
    private model.accounts.Seller seller;
    private ArrayList <model.productRelated.Product>allProducts;
    private static ArrayList <Sale> allSales;

    public Sale(String offId, model.off.SaleStatus saleStatus, Date startOfSalePeriod, Date endOfSalePeriod, int saleAmount, model.accounts.Seller seller) {
        this.offId = offId;
        this.saleStatus = saleStatus;
        this.startOfSalePeriod = startOfSalePeriod;
        this.endOfSalePeriod = endOfSalePeriod;
        this.saleAmount = saleAmount;
        this.seller = seller;
        allProducts=new ArrayList<>();
        allSales.add(this);

    }

    public String getOffId() {
        return offId;
    }

    public model.off.SaleStatus getSaleStatus() {
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

    public model.accounts.Seller getSeller() {
        return seller;
    }

    public ArrayList<model.productRelated.Product> getAllProducts() {
        return allProducts;
    }

    public  Sale getSaleWithId(String id){
        for (main.java.model.off.Sale sale : allSales) {
            if (sale.getOffId().equals(id)) {
                return sale;
            }
        }
        return null;
    }
    public boolean isThereSaleWithId(String id){
       return allSales.contains(getSaleWithId(id));
    }


     public int getAllSalesSize(){
            return allSales.size();
    }
    public Sale getSaleWithSeller(model.accounts.Seller seller){
        for (Sale sale:allSales){
            if ((sale.getSeller())==(seller)){
                return sale;
            }

        }
        return null;
    }
     public Sale getSaleWithProduct(model.productRelated.Product product){
         for (Sale sale : allSales) {
             for (model.productRelated.Product products : sale.getAllProducts()) {
                 if (products.equals(product)){
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

    public boolean isTheProductInAnotherSale(model.productRelated.Product product) {
        for (Sale sale : allSales) {
            if (sale.getAllProducts().contains(product)) {
                return true;}
        }
        return false;

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
