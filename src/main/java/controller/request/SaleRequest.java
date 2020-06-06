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
    private static String offId = null;
    private static Date startOfSalePeriod = null;
    private static Date endOfSalePeriod = null;
    private static int saleAmount = 0;
    private static String product= null;
    private static Account seller = null;
    private static ArrayList<Product> allSaleProducts = new ArrayList<>();
    private SaleStatus saleStatus;
    private static Sale sale;
    private static ArrayList<SaleRequest> allSaleRequests = new ArrayList<>();


    public SaleRequest(String requestID) throws IOException {
        super(requestID);
        allSaleRequests.add(this);

    }


    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allSaleRequests.remove(request);
        Request.getAllRequests().remove(request);
    }


    public static void acceptRequest(Request request) throws IOException {
        sale= Sale.getSaleWithId(offId);
        sale.setSaleDetails(SaleStatus.CONFIRMED, startOfSalePeriod, endOfSalePeriod, saleAmount, seller);
        sale.setAllSaleProducts(allSaleProducts);
        sale.setSaleStatus(SaleStatus.CONFIRMED);
        Product.getProductById(product).setInSale(true);
        Request.getAllRequests().remove(request);
        allSaleRequests.remove(request);
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