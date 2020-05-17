package controller.menus;

import model.accounts.Account;
import model.log.BuyLog;
import model.log.Log;
import model.off.DiscountCode;
import model.productRelated.Product;
import model.productRelated.Score;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class CustomerMenu {
    private static int outputNo;
    private static Product product;
    private static BuyLog buyLog;
    private static String productID;

    private static boolean isThereBuyLog() {
        if (ProductMenu.getBuyLog() != null) {
            return true;
        }
        OutputMassageHandler.showManageOutput(0);
        return false;
    }

    //gson
    public static void processViewCart() throws FileNotFoundException {
        if(isThereBuyLog()) {
            OutputHandler.showCustomerLog(buyLog.getId());
            CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
        }
    }

    //gson
    public static void showTotalPrice() throws FileNotFoundException {
        if(isThereBuyLog()) {
            OutputHandler.showTotalPrice(buyLog.getId());
        }
    }

    //product.......................................................................................

    // manager // customer // seller
    private static boolean checkProduct(String productID) {
        // if (productID.matches("((?!^ +$)^.+$)")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 1;
        // } else outputNo = 0;
        OutputMassageHandler.showCustomerOutput(outputNo);
        return false;
    }

    //gson
    public static void showProducts() throws FileNotFoundException {
        OutputHandler.showProduct(product.getId());
    }

    //GSON
    public static void viewProduct(String productID) throws FileNotFoundException {
        if (checkProduct(productID)) {
            OutputHandler.showProduct(productID);
        }
    }

    public static void increaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            CustomerMenu.productID = productID;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.INCREASEPRODUCTNUMBER);
            OutputMassageHandler.showOutput(2);
        }

    }

    public static void increaseLogProduct(String number) {
        if (number.matches("\\d+")) {
            Product product = Product.getProductById(productID);
            if (product.getNumberOfProducts() >= p) {
                buyLog.addProductToBuyLog(productID, Integer.parseInt(number));
            }
        }
    }

    public static void decreaseLogProduct(String number) {
        if (number.matches("\\d+")) {
            buyLog.deleteProductFromBuyLog(productID, Integer.parseInt(number));
        }

    }

    //naghes
    public void productNumber(String number) {
        if (number.matches("\\d+")) {
            // product.addProductToLog(LoginMenu.getLoginAccount().getUsername(), productID, Integer.parseInt(number));
            OutputMassageHandler.showOutputWith2String(productID, number, 2);
        } else OutputMassageHandler.showCustomerOutput(4);
    }

    public static void decreaseProductNumber(String productID) {
        if (checkProduct(productID)) {
            CustomerMenu.productID = productID;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.DECREASEPRODUCTNUMBER);
            OutputMassageHandler.showOutput(3);
        }
    }


    //purches............................................................................
    public static void purchase() {
        //  CommandProcessor.setMenuStatus(MenuStatus.PURCHASE);
        CommandProcessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
        CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        OutputMassageHandler.showCustomerOutput(5);

    }

    //product menu bayad bzrmsh*******************************************
    public static void processPurchase() {
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

    private static boolean checkDiscountCode(String discountCodeID) {
        //if (discountCodeID.matches("")) {
        if (DiscountCode.isThereDiscountWithId(discountCodeID)) {
            return true;
        } else outputNo = 7;
        // } else outputNo = ;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    public static void haveDiscount(String have) {
        if (have.matches("(?i)(?:yes|no)")) {
            if (have.equalsIgnoreCase("yes")) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.CHECKDISCOUNTCODE);
                outputNo = 0;
            } else {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PAYMENT);
                outputNo = 0;
            }
        } else outputNo = 0;
        OutputMassageHandler.showCustomerOutput(outputNo);
    }


    public static void discountCodeValidation(String discountCodeId) {
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

    public static void payment() {
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

    private static void finishingPayment() {
        Account loginAccount = LoginMenu.getLoginAccount();
        double money = loginAccount.getCredit() - buyLog.holePriceWithDiscount();
        loginAccount.setCredit(money);
        //set buy log
        //set say log
        //set manager creadit
        //set seller credit
        //decrease product

    }


    //log.............................................................................

    private static boolean checkLog(String orderID) {
        // if (orderID.matches("(?!^ +$)^.+$")) {
        if (Log.isThereLogWithID(orderID)) {
            return true;
        } else outputNo = 8;
        //} else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //gson
    public static void processViewOrders() throws FileNotFoundException {
        OutputHandler.showOrders();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }


    //gson
    public static void showOrder(String orderID) throws FileNotFoundException {
        if (checkLog(orderID)) {
            OutputHandler.showOrder(buyLog.getId());
        }

    }

    //score.............................................................
    public static void rateProduct(String productID, int number) throws IOException {
        if (checkProduct(productID)) {
            if (number >= 1 && number <= 5) {
                Score newScore = new Score(LoginMenu.getLoginAccount(), Product.getProductById(productID), number);
                OutputMassageHandler.showOutputWith2String(productID, String.valueOf(number), 1);
            } else outputNo = 11;
        }
    }

    //GSON
    public static void processViewBalance() throws FileNotFoundException {
        // OutputMassageHandler.showOutputWithString(String.valueOf(LoginMenu.getLoginAccount().getCredit()), 8);
        OutputHandler.showBalance(LoginMenu.getLoginAccount().getUsername());
    }

    //GSON
    public static void processViewDiscountCodes() throws FileNotFoundException {
        OutputHandler.showDiscountCodes();
    }

}