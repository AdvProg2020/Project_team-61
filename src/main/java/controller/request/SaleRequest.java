package controller.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.log.SaleLog;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SaleRequest extends Request {
    private static String id = null;
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
    }



    public static SaleRequest getRequestFromID(String requestID){
        for(SaleRequest request : allSaleRequests){
            if (request.id.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }

    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allSaleRequests.remove(request);
        Request.getAllRequests().remove(request);
    }


    public static void acceptRequest(Request request) throws IOException {
        SaleRequest sr = SaleRequest.getRequestFromID(request.getRequestText());
        sale= Sale.getSaleWithId(sr.offId);
        sale.setSaleDetails(SaleStatus.CONFIRMED, sr.startOfSalePeriod, sr.endOfSalePeriod, sr.saleAmount, sr.seller);
        sale.setAllSaleProducts(sr.allSaleProducts);
        sale.setSaleStatus(SaleStatus.CONFIRMED);
        Product.getProductById(sr.product).setInSale(true);
        Request.getAllRequests().remove(request);
        allSaleRequests.remove(request);
    }



    public void removeProduct(Product product) {
        allSaleProducts.remove(product);
    }

    public static void setId(String id) throws IOException {
        SaleRequest.id = id;
        writeInJ();
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

    public static void writeInJ() throws IOException {
        FileHandling.setGson(new Gson());
        String json = FileHandling.getGson().toJson(SaleRequest.allSaleRequests, saleRequestType);
        FileHandling.writeInFile(json, "saleRequest.json");
    }
}