package controller.menus;


import model.off.Sale;
import model.productRelated.Product;
import view.OutputMassageHandler;

public class SaleMenu {
    private int outputNo;
    private OutputMassageHandler outputMassageHandler = new OutputMassageHandler();
    private Sale sale;

    //array
    public void processOffs() {

    }

    public void processShowProductsID(String id) {
        outputMassageHandler.showProduct(Product.getProductById(id));
    }


}
