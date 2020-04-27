package controller.menus;


import model.accounts.Account;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputHandler;
import view.SubMenuStatus;


public class CustomerMenu {
    private int inputNo;
    private int outputNo;
    private Account account;
    private OutputHandler outputHandler;
    private Product product;
    private CommandProcessor commandProcessor;

    //array
    public void processViewCart() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
    }

    // manager // customer // seller
    private boolean checkProduct(String productID) {
        if (productID.matches("")) {
            if (product.isThereProductWithId(productID)) {
                return true;
            } else inputNo =0;
        } else inputNo =0;
        return false;
    }

    //array
    public void showProducts() {

    }

    public void viewProduct(String productID) {
        if(checkProduct(productID)) {
            outputHandler.showProduct(product.getProductById(productID));
        }
        outputHandler.showOutput(inputNo);
    }

    public void increaseProductNumber(String productID) {

    }

    public void decreaseProductNumber(String productID) {

    }

    public void showTotalPrice() {

    }

    public void purchase() {

    }

    public void processPurchase() {

    }

    //array
    public void processViewOrders() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }

    public void showOrder(String orderID) {

    }

    public void rateProduct(String productID, int number) {

    }

    public void processViewBalance() {

    }

    //array
    public void processViewDiscountCodes() {

    }

}
