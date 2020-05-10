package controller.menus;

import controller.request.AccountRequest;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import view.*;


public class RegisterMenu {
    private int outputNo;
    private Request request;
    private int detailMenu = 0;
    private boolean managerWant = false;
    private boolean headManager = true;
    private OutputMassageHandler outputMassageHandler = new OutputMassageHandler();
    private AccountRequest accountRequest;
    private String role;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String Email;
    private double phoneNo;
    private Account account;

    public void processRegister(String role, String username) {
        if (username.matches("^(?i)(?=.[a-z])(?=.[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
            if (!Account.isThereAccountWithUsername(username)) {
                if (role.matches(".+")) {
                    this.role = role;
                    this.username = username;
                    registerByRole(role, username);
                    commandProcessor.setSubMenuStatus(SubMenuStatus.REGISTERATIONDETAILS);
                    commandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
                    outputNo = 2;
                } else outputNo = 26;
            } else outputNo = 1;
        } else outputNo = 0;
        outputMassageHandler.showAccountOutput(outputNo);
    }

    ///////////////// go back to menu for seller request
    private void registerByRole(String role, String username) {
        if (role.equalsIgnoreCase("customer")) {
            Customer newCustomer = new Customer(username);
        } else if (role.equalsIgnoreCase("manager")) {
            createManagerAccount(username);

        } else if (role.equalsIgnoreCase("seller")) {
            AccountRequest newRequest = new AccountRequest(username + " wants seller account");
        }

    }

    private void createManagerAccount(String username) {
        if (managerWant || (headManager)) {
            Manager newManager = new Manager(username);
            headManager = false;
            managerWant = false;
        } else {
            //???????
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            outputNo = 23;
        }
    }

    /*private void createSellerAccount(String username) {
        String sellerAccountRequest = username + " wants seller account";
        if (request.isThereRequestFromID(sellerAccountRequest)) {
            if (request.isRequestViewed()) {
                if (request.isRequestAccepted()) {
                    Seller newSeller = new Seller(username);
                } else outputNo = 28;
            } else outputNo = 29;
        } else {
            Request newRequest = new Request(sellerAccountRequest);
            outputNo = 27;
        }
        outputHandler.showAccountOutput(outputNo);
    }*/


    public void completeRegisterProcess(String detail) {
        if (detailMenu == 0) {
            if (detail.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
                this.password = detail;
                detailMenu++;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches(".+")) {
                this.name = detail;
                detailMenu++;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                this.lastname = detail;
                detailMenu++;
                outputNo = 8;
            } else outputNo = 7;
        } else if (detailMenu == 3) {
            if (detail.matches(".+")) {
                this.Email = detail;
                detailMenu++;
                outputNo = 10;
            } else outputNo = 9;
        } else if (detailMenu == 4) {
            if (detail.matches(".+")) {
                this.phoneNo = Double.parseDouble(detail);
                detailMenu = 0;
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                commandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                createAccountWithDetails();
                outputNo = 12;
            } else outputNo = 11;
        }

    }

    public void createAccountWithDetails(){
        if (!(role.equalsIgnoreCase("seller"))) {
            account.setDetailsToAccount( password, name, lastname, Email, phoneNo);
        }
        else {
            accountRequest.sellerAccountDetails(username, password, name, lastname, Email, phoneNo);
        }
    }

    //******************************************************88
    public void receiverInformation(String detail){
        if (detailMenu == 0) {
            if (detail.matches("\\d+")) {
                LoginMenu.getLoginAccount().setCurrentPhoneNo(Double.parseDouble(detail));
                detailMenu =1;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches(".+")) {
                LoginMenu.getLoginAccount().setAddress(detail);
                detailMenu=2;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                this.phoneNo = Double.parseDouble(detail);
                detailMenu = 0;
                 commandProcessor.setMenuStatus(MenuStatus.DISCOUNTCODE);
                commandProcessor.setInternalMenu(InternalMenu.MAINMENU);
                createAccountWithDetails();
                outputNo = 12;
            } else outputNo = 11;
        }

    }




    public static void setManagerWant(boolean managerWant) {
        managerWant = managerWant;
    }
}