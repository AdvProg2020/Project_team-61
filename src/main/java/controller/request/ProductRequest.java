package controller.request;

import model.productRelated.Product;

public class ProductRequest extends Request {
    private Product product;
    private Product selectedProduct;
    private String productId;
<<<<<<< HEAD
=======
    private String productName;
    private double price;
    private String sellerName;
    private String companyName;
    private String categoryName;
    private String additionalDetail;
    private int numberOfProduct;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
>>>>>>> f27c852e550a561c4d85300ed7630aa4436c40fc

    public ProductRequest(String requestID) {
        super(requestID);
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void acceptRequestDetail(){
        selectedProduct = product.getProductWithName(productId);
        //selectedProduct.setDetailProduct();
    }

}
