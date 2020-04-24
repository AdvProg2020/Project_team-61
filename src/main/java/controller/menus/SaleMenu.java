package controller.menus;


import model.off.Sale;
import model.productRelated.Product;
import view.OutputHandler;

public class SaleMenu {
    private int outputNo;
    private int inputNo;
    private Product product;
    private Sale sale;

    public void processOffs() {
          OutputHandler.showObjectOutput(sale.getSaleListSize(), sale.getListSaleSize(),1);
    }

    public void processShowProductsID(String id) {
         OutputHandler.showObjectOutput(product.getProductById (id) , 1,1);

    }


}
