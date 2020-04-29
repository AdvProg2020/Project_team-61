package controller.menus;

import controller.request.Request;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;


public class RegisterMenu {
    private int outputNo;
    private Account account;
    private Request request;
    private CommandProcessor commandProcessor;
    private int detailMenu = 0;
    private boolean managerWant = false;
    private boolean headManager = true;
    private OutputHandler outputHandler = new OutputHandler();
    private String username;
    private String role;

    public void processRegister(String role, String username) {
        if (username.matches(".+")) {
            if (!account.isThereAccountWithUsername(username)) {
                if (role.matches(".+")) {
                    this.username = username;
                    this.role = role;
                    commandProcessor.setSubMenuStatus(SubMenuStatus.REGISTERATIONDETAILS);
                    outputNo = 2;
                } else outputNo = 26;
            } else outputNo = 1;
        } else outputNo = 0;
        outputHandler.showAccountOutput(outputNo);
    }

    ///////////////// go back to menu for seller request
    private void registerByRole(String role, String username) {
        if (role.equalsIgnoreCase("customer")) {
            Customer newCustomer = new Customer(username);
        } else if (role.equalsIgnoreCase("manager")) {
            createManagerAccount(username);

        } else if (role.equalsIgnoreCase("seller")) {
            createSellerAccount(username);
        }

    }

    private void createManagerAccount(String username) {
        if (managerWant || (!headManager)) {
            Manager newManager = new Manager(username);
            headManager = false;
            managerWant = false;
        } else {
            //???????
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            outputNo = 23;
        }
    }

    private void createSellerAccount(String username) {
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
    }


    public void completeRegisterProcess(String detail) {
        registerByRole(role, username);
        if (detailMenu == 0) {
            if (detail.matches(".+")) {
                account.setPassword(detail);
                detailMenu++;
                outputNo = 4;
            } else outputNo = 3;
        } else if (detailMenu == 1) {
            if (detail.matches(".+")) {
                account.setName(detail);
                detailMenu++;
                outputNo = 6;
            } else outputNo = 5;
        } else if (detailMenu == 2) {
            if (detail.matches(".+")) {
                account.setLastname(detail);
                detailMenu++;
                outputNo = 8;
            } else outputNo = 7;
        } else if (detailMenu == 3) {
            if (detail.matches(".+")) {
                account.setEmail(detail);
                detailMenu++;
                outputNo = 10;
            } else outputNo = 9;
        } else if (detailMenu == 4) {
            if (detail.matches(".+")) {
                account.setPhoneNo(detailMenu);
                detailMenu = 0;
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                outputNo = 12;
            } else outputNo = 11;
        }
    }

    public void setManagerWant(boolean managerWant) {
        this.managerWant = managerWant;
    }
}
