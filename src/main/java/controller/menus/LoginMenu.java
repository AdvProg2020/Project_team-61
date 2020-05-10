package controller.menus;

import model.accounts.Account;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputMassageHandler;
import view.SubMenuStatus;

public class LoginMenu {
    private int outputNo;
    private Account account;
    private static Account loginAccount;
    private CommandProcessor commandProcessor;
    private String field = null;
    private String username;
    private boolean login;
    private OutputMassageHandler outputHandler= new OutputMassageHandler();

    public boolean isLogin() {
        return login;
    }

    public static Account getLoginAccount() {
        return loginAccount;
    }

    public void processLogin(String username) {
        if (!login) {
            if (username.matches(" ")) {
                if (account.isThereAccountWithUsername(username)) {
                    commandProcessor.setSubMenuStatus(SubMenuStatus.PASSWORD);
                    outputNo = 2;
                } else outputNo = 14;
            } else outputNo = 0;
        } else outputNo = 24;
        outputHandler.showAccountOutput(outputNo);
    }

    public void checkPassword(String password) {
        if (password.matches(" ")) {
            if (account.isThereAccountWithUsernameAndPassword(username, password)) {
                loginAccount = account.getAccountWithUsername(username);
                login = true;
                findRole();
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
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
        commandProcessor.setMenuStatus(menu);
    }

    public void viewPersonalInfo() {
        if(login) {
            outputHandler.showAccount(loginAccount);
        }outputHandler.showAccountOutput(25);
    }

    public void processEdit(String field) {
        if(login) {
            if(!field.matches("")) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
                this.field = field;
                outputHandler.showOutputWithString(field, 3);
            }else outputNo = 16;
        }else outputNo = 25;
        outputHandler.showAccountOutput(outputNo);
    }

    public void editField(String edit) {
        if (this.field.equalsIgnoreCase("password")) {
            if (edit.matches("")) {
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
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            login = false;
            outputNo = 22;
        } else outputNo = 25;
        outputHandler.showAccountOutput(outputNo);
    }
}
