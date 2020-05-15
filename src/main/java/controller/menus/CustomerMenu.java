package controller.menus;

import model.accounts.Account;
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
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
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
            CommandProcessor.setSubMenuStatus(SubMenuStatus.INCREASEPRODUCTNUMBER);
            OutputMassageHandler.showOutput(2);
        }
    }

    public void increaseLogProduct(String number){
        if (number.matches("\\d+")) {
           Product product= Product.getProductById(productID);
            if(product.getNumberOfProducts()<= p){
                
            }
        }
    }

    public void decreaseLogProduct(String number){
        if (number.matches("\\d+")) {

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
            CommandProcessor.setSubMenuStatus(SubMenuStatus.DECREASEPRODUCTNUMBER);
            OutputMassageHandler.showOutput(3);
        }
    }


    //purches............................................................................
    public void purchase() {
      //  CommandProcessor.setMenuStatus(MenuStatus.PURCHASE);
        CommandProcessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
        CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        OutputMassageHandler.showCustomerOutput(5);

    }

    //product menu bayad bzrmsh*******************************************
    public void processPurchase() {
        if (LoginMenu.isLogin()) {
            if (LoginMenu.getLoginAccount().getRole().equals("customer")) {
                CommandProcessor.setMenuStatus(MenuStatus.PURCHASE);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
                CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
                outputNo = 5;
            } else outputNo = 9;
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

    public void haveDiscount(String have){
        if(have.matches("(?i)(?:yes|no)")){
            if (have.equalsIgnoreCase("yes")) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.CHECKDISCOUNTCODE);
                outputNo=0;
            } else {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PAYMENT);
                outputNo=0;
            }
        }else outputNo=0;
        OutputMassageHandler.showCustomerOutput(outputNo);
    }


    public void discountCodeValidation(String discountCodeId) {
        Account loginAccount = LoginMenu.getLoginAccount();
            DiscountCode discountCode = DiscountCode.getDiscountWithId(discountCodeId);
            if (checkDiscountCode(discountCodeId)) {
                if (discountCode.discountMatchAccount(loginAccount.getUsername())) {
                    if (discountCode.discountDateValid()) {
                        if (loginAccount.getUsedDiscount() < DiscountCode.getDiscountWithId(discountCodeId).getTotalTimesOfUse()) {
                            CommandProcessor.setSubMenuStatus(SubMenuStatus.PAYMENT);
                            loginAccount.increaseDiscountUsed();
                            outputNo = 0;
                        } else outputNo = 10;
                    } else outputNo = 10;
                } else outputNo = 10;
            }
         OutputMassageHandler.showCustomerOutput(outputNo);
    }

    public void payment() {
        if (buyLog.holePriceWithDiscount() <= LoginMenu.getLoginAccount().getCredit()) {
            finishingPayment();
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            outputNo = 0;
        } else {
            outputNo = 0;
        }
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    private void finishingPayment() {
        Account loginAccount =  LoginMenu.getLoginAccount();
        double money = loginAccount.getCredit() - buyLog.holePriceWithDiscount();
        loginAccount.setCredit(money);
        //set buy log
        //set say log
        //set manager creadit
        //set seller credit
        //decrease product

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
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
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