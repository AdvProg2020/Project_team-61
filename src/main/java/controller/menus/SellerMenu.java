package controller.menus;

import controller.request.ProductRequest;
import controller.request.Request;
import controller.request.SaleRequest;
import model.accounts.Seller;
import model.filtar.Filter;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;
import model.sort.Sort;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SellerMenu {

    private static int outputNo = 0;
    private static String field = null;
    private static int detailMenu = 0;
    private static ProductRequest productRequest;
    private static SaleRequest saleRequest;
    private static String productId;
    private static String offId;
    private static String editValue;
   // static ArrayList<String> keys = new ArrayList<String>(productRequest.getSpecialValue().keySet());
    private static int number = 0;


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
        } else OutputMassageHandler.showSellerOutput(outputNo);
    }



    //gson
    public static void viewBuyersProduct(String productID) throws FileNotFoundException {
        if (checkProduct(productID)) {
            OutputHandler.showProductBuyers(productID);
        } else OutputMassageHandler.showSellerOutput(outputNo);

    }

    public static void editProduct(String productID) {
        Product product = Product.getProductById(productID);
        if (checkProduct(productID)) {
            if (product.getSeller() == LoginMenu.getLoginAccount()) {
                productId = productID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTFIELD);
                outputNo = 2;
            } else outputNo = 22;
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }

    public static void productField(String field) throws IOException {
        if (field.matches("(?i)(?:Name|price|category|additional\\s*details|number\\s*Of\\s*Product)")) {
            String id = LoginMenu.getLoginAccount().getUsername() + " wants edit product " + productId + "'s " + field;
            if (!productRequest.isThereRequestFromID(id)) {
                Product product = Product.getProductById(productId);
                product.setProductStatus(ProductStatus.UNDERREVIEWFOREDITING);
                productRequest = new ProductRequest(id);
                productRequest.setLastCategory(product.getCategory());
                Seller seller = (Seller) LoginMenu.getLoginAccount();
                productRequest.setSellerName(seller);
                productRequest.setProductId(productId);
            } else productRequest = (ProductRequest) Request.getRequestFromID(id);
            SellerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITPRODUCT);
            outputNo = 3;
        } else outputNo = 23;
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void editProductField(String edit) throws IOException {
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
        } else if (field.equalsIgnoreCase("category")) {
            if (edit.matches("^(?!\\s*$).+")) {
                if (Category.isThereCategoryWithName(edit)) {
                    productRequest.setCategoryName(Category.getCategoryWithName(edit));
                    outputNo = 5;
                } else outputNo = 21;
            } else outputNo = 20;
        } else if (field.matches("(?i)category\\s*Specifications")) {
            if (Product.getCategorySpecifications().containsKey(edit)) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSPECIFICATION);
                editValue = edit;
                outputNo = 32;
            } else outputNo = 31;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void editCategorySpecifications(String value) throws IOException {
        productRequest.addHashmapValue(editValue, value);
        OutputMassageHandler.showSaleOutput(33);
    }

    public static void processAddProduct() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDPRODUCT);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(10);
    }

    public static void addProduct(String detail) throws IOException {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (!detail.equalsIgnoreCase("finish")) {
                    if (!Product.isThereProductWithId(detail)) {
                        String id = LoginMenu.getLoginAccount().getUsername() + "wants add product " + detail;
                        if (!productRequest.isThereRequestFromID(id)) {
                            Product product = new Product(detail);
                            product.setProductStatus(ProductStatus.UNDERREVIEWFORCONSTRUCTION);
                            productRequest = new ProductRequest(id);
                            productRequest.setProductId(detail);
                            productRequest.setCompanyName(LoginMenu.getFirm());
                        } else if(Request.getRequestFromID(id) instanceof ProductRequest){
                            productRequest = (ProductRequest) Request.getRequestFromID(id);
                        }
                        detailMenu = 1;
                        outputNo = 11;
                    } else outputNo = 27;
                } else outputNo = 19;
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
                    productRequest.addKey();
                    detailMenu = 4;
                    outputNo = 26;
                } else outputNo = 25;
            } else outputNo = 24;
        } else if (detailMenu == 4) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setAdditionalDetail(detail);
                detailMenu = 5;
                outputNo =0;
            } else outputNo = 15;
        } else if (detailMenu == 5) {
            if (detail.matches("\\d+")) {
                productRequest.setNumberOfProduct(Integer.parseInt(detail));
                detailMenu = 0;
                outputNo = 17;
                OutputMassageHandler.show((String) productRequest.getSpecialValue().keySet().toArray()[0]);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.TRAIT);
            } else outputNo = 8;
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }

    public static void traitValue(String detail) throws IOException {
        ArrayList<String> keys = new ArrayList<>();
        for (String s : productRequest.getSpecialValue().keySet()) {
            keys.add(s);
        }
        if (!detail.equalsIgnoreCase("finish")) {
            if (number < keys.size()-1 ) {
                if (detail.matches(".+")) {
                    productRequest.addHashmapValue(keys.get(number), detail);
                    outputNo = 30;
                    number++;
                    OutputMassageHandler.show(keys.get(number));
                } else outputNo = 29;
            } else if (number == keys.size() -1) {
                if (detail.matches(".+")) {
                    productRequest.addHashmapValue(keys.get(number), detail);
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                    number=0;
                    outputNo = 28;
                } else outputNo = 29;
            } else {
                outputNo = 28;
                number=0;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            }
        } else {
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            outputNo = 28;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public static void processRemoveProduct(String productID) {
        if (checkProduct(productID)) {
            if (Product.getProductById(productID).getSeller() == LoginMenu.getLoginAccount()) {
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
        } else outputNo = 1;
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
            Sale sale = Sale.getSaleWithId(offID);
            if (sale.getSeller() == LoginMenu.getLoginAccount()) {
                offId = offID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
                outputNo = 2;
            } else outputNo = 5;
        }
        OutputMassageHandler.showSaleOutput(outputNo);
    }

    public static void offField(String field) throws IOException {
        if (field.matches("(?i)(?:sale\\s*status|start\\s*of\\s*sale\\s*period|end\\s*of\\s*sale\\s*period|remove\\s*product|add\\s*product|category\\s*Specifications)")) {
            String id = LoginMenu.getLoginAccount() + " wants edit off " + offId + "'s " + field;
            if (!Request.isThereRequestFromID(id)) {
                Sale.getSaleWithId(offId).setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
                saleRequest = new SaleRequest(id);
                saleRequest.setOffId(offId);
                saleRequest.setSeller(LoginMenu.getLoginAccount());
            } else {
                saleRequest = (SaleRequest) Request.getRequestFromID(id);
            }
            if (field.matches("(?i)category\\s*Specifications")) {
                outputNo = 26;
            } else outputNo = 4;
            SellerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSALE);

        }
        outputNo = 3;
        OutputMassageHandler.showSaleOutput(outputNo);
    }

    public static void editOffField(String edit) throws ParseException, IOException {
        if (field.matches("(?i)start\\s*of\\s*sale\\s*period")) {
            if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    outputNo = 11;
                } else outputNo = 12;
            } else outputNo = 9;
        } else if (field.matches("(?i)end\\s*of\\s*sale\\s*period")) {
            if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    outputNo = 13;
                } else outputNo = 12;
            } else outputNo = 14;
        } else if (field.matches("(?i)sale\\s*amount")) {
            if (edit.matches("\\d+\\.?\\d*")) {
                saleRequest.setSaleAmount(Integer.parseInt(edit));
                outputNo = 16;
            } else outputNo = 15;
        } else if (field.matches("(?i)remove\\s*product")) {
            if (edit.matches("((?!^ +$)^.+$)")) {
                if (checkProductSale(edit)) {
                    saleRequest.removeProduct(Product.getProductById(edit));
                    outputNo = 17;
                }
            } else outputNo = 19;
        } else if (field.matches("(?i)add\\s*product")) {
            if (edit.matches("((?!^ +$)^.+$)")) {
                if (checkProductSale(edit)) {
                    saleRequest.addProductToSale(Product.getProductById(edit));
                    outputNo = 18;
                }
            } else outputNo = 19;
        }
        OutputMassageHandler.showSaleOutput(outputNo);
    }

    public static void addOff() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDSALE);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSaleOutput(6);
    }

    private static boolean checkSaleId(String detail) throws IOException {
        if (!Sale.isThereSaleWithId(detail)) {
            String id = "add sale: " + detail;
            if (!saleRequest.isThereRequestFromID(id)) {
                Sale sale = new Sale(detail);
                sale.setSaleStatus(SaleStatus.UNDERREVIEWFORCONSTRUCTION);
                saleRequest = new SaleRequest(id);
                saleRequest.setOffId(detail);
                saleRequest.setSeller(LoginMenu.getLoginAccount());
            } else {
                saleRequest = (SaleRequest) Request.getRequestFromID(id);
            }
            return true;
        } else outputNo = 7;
        return false;
    }

    public static void setDetailsToSale(String detail) throws ParseException, IOException {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (checkSaleId(detail)) {
                    detailMenu = 1;
                    outputNo = 10;
                }
            } else outputNo = 9;
        } else if (detailMenu == 1) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    detailMenu = 2;
                    outputNo = 20;
                } else outputNo = 12;
            } else outputNo = 9;
        } else if (detailMenu == 2) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    detailMenu = 3;
                    outputNo = 21;
                } else outputNo = 12;
            } else outputNo = 19;
        } else if (detailMenu == 3) {
            if (detail.matches("\\d+")) {
                saleRequest.setSaleAmount(Integer.parseInt(detail));
                detailMenu = 4;
                outputNo = 22;
            } else outputNo = 15;
        } else if (detailMenu == 4) {
            if (detail.matches("((?!^ +$)^.+$)")) {
                if (!detail.equalsIgnoreCase("finish")) {
                    if (checkProductSale(detail)) {
                        saleRequest.setProduct(detail);
                        saleRequest.addProductToSale(Product.getProductById(detail));
                        outputNo = 22;
                    }
                } else {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEPRODUCTS);
                    CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                    detailMenu = 0;
                    outputNo = 24;
                }
            } else outputNo = 19;
        }
        OutputMassageHandler.showSaleOutput(outputNo);
    }

    public static void sortBy(String sort) throws FileNotFoundException {
        if(sort.matches("(?i)(?:product\\s+view|product\\s+score|log\\s+date)")){
            if(sort.matches("product\\s+view")) {
                Seller seller = (Seller) LoginMenu.getLoginAccount();
                Sort.setNewArrayOfProductSort(seller.getAllProduct());
                Sort.numberOfViewsSort();
            }else if(sort.matches("product\\s+score")) {
                Seller seller = (Seller) LoginMenu.getLoginAccount();
                Sort.setNewArrayOfProductSort(seller.getAllProduct());
                Sort.scoreSort();
            }else if(sort.matches("log\\s+date")) {
                Seller seller = (Seller) LoginMenu.getLoginAccount();
                Sort.setNewArrayOfSalelog(seller.getSaleLogsHistory());
                Sort.saleLogSortDate();
            }
        }
    }

    private static boolean checkProductSale(String detail) {
        if (Product.isThereProductWithId(detail)) {
            if (Product.getProductById(productId).getSeller() == LoginMenu.getLoginAccount()) {
                if( Product.getProductById(productId).getInSale()) {
                    return true;
                }else outputNo=0;
            } else outputNo = 5;
        } else outputNo = 8;
        return false;
    }

    //-------------------------------------------------------------------------------
    //gson
    public static void processViewBalance() throws FileNotFoundException {
        OutputHandler.showBalance(LoginMenu.getLoginAccount().getUsername());
    }

}