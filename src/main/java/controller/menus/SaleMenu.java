package controller.menus;

import model.productRelated.Product;
import view.OutputHandler;


public class SaleMenu {


    //gson
    public void processOffs() {
        OutputHandler.showOffs();
    }

    //moshtarak etelat bishtr?
    public void processShowProductsID(String id) {
        OutputHandler.showProductsIds(Product.listOfId);
    }


}
