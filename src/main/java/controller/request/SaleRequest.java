package controller.request;

import model.accounts.Account;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SaleRequest extends Request {
    private String offId = null;
    private Date startOfSalePeriod = null;
    private Date endOfSalePeriod = null;
    private int saleAmount = 0;
    private String product= null;
    private Account seller = null;
    private ArrayList<Product> allSaleProducts = new ArrayList<>();
    private SaleStatus saleStatus;
    private Sale sale;
    private ArrayList<SaleRequest> allSaleRequests = new ArrayList<>();


    public SaleRequest(String requestID) throws IOException {
        super(requestID);
        allSaleRequests.add(this);

    }

    @Override
    public void declineRequest() {
        allRequests.remove(this);
        allSaleRequests.remove(this);
        Request.getAllRequests().remove(this);
    }

    @Override
    public void acceptRequest() throws IOException {
        sale= Sale.getSaleWithId(offId);
        sale.setSaleDetails(SaleStatus.CONFIRMED, startOfSalePeriod, endOfSalePeriod, saleAmount, seller);
        sale.setAllSaleProducts(allSaleProducts);
        sale.setSaleStatus(SaleStatus.CONFIRMED);
        Product.getProductById(product).setInSale(true);
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

    public void setProduct(String product) {
        this.product = product;
    }
}