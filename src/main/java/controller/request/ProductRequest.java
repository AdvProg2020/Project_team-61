package controller.request;

import model.accounts.Account;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;

import java.util.ArrayList;

public class ProductRequest extends Request {

    private String productId = null;
    private String productName = null;
    private double price = 0;
    private Account sellerName = null;
    private Firm companyName = null;
    private Category categoryName = null;
    private String additionalDetail = null;
    private int numberOfProduct = 0;
    private ArrayList<ProductRequest> allProductRequests;


    public ProductRequest(String requestID) {
        super(requestID);
        allProductRequests.add(this);
    }

    @Override
    public void acceptRequest() {
        Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
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




}