package view;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.filtar.Filter;
import model.firms.Firm;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Category;
import model.productRelated.Comment;
import model.productRelated.Product;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class OutputHandler {


    //withoutJson//pr
    public static void showAvailableFilters(ArrayList<String> available) {
        for (String availableFilter : available) {
            System.out.println(availableFilter);
        }
    }

    //withoutJson//pr
    public static void showAllProductAfterFilter() {
        for (Product product : Filter.getNewArrayOfProductFilter()) {
            System.out.println(product);
        }
    }

    //withoutJson//pr
    public static void showCurrentFilter(ArrayList<String> current) {
        for (String currentFilter : current) {
            System.out.println(currentFilter);
        }
    }

    //withoutJson//pr
    public static void showAvailableSorts(ArrayList<String> available) {
        for (String availableSort : available) {
            System.out.println(availableSort);
        }
    }

    //withoutJson//pr
    public static void showAllProductAfterSort(ArrayList<Product> sorted) {
        for (Product product : sorted) {
            System.out.println(product);
        }
    }

    //withoutJson//pr
    public static void showCurrentSorts(ArrayList<String> currentSorts) {
        for (String currentSort : currentSorts) {
            System.out.println(currentSort);
        }
    }

    //finish//pr
    public static void showProducts(ArrayList<Product> data) throws FileNotFoundException {
        for (Product product : data) {
            System.out.println(product);
        }
    }

    //finish//pr
    public static void showProductsIds() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        for (Product product : data) {
            System.out.println(product.getId());
        }
    }

    //finish//pr
    public static void compareProducts(String first, String second) throws FileNotFoundException {
        Product firstPro=null;
        Product secondPro=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        for (Product product : data) {
            if (product.getId().equals(first)){
                firstPro=product;
            }else if (product.getId().equals(second)){
                secondPro=product;
            }
        }
        System.out.println(firstPro);
        System.out.println(secondPro);
    }

    //finish//pr
    public static void showAllSellersForOneProduct(String productId) throws FileNotFoundException {
        ArrayList<Account> proSellers=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        for (Product datum : data) {
            if (datum.getId().equals(productId)){
                proSellers=datum.getListOfSellers();
            }
        }
        for (Account seller : proSellers) {
            System.out.println(seller);
        }
    }

    //..................................................................

    //finish//pr
    public static void showAccountInformation(String username) throws FileNotFoundException {
        Account accountToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Account>>() {
        }.getType();
        JsonReader sellerReader=FileHandling.readFile("account.json");
        ArrayList<Account> data = FileHandling.getGson().fromJson(sellerReader, REVIEW_TYPE);
        for (Account account : data) {
            if (account.getUsername().equals(username)){
                accountToShow=account;
            }
        }
        System.out.println(accountToShow);
    }

    //finish//pr
    public static void showAccounts() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Account>>() {
        }.getType();
        JsonReader sellerReader=FileHandling.readFile("account.json");
        ArrayList<Account> data = FileHandling.getGson().fromJson(sellerReader, REVIEW_TYPE);
        for (Account account : data) {
            System.out.println(account);
        }
    }

    //finish//pr
    public static void showDiscountCodes() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<DiscountCode>>() {
        }.getType();
        JsonReader discountReader=FileHandling.readFile("discountCode.json");
        ArrayList<DiscountCode> data = FileHandling.getGson().fromJson(discountReader, REVIEW_TYPE);
        for (DiscountCode discountCode : data) {
            System.out.println(discountCode);
        }
    }

    //finish//pr
    public static void showDiscountCode(String discountId) throws FileNotFoundException {
        DiscountCode discountCodeToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<DiscountCode>>() {
        }.getType();
        JsonReader discountReader=FileHandling.readFile("discountCode.json");
        ArrayList<DiscountCode> data = FileHandling.getGson().fromJson(discountReader, REVIEW_TYPE);
        for (DiscountCode discountCode : data) {
            if (discountCode.getDiscountId().equals(discountId)){
                discountCodeToShow=discountCode;
            }
        }
        System.out.println(discountCodeToShow);
    }

    //finish//pr
    public static void showRequest(String requestId) throws FileNotFoundException {
        Request requestToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Request>>() {
        }.getType();
        JsonReader requestReader=FileHandling.readFile("request.json");
        ArrayList<Request> data = FileHandling.getGson().fromJson(requestReader, REVIEW_TYPE);
        for (Request request : data) {
            if (request.getRequestText().equals(requestId)){
                requestToShow=request;
            }
        }
        System.out.println(requestToShow);
    }

    //finish//pr
    public static void showRequests() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Request>>() {
        }.getType();
        JsonReader requestReader=FileHandling.readFile("request.json");
        ArrayList<Request> data = FileHandling.getGson().fromJson(requestReader, REVIEW_TYPE);
        for (Request request : data) {
            System.out.println(request);
        }
    }

    //finish//pr
    public static void showCategories() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Category>>() {
        }.getType();
        JsonReader categoryReader=FileHandling.readFile("category.json");
        ArrayList<Category> data = FileHandling.getGson().fromJson(categoryReader, REVIEW_TYPE);
        for (Category category : data) {
            System.out.println(category);
        }
    }

    //finish//pr
    public static void showOff(String saleId) throws FileNotFoundException {
        Sale sale=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Sale>>() {
        }.getType();
        JsonReader SaleReader=FileHandling.readFile("sale.json");
        ArrayList<Sale> data = FileHandling.getGson().fromJson(SaleReader, REVIEW_TYPE);
        for (Sale sale1 : data) {
            if (sale1.getOffId().equals(saleId)) {
                sale=sale1;
            }
        }
        System.out.println(sale);
    }

    //finish//pr
    public static void showOffs() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Sale>>() {
        }.getType();
        JsonReader SaleReader=FileHandling.readFile("sale.json");
        ArrayList<Sale> data = FileHandling.getGson().fromJson(SaleReader, REVIEW_TYPE);
        for (Sale sale : data) {
            System.out.println(sale);
        }
    }



    //naaaaaaaaaaaaaaaaaaaagheeeeeeeeeeeeeeeeeeeessssssssssssssssssssssssssssss
    public static void showSalesHistory(String username) throws FileNotFoundException {
        ArrayList<SaleLog> saleLogs=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Account>>() {
        }.getType();
        JsonReader accountReader=FileHandling.readFile("account.json");
        ArrayList<Account> data = FileHandling.getGson().fromJson(accountReader, REVIEW_TYPE);
        for (Account account : data) {
            if (account instanceof Seller){
                if (((Seller)account).getUsername().equals(username)){
                    saleLogs=((Seller) account).getSaleLogsHistory();
                }
            }
        }
        for (SaleLog saleLog : saleLogs) {
            System.out.println(saleLog);
        }
    }

    //finish//pr
    public static void showFirmInformation(String firmName) throws FileNotFoundException {
        Firm firmToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Firm>>() {
        }.getType();
        JsonReader FirmReader=FileHandling.readFile("firm.json");
        ArrayList<Firm> data = FileHandling.getGson().fromJson(FirmReader, REVIEW_TYPE);
        for (Firm firm : data) {
            if (firm.getName().equals(firmName)) {
                firmToShow=firm;
            }
        }
        System.out.println(firmToShow);
    }

    //finish//pr
    public static void showCustomerLog(String buyLogId) throws FileNotFoundException {
        BuyLog buyLogToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        for (BuyLog buyLog : data) {
            if (buyLog.getId().equals(buyLogId)){
                buyLogToShow=buyLog;
            }
        }
        System.out.println(buyLogToShow);
    }

    //finish//pr
    public static void showOrders() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        for (BuyLog buyLog : data) {
            System.out.println(buyLog);
        }
    }

    //finish//pr
    public static void showOrder(String buyLogId) throws FileNotFoundException {
        BuyLog buyLogToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        for (BuyLog buyLog : data) {
            if (buyLog.getId().equals(buyLogId)){
                buyLogToShow=buyLog;
            }
        }
        System.out.println(buyLogToShow);
    }

    //finish//pr
    public static void showProductSeller(String productId) throws FileNotFoundException {
        ArrayList<Account> sellerArrayList=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader productReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(productReader, REVIEW_TYPE);
        for (Product product : data) {
            if (product.getId().equals(productId)){
                sellerArrayList=product.getListOfSellers();
            }
        }
        for (Account account : sellerArrayList) {
            System.out.println(account);
        }
    }


    public static void showProductBuyers(String productId) throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Customer>>() {
        }.getType();
        JsonReader customerReader=FileHandling.readFile("customer.json");
        ArrayList<Customer> data = FileHandling.getGson().fromJson(customerReader, REVIEW_TYPE);
        for (Customer customer : data) {

        }
    }

    //finish//pr
    public static void showProduct(String productId) throws FileNotFoundException {
        Product productToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader proReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(proReader, REVIEW_TYPE);
        for (Product product : data) {
            if (product.getId().equals(productId)){
                productToShow=product;
            }
        }
        System.out.println(productToShow);
    }

    //finish//pr
    public static void showTotalPrice(String logId) throws FileNotFoundException {
        double totalPriceToShow=0;
        Type REVIEW_TYPE = new TypeToken<ArrayList<BuyLog>>() {
        }.getType();
        JsonReader BuyLogReader=FileHandling.readFile("buyLog.json");
        ArrayList<BuyLog> data = FileHandling.getGson().fromJson(BuyLogReader, REVIEW_TYPE);
        for (BuyLog buyLog : data) {
            if (buyLog.getId().equals(logId)){
                //totalPriceToShow=buyLog.getAmountBeforeDis();
            }
        }
        System.out.println(totalPriceToShow);
    }

    //finish//pr
    public static void showBalance(String username) throws FileNotFoundException {
        Account accountToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Account>>() {
        }.getType();
        JsonReader sellerReader=FileHandling.readFile("account.json");
        ArrayList<Account> data = FileHandling.getGson().fromJson(sellerReader, REVIEW_TYPE);
        for (Account account : data) {
            if (account.getUsername().equals(username)){
                accountToShow=account;
            }
        }
        System.out.println(accountToShow.getCredit());
    }

    //finish//pr
    public static void showAllComments() throws FileNotFoundException {
        Type REVIEW_TYPE = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        JsonReader commentReader=FileHandling.readFile("comment.json");
        ArrayList<Comment> data = FileHandling.getGson().fromJson(commentReader, REVIEW_TYPE);
        for (Comment comment : data) {
            System.out.println(comment);
        }
    }

    //finish//pr
    public static void showCommentOnOneProduct(String productId) throws FileNotFoundException {
        Comment commentToShow=null;
        Type REVIEW_TYPE = new TypeToken<ArrayList<Product>>() {
        }.getType();
        JsonReader productReader=FileHandling.readFile("product.json");
        ArrayList<Product> data = FileHandling.getGson().fromJson(productReader, REVIEW_TYPE);
        for (Product product : data) {
            if (product.getId().matches(productId)){
                System.out.println(product.getComment());
            }
        }

    }

}