package productRelatedTest;

import model.accounts.Seller;
import model.productRelated.Category;
import model.productRelated.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class productTest {

    private Product product=null;
    private Category category;
    private Seller seller;


    @Test
    public void getProductWId(){
        product=new Product("salt");
        System.out.println(Product.getProductById("salt"));
        System.out.println(product);
        try {
            Assert.assertEquals(product,Product.getProductById("salt"));
        } catch (AssertionError e) {
            System.out.println("Not equal");
            throw e;
        }
        System.out.println("Equal");
    }

    @Test
    public void isThereProWId() throws IOException {
        product=new Product("salt");
        Category category=new Category("iphone");
        Seller seller=new Seller("shi1379");
        //product.setDetailProduct("salt","company",12,seller,2,category );

        try {
            Assert.assertEquals(true,Product.isThereProductWithId("salt"));
        } catch (AssertionError e) {
            System.out.println("Not equal");
            throw e;
        }
        System.out.println("Equal");

    }

    @Test
    public void deleteProduct(){
        product=new Product("salt");
       int n=Product.getProductList().size();
        Product.deleteProduct(product.getId());
        int m =Product.getProductList().size();
        try {
            Assert.assertEquals(m,n-1);
        } catch (AssertionError e) {
            System.out.println("Not equal");
            throw e;
        }
        System.out.println("Equal");
    }

}
