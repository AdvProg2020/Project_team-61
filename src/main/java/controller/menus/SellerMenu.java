package controller.menus;

import controller.request.ProductRequest;
import controller.request.Request;
import controller.request.SaleRequest;
import model.accounts.Account;
import model.filtar.Filter;
import model.firms.Firm;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Category;
import model.productRelated.Product;
import model.productRelated.ProductStatus;
import view.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SellerMenu {

    private int outputNo;
    private String field;
    private int detailMenu = 0;
    private ProductRequest productRequest;
    private SaleRequest saleRequest;
    private String productId;
    private String offId;
    private Account loginAccount = LoginMenu.getLoginAccount();


    //gson
    public void processViewCompanyInformation() {
        OutputHandler.showFirmInformation();
    }

    //gson
    public void processViewSalesHistory() {
        OutputHandler.showSalesHistory();
    }

    //product--------------------------------------------------------------------------------------
    // manager // customer // seller
    private boolean checkProduct(String productID) {
        // if (productID.matches("((?!^ +$)^.+$)")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 1;
        //} else outputNo = 2;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //gson
    public void processManageProducts() {
        OutputHandler.showProducts(Filter.newArrayOfProductFilter);
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEPRODUCTS);

    }

    //gson
    public void viewProduct(String productID) {
        if (checkProduct(productID)) {
            OutputHandler.showProductsIds(Product.listOfId);
        }
    }

    //gson
    public void viewBuyersProduct(String productID) {
        if (checkProduct(productID)) {
            OutputHandler.showProductBuyers();
        }

    }

    public void editProduct(String productID) {
        Product product = Product.getProductById(productID);
        if (checkProduct(productID)) {
            if (product.ifProductHasSeller(productID, LoginMenu.getLoginAccount().getUsername())) {
                //  Product.deleteProduct(productID);
                //  Product product= new Product(productID);
                Product.getProductById(productID).setProductStatus(ProductStatus.UNDERREVIEWFOREDITING);
                productId = productID;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTFIELD);
                outputNo = 2;
            }
            outputNo = 0;
            OutputMassageHandler.showSellerOutput(outputNo);
        }
    }

    public void productField(String field) {
        if (field.matches("(?i)(?:Name|price|category|company\\s*Name|number\\s*Of\\s*Product)")) {
            String id = LoginMenu.getLoginAccount().getUsername() + " wants edit product " + productId + "'s " + field;
            if (productRequest.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            productRequest = new ProductRequest(id);
            productRequest.setSellerName(LoginMenu.getLoginAccount());
            productRequest.setProductId(productId);
            this.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITPRODUCT);
            outputNo = 3;
        } else outputNo = 0;
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public void editProductField(String edit) {
        if (field.equalsIgnoreCase("Name")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(edit);
                outputNo = 4;
            } else outputNo = 0;
        } else if (field.equalsIgnoreCase("price")) {
            if (edit.matches("[+-]?\\d*\\.?\\d+")) {
                productRequest.setPrice(Double.parseDouble(edit));
                outputNo = 5;
            } else outputNo = 0;
        } else if (field.equalsIgnoreCase("category")) {
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
        } else if (field.matches("(?i)number\\s*Of\\s*Product")) {
            if (edit.matches("(?<=\\s|^)\\d+(?=\\s|$)")) {
                productRequest.setNumberOfProduct(Integer.parseInt(edit));
                outputNo = 9;

            }
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public void processAddProduct() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDPRODUCT);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(10);
    }

    public void addProduct(String detail) {
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
                    detailMenu = 1;
                    outputNo = 11;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 1) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(detail);
                detailMenu = 1;
                outputNo = 12;
            } else outputNo = 0;
        } else if (detailMenu == 2) {
            if (detail.matches("[+-]?\\\\d*\\\\.?\\\\d+")) {
                productRequest.setPrice(Double.parseDouble(detail));
                detailMenu = 1;
                outputNo = 13;
            } else outputNo = 0;
        } else if (detailMenu == 3) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (Category.isThereCategoryWithName(detail)) {
                    productRequest.setCategoryName(Category.getCategoryWithName(detail));
                    detailMenu = 1;
                    outputNo = 15;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 4) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (Firm.isThereFirmWithID(detail)) {
                    if (LoginMenu.checkFirm()) {
                        productRequest.setCompanyName(Firm.getFirmWithID(detail));
                        detailMenu = 1;
                        outputNo = 16;
                    } else outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 5) {
            if (detail.matches("(?<=\\s|^)\\d+(?=\\s|$)")) {
                productRequest.setNumberOfProduct(Integer.parseInt(detail));
                detailMenu = 0;
                outputNo = 17;
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            } else outputNo = 0;
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }


    public void processRemoveProduct(String productID) {

        if (checkProduct(productID)) {
            if (Product.getProductById(productID).ifProductHasSeller(productID, LoginMenu.getLoginAccount().getUsername())) {
                Product.deleteProduct(productID);
                OutputMassageHandler.showAccountOutput(18);
            }
        }

    }
    //----------------------------------------------------------------------------------------

    //array
    public void processShowCategories() {
        OutputHandler.showCategories();
    }

    //---------------------------------------------------------------------------------------
    private boolean checkSale(String offID) {
        //if (offID.matches("")) {
        if (Sale.isThereSaleWithId(offID)) {
            return true;
        } else outputNo = 19;
        // } else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;

    }

    //array
    public void processViewOffs() {
        OutputHandler.showOffs();
    }

    public void viewOff(String offID) {
        if (checkSale(offID)) {
            OutputHandler.showOff();
        }

    }

    public void editOff(String offID) {
        if (checkSale(offID)) {
            if (Sale.getSeller() == loginAccount) {
                //   Sale sale = new Sale(offID);
                Sale.getSaleWithId(offID).setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
                this.offId = offId;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
                outputNo = 20;
            } else outputNo = 0;

        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public void offField(String field) {
        if (field.matches("(?i)(?:sale\\s*status|start\\s*of\\s*sale\\s*period|end\\s*of\\s*sale\\s*period|remove\\s*product|add\\s*product)")) {
            String id = loginAccount + " wants edit off " + offId + "'s " + field;
            if (Request.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            saleRequest = new SaleRequest(id);
            saleRequest.setOffId(offId);
            this.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSALE);
            outputNo = 21;
        }
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public void editOffField(String edit) {
        if (field.matches("(?i))start\\s*of\\s*sale\\s*period")) {
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
        OutputMassageHandler.showSellerOutput(outputNo);
    }

    public void addOff() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDSALE);
        CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(0);
    }

    public boolean checkSaleId(String detail) {
        if (!Sale.isThereSaleWithId(detail)) {
            Sale sale = new Sale(detail);
            sale.setSaleStatus(SaleStatus.UNDERREVIEWFORCONSTRUCTION);
            String id = "add sale: " + detail;
            if (saleRequest.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            saleRequest = new SaleRequest(id);
            saleRequest.setOffId(detail);
            saleRequest.setSeller(loginAccount);
            return true;
        }
        return false;
    }

    public void setDetailsToSale(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                if (checkSaleId(detail)) {
                    detailMenu = 1;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 1) {
            if (detail.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    detailMenu = 2;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 2) {
            if (detail.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    detailMenu = 3;
                    outputNo = 0;
                } else outputNo = 0;
            } else outputNo = 0;
        } else if (detailMenu == 3) {
            if (detail.matches("[1-9]|[1-9][1-9]|[100]")) {
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
        OutputMassageHandler.showSellerOutput(outputNo);

    }

    private boolean checkProductSale(String detail) {
        if (checkProduct(detail)) {
            if (Product.getProductById(productId).ifProductHasSeller(detail, loginAccount.getUsername())) {
                return true;
            }
        }
        return false;
    }

    //-------------------------------------------------------------------------------
    //gson
    public void processViewBalance() {
        OutputHandler.showBalance();
    }
}