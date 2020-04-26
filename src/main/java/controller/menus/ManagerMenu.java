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

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private String field;
    private OutputHandler outputHandler;
    private int detailMenu = 0;
    private  DiscountCode newDiscountCode;

    public void setField(String field) {
        this.field = field;
    }

    //array
    public void processManageUsers() {
    }

    private boolean checkUsername(String username) {
        if (username.matches("")) {
            if (account.isThereAccountWithUsername(username)) {
                return true;
            } else inputNo = 2;
        } else inputNo = 1;
        return false;
    }

    public void view(String username) {
        if (checkUsername(username)) {
            outputHandler.showAccount(account.getAccountWithUsername(username));
        }
        outputHandler.showOutput(inputNo);
    }

    public void deleteUser(String username) {
        if (checkUsername(username)) {
            account.deleteAccount(username);
            outputHandler.showOutputWithString(username, 1);
        }
        outputHandler.showOutput(inputNo);
    }

    public void createManagerProfile() {
        commandProcessor.setMenuStatus(MenuStatus.REGISTERMENUE);
        registerMenu.setManagerWant(true);
    }

    // array
    public void processManageAllProducts() {
    }

    private boolean checkProduct(String productID) {
        if (productID.matches("")) {
            if (product.isThereProductWithId(productID)) {
                return true;
            } else inputNo =;
        } else inputNo =;
        return false;
    }

    public void removeProduct(String productID) {
        if (checkProduct(productID)) {
            product.deleteProduct(productID);
            outputHandler.showOutputWithString(productID, 2);
        }
        outputHandler.showOutput(inputNo);
    }

    private boolean checkDiscountCode(String discountCodeID) {
        if (discountCodeID.matches("")) {
            if (discountCode.isThereDiscountWithId(discountCodeID)) {
                return true;
            } else inputNo =;
        } else inputNo =;
        return false;
    }

    public void processCreateDiscountCode() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.ADDDISCOUNTCODE);
    }

    public void createNewDiscountCode(String discountCodeId){
        DiscountCode newDiscountCode = new DiscountCode(discountCode);
        commandProcessor.setSubMenuStatus();
    }

    public void setDetailToDiscountCode(String detail){
        if(detailMenu==0){
            if(detail.matches("")){
                newDiscountCode.setStartOfDiscountPeriod(detail);
                inputNo =0;
            }else inputNo= 0;
        }else if(detailMenu==0){
            if(detail.matches("")){
                newDiscountCode.setEndOfDiscountPeriod(detail);
                inputNo =0;
            }else inputNo= 0;
        }else if(detailMenu==0){
            if(detail.matches("")){
                newDiscountCode.setMaxDiscountAmount(detailMenu);
                inputNo =0;
            }else inputNo= 0;
        }else if(detailMenu==0){
            if(detail.matches("")){
                newDiscountCode.setTotalTimesOfUse(Integer.parseInt(detail));
                inputNo =0;
            }else inputNo= 0;
        }
    }

    // array
    public void processViewDiscountCodes() {
    }

    public void viewDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            outputHandler.showDiscountCode(discountCode.getDiscountWithId(DiscountCodeID));
        }
        outputHandler.showOutput(inputNo);
    }

    public void editDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            editableDiscountCode = discountCode.getDiscountWithId(discountCodeID);
            commandProcessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
        }
        outputHandler.showOutput(inputNo);
    }

    ///????????ParseException
    public void editDiscountCodeField(String edit) throws ParseException {
        if (field.equalsIgnoreCase("start Of Discount Period")) {
            if (field.matches("")) {
                editableDiscountCode.setStartOfDiscountPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(edit));
            } else inputNo =;
        } else if (field.equalsIgnoreCase("end Of Discount Period")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setEndOfDiscountPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(edit));
            } else inputNo =;
        } else if (field.equalsIgnoreCase("max Discount Amount")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setMaxDiscountAmount(Double.parseDouble(edit));
            } else inputNo =;
        } else if (field.equalsIgnoreCase("total Times Of Use")) {
            if (field.equalsIgnoreCase("start Of Discount Period")) {
                editableDiscountCode.setTotalTimesOfUse(Integer.parseInt(edit));
            } else inputNo =;
        }
    }

    public void removeDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            discountCode.deleteDiscount(discountCodeID);
        }
        outputHandler.showOutput(inputNo);
    }

    private boolean checkRequest(String requestID) {
        if (requestID.matches("")) {
            if (request.isThereRequestFromID(requestID)) {
                return true;
            } else inputNo =;
        } else inputNo =;
        return false;
    }

    /// array
    public void processManageRequests() {
    }

    public void detailsRequest(String requestID) {
        if (checkRequest(requestID)) {
            outputHandler.showRequest(request.getRequestFromID(requestID));
        }
        outputHandler.showOutput(inputNo);
    }

    public void acceptRequest(String requestID) {
        if (checkRequest(requestID)) {
            request.setIsRequestAccepted(true);
            inputNo =;
        }
        outputHandler.showOutput(inputNo);
    }

    public void declineRequest(String requestID) {
        if (checkRequest(requestID)) {
            request.setIsRequestAccepted(false);
            inputNo =;
        }
        outputHandler.showOutput(inputNo);
    }

    private boolean checkCategory(String category){
        if (category.matches("")) {
           // if (category.isTh(category)) {
                return true;
            //} else inputNo =;
        } else inputNo =;
        return false;
    }

    // array
    public void processManageCategories() {

    }

    public void editCategory(String category) {

    }

    public void editCategoryField(String edit) {

    }

    public void addCategory(String category) {

    }

    public void removeCategory(String category) {

    }

}
