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

    private String productId = null;
    private String productName = null;
    private double price = 0;
    private Seller sellerName = null;
    private Firm companyName = null;
    private Category categoryName = null;
    private Category lastCategory = null;
    private String additionalDetail = null;
    private int numberOfProduct = 0;
    private ArrayList<ProductRequest> allProductRequests = new ArrayList<>();
    private HashMap<String,String> specialValue = new HashMap<>();


    public ProductRequest(String requestID) throws IOException {
        super(requestID);
        allProductRequests.add(this);
    }

    @Override
    public void declineRequest() {
        allRequests.remove(this);
        allProductRequests.remove(this);
        Product.getProductList().remove(this);
        Seller.getAllProduct().remove(this);
    }


    @Override
    public void acceptRequest() throws IOException {
        Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
        newProduct.getCategorySpecifications().putAll(specialValue);
        lastCategory.removeProductToCategory(newProduct);
        categoryName.addProductToCategory(newProduct);
        newProduct.setProductStatus(ProductStatus.CONFIRMED);
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