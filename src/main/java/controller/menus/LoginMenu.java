package controller.menus;

import controller.request.AccountRequest;
import controller.request.FirmRequest;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.AccountStatus;
import model.accounts.Seller;
import view.*;

import java.io.FileNotFoundException;

public class LoginMenu {
    private static int outputNo;
    private static Account loginAccount;
    private static String field = null;
    private static String username = null;
    private static boolean login = false;
    private static SubMenuStatus subMenuStatus;
    private static FirmRequest firmRequest;
    private static AccountRequest accountRequest;
    private static String firmName;



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
            if (username.matches("^(?i)(?=.[a-z])(?=.[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
                if (Account.isThereAccountWithUsername(username)) {
                    LoginMenu.username =username;
                    subMenuStatus = CommandProcessor.getSubMenuStatus();
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.PASSWORD);
                    outputNo = 2;
                } else outputNo = 13;
            } else outputNo = 0;
        } else outputNo = 24;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void checkPassword(String password) {
        if (password.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
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
            //if()
                menu = MenuStatus.SELLERMENU;
        }
        CommandProcessor.setMenuStatus(menu);
    }

    //gson
    public static void viewPersonalInfo() throws FileNotFoundException {
        //if(login) {
        OutputHandler.showAccountInformation(loginAccount.getUsername());
        CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
        // }outputHandler.showAccountOutput(25);
    }


    public static void processEdit(String field) {
        // if(login) {
        if (field.matches("(?i)(?:username|password|last\\s*name|email|phone\\s*number|firm)")) {
            if (loginAccount.getRole() == "seller") {
                String id = "seller " + LoginMenu.getLoginAccount().getUsername() + "wants edit account's " + field;
                if (Request.isThereRequestFromID(id)) {
                    Request.deleteRequest(id);
                }
                accountRequest = new AccountRequest(id);
                loginAccount.setAccountStatus(AccountStatus.UNDERREVIEWFOREDITING);
                CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITSELLERACCOUNT);
            } else {
                if (!field.equalsIgnoreCase("firm")) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
                } else outputNo = 27;
            }
            LoginMenu.field = field;
            OutputMassageHandler.showOutputWithString(field, 3);
        } else outputNo = 16;
        // }else outputNo = 25;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void editSellerField(String edit) {
        if (field.equalsIgnoreCase("password")) {
            if (edit.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
                accountRequest.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (field.equalsIgnoreCase("name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                accountRequest.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (field.matches("last\\s*name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                accountRequest.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (field.equalsIgnoreCase("Email")) {
            if (edit.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                accountRequest.setEmail(edit);
                outputNo = 20;
            } else outputNo = 9;
        } else if (field.matches("Phone\\s*number")) {
            if (edit.matches("09[0-9]{9}")) {
                accountRequest.setPhoneNo(Integer.parseInt(edit));
                outputNo = 21;
            } else outputNo = 11;
        } else if (field.equalsIgnoreCase("firm")) {
            CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMNAME);
            outputNo = 28;
        }
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public static void editAccount(String edit) {
        if (field.equalsIgnoreCase("password")) {
            if (edit.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
                loginAccount.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (field.equalsIgnoreCase("name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                loginAccount.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (field.matches("last\\s*name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                loginAccount.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (field.equalsIgnoreCase("Email")) {
            if (edit.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
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

    public static void firmField(String field) {
        if (!field.matches("(?i)(?:name|address|email|phone\\s*number)")) {
            String id = "seller " + LoginMenu.getLoginAccount().getUsername() + "wants edit firm " + firmName + "'s " + field;
            if (firmRequest.isThereRequestFromID(id)) {
                Request.deleteRequest(id);
            }
            firmRequest = new FirmRequest(id);
            firmRequest.setName(firmName);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITFIRM);
            LoginMenu.field = field;
            outputNo = 2;
        } else outputNo = 1;
        OutputMassageHandler.showFirmOutput(outputNo);
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

    public static boolean checkFirm() {
        if (loginAccount instanceof Seller) {
            if (((Seller) loginAccount).getFirm().getName().equalsIgnoreCase(firmName)) {
                return true;
            }
        }
        return false;
    }

    public static void editFirm(String detail) {
        if (field.matches("phone\\s*number")) {
            if (detail.matches("09[0-9]{9}")) {
                firmRequest.setPhoneNO(Double.parseDouble(detail));
                outputNo = 7;
            } else outputNo = 6;
        } else if (field.equalsIgnoreCase("address")) {
            if (detail.matches(".+")) {
                firmRequest.setAddress(detail);
                outputNo = 9;
            } else outputNo = 8;
        } else if (field.equalsIgnoreCase("email")) {
            if (detail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                firmRequest.setEmail(detail);
                CommandProcessor.setSubMenuStatus(subMenuStatus);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
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