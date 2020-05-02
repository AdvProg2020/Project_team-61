package controller.menus;


import model.off.Sale;
import model.productRelated.Product;
import view.OutputHandler;

public class SaleMenu {
    private int outputNo;
    private OutputHandler outputHandler= new OutputHandler();
    private Sale sale;

    //array
    public void processOffs() {

    }

    public void processShowProductsID(String id) {
        outputHandler.showProduct(Product.getProductById(id));
    }


}
