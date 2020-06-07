package controller.menus;

import controller.request.AccountRequest;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.Seller;
import model.firms.Firm;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginMenu {
    private static int outputNo;
    private static Account loginAccount;
    private static String field = null;
    private static String username = null;
    private static boolean login = false;
    private static SubMenuStatus subMenuStatus;
    private static AccountRequest accountRequest;
    private static Firm firm;
    private static String firmName;

    public static Firm getFirm() {
        return firm;
    }

    public static String getField() {
        return field;
    }

    public static Account getLoginAccount() {
        return loginAccount;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void processLogin(String username) {
        if (!login) {
            if (username.matches(".+")) {
                if (Account.isThereAccountWithUsername(username)) {
                    LoginMenu.username = username;
                    subMenuStatus = CommandProcessor.getSubMenuStatus();
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.PASSWORD);
                    outputNo = 2;
                } else outputNo = 13;
            } else outputNo = 32;
        } else outputNo = 24;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void checkPassword(String password) {
        if (password.matches(".+")) {
            if (Account.isThereAccountWithUsernameAndPassword(username, password)) {
                loginAccount = Account.getAccountWithUsername(username);
                login = true;
                findRole();
                CommandProcessor.setSubMenuStatus(subMenuStatus);
            } else outputNo = 14;
        } else outputNo = 3;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    private static void findRole() {
        String role = loginAccount.getRole();
        MenuStatus menu = null;
        if (role.equalsIgnoreCase("customer")) {
            outputNo = 15;
            menu = MenuStatus.CUSTOMERMENU;
        } else if (role.equalsIgnoreCase("manager")) {
            outputNo = 15;
            menu = MenuStatus.MANAGERMENU;
        } else if (role.equalsIgnoreCase("seller")) {
            menu = MenuStatus.SELLERMENU;
            outputNo = 15;
        }
        CommandProcessor.setMenuStatus(menu);
    }

    //gson
    public static void viewPersonalInfo() throws FileNotFoundException {
        OutputHandler.showAccountInformation(loginAccount.getUsername());
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
    }

    public static void processEdit(String field) throws IOException {
        if (field.matches("(?i)(?:username|password|last\\s*name|email|phone\\s*number|firm)")) {
            if (loginAccount.getRole() == "seller") {
                String id = "seller " + LoginMenu.getLoginAccount().getUsername() + "wants edit account's " + field;
                if (accountRequest.isThereRequestFromID(id)) {
                    accountRequest = new AccountRequest(id);
                    accountRequest.setLastname(LoginMenu.getLoginAccount().getUsername());
                    accountRequest.setFirmName(firm.getName());
                }else accountRequest= (AccountRequest) Request.getRequestFromID(id);
                if(field.equalsIgnoreCase("firm")){
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMNAME);
                    outputNo = 28;
                }else {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSELLERACCOUNT);
                    outputNo = 34;
                }
            } else {
                if (!field.equalsIgnoreCase("firm")) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
                    outputNo = 34;
                } else outputNo = 27;
            }
            LoginMenu.field = field;
            // OutputMassageHandler.showOutputWithString(field, 3);
        } else outputNo = 16;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void editAccount(String edit) throws IOException {
        if (field.equalsIgnoreCase("password")) {
            if (edit.matches(".+")) {
                loginAccount.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (field.equalsIgnoreCase("name")) {
            if (edit.matches(".+")) {
                loginAccount.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (field.matches("last\\s*name")) {
            if (edit.matches(".+")) {
                loginAccount.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (field.equalsIgnoreCase("Email")) {
            if (edit.matches("^(.+)@(.+)$")) {
                loginAccount.setEmail(edit);
                outputNo = 20;
            } else outputNo = 9;
        } else if (field.matches("Phone\\s*number")) {
            if (edit.matches("09[0-9]{9}")) {
                loginAccount.setPhoneNo(Integer.parseInt(edit));
                outputNo = 21;
            } else outputNo = 11;
        }
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void editSellerField(String edit) throws IOException {
        if (field.equalsIgnoreCase("password")) {
            if (edit.matches(".+")) {
                accountRequest.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (field.equalsIgnoreCase("name")) {
            if (edit.matches(".+")) {
                accountRequest.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (field.matches("last\\s*name")) {
            if (edit.matches(".+")) {
                accountRequest.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (field.equalsIgnoreCase("Email")) {
            if (edit.matches("^(.+)@(.+)$")) {
                accountRequest.setEmail(edit);
                outputNo = 20;
            } else outputNo = 9;
        } else if (field.matches("Phone\\s*number")) {
            if (edit.matches("09[0-9]{9}")) {
                accountRequest.setPhoneNo(Integer.parseInt(edit));
                outputNo = 21;
            } else outputNo = 11;
        }
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static boolean checkFirm() {
        if (loginAccount instanceof Seller) {
            if (((Seller) loginAccount).getFirm().getName().equalsIgnoreCase(firmName)) {
                firm=((Seller) loginAccount).getFirm();
                return true;
            }
        }
        return false;
    }

    public static void firmName(String name) {
        if (name.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
            if (checkFirm()) {
                firmName = name;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMFIELD);
                outputNo = 5;
            } else outputNo = 12;
        } else outputNo = 3;
        OutputMassageHandler.showFirmOutput(outputNo);
    }

    public static void firmField(String field) throws IOException {
        if (!field.matches("(?i)(?:name|address|email|phone\\s*number)")) {
            accountRequest.setFirmName(firmName);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITFIRM);
            LoginMenu.field = field;
            outputNo = 2;
        } else outputNo = 1;
        OutputMassageHandler.showFirmOutput(outputNo);
    }

    public static void editFirm(String detail) throws IOException {

        if (field.matches("phone\\s*number")) {
            if (detail.matches("09[0-9]{9}")) {
                accountRequest.setFirmPhoneNO(Double.parseDouble(detail));
                outputNo = 7;
            } else outputNo = 6;
        } else if (field.equalsIgnoreCase("address")) {
            if (detail.matches(".+")) {
                accountRequest.setFirmAddress(detail);
                outputNo = 9;
            } else outputNo = 8;
        } else if (field.equalsIgnoreCase("email")) {
            if (detail.matches("^(.+)@(.+)$")) {
                accountRequest.setFirmEmail(detail);
                outputNo = 11;
            } else outputNo = 10;
        }
    }

    public static void processLogout() {
        if (login) {
            loginAccount = null;
            login = false;
            CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            outputNo = 22;
        } else outputNo = 25;
        OutputMassageHandler.showAccountOutput(outputNo);
    }
}