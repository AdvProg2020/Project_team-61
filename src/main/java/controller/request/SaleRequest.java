package controller.request;

import model.accounts.Account;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;

public class SaleRequest extends Request {
    private String offId;
    private Date startOfSalePeriod;
    private Date endOfSalePeriod;
    private int saleAmount;
    private Account seller;
    private ArrayList<Product> allSaleProducts;
    private SaleStatus saleStatus;
    private Sale sale;
    private ArrayList<SaleRequest> allSaleRequests;

    public SaleRequest(String requestID) {
        super(requestID);
        allSaleRequests.add(this);

    }

    public void acceptSaleRequest() {
        sale= Sale.getSaleWithId(offId);
        sale.setSaleDetails(SaleStatus.CONFIRMED, startOfSalePeriod, endOfSalePeriod, saleAmount, seller);
        sale.setAllSaleProducts(allSaleProducts);
    }


    public void removeProduct(Product product) {
        allSaleProducts.remove(product);
    }

    public void setSeller(Account seller) {
        this.seller = seller;
    }

    public void addProductToSale(Product product) {
        allSaleProducts.add(product);
    }

    public void setOffId(String offId) {
        this.offId = offId;
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


}
