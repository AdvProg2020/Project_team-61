package controller.menus;

import controller.request.AccountRequest;
import model.accounts.*;
import view.CommandProcessor;
import view.InternalMenu;
import view.OutputMassageHandler;
import view.SubMenuStatus;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegisterMenu {
    private static int outputNo;
    private static Manager manager;
    private static Customer customer;
    private static AccountRequest accountRequest;
    private static int detailMenu = 0;
    private static boolean managerWant = false;
    private static boolean headManager = true;
    private static String role;
    private static String username;
    private static String password;
    private static String name;
    private static String lastname;
    private static String Email;
    private static double phoneNo;
    private static Date birthdayDate;
    private static SubMenuStatus subMenuStatus;

    public static int getDetailMenu() {
        return detailMenu;
    }

    public static void setManagerWant(boolean managerWant) {
        RegisterMenu.managerWant = managerWant;
    }

    public static void processRegister(String role, String username) throws IOException {
        if (username.matches("^[a-z0-9_-]{3,15}$")) {
            if (!Account.isThereAccountWithUsername(username)) {
                if (role.matches("(?i)(?:customer|manager|seller)")) {
                    RegisterMenu.role = role;
                    RegisterMenu.username = username;
                    registerByRole(role, username);
                    subMenuStatus = CommandProcessor.getSubMenuStatus();
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.REGISTERATIONDETAILS);
                    CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
                } else outputNo = 26;
            } else outputNo = 1;
        } else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    private static void registerByRole(String role, String username) throws IOException {
        if (role.equalsIgnoreCase("customer")) {
            customer = new Customer(username);
            outputNo = 2;
        } else if (role.equalsIgnoreCase("manager")) {
            createManagerAccount(username);
        } else if (role.equalsIgnoreCase("seller")) {
            Seller seller = new Seller(username);
            seller.setAccountStatus(AccountStatus.UNDERREVIEWFORCONSTRUCTION);
            accountRequest = new AccountRequest(username + " wants seller account");
            outputNo = 2;
        }
        // OutputMassageHandler.showAccountOutput(outputNo);

    }

    private static void createManagerAccount(String username) throws IOException {
        if (managerWant || (headManager)) {
            manager = new Manager(username);
            headManager = false;
            managerWant = false;
            outputNo = 2;
        } else {
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            CommandProcessor.setSubMenuStatus(subMenuStatus);
            outputNo = 23;
        }
    }

    public static void completeRegisterProcess(String detail) throws IOException, ParseException {
        if (detailMenu == 0) {
            if (detail.matches(".+")) {
                password = detail;
                detailMenu++;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches(".+")) {
                name = detail;
                detailMenu++;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                lastname = detail;
                detailMenu++;
                outputNo = 8;
            } else outputNo = 7;
        } else if (detailMenu == 3) {
            if (detail.matches("^(.+)@(.+)$")) {
                Email = detail;
                detailMenu++;
                outputNo = 10;
            } else outputNo = 9;
        } else if (detailMenu == 4) {
            if (detail.matches("09[0-9]{9}")) {
                phoneNo = Double.parseDouble(detail);
                detailMenu = 5;
                outputNo = 29;
            } else outputNo = 11;
        } else if (detailMenu == 5) {
            if (detail.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")) {
                DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
                birthdayDate = format.parse(detail);
                detailMenu = 0;
                createAccountWithDetails();
            } else outputNo = 30;
        }OutputMassageHandler.showAccountOutput(outputNo);

    }

    public static void createAccountWithDetails() throws IOException {
        if (role.equalsIgnoreCase("seller")) {
            accountRequest.sellerAccountDetails(username, password, name, lastname, Email, phoneNo, birthdayDate);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.ADDFIRM);
            outputNo=31;
        } else if(role.equalsIgnoreCase("customer")) {
            customer.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate , null);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            outputNo = 12;
        }else if(role.equalsIgnoreCase("manager")) {
            manager.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate , null);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
            outputNo = 12;
        }
    }

    public static void createFirm(String detail) {
        if (detailMenu == 0) {
            if (detail.matches(".+")) {
                //String id = "seller " + LoginMenu.getLoginAccount().getUsername() + "wants firm " + detail;
                // if (!Request.isThereRequestFromID(id)) {
                //   firmRequest = new FirmRequest(id);
                accountRequest.setFirmName(detail);
                detailMenu++;
                outputNo = 14;
                //} else outputNo = 0;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches("09[0-9]{9}")) {
                accountRequest.setPhoneNo(Double.parseDouble(detail));
                //firmRequest.setPhoneNO(Double.parseDouble(detail));
                detailMenu++;
                outputNo = 16;
            } else outputNo = 6;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                accountRequest.setFirmAddress(detail);
                // firmRequest.setAddress(detail);
                detailMenu = 3;
                outputNo = 19;
            } else outputNo = 8;
        }else if (detailMenu == 3) {
            if (detail.matches("(?i)(?:company|factory|workshop)")) {
                accountRequest.setFirmType(detail);
                detailMenu = 4;
                outputNo = 15;
            } else outputNo = 18;
        } else if (detailMenu == 4) {
            if (detail.matches("(?:[a-z0-9!#$%&'+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'+/=?^_`{|}~-]+)|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])\")@(?:(?:[a-z0-9](?:[a-z0-9-][a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-][a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                //firmRequest.setEmail(detail);
                accountRequest.setFirmEmail(detail);
                detailMenu = 0;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                outputNo = 17;
            } else outputNo = 10;
        }OutputMassageHandler.showFirmOutput(outputNo);
    }

    public static void receiverInformation(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("\\d+")) {
                LoginMenu.getLoginAccount().setCurrentPhoneNo(Double.parseDouble(detail));
                detailMenu = 1;
                outputNo = 2;
            } else outputNo = 1;
        } else if (detailMenu == 1) {
            if (detail.matches("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.")) {
                LoginMenu.getLoginAccount().setAddress(detail);
                detailMenu = 2;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 2) {
            if (detail.matches("(?i)(?:yes|no)")) {
                if(detail.equalsIgnoreCase("yes")){
                    LoginMenu.getLoginAccount().setFast(true);
                }else{
                    LoginMenu.getLoginAccount().setFast(false);
                }
                detailMenu = 0;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.HAVEDISCOUNT);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                outputNo = 6;
            } else outputNo = 5;
        }
        OutputMassageHandler.showReceiverInfo(outputNo);

    }


}