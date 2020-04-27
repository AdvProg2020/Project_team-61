package productRelatedTest;

import model.productRelated.Product;
import org.junit.Assert;
import org.junit.Test;


public class productTest {

    private Product product=new Product("salt");

    @Test
    public void testGettingProductWithId(){
        Product product1=null;
        product.setAverageScore(2);
        Assert.assertEquals(product,product1.getProductById("salt"));
    }
}
