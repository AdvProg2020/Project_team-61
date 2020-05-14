package controller.menus;

import controller.request.AccountRequest;
import controller.request.FirmRequest;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import view.CommandProcessor;
import view.*;

import javax.xml.crypto.Data;


public class RegisterMenu {
    private int outputNo;
    private Manager manager;
    private Customer customer;
    private AccountRequest accountRequest;
    private FirmRequest firmRequest;
    private int detailMenu = 0;
    private  static boolean managerWant = false;
    private boolean headManager = true;
    private String role;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String Email;
    private double phoneNo;
    private Data birthdayDate;
    private SubMenuStatus subMenuStatus;

    public static void setManagerWant(boolean managerWant) {
        RegisterMenu.managerWant = managerWant;
    }

    public void processRegister(String role, String username) {
        if (username.matches("^(?i)(?=.[a-z])(?=.[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
            if (!Account.isThereAccountWithUsername(username)) {
                if (role.matches("(?i)(?:customer|manager|seller)")) {
                    this.role = role;
                    this.username = username;
                    registerByRole(role, username);
                    subMenuStatus = CommandProcessor.getSubMenuStatus();
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.REGISTERATIONDETAILS);
                    CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
                } else outputNo = 26;
            } else outputNo = 1;
        } else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
    }


    private void registerByRole(String role, String username) {
        if (role.equalsIgnoreCase("customer")) {
            customer = new Customer(username);
            outputNo = 2;
        } else if (role.equalsIgnoreCase("manager")) {
            createManagerAccount(username);
        } else if (role.equalsIgnoreCase("seller")) {
            accountRequest = new AccountRequest(username + " wants seller account");
            outputNo = 2;
        }
        OutputMassageHandler.showAccountOutput(outputNo);

    }

    private void createManagerAccount(String username) {
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

    public void completeRegisterProcess(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
                this.password = detail;
                detailMenu++;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                this.name = detail;
                detailMenu++;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                this.lastname = detail;
                detailMenu++;
                outputNo = 8;
            } else outputNo = 7;
        } else if (detailMenu == 3) {
            if (detail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                this.Email = detail;
                detailMenu++;
                outputNo = 10;
            } else outputNo = 9;
        } else if (detailMenu == 4) {
            if (detail.matches("09[0-9]{9}")) {
                this.phoneNo = Double.parseDouble(detail);
                detailMenu = 5;
                outputNo = 12;
            } else outputNo = 11;
        }else if (detailMenu == 5) {
            if (detail.matches("")) {
                this.birthdayDate = Double.parseDouble(detail);
                detailMenu = 0;
                createAccountWithDetails();
                outputNo = 0;
            } else outputNo = 0;
        }

    }

    public void createAccountWithDetails() {
        if (role.equalsIgnoreCase("seller")) {
            accountRequest.sellerAccountDetails(username, password, name, lastname, Email, phoneNo, birthdayDate);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMINFORMATION);
        } else if (role.equalsIgnoreCase("customer")) {
            customer.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate);
            CommandProcessor.setSubMenuStatus(subMenuStatus);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        } else if (role.equalsIgnoreCase("customer")) {
            manager.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate);
            CommandProcessor.setSubMenuStatus(subMenuStatus);
            CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        }
    }

    public void createFirm(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                String id = "seller "+ LoginMenu.getLoginAccount().getUsername()+ "wants firm "+ detail ;
                if(!firmRequest.isThereRequestFromID(id)) {
                    firmRequest = new FirmRequest(id);
                    firmRequest.setName(detail);
                    detailMenu++;
                    outputNo = 4;
                }else outputNo = 0;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches("09[0-9]{9}")) {
                firmRequest.setPhoneNO(Double.parseDouble(detail));
                detailMenu++;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                firmRequest.setAddress(detail);
                detailMenu = 3;
                outputNo = 8;
            } else outputNo = 7;
        }else if (detailMenu == 3) {
            if (detail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                firmRequest.setEmail(detail);
                detailMenu = 0;
                CommandProcessor.setSubMenuStatus(subMenuStatus);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                outputNo = 0;
            } else outputNo = 0;
        }
    }

    //******************************************************
    public void receiverInformation(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("\\d+")) {
                LoginMenu.getLoginAccount().setCurrentPhoneNo(Double.parseDouble(detail));
                detailMenu = 1;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.")) {
                LoginMenu.getLoginAccount().setAddress(detail);
                detailMenu = 2;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                this.phoneNo = Double.parseDouble(detail);
                detailMenu = 0;
                CommandProcessor.setSubMenuStatus(SubMenuStatus.HAVEDISCOUNT);
                CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                outputNo = 12;
            } else outputNo = 11;
        }

    }



}