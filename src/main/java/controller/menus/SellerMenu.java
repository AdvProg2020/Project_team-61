package controller.menus;

import controller.request.ProductRequest;
import controller.request.SaleRequest;
import model.filtar.Filter;
import model.off.Sale;
import model.off.SaleStatus;
import model.productRelated.Product;
import view.*;
import model.accounts.Seller;

import java.util.Date;


public class SellerMenu {

    private int outputNo;
    private String field;
    private int detailMenu = 0;
    private ProductRequest productRequest;
    private SaleRequest saleRequest;


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
        commandprocessor.setSubMenuStatus(SubMenuStatus.MANAGEPRODUCTS);

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
        if (checkProduct(productID)) {
            String id = LoginMenu.getLoginAccount().getUsername() + "wants edit product " + productID;
            if(!(productRequest.isThereRequestFromID(id))) {
                productRequest = new ProductRequest(id);
                commandprocessor.setSubMenuStatus(SubMenuStatus.PRODUCTFIELD);
              outputNo=2;
            }outputNo=0;
            OutputMassageHandler.showSellerOutput(outputNo);
        }
    }

    public void productField(String field) {
        if (field.matches("(?i)(?:seller||||)")) {
            this.field = field;
            commandprocessor.setSubMenuStatus(SubMenuStatus.EDITPRODUCT);
            OutputMassageHandler.showSellerOutput(3);
        }
    }

    public void editProductField(String edit) {
        if (field.equalsIgnoreCase("Name")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(edit);
                outputNo = 4;
            }
        } else if (field.equalsIgnoreCase("price")) {
            if (edit.matches("[+-]?\\\\d*\\\\.?\\\\d+")) {
                productRequest.setPrice(Double.parseDouble(edit));
                outputNo = 5;
            }
        } else if (field.equalsIgnoreCase("category")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setCategoryName(edit);
                outputNo = 5;
            }
        } else if (field.equalsIgnoreCase("seller")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setSellerName(edit);
                outputNo = 7;
            }
        } else if (field.equalsIgnoreCase("companyName")) {
            if (edit.matches("^(?!\\s*$).+")) {
                productRequest.setCompanyName(edit);
                outputNo = 8;
            }
        } else if (field.equalsIgnoreCase("numberOfProduct")) {
            if (edit.matches("(?<=\\s|^)\\d+(?=\\s|$)")) {
                productRequest.setNumberOfProduct(Integer.parseInt(edit));
                outputNo = 9;

            }
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }

    public void processAddProduct() {
        commandprocessor.setSubMenuStatus(SubMenuStatus.ADDPRODUCT);
        commandprocessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(10);
    }

    public void addProduct(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                String id =LoginMenu.getLoginAccount().getUsername() + "wants add product " + detail;
                if(!(productRequest.isThereRequestFromID(id))) {
                    productRequest = new ProductRequest(id);
                    productRequest.setProductId(detail);
                    outputNo = 11;
         ///////////////////////////////////////////////shomare
                }else outputNo=0;
            }
        } else if (detailMenu == 1) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setProductName(detail);
                outputNo = 12;
            }
        } else if (detailMenu == 2) {
            if (detail.matches("[+-]?\\\\d*\\\\.?\\\\d+")) {
                productRequest.setPrice(Double.parseDouble(detail));
                outputNo = 13;
            }
        } else if (detailMenu == 3) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setCategoryName(detail);
                outputNo = 14;
            }
        } else if (detailMenu == 4) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setSellerName(detail);
                outputNo = 15;
            }
        } else if (detailMenu == 5) {
            if (detail.matches("^(?!\\s*$).+")) {
                productRequest.setCompanyName(detail);
                outputNo = 16;
            }
        } else if (detailMenu == 6) {
            if (detail.matches("(?<=\\s|^)\\d+(?=\\s|$)")) {
                productRequest.setNumberOfProduct(Integer.parseInt(detail));
                detailMenu = 0;
                outputNo = 17;
                commandprocessor.setInternalMenu(InternalMenu.MAINMENU);
                commandprocessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            }
        }
        OutputMassageHandler.showSellerOutput(outputNo);

    }


    public void processRemoveProduct(String productID) {
        if (checkProduct(productID)) {
            if() {
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
            if() {
                saleRequest = new SaleRequest("edit off " + offID);
                saleRequest.setOffId(offID);
                commandprocessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
                OutputMassageHandler.showSellerOutput(20);
            }else

        }
    }

    public void offField(String field) {
        if (field.matches("(?i)(?:sale status|start of sale period|end of sale period|seller)")) {
            this.field = field;
            commandprocessor.setSubMenuStatus(SubMenuStatus.EDITSALE);
            OutputMassageHandler.showSellerOutput(21);
        }
    }

    public void editOffField(String edit) {
        if (field.equalsIgnoreCase("sale status")) {
            if (edit.matches("CONFIRMED")) {
                saleRequest.setSaleStatus(SaleStatus.CONFIRMED);
            } else if (edit.matches("UNDERREVIEWFOREDITING")) {
                saleRequest.setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
            } else {
                saleRequest.setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
            }
        } else if (field.equalsIgnoreCase("start of sale period")) {
            if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                }
            } else if (field.equalsIgnoreCase("end of sale period")) {
                if (edit.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                    Date currentDate = new Date();
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(edit);
                    if (inputDate.after(currentDate)) {
                        saleRequest.setEndOfSalePeriod(inputDate);
                    }
                }
            } else if (field.equalsIgnoreCase("sale amount")) {
                if (edit.matches("\\d+\\.?\\d*")) {
                    saleRequest.setSaleAmount(Integer.parseInt(edit));
                }
            } else if (field.equalsIgnoreCase("seller")) {
                if (edit.matches("\\D+")) {
                    //convert seller to string!!!
                    saleRequest.setSeller(LoginMenu.getLoginAccount());
                }

            }
        }


    }

    public void addOff() {
        commandprocessor.setSubMenuStatus(SubMenuStatus.ADDSALE);
        commandprocessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        OutputMassageHandler.showSellerOutput(0);
    }

    public void setDetailsToSale(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?!\\s*$).+")) {
                String id = "add sale: " + detail;
                if(saleRequest.isThereRequestFromID(id)) {
                    saleRequest = new SaleRequest("add sale: " + detail);
                    saleRequest.setOffId(detail);
                    outputNo = 0;
                }else outputNo=0;
            }
        } else if (detailMenu == 1) {
            if (detail.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setStartOfSalePeriod(inputDate);
                    outputNo = 0;
                } else outputNo = 0;
            }
        } else if (detailMenu == 2) {
            if (detail.matches("([0-2][0-9]|3[0-1])/([0-9]|1[0-2])/20[0-5][0-9]")) {
                Date currentDate = new Date();
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                if (inputDate.after(currentDate)) {
                    saleRequest.setEndOfSalePeriod(inputDate);
                    outputNo = 0;
                } else outputNo = 0;
            }
        } else if (detailMenu == 3) {
            if (detail.matches("\\d+")) {
                saleRequest.setSaleAmount(Integer.parseInt(detail));
            }
        } else if (detailMenu == 3) {
            if (detail.matches("\\D+")) {

                if (detail.equals(SaleStatus.CONFIRMED)) {
                    saleRequest.setSaleStatus(SaleStatus.CONFIRMED);
                } else if (detail.equals(SaleStatus.UNDERREVIEWFORCONSTRUCTION)) {
                    saleRequest.setSaleStatus(SaleStatus.UNDERREVIEWFORCONSTRUCTION);
                } else {
                    saleRequest.setSaleStatus(SaleStatus.UNDERREVIEWFOREDITING);
                }
                detailMenu = 0;
                commandprocessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                commandprocessor.setInternalMenu(InternalMenu.MAINMENU);
            }
        }

    }

    //-------------------------------------------------------------------------------
    //gson
    public void processViewBalance() {
        OutputHandler.showBalance();
    }
}