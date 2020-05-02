package controller.menus;

import model.accounts.Account;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;

public class LoginMenu {
    private int outputNo;
    private static Account loginAccount;
    private String field = null;
    private String username;
    private boolean login;
    private OutputHandler outputHandler= new OutputHandler();

    public static Account getLoginAccount() {
        return loginAccount;
    }

    public void processLogin(String username) {
        if (!login) {
            if (username.matches("^(?i)(?=.*[a-z])(?=.*[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
                if (Account.isThereAccountWithUsername(username)) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.PASSWORD);
                    outputNo = 2;
                } else outputNo = 14;
            } else outputNo = 0;
        } else outputNo = 24;
        outputHandler.showAccountOutput(outputNo);
    }

    public void checkPassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            if (Account.isThereAccountWithUsernameAndPassword(username, password)) {
                loginAccount = Account.getAccountWithUsername(username);
                login = true;
                findRole();
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                outputNo = 15;
            } else outputNo = 14;
        } else outputNo = 3;
        outputHandler.showAccountOutput(outputNo);
    }

    private void findRole() {
        String role = loginAccount.getRole();
        MenuStatus menu = null;
        if (role.equalsIgnoreCase("customer")) {
            menu = MenuStatus.CUSTOMERMENU;
        } else if (role.equalsIgnoreCase("manager")) {
            menu = MenuStatus.MANAGERMENU;
        } else if (role.equalsIgnoreCase("seller")) {
            menu = MenuStatus.SELLERMENU;
        }
        CommandProcessor.setMenuStatus(menu);
    }

    public void viewPersonalInfo() {
        //if(login) {
            outputHandler.showAccount(loginAccount);
            CommandProcessor.setSubMenuStatus(SubMenuStatus.EDIT);
       // }outputHandler.showAccountOutput(25);
    }

    public void processEdit(String field) {
       // if(login) {
            if(!field.matches("(?i)(?:username|password|last name|email|phone numbaer)")) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
                this.field = field;
                outputHandler.showOutputWithString(field, 3);
            }else outputNo = 16;
       // }else outputNo = 25;
        outputHandler.showAccountOutput(outputNo);
    }

    public void editField(String edit) {
        if (this.field.equalsIgnoreCase("password")) {
            if (edit.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                loginAccount.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (this.field.equalsIgnoreCase("name")) {
            if (edit.matches("")) {
                loginAccount.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (this.field.equalsIgnoreCase("lastname")) {
            if (edit.matches("")) {
                loginAccount.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (this.field.equalsIgnoreCase("Email")) {
            if (edit.matches("")) {
                loginAccount.setEmail(edit);
                outputNo = 20;
            } else outputNo = 9;
        } else if (this.field.equalsIgnoreCase("Phone number")) {
            if (edit.matches("")) {
                loginAccount.setPhoneNo(Integer.parseInt(edit));
                outputNo = 21;
            } else outputNo = 11;
        }
        outputHandler.showAccountOutput(outputNo);
    }

    public void processLogout() {
        if (login) {
            loginAccount = null;
            CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            login = false;
            outputNo = 22;
        } else outputNo = 25;
        outputHandler.showAccountOutput(outputNo);
    }
}
