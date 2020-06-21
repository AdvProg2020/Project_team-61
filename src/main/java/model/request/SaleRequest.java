package model.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

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
    public static Type saleRequestType = new TypeToken<ArrayList<SaleRequest>>() {
    }.getType();


    public SaleRequest(String requestID) throws IOException {
        super(requestID);
        allSaleRequests.add(this);
        writeInJ();
    }

    @Override
    public  void declineRequest() throws IOException {
        getAllRequests().remove(this);
        allSaleRequests.remove(this);
        getAllRequests().remove(this);
        writeInJ();
    }

    @Override
    public  void acceptRequest() throws IOException {
        sale= Sale.getSaleWithId(offId);
        sale.setSaleDetails(SaleStatus.CONFIRMED, startOfSalePeriod, endOfSalePeriod, saleAmount, seller);
        sale.setAllSaleProducts(allSaleProducts);
        sale.setSaleStatus(SaleStatus.CONFIRMED);
        Product.getProductById(product).setInSale(true);
        getAllRequests().remove(this);
        allSaleRequests.remove(this);
        writeInJ();
    }



    public void removeProduct(Product product) {
        allSaleProducts.remove(product);
    }

    public void setSeller(Account seller) throws IOException {
        this.seller = seller;
        writeInJ();

    }

    public void addProductToSale(Product product) throws IOException {
        allSaleProducts.add(product);
        writeInJ();

    }

    public void setOffId(String offId) throws IOException {
        this.offId = offId;
        writeInJ();

    }

    public void setStartOfSalePeriod(Date startOfSalePeriod) throws IOException {
        this.startOfSalePeriod = startOfSalePeriod;
        writeInJ();

    }

    public void setEndOfSalePeriod(Date endOfSalePeriod) throws IOException {
        this.endOfSalePeriod = endOfSalePeriod;
        writeInJ();

    }

    public void setSaleAmount(int saleAmount) throws IOException {
        this.saleAmount = saleAmount;
        writeInJ();

    }

    public void setProduct(String product) throws IOException {
        this.product = product;
        writeInJ();
    }

    public static void setAllSaleRequests(ArrayList<SaleRequest> allSaleRequests) {
        SaleRequest.allSaleRequests = allSaleRequests;
    }

    public static ArrayList<SaleRequest> getAllSaleRequests() {
        return allSaleRequests;
    }

    public static void writeInJ() throws IOException {
        FileHandling.setGson(new Gson());
        String json = FileHandling.getGson().toJson(SaleRequest.allSaleRequests, saleRequestType);
        FileHandling.writeInFile(json, "saleRequest.json");
    }
}