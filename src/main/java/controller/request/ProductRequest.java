package controller.request;

import model.accounts.Account;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;

import java.io.IOException;
import java.util.ArrayList;

public class ProductRequest extends Request {

    private String productId = null;
    private String productName = null;
    private double price = 0;
    private Account sellerName = null;
    private Firm companyName = null;
    private Category categoryName = null;
    private Category lastCategory = null;
    private String additionalDetail = null;
    private int numberOfProduct = 0;
    private ArrayList<ProductRequest> allProductRequests = new ArrayList<>();


    public ProductRequest(String requestID) {
        super(requestID);
        allProductRequests.add(this);
    }

    @Override
    public void acceptRequest() throws IOException {
        Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
        lastCategory.removeProductToCategory(newProduct);
        categoryName.addProductToCategory(newProduct);
        newProduct.setProductStatus(ProductStatus.CONFIRMED);
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
    public void setSellerName(Account sellerName) {
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