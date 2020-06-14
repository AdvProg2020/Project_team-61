package model.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.menus.LoginMenu;
import model.accounts.Seller;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductRequest extends Request {

    private static String productId = null;
    private static String productName = null;
    private static double price = 0;
    private static Seller sellerName = null;
    private static Firm companyName = null;
    private static Category categoryName = null;
    private static Category lastCategory = null;
    private static String additionalDetail = null;
    private static int numberOfProduct = 0;
    private static ArrayList<ProductRequest> allProductRequests = new ArrayList<>();
    private static HashMap<String,String> specialValue = new HashMap<>();
    public static Type productRequestType = new TypeToken<ArrayList<ProductRequest>>() {
    }.getType();


    public ProductRequest(String requestID) throws IOException {
        super(requestID);
        allProductRequests.add(this);
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            sellerName = (Seller) LoginMenu.getLoginAccount();
        }
        writeInJ();
    }



    public void addKey(){
        for (String tr : categoryName.getTraits()) {
            specialValue.put(tr, null);
        }
    }

    public HashMap<String, String> getSpecialValue() {
        return specialValue;
    }

    @Override
    public void declineRequest() {
        getAllRequests().remove(this);
        allProductRequests.remove(this);
        Product.getProductList().remove(this);
        Seller.getAllProduct().remove(this);
    }

    @Override
    public  void acceptRequest() throws IOException {
        Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
        newProduct.getCategorySpecifications().putAll(specialValue);
        if(lastCategory != null) {
            lastCategory.removeProductToCategory(newProduct);
        }
        categoryName.addProductToCategory(newProduct);
        newProduct.setProductStatus(ProductStatus.CONFIRMED);
        getAllRequests().remove(this);
        allProductRequests.remove(this);
    }

    public void addHashmapValue(String key, String value) throws IOException {
        specialValue.put(key,value);
        writeInJ();
    }

    public void setProductId(String productId) throws IOException {
        this.productId = productId;
        writeInJ();

    }
    public void setProductName(String productName) throws IOException {
        this.productName = productName;
        writeInJ();

    }
    public void setAdditionalDetail(String additionalDetail) throws IOException {
        this.additionalDetail = additionalDetail;
        writeInJ();

    }
    public void setSellerName(Seller sellerName) throws IOException {
        this.sellerName = sellerName;
        writeInJ();

    }
    public void setPrice(double price) throws IOException {
        this.price = price;
        writeInJ();

    }
    public void setCompanyName(Firm companyName) throws IOException {
        this.companyName = companyName;
        writeInJ();

    }
    public void setCategoryName(Category categoryName) throws IOException {
        this.categoryName = categoryName;
        writeInJ();

    }
    public void setNumberOfProduct(int numberOfProduct) throws IOException {
        this.numberOfProduct = numberOfProduct;
        writeInJ();

    }

    public void setLastCategory(Category lastCategory) throws IOException {
        this.lastCategory = lastCategory;
        writeInJ();

    }

    public static void setAllProductRequests(ArrayList<ProductRequest> allProductRequests) {
        ProductRequest.allProductRequests = allProductRequests;
    }

    public static ArrayList<ProductRequest> getAllProductRequests() {
        return allProductRequests;
    }

    public static void writeInJ() throws IOException {
        FileHandling.setGson(new Gson());
        String json = FileHandling.getGson().toJson(ProductRequest.allProductRequests, productRequestType);
        FileHandling.writeInFile(json, "productRequest.json");
    }
}