package controller.menus;


import model.log.BuyLog;
import model.log.Log;
import model.productRelated.Product;
import model.productRelated.Score;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputMassageHandler;
import view.SubMenuStatus;


public class CustomerMenu {
    private int outputNo;
    private OutputMassageHandler outputHandler = new OutputMassageHandler();
    private Product product;
    private BuyLog buyLog;
    private String productID;

    //array
    public void processViewCart() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
    }

    // manager // customer // seller
    private boolean checkProduct(String productID) {
        if (productID.matches(".+")) {
            if (Product.isThereProductWithId(productID)) {
                return true;
            } else outputNo = 0;
        } else outputNo = 0;
        outputHandler.showAccountOutput(outputNo);
        return false;
    }

    //array
    public void showProducts() {

    }

    public void viewProduct(String productID) {
        if (checkProduct(productID)) {
            outputHandler.showProduct(Product.getProductById(productID));
        }
    }

    public void increaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
            outputHandler.showOutput(1);
        }
    }

    public void productNumber(String number) {
        if (number.matches("\\d+")) {
            buyLog.addProductToBuyLog(productID,Integer.parseInt(number));
            outputHandler.showOutputWith2String(productID,number,2);
        }
    }

    public void decreaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
            outputHandler.showOutput(2);
        }
    }

    public void showTotalPrice() {
        outputHandler.showOutputWithString(String.valueOf(buyLog.holePriceWithOutDiscount()), 8);
    }

    //purches--------------------------------------------------------
    public void purchase() {
        CommandProcessor.setMenuStatus(MenuStatus.RECEIVERINFORMATION);
    }

    public void processPurchase() {
        outputHandler.showOutput(3);
        CommandProcessor.setMenuStatus(MenuStatus.RECEIVERINFORMATION);
    }

    public void receiverInformation(String information) {
        CommandProcessor.setMenuStatus(MenuStatus.DISCOUNTCODE);
    }

    public void discountCode(String discountCodeId) {

        CommandProcessor.setMenuStatus(MenuStatus.PAYMENT);
    }

    public void payment() {
        if (buyLog.holePriceWithDiscount() <= LoginMenu.getLoginAccount().getCredit()) {
            finishingPayment();

            CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
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
            if (Log.isThereLogWithID(orderID)) {
                return true;
            } else outputNo = 0;
        } else outputNo = 0;
        outputHandler.showAccountOutput(outputNo);
        return false;
    }

    //array
    public void processViewOrders() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
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
