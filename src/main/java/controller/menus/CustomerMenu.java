package controller.menus;


import model.accounts.Account;
import model.log.Log;
import model.log.SaleLog;
import model.productRelated.Product;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;


public class CustomerMenu {
    private int inputNo;
    private int outputNo;
    private Account account;
    private OutputHandler outputHandler= new OutputHandler();
    private Product product;
    private CommandProcessor commandProcessor;
    private SaleLog saleLog;
    private Log log;

    //array
    public void processViewCart() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
    }

    // manager // customer // seller
    private boolean checkProduct(String productID) {
        if (productID.matches(".+")) {
            if (product.isThereProductWithId(productID)) {
                return true;
            } else inputNo =0;
        } else inputNo =0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    //array
    public void showProducts() {

    }

    public void viewProduct(String productID) {
        if(checkProduct(productID)) {
            outputHandler.showProduct(product.getProductById(productID));
        }
    }

    public void increaseProductNumber(String productID) {
       if(checkProduct(productID)) {
           //  product.getProductById()
           //commandProcessor.setSubMenuStatus(number);

       }
    }

    public void decreaseProductNumber(String productID) {

    }

    public void showTotalPrice() {
    }

    //purches--------------------------------------------------------
    public void purchase() {
        commandProcessor.setMenuStatus(MenuStatus.PURCHASE);
    }

    public void processPurchase() {
        commandProcessor.setMenuStatus(MenuStatus.RECEIVERINFORMATION);
    }

    public void receiverInformation(String information){
        commandProcessor.setMenuStatus(MenuStatus.DISCOUNTCODE);
    }

    public void discountCode(String discountCodeId){
        commandProcessor.setMenuStatus(MenuStatus.PAYMENT);
    }

    public void payment(){
        commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
    }

    //log---------------------------------------------------------------
    private boolean checkLog(String orderID) {
        if (orderID.matches(".+")) {
            if (log.isThereLogWithID(orderID)) {
                return true;
            } else inputNo =0;
        } else inputNo =0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    //array
    public void processViewOrders() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }


    public void showOrder(String orderID) {
        if(checkLog(orderID)){

        }

    }

    //-------------------------------------------------------
    public void rateProduct(String productID, int number) {
        if(checkProduct(productID)){

        }
    }

    public void processViewBalance() {

    }

    //array
    public void processViewDiscountCodes() {

    }

}
