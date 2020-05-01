package controller.menus;


import model.accounts.Account;
import model.log.BuyLog;
import model.log.Log;
import model.log.SaleLog;
import model.productRelated.Product;
import model.productRelated.Score;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;


public class CustomerMenu {
    private int inputNo;
    private int outputNo;
    private Account account;
    private OutputHandler outputHandler = new OutputHandler();
    private Product product;
    private CommandProcessor commandProcessor;
    // private SaleLog saleLog;
    private BuyLog buyLog;
    private Log log;
    private String productID;

    //array
    public void processViewCart() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
    }

    // manager // customer // seller
    private boolean checkProduct(String productID) {
        if (productID.matches(".+")) {
            if (product.isThereProductWithId(productID)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    //array
    public void showProducts() {

    }

    public void viewProduct(String productID) {
        if (checkProduct(productID)) {
            outputHandler.showProduct(product.getProductById(productID));
        }
    }

    public void increaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            commandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
        }
    }

    public void productNumber(String number) {
        if (number.matches("\\d+")) {
            product.addProductToLog(LoginMenu.getLoginAccount().getUsername(), productID, Integer.parseInt(number));
        }
    }

    public void decreaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            commandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
        }
    }

    public void showTotalPrice() {
        outputHandler.showOutputWithString(String.valueOf(buyLog.holePriceWithOutDiscount()), 8);
    }

    //purches--------------------------------------------------------
    public void purchase() {
        commandProcessor.setMenuStatus(MenuStatus.RECEIVERINFORMATION);
    }

    public void processPurchase() {
        commandProcessor.setMenuStatus(MenuStatus.RECEIVERINFORMATION);
    }

    public void receiverInformation(String information) {
        commandProcessor.setMenuStatus(MenuStatus.DISCOUNTCODE);
    }

    public void discountCode(String discountCodeId) {

        commandProcessor.setMenuStatus(MenuStatus.PAYMENT);
    }

    public void payment() {
        if (buyLog.holePriceWithDiscount() <= LoginMenu.getLoginAccount().getCredit()) {
            finishingPayment();

            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
        } else {

        }
    }

    private void finishingPayment() {
        double money = LoginMenu.getLoginAccount().getCredit() - buyLog.holePriceWithDiscount();
        LoginMenu.getLoginAccount().setCredit(money);

    }


    //log---------------------------------------------------------------
    private boolean checkLog(String orderID) {
        if (orderID.matches(".+")) {
            if (log.isThereLogWithID(orderID)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    //array
    public void processViewOrders() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }


    public void showOrder(String orderID) {
        if (checkLog(orderID)) {

        }

    }

    //-------------------------------------------------------
    public void rateProduct(String productID, int number) {
        if (checkProduct(productID)) {
            Score newScore = new Score(LoginMenu.getLoginAccount(), Product.getProductById(productID), number);
            outputHandler.showOutputWith2String(productID, String.valueOf(number), 1);
        }
    }

    public void processViewBalance() {
        outputHandler.showOutputWithString(String.valueOf(LoginMenu.getLoginAccount().getCredit()), 8);
    }

    //array
    public void processViewDiscountCodes() {

    }

}
