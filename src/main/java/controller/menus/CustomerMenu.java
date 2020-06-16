package controller.menus;

import controller.ProductMenu;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.log.BuyLog;
import model.log.Log;
import model.log.SaleLog;
import model.off.DiscountCode;
import model.productRelated.Product;
import model.productRelated.Score;
import model.sort.Sort;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


public class CustomerMenu {
    private static int outputNo;
    private static String productID;
    private static boolean hasDiscount;
    private static String discountID;
    private static Customer customer;

    public static String getDiscountID() {
        return discountID;
    }

    public static boolean isHasDiscount() {
        return hasDiscount;
    }

    private static boolean isThereBuyLog() {
        if (ProductMenu.getBuyLog() != null) {
            return true;
        }
        outputNo = 12;
        return false;
    }


    //product.......................................................................................
    private static boolean checkProduct(String productID) {
        // if (productID.matches("((?!^ +$)^.+$)")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 1;
        // } else outputNo = 0;
        return false;
    }



    public static void increaseProductNumber(String productID) {
        if (isThereBuyLog()) {
            if (checkProduct(productID)) {
                CustomerMenu.productID = productID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.INCREASEPRODUCTNUMBER);
                outputNo = 2;
            }
        }
        OutputMassageHandler.showCustomerOutput(outputNo);

    }

    public static void sortBy(String sort) throws FileNotFoundException {
        if(sort.matches("(?i)(?:log\\s+date)")){
            if(sort.matches("log\\s+date")) {
                Customer customer= (Customer) LoginMenu.getLoginAccount();
                Sort.setNewArrayOfBuyLog(customer.getBuyLogsHistory());
                Sort.buyLogSortDate();
            }

        }
    }

    public static void increaseLogProduct(String number) {
        if (number.matches("\\d+")) {
            Product product = Product.getProductById(productID);
            ProductMenu.getBuyLog().increaseNumberOfProduct(productID, Integer.parseInt(number));
            outputNo=10;
        }else outputNo=4;
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    public static void decreaseLogProduct(String number) {
        if (number.matches("\\d+")) {
            ProductMenu.getBuyLog().reduceNumberOfProduct(productID, Integer.parseInt(number));
            outputNo=15;
        }else outputNo=4;
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    public static void decreaseProductNumber(String productID) {
        if (isThereBuyLog()) {
            if (checkProduct(productID)) {
                CustomerMenu.productID = productID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.DECREASEPRODUCTNUMBER);
                outputNo = 3;
            }
        }
        OutputMassageHandler.showCustomerOutput(outputNo);
    }

    //purches............................................................................
    public static void purchase() {
        if (LoginMenu.isLogin()) {
            if (LoginMenu.getLoginAccount().getRole().equals("customer")) {
                //CommandProcessor.setMenuStatus(MenuStatus.PURCHASE);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
                //CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
                outputNo = 7;
            } else outputNo = 8;
        } else outputNo = 6;
        OutputMassageHandler.showPurchaseOutput(outputNo);
    }

    private static boolean checkDiscountCode(String discountCodeID) {
        //if (discountCodeID.matches("")) {
        if (DiscountCode.isThereDiscountWithId(discountCodeID)) {
            return true;
        } else outputNo = 7;
        // } else outputNo = ;
        return false;
    }

    public static void haveDiscount(String have) {
        if (have.matches("(?i)(?:yes|no)")) {
            if (have.equalsIgnoreCase("yes")) {
                hasDiscount = true;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.CHECKDISCOUNTCODE);
                outputNo = 1;
            } else {
                hasDiscount = false;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PAYMENT);
                outputNo = 3;
            }
        } else outputNo = 2;
        OutputMassageHandler.showPurchaseOutput(outputNo);
    }


    public static void discountCodeValidation(String discountCodeId) {
        Account loginAccount = LoginMenu.getLoginAccount();
        DiscountCode discountCode = DiscountCode.getDiscountWithId(discountCodeId);
        if (checkDiscountCode(discountCodeId)) {
            if (discountCode.discountMatchAccount(loginAccount.getUsername())) {
                if (discountCode.discountDateValid()) {
                    if (discountCode.getTotalTimesOfUse() > 0) {
                        CommandProcessor.setSubMenuStatus(SubMenuStatus.PAYMENT);
                        discountID = discountCodeId;
                        outputNo = 2;
                    } else outputNo = 5;
                } else outputNo = 4;
            } else outputNo = 3;
        }
        OutputMassageHandler.showPurchaseOutput(outputNo);
    }

    public static void payment() throws IOException {
        if (ProductMenu.getBuyLog().holePrice <= LoginMenu.getLoginAccount().getCredit()) {
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            finishingPayment();
            outputNo = 0;
        } else outputNo = 0;
        OutputMassageHandler.showPurchaseOutput(outputNo);
    }

    private static void finishingPayment() throws IOException {
        double holePrice = ProductMenu.getBuyLog().calculateHolePrice();
        Account loginAccount = LoginMenu.getLoginAccount();
        double money = loginAccount.getCredit() - holePrice;
        loginAccount.setCredit(money);

        if (CustomerMenu.hasDiscount) {

        }
        for (Product p : ProductMenu.getBuyLog().getChosenProduct().keySet()) {
            //faghat price bedoon discount be seller eafe
            p.getSeller().setCredit(p.getSeller().getCredit() + p.getPrice());
            p.getListOfBuyers().add((Customer) loginAccount);
        }
        ProductMenu.getBuyLog().setAllBoughtProduct(ProductMenu.getBuyLog().getChosenProduct());
        ProductMenu.getBuyLog().setItsFinal(true);

        for (Seller seller : ProductMenu.getBuyLog().getSellers()) {
            for (Product p : ProductMenu.getBuyLog().getAllBoughtProduct().keySet()) {
                if (p.getSeller().equals(seller)) {
                    UUID id = UUID.randomUUID();
                    SaleLog saleLog = new SaleLog(id.toString());
                    saleLog.setSaleLogDetail(p.getPrice(), seller.getName());
                    saleLog.addProductToSaleLog(p.getId());
                    if (p.getInSale()) {
                        saleLog.setReducedAmount(p.getSale().getSaleAmount());
                    } else saleLog.setReducedAmount(0);
                }
            }
        }
    }

    //log.............................................................................

    private static boolean checkLog(String orderID) {
        // if (orderID.matches("(?!^ +$)^.+$")) {
        if (Log.isThereLogWithID(orderID)) {
            return true;
        } else outputNo = 8;
        //} else outputNo = 0;
        return false;
    }



    //score.............................................................
    public static void rateProduct(String productID, int number) throws IOException {
        if (checkProduct(productID)) {
            if (number >= 1 && number <= 5) {
                if (checkCustomer()) {
                    if (isBought()) {
                        Score newScore = new Score(LoginMenu.getLoginAccount(), Product.getProductById(productID), number);
                        // OutputMassageHandler.showOutputWith2String(productID, String.valueOf(number), 1);
                        outputNo = 14;
                    }else outputNo = 13;
                }else outputNo = 9;
            } else outputNo = 11;
        }OutputMassageHandler.showCustomerOutput(outputNo);
    }

    private static boolean isBought() {
        for (BuyLog buyLog : customer.getBuyLogsHistory()) {
            if (buyLog.checkIfProductIsBought(productID)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkCustomer() {
        Account account = LoginMenu.getLoginAccount();
        if (account instanceof Customer) {
            customer = ((Customer) account);
            return true;
        }
        return false;
    }

/*    //GSON
    public static void processViewBalance() throws FileNotFoundException {
        OutputHandler.showBalance(LoginMenu.getLoginAccount().getUsername());
    }

    //GSON
    public static void processViewDiscountCodes() throws FileNotFoundException {
        OutputHandler.showDiscountCodes();
    }

    //gson
    public static void processViewCart() throws FileNotFoundException {
        if (isThereBuyLog()) {
            OutputHandler.showCustomerLog(ProductMenu.getBuyLog().getId());
            CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
        } else OutputMassageHandler.showCustomerOutput(outputNo);
    }

    //gson
    public static void showTotalPrice() throws FileNotFoundException {
        if (isThereBuyLog()) {
            OutputHandler.showTotalPrice(ProductMenu.getBuyLog().getId());
        } else OutputMassageHandler.showCustomerOutput(outputNo);
    }

    //gson
    public static void showProducts() throws FileNotFoundException {
        if (isThereBuyLog()) {
            OutputHandler.showProducts(Filter.getNewArrayOfProductFilter());
        } else OutputMassageHandler.showCustomerOutput(outputNo);
    }

    //GSON
    public static void viewProduct(String productID) throws FileNotFoundException {
        if (checkProduct(productID)) {
            Product.getProductById(productID).setNumberOfViews();
            OutputHandler.showProduct(productID);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setMenuStatus(MenuStatus.PRODUCTMENU);
        } else OutputMassageHandler.showCustomerOutput(outputNo);
    }

    //gson
    public static void processViewOrders() throws FileNotFoundException {
        OutputHandler.showOrders();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWORDERS);
    }

 //gson
    public static void showOrder(String orderID) throws FileNotFoundException {
        if (checkLog(orderID)) {
            OutputHandler.showOrder(orderID);
        }else OutputMassageHandler.showCustomerOutput(outputNo);

    }

 */



}