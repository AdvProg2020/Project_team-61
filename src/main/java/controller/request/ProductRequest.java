package controller.request;

import model.accounts.Account;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;

import java.util.ArrayList;

public class ProductRequest extends Request {

    private String productId;
    private String productName;
    private double price;
    private Account sellerName;
    private Firm companyName;
    private Category categoryName;
    private String additionalDetail;
    private int numberOfProduct;
    private ArrayList<ProductRequest> allProductRequests;


    public ProductRequest(String requestID) {
        super(requestID);
        allProductRequests.add(this);
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


    public void acceptRequestDetail(){
       Product newProduct = Product.getProductById(productId);
        newProduct.setDetailProduct(productName, companyName,price, sellerName,numberOfProduct,categoryName);
        newProduct.setAdditionalDetail(additionalDetail);
        //additional,
    }


}