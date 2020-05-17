package controller.menus;

import controller.request.ProductRequest;
import controller.request.Request;
import controller.request.SaleRequest;
import model.filtar.Filter;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;
import view.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SellerMenu {

    private static int outputNo;
    private static String field;
    private static int detailMenu = 0;
    private static ProductRequest productRequest;
    private static SaleRequest saleRequest;
    private static String productId;
    private static String offId;

    public static String getField() {
        return field;
    }

    public static int getDetailMenu() {
        return detailMenu;
    }

    //gson
    public static void processViewCompanyInformation() throws FileNotFoundException {
        OutputHandler.showFirmInformation(LoginMenu.getFirm().getName());
    }

    //gson
    //vorodiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
    public static void processViewSalesHistory() throws FileNotFoundException {
        OutputHandler.showSalesHistory(LoginMenu.getLoginAccount().getUsername());
    }

    //product--------------------------------------------------------------------------------------
    // manager // customer // seller
    private static boolean checkProduct(String productID) {
        // if (productID.matches("((?!^ +$)^.+$)")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 1;
        //} else outputNo = 2;
        return false;
    }

    //gson
    public static void processManageProducts() throws FileNotFoundException {
        OutputHandler.showProducts(Filter.newArrayOfProductFilter);
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEPRODUCTS);

    }

    //gson
    public static void viewProduct(String productID) throws FileNotFoundException {
        if (checkProduct(productID)) {
            OutputHandler.showProduct(productID);
        }
    }

    //gson
    public static void viewBuyersProduct(String productID) throws FileNotFoundException {
        if (checkProduct(productID)) {
            OutputHandler.showProductBuyers(productID);
        }

    }

    public static void editProduct(String productID) {
        Product product = Product.getProductById(productID);
        if (checkProduct(productID)) {
            if (product.ifProductHasSeller(productID, LoginMenu.getLoginAccount().getUsername())) {
                productId = productID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTFIELD);
                outputNo = 2;
            } else outputNo = 22;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void productField(String field) {
        if (field.matches("(?i)(?:Name|price|category|additional\\s*details|number\\s*Of\\s*Product)")) {
            String id = LoginMenu.getLoginAccount().getUsername() + " wants edit product " + productId + "'s " + field;
            if (productRequest.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            Product.getProductById(productId).setProductStatus(ProductStatus.UNDERREVIEWFOREDITING);
            productRequest = new ProductRequest(id);
            productRequest.setSellerName(LoginMenu.getLoginAccount());
            productRequest.setProductId(productId);
            SellerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITPRODUCT);
            outputNo = 3;
        } else outputNo = 23;
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void editProductField(String edit) {
        if (field.equalsIgnoreCase("Name")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(edit);
                outputNo = 4;
            } else outputNo = 6;
        } else if (field.equalsIgnoreCase("price")) {
            if (edit.matches("[+-]?\\d*\\.?\\d+")) {
                productRequest.setPrice(Double.parseDouble(edit));
                outputNo = 5;
            } else outputNo = 7;
        } else if (field.matches("(?i)number\\s*Of\\s*Product")) {
            if (edit.matches("\\d+")) {
                productRequest.setNumberOfProduct(Integer.parseInt(edit));
                outputNo = 9;
            } else outputNo = 8;
        } else if (field.matches("additional\\s*details")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setAdditionalDetail(edit);
                outputNo = 14;
            } else outputNo = 15;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }
        /*} else if (field.equalsIgnoreCase("category")) {
            if (edit.matches("^(?!\\s*$).+")) {
                if (Category.isThereCategoryWithName(edit)) {
                    productRequest.setCategoryName(Category.getCategoryWithName(edit));
                    outputNo = 5;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (field.matches("(?i)company\\s*Name")) {
            if (edit.matches("^(?!\\s*$).+")) {
                if (Firm.isThereFirmWithID(edit)) {
                    productRequest.setCompanyName(Firm.getFirmWithID(edit));
                    outputNo = 8;
                } else outputNo = 0;
            } else outputNo = 0;
        */

    public static void processAddProduct() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDPRODUCT);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(10);
    }

    public static void addProduct(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (!Product.isThereProductWithId(detail)) {
                    Product product = new Product(detail);
                    product.setProductStatus(ProductStatus.UNDERREVIEWFORCONSTRUCTION);
                    String id = LoginMenu.getLoginAccount().getUsername() + "wants add product " + detail;
                    if (productRequest.isThereRequestFromID(id)) {
                        Request.deleteRequest(id);
                    }
                    productRequest = new ProductRequest(id);
                    productRequest.setProductId(detail);
                    productRequest.setCompanyName(LoginMenu.getFirm());
                    detailMenu = 1;
                    outputNo = 11;
                } else outputNo = 27;
            } else outputNo = 0;
        } else if (detailMenu == 1) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(detail);
                detailMenu = 2;
                outputNo = 12;
            } else outputNo = 6;
        } else if (detailMenu == 2) {
            if (detail.matches("[+-]?\\d*\\.?\\d+")) {
                productRequest.setPrice(Double.parseDouble(detail));
                detailMenu = 3;
                outputNo = 13;
            } else outputNo = 7;
        } else if (detailMenu == 3) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (Category.isThereCategoryWithName(detail)) {
                    productRequest.setCategoryName(Category.getCategoryWithName(detail));
                    detailMenu = 4;
                    outputNo = 26;
                } else outputNo = 25;
            } else outputNo = 24;
        } else if (detailMenu == 4) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setAdditionalDetail(detail);
                detailMenu = 16;
            } else outputNo = 15;
        } else if (detailMenu == 5) {
            if (detail.matches("\\d+")) {
                productRequest.setNumberOfProduct(Integer.parseInt(detail));
                detailMenu = 0;
                outputNo = 17;
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            } else outputNo = 8;
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }


    public static void processRemoveProduct(String productID) {

        if (checkProduct(productID)) {
            if (Product.getProductById(productID).ifProductHasSeller(productID, LoginMenu.getLoginAccount().getUsername())) {
                Product.deleteProduct(productID);
                outputNo = 18;
            }
        }
        OutputMassageHandler.showAccountOutput(outputNo);

    }
    //----------------------------------------------------------------------------------------

    //array
    public static void processShowCategories() throws FileNotFoundException {
        OutputHandler.showCategories();
    }

    //---------------------------------------------------------------------------------------
    private static boolean checkSale(String offID) {
        //if (offID.matches("")) {
        if (Sale.isThereSaleWithId(offID)) {
            return true;
        } else outputNo = 19;
        // } else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;

    }

    //array
    public static void processViewOffs() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWOFFS);
        OutputHandler.showOffs();
    }

    public static void viewOff(String offID) throws FileNotFoundException {
        if (checkSale(offID)) {
            OutputHandler.showOff(offID);
        }

    }

    public static void editOff(String offID) {
        if (checkSale(offID)) {
            if (Sale.getSeller() == LoginMenu.getLoginAccount()) {
                offId = offId;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
                outputNo = 20;
            } else outputNo = 0;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void offField(String field) {
        if (field.matches("(?i)(?:sale\\s*status|start\\s*of\\s*sale\\s*period|end\\s*of\\s*sale\\s*period|remove\\s*product|add\\s*product)")) {
            String id = LoginMenu.getLoginAccount() + " wants edit off " + offId + "'s " + field;
            if (Request.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            Sale.getSaleWithId(offId).setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
            saleRequest = new SaleRequest(id);
            saleRequest.setOffId(offId);
            SellerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSALE);
            outputNo = 21;
        }
        outputNo = 0;
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void editOffField(String edit) throws ParseException {
        if (field.matches("(?i)start\\s*of\\s*sale\\s*period")) {
            if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (field.matches("(?i)end\\s*of\\s*sale\\s*period")) {
            if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (field.equalsIgnoreCase("(?i)sale\\s*amount")) {
            if (edit.matches("\\d+\\.?\\d*")) {
                saleRequest.setSaleAmount(Integer.parseInt(edit));
                outputNo = 0;
            } else outputNo = 0;
        } else if (field.equalsIgnoreCase("(?i)remove\\s*product")) {
            if (edit.matches("((?!^ +$)^.+$)")) {
                if (checkProductSale(edit)) {
                    saleRequest.removeProduct(Product.getProductById(edit));
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (field.equalsIgnoreCase("(?i)add\\s*product")) {
            if (edit.matches("((?!^ +$)^.+$)")) {
                if (checkProductSale(edit)) {
                    saleRequest.addProductToSale(Product.getProductById(edit));
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;

        }
        OutputMassageHandler.showSaleOutput(outputNo);
    }

    public static void addOff() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDSALE);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(0);
    }

    private static boolean checkSaleId(String detail) {
        if (!Sale.isThereSaleWithId(detail)) {
            Sale sale = new Sale(detail);
            sale.setSaleStatus(SaleStatus.UNDERREVIEWFORCONSTRUCTION);
            String id = "add sale: " + detail;
            if (saleRequest.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            saleRequest = new SaleRequest(id);
            saleRequest.setOffId(detail);
            saleRequest.setSeller(LoginMenu.getLoginAccount());
            return true;
        }
        return false;
    }

    public static void setDetailsToSale(String detail) throws ParseException {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (checkSaleId(detail)) {
                    detailMenu = 1;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 1) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    detailMenu = 2;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 2) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    detailMenu = 3;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 3) {
            if (detail.matches("\\d+")) {
                saleRequest.setSaleAmount(Integer.parseInt(detail));
                detailMenu = 4;
                outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 4) {
            if (detail.matches("((?!^ +$)^.+$)")) {
                if (checkProductSale(detail)) {
                    saleRequest.addProductToSale(Product.getProductById(detail));
                    detailMenu = 0;
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                } else outputNo = 0;
            } else outputNo = 0;
        }
        OutputMassageHandler.showSaleOutput(outputNo);

    }

    private static boolean checkProductSale(String detail) {
        if (checkProduct(detail)) {
            if (Product.getProductById(productId).ifProductHasSeller(detail, LoginMenu.getLoginAccount().getUsername())) {
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------------
    //gson
    public static void processViewBalance() throws FileNotFoundException {
        OutputHandler.showBalance(LoginMenu.getLoginAccount().getUsername());
    }

}