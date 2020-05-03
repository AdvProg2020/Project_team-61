package controller.request;

import model.productRelated.Product;

public class ProductRequest extends Request {
    private Product product;
    private Product selectedProduct;
    private String productId;

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
