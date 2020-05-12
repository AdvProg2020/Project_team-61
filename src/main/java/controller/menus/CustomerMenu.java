package controller.menus;

import model.log.BuyLog;
import model.log.Log;
import model.off.DiscountCode;
import model.productRelated.Product;
import model.productRelated.Score;
import view.*;


public class CustomerMenu {
    private int outputNo;
    private Product product;
    private BuyLog buyLog;
    private String productID;

    //gson
    public void processViewCart() {
        OutputHandler.showCustomerLog();
        commandprocessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
    }

    //gson
    public void showTotalPrice() {
        // OutputMassageHandler.showOutputWithString(String.valueOf(buyLog.holePriceWithOutDiscount()), 8);
        OutputHandler.showTotalPrice();
    }

    //product.......................................................................................
    // manager // customer // seller
    private boolean checkProduct(String productID) {
        // if (productID.matches("((?!^ +$)^.+$)")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 1;
        // } else outputNo = 0;
        OutputMassageHandler.showCustomerOutput(outputNo);
        return false;
    }

    //gson
    public void showProducts() {
        OutputHandler.showProduct();
    }

    //GSON
    public void viewProduct(String productID) {
        if (checkProduct(productID)) {
            OutputHandler.showProduct();
        }
    }

    public void increaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            commandprocessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
            OutputMassageHandler.showOutput(2);
        }
    }

    //naghes
    public void productNumber(String number) {
        if (number.matches("\\d+")) {
            // product.addProductToLog(LoginMenu.getLoginAccount().getUsername(), productID, Integer.parseInt(number));
            OutputMassageHandler.showOutputWith2String(productID, number, 2);
        } else OutputMassageHandler.showCustomerOutput(4);
    }

    public void decreaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            this.productID = productID;
            commandprocessor.setSubMenuStatus(SubMenuStatus.PRODUCTNUMBER);
            OutputMassageHandler.showOutput(3);
        }
    }


    //purches............................................................................
    public void purchase() {
        commandprocessor.setMenuStatus(MenuStatus.PURCHASE);
        commandprocessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
        commandprocessor.setInternalMenu(InternalMenu.MAINMENU);
        OutputMassageHandler.showCustomerOutput(5);

    }

    //product menu bayad bzrmsh*******************************************
    public void processPurchase() {
        if (LoginMenu.isLogin()) {
            if (LoginMenu.getLoginAccount().getRole().equals("customer")) {
                commandprocessor.setMenuStatus(MenuStatus.PURCHASE);
                commandprocessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
                commandprocessor.setInternalMenu(InternalMenu.MAINMENU);
                outputNo = 5;
            }else outputNo=9;
        } else outputNo = 6;
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    private boolean checkDiscountCode(String discountCodeID) {
        //if (discountCodeID.matches("")) {
        if (DiscountCode.isThereDiscountWithId(discountCodeID)) {
            return true;
        } else outputNo = 7;
        // } else outputNo = ;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }


    public void discountCodeValidation(String discountCodeId) {
        if (checkDiscountCode(discountCodeId)) {
            if () {
                commandprocessor.setSubMenuStatus(SubMenuStatus.PAYMENT);

            } else outputNo = 10;
                OutputMassageHandler.showCustomerOutput(outputNo);
        }
    }

    public void payment() {
        if (buyLog.holePriceWithDiscount() <= LoginMenu.getLoginAccount().getCredit()) {
            finishingPayment();
            commandprocessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            commandprocessor.setMenuStatus(MenuStatus.MAINMENU);
            outputNo =0;
        } else {
            outputNo=0;
        }
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    private void finishingPayment() {
        double money = LoginMenu.getLoginAccount().getCredit() - buyLog.holePriceWithDiscount();
        LoginMenu.getLoginAccount().setCredit(money);

    }


    //log.............................................................................
    private boolean checkLog(String orderID) {
        // if (orderID.matches("(?!^ +$)^.+$")) {
        if (Log.isThereLogWithID(orderID)) {
            return true;
        } else outputNo = 8;
        //} else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //gson
    public void processViewOrders() {
        OutputHandler.showOrders();
        commandprocessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }


    //gson
    public void showOrder(String orderID) {
        if (checkLog(orderID)) {
            OutputHandler.showOrder();
        }

    }

    //score.............................................................
    public void rateProduct(String productID, int number) {
        if (checkProduct(productID)) {
            if (number >= 1 && number <= 5) {
                Score newScore = new Score(LoginMenu.getLoginAccount(), Product.getProductById(productID), number);
                OutputMassageHandler.showOutputWith2String(productID, String.valueOf(number), 1);
            } else outputNo = 11;
        }
    }

    //GSON
    public void processViewBalance() {
        // OutputMassageHandler.showOutputWithString(String.valueOf(LoginMenu.getLoginAccount().getCredit()), 8);
        OutputHandler.showBalance();
    }

    //GSON
    public void processViewDiscountCodes() {
        OutputHandler.showDiscountCodes();
    }

}