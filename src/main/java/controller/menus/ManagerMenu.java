package controller.menus;

import controller.request.Request;
import model.accounts.Account;
import model.filtar.Filter;
import model.off.DiscountCode;
import model.productRelated.Category;
import model.productRelated.Product;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class ManagerMenu {

    private static int outputNo;
    private static DiscountCode editableDiscountCode;
    private static Category editableCategory;
    private static String field;
    private static int detailMenu = 0;
    private static DiscountCode newDiscountCode;
    private static Category newCategory;

    public static String getField() {
        return field;
    }

    public static int getDetailMenu() {
        return detailMenu;
    }

    //gson
    public static void processManageUsers() throws FileNotFoundException {
        OutputHandler.showAccounts();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEUSERS);
    }

    private static boolean checkUsername(String username) {
        if (username.matches("^(?i)(?=.[a-z])(?=.[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
            if (Account.isThereAccountWithUsername(username)) {
                return true;
            } else outputNo = 35;
        } else outputNo = 32;
        return false;
    }

    //gson
    public static void view(String username) throws FileNotFoundException {
        if (checkUsername(username)) {
            OutputHandler.showAccountInformation(username);
        } else OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void deleteUser(String username) {
        if (checkUsername(username)) {
            Account.deleteAccount(username);
            OutputMassageHandler.showOutputWithString(username, 1);
        } else OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void createManagerProfile() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.CREATEMANAGERACCOUNT);
        RegisterMenu.setManagerWant(true);
        OutputMassageHandler.showManagerOutput(1);
    }
    //--------------------------------------------------------------------

    //gson
    public static void processManageAllProducts() throws FileNotFoundException {
        OutputHandler.showProducts(Filter.getNewArrayOfProductFilter());
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEALLPRODUCTS);
    }

    private static boolean checkProduct(String productID) {
        // if (productID.matches("")) {
        if (Product.isThereProductWithId(productID)) {
            return true;
        } else outputNo = 3;
        // } else outputNo = 2;

        return false;
    }

    public static void removeProduct(String productID) {
        if (checkProduct(productID)) {
            Product.deleteProduct(productID);
            OutputMassageHandler.showOutputWithString(productID, 2);
        }
        OutputMassageHandler.showAccountOutput(outputNo);
    }
    //------------------------------------------------

    private static boolean checkDiscountCode(String discountCodeID) {
        //if (discountCodeID.matches("")) {
        if (DiscountCode.isThereDiscountWithId(discountCodeID)) {
            return true;
        } else outputNo = 5;
        // } else outputNo = 4;
        return false;
    }

    public static void processCreateDiscountCode() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDDISCOUNTCODE);
        OutputMassageHandler.showAccountOutput(6);
    }

    public static void createNewDiscountCode(String discountCodeId) throws IOException {
        if (!DiscountCode.isThereDiscountWithId(discountCodeId)) {
            newDiscountCode = new DiscountCode(discountCodeId);
            CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.DETAILDESCOUNTCODE);
            outputNo = 7;
        } else outputNo = 25;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void setDetailToDiscountCode(String detail) throws ParseException {
        if (detailMenu == 0) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                LocalDateTime currentDate = LocalDateTime.now();
                //Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime inputDate = LocalDateTime.parse(detail, formatter);
                if (inputDate.isAfter(currentDate)) {
                    newDiscountCode.setStartOfDiscountPeriod(inputDate);
                    outputNo = 9;
                    detailMenu = 1;
                } else outputNo = 26;
            } else outputNo = 8;
        } else if (detailMenu == 1) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                LocalDateTime currentDate = LocalDateTime.now();
                //Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime inputDate = LocalDateTime.parse(detail, formatter);
                if (inputDate.isAfter(currentDate)) {
                    newDiscountCode.setEndOfDiscountPeriod(inputDate);
                    outputNo = 11;
                    detailMenu = 2;
                } else outputNo = 26;
            } else outputNo = 10;
        } else if (detailMenu == 2) {
            if (detail.matches("\\d+")) {
                newDiscountCode.setMaxDiscountAmount(Double.parseDouble(detail));
                outputNo = 13;
                detailMenu = 3;
            } else outputNo = 12;
        } else if (detailMenu == 3) {
            if (detail.matches("\\d+")) {
                newDiscountCode.setTotalTimesOfUse(Integer.parseInt(detail));
                outputNo = 35;
                detailMenu = 4;
            } else outputNo = 14;
        } else if (detailMenu == 4) {
            if (detail.matches("\\d+")) {
                newDiscountCode.setDiscountAmount(Integer.parseInt(detail));
                outputNo = 36;
                detailMenu = 5;
            } else outputNo = 28;
        } else if (detailMenu == 5) {
            if (detail.matches("\\d+")) {
                if (Account.isThereAccountWithUsername(detail)) {
                    newDiscountCode.addAccount(Account.getAccountWithUsername(detail));
                    outputNo = 37;
                    detailMenu = 0;
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                } else outputNo = 31;
            } else outputNo = 30;
        }
        OutputMassageHandler.showManagerOutput(outputNo);
    }

    // gson
    public static void processViewDiscountCodes() throws FileNotFoundException {
        OutputHandler.showDiscountCodes();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
    }

    //gson
    public static void viewDiscountCode(String discountCodeID) throws FileNotFoundException {
        OutputHandler.showDiscountCode(discountCodeID);
        if (checkDiscountCode(discountCodeID)) {
            OutputHandler.showDiscountCode(discountCodeID);
        } else OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void editDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            editableDiscountCode = DiscountCode.getDiscountWithId(discountCodeID);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
            outputNo = 15;
        } else OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void discountCodeField(String field) {
        if (field.matches("(?i)(?:start\\s+Of\\s+Discount\\s+Period|end\\s+Of\\s+Discount\\s+Period|remove\\s+account|add\\s+account|max\\s+Discount\\s+Amount|total\\s+Times\\s+Of\\s+Use|discount\\s+amount)")) {
            ManagerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITDISCOUNTCODE);
            OutputMassageHandler.showOutputWithString(field, 3);
        }
    }

    public static void editDiscountCodeField(String edit) throws ParseException {
        if (field.matches("(?i)start\\s+Of\\s+Discount\\s+Period")) {
            if (edit.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                LocalDateTime currentDate = LocalDateTime.now();
                //Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime inputDate = LocalDateTime.parse(edit, formatter);

                if (inputDate.isAfter(currentDate)) {
                    editableDiscountCode.setStartOfDiscountPeriod(inputDate);
                    outputNo = 16;
                } else outputNo = 26;
            } else outputNo = 8;
        } else if (field.matches("(?i)end\\s+Of\\s+Discount\\s+Period")) {
            if (edit.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                LocalDateTime currentDate = LocalDateTime.now();
                //Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime inputDate = LocalDateTime.parse(edit, formatter);
                if (inputDate.isAfter(currentDate)) {
                    editableDiscountCode.setEndOfDiscountPeriod(inputDate);
                    outputNo = 17;
                } else outputNo = 26;
            } else outputNo = 10;
        } else if (field.matches("(?i)max\\s+Discount\\s+Amount")) {
            if (edit.matches("\\d+\\.\\d*")) {
                editableDiscountCode.setMaxDiscountAmount(Double.parseDouble(edit));
                outputNo = 18;
            } else outputNo = 12;
        } else if (field.matches("(?i)total\\s+Times\\s+Of\\s+Use")) {
            if (edit.matches("\\d+")) {
                editableDiscountCode.setTotalTimesOfUse(Integer.parseInt(edit));
                outputNo = 19;
            } else outputNo = 14;
        } else if (field.matches("(?i)discount\\s+amount")) {
            if (edit.matches("\\d+")) {
                editableDiscountCode.setDiscountAmount(Integer.parseInt(edit));
                outputNo = 27;
            } else outputNo = 28;
        } else if (field.matches("(?i)add\\s+account")) {
            if (edit.matches("\\d+")) {
                if (Account.isThereAccountWithUsername(edit)) {
                    editableDiscountCode.addAccount(Account.getAccountWithUsername(edit));
                    outputNo = 29;
                } else outputNo = 31;
            } else outputNo = 30;
        } else if (field.matches("(?i)remove\\s+account")) {
            if (edit.matches("\\d+")) {
                if (Account.isThereAccountWithUsername(edit)) {
                    editableDiscountCode.removeAccount(Account.getAccountWithUsername(edit));
                    outputNo = 19;
                } else outputNo = 32;
            } else outputNo = 30;
        }
        OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void removeDiscountCode(String discountCodeID) {
        if (checkDiscountCode(discountCodeID)) {
            DiscountCode.deleteDiscount(discountCodeID);
            OutputMassageHandler.showOutputWithString(discountCodeID, 4);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }

    //-------------------------------------------------------------------------------------
    private static boolean checkRequest(String requestID) {
        // if (requestID.matches("")) {
        if (Request.isThereRequestFromID(requestID)) {
            return true;
        } else outputNo = 21;
        // } else outputNo = 20;
        return false;
    }

    /// gson
    public static void processManageRequests() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEREQUESTS);
        OutputHandler.showRequests();
    }

    public static void detailsRequest(String requestID) throws FileNotFoundException {
        if (checkRequest(requestID)) {
            OutputHandler.showRequest(requestID);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void acceptRequest(String requestID) throws IOException {
        if (checkRequest(requestID)) {
            Request.getRequestFromID(requestID).acceptRequest();
            OutputMassageHandler.showOutputWithString(requestID, 5);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void declineRequest(String requestID) {
        if (checkRequest(requestID)) {
            Request.getRequestFromID(requestID).declineRequest();
            OutputMassageHandler.showOutputWithString(requestID, 6);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }
    //------------------------------------------------------------------------------

    private static boolean checkCategory(String category) {
        //if (category.matches("")) {
        if (Category.isThereCategoryWithName(category)) {
            return true;
        } else outputNo = 23;
        // } else outputNo = 22;
        return false;
    }

    // gson
    public static void processManageCategories() throws FileNotFoundException {
        OutputHandler.showCategories();
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
    }

    public static void editCategory(String category) {
        if (checkCategory(category)) {
            editableCategory = Category.getCategoryWithName(category);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
            outputNo = 33;
        }
        OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void categoryField(String field) {
        if (field.matches("(?i)(?:add\\s*product|remove\\s*product|remove\\s*trait|add\\s*trait)")) {
            ManagerMenu.field = field;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITCATEGORY);
            OutputMassageHandler.showOutputWithString(field, 3);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void editCategoryField(String edit) {
        if (field.matches("remove\\s*trait")) {
            if (field.matches("\\D")) {
                editableCategory.removeTrait(edit);
                outputNo = 41;
            } else outputNo = 42;
        } else if (field.matches("add\\s*trait")) {
            if (field.matches("\\D")) {
                editableCategory.addTrait(edit);
                outputNo = 43;
            } else outputNo = 42;
        } else if (field.matches("(?i)remove\\s*product")) {
            if (edit.matches("")) {
                if (checkProduct(edit)) {
                    editableCategory.removeProductToCategory(Product.getProductById(edit));
                    outputNo = 40;
                }
            } else outputNo = 2;
        } else if (field.matches("(?i)add\\s*product")) {
            if (edit.matches("")) {
                if (checkProduct(edit)) {
                    editableCategory.addProductToCategory(Product.getProductById(edit));
                    outputNo = 39;
                }
            } else outputNo = 2;
        }
        OutputMassageHandler.showManagerOutput(outputNo);

    }

    public static void addCategory(String category) throws IOException {
        if (!(Category.isThereCategoryWithName(category))) {
            newCategory = new Category(category);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.DETAILCATEGORY);
            CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
            outputNo = 44;
        } else outputNo = 34;
        OutputMassageHandler.showManagerOutput(outputNo);
    }

    public static void setDetailToCategory(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("\\D+")) {
                if (!detail.equalsIgnoreCase("finish")) {
                    if (Product.isThereProductWithId(detail)) {
                        newCategory.addProductToCategory(Product.getProductById(detail));
                        outputNo = 39;
                    } else outputNo = 46;
                } else {
                    detailMenu = 1;
                    outputNo = 38;
                }
            } else outputNo = 2;
        } else if (detailMenu == 1) {
            if (detail.matches("\\D+")) {
                if (!detail.equalsIgnoreCase("finish")) {
                    newCategory.addTrait(detail);
                    outputNo = 43;
                } else {
                    CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                    Category.addKey();
                    detailMenu = 0;
                    outputNo = 45;
                }
            } else outputNo = 42;
        }
        OutputMassageHandler.showManagerOutput(outputNo);

    }

    public static void removeCategory(String category) {
        if (checkCategory(category)) {
            Category.deleteCategory(category);
            OutputMassageHandler.showOutputWithString(category, 7);
        } else OutputMassageHandler.showManagerOutput(outputNo);
    }

}