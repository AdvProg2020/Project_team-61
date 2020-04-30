package controller.menus;

import controller.request.Request;
import model.accounts.Account;
import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;


public class ManagerMenu {
    private int outputNo;
    private int inputNo;
    private Account account;
    private Product product;
    private DiscountCode discountCode;
    private Category category;
    private Request request;
    private CommandProcessor commandProcessor;
    private RegisterMenu registerMenu;
    private DiscountCode editableDiscountCode;
    private Category editableCategory;
    private String field;
    private OutputHandler outputHandler = new OutputHandler();
    private int detailMenu = 0;
    private DiscountCode newDiscountCode;

    //----------------------------------------------------------------------
    //array
    public void processManageUsers() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEUSERS);
    }

    private boolean checkUsername(String username) {
        if (username.matches("^(?i)(?=.*[a-z])(?=.*[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
            if (account.isThereAccountWithUsername(username)) {
                return true;
            } else inputNo = 13;
        } else inputNo = 0;
        return false;
    }

    public void view(String username) {
        if (checkUsername(username)) {
            outputHandler.showAccount(account.getAccountWithUsername(username));
        } else outputHandler.showAccountOutput(inputNo);
    }

    public void deleteUser(String username) {
        if (checkUsername(username)) {
            account.deleteAccount(username);
            outputHandler.showOutputWithString(username, 1);
        }
        outputHandler.showAccountOutput(inputNo);
    }

    public void createManagerProfile() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.CREATEMANAGERACCOUNT);
        registerMenu.setManagerWant(true);
        outputHandler.showManagerMenuOutput(1);
    }
    //--------------------------------------------------------------------

    // array
    public void processManageAllProducts() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEALLPRODUCTS);
    }

    private boolean checkProduct(String productID) {
        if (productID.matches("")) {
            if (product.isThereProductWithId(productID)) {
                return true;
            } else inputNo = 3;
        } else inputNo = 2;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void removeProduct(String productID) {
        if (checkProduct(productID)) {
            product.deleteProduct(productID);
            outputHandler.showOutputWithString(productID, 2);
        }
    }
    //------------------------------------------------

    private boolean checkDiscountCode(String discountCodeID) {
        if (discountCodeID.matches("")) {
            if (discountCode.isThereDiscountWithId(discountCodeID)) {
                return true;
            } else inputNo = 5;
        } else inputNo = 4;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void processCreateDiscountCode() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.ADDDISCOUNTCODE);
        outputHandler.showAccountOutput(6);
    }
    public void createNewDiscountCode(String discountCodeId) {
        if (checkDiscountCode(discountCodeId)) {
            DiscountCode newDiscountCode = new DiscountCode(discountCodeId);
            commandProcessor.setMenuStatus(MenuStatus.CREATIONMENU);
            commandProcessor.setSubMenuStatus(SubMenuStatus.DETAILDESCOUNTCODE);
            outputHandler.showAccountOutput(7);
        }
    }
    public void setDetailToDiscountCode(String detail) {
        /*
        if (detailMenu == 0) {
            if (detail.matches("")) {
                newDiscountCode.setStartOfDiscountPeriod(detail);
                inputNo = 9;
                detailMenu = 1;
            } else inputNo = 8;
        } else if (detailMenu == 1) {
            if (detail.matches("")) {
                newDiscountCode.setEndOfDiscountPeriod(detail);
                inputNo = 11;
                detailMenu = 1;
            } else inputNo = 10;
        } else if (detailMenu == 2) {
            if (detail.matches("")) {
                newDiscountCode.setMaxDiscountAmount(detailMenu);
                inputNo = 13;
                detailMenu = 1;
            } else inputNo = 12;
        } else if (detailMenu == 3) {
            if (detail.matches("")) {
                newDiscountCode.setTotalTimesOfUse(Integer.parseInt(detail));
              //  inputNo = 15;
                detailMenu = 0;
            } else inputNo = 14;
        }*/
        commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
    }

    // array
    public void processViewDiscountCodes() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
    }

    public void viewDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            outputHandler.showDiscountCode(discountCode.getDiscountWithId(discountCodeID));
        }
    }

    public void editDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            editableDiscountCode = discountCode.getDiscountWithId(discountCodeID);
            commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
            outputHandler.showAccountOutput(15);
        }
    }
    public void discountCodeField(String field) {
        this.field = field;
        commandProcessor.setSubMenuStatus(SubMenuStatus.EDITDISCOUNTCODE);
        outputHandler.showOutputWithString(field,3);
    }
    ///????????ParseException
    public void editDiscountCodeField(String edit) {
        /*
        if (field.equalsIgnoreCase("start Of Discount Period")) {
            if (field.matches("")) {
                editableDiscountCode.setStartOfDiscountPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(edit));
                inputNo = 16;
            } else inputNo = 8;
        } else if (field.equalsIgnoreCase("end Of Discount Period")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setEndOfDiscountPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(edit));
                inputNo = 17;
            } else inputNo = 10;
        } else if (field.equalsIgnoreCase("max Discount Amount")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setMaxDiscountAmount(Double.parseDouble(edit));
                inputNo = 18;
            } else inputNo = 12;
        } else if (field.equalsIgnoreCase("total Times Of Use")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setTotalTimesOfUse(Integer.parseInt(edit));
                inputNo = 19;
            } else inputNo = 14;
        }*/
        commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        commandProcessor.setMenuStatus(MenuStatus.MANAGERMENU);
    }


    public void removeDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            discountCode.deleteDiscount(discountCodeID);
            outputHandler.showOutputWithString(discountCodeID,4);
        }
    }
    //-------------------------------------------------------------------------------------

    private boolean checkRequest(String requestID) {
        if (requestID.matches("")) {
            if (request.isThereRequestFromID(requestID)) {
                return true;
            } else inputNo = 21;
        } else inputNo = 20;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    /// array
    public void processManageRequests() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEREQUESTS);
    }

    public void detailsRequest(String requestID) {
        if (checkRequest(requestID)) {
            outputHandler.showRequest(request.getRequestFromID(requestID));
        }
    }

    public void acceptRequest(String requestID) {
        if (checkRequest(requestID)) {
            Request chosenRequest = request.getRequestFromID(requestID);
            chosenRequest.setRequestAccepted(true);
            chosenRequest.setRequestViewed(true);
            outputHandler.showOutputWithString(requestID,4);
        }
    }

    public void declineRequest(String requestID) {
        if (checkRequest(requestID)) {
            Request chosenRequest = request.getRequestFromID(requestID);
            chosenRequest.setRequestAccepted(false);
            chosenRequest.setRequestViewed(true);
            outputHandler.showOutputWithString(requestID,4);
        }
    }
    //------------------------------------------------------------------------------

    private boolean checkCategory(String category) {
        if (category.matches("")) {
             //if (category.isThereCategoryWithName(category)) {
            return true;
           // } else inputNo =23;
        } else inputNo = 22;
        return false;
    }

    // array
    public void processManageCategories() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATAGORIES);

    }

    public void editCategory(String category) {
        if(checkCategory(category)){
            //editableCategory = category.get
            commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);

        }
    }
    public void categoryField(String field) {
        this.field = field;
        commandProcessor.setSubMenuStatus(SubMenuStatus.EDITCATEGORY);
        outputHandler.showOutputWithString(field,3);
    }
    public void editCategoryField(String edit) {
        /*if (field.equalsIgnoreCase("trait")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }*/
    }

    public void addCategory(String category) {
        if (checkCategory(category)) {
            Category newCategory = new Category(category);
            commandProcessor.setSubMenuStatus(SubMenuStatus.DETAILCATEGORY);
           // outputHandler.showAccountOutput();
        }
    }
    public void setDetailToCategory(String detail){

       /*if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {

            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }else if (field.equalsIgnoreCase("")) {
            if (field.matches("")) {
            }
        }*/

    }


    public void removeCategory(String category) {
        if (checkCategory(category)) {
           // category.deleteCategory(category);
            outputHandler.showOutputWithString(category, 7);
        }
    }

}
