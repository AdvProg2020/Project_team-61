package controller.request;

import model.accounts.Seller;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;

import java.io.IOException;
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


    public ProductRequest(String requestID) throws IOException {
        super(requestID);
        allProductRequests.add(this);
    }


    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allProductRequests.remove(request);
        Product.getProductList().remove(request);
        Seller.getAllProduct().remove(request);
    }

    public  void addKey(){
        for (String tr : categoryName.getTraits()) {
            specialValue.put(tr, null);
        }
    }

    public HashMap<String, String> getSpecialValue() {
        return specialValue;
    }


    public static void acceptRequest(Request request) throws IOException {
        Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
        newProduct.getCategorySpecifications().putAll(specialValue);
        lastCategory.removeProductToCategory(newProduct);
        categoryName.addProductToCategory(newProduct);
        newProduct.setProductStatus(ProductStatus.CONFIRMED);
        Request.getAllRequests().remove(request);
        allProductRequests.remove(request);
    }

    public void addHashmapValue(String key, String value){
        specialValue.put(key,value);
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }
    public void setSellerName(Seller sellerName) {
        this.sellerName = sellerName;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCompanyName(Firm companyName) {
        this.companyName = companyName;
    }
    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }
    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public void setLastCategory(Category lastCategory) {
        this.lastCategory = lastCategory;
    }
}