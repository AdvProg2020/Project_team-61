package controller.menus;


import model.off.Sale;
import model.productRelated.Product;
import view.OutputMassageHandler;

public class SaleMenu {
    private int outputNo;
    private OutputMassageHandler outputHandler= new OutputMassageHandler();
    private Sale sale;

    //array
    public void processOffs() {

    }

    public void processShowProductsID(String id) {
        outputHandler.showProduct(Product.getProductById(id));
    }


}
