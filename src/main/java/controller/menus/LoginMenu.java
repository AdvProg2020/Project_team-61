package controller.menus;

import model.accounts.Account;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.OutputMassageHandler;
import view.SubMenuStatus;

public class LoginMenu {
    private int outputNo;
    private static Account loginAccount;
    private String field = null;
    private String username = null;
    private boolean login = false;
    private SubMenuStatus subMenuStatus;
    private CommandProcessor commandProcessor ;

    public static Account getLoginAccount() {
        return loginAccount;
    }

    public static boolean isLogin() {
        return login;
    }

    public void processLogin(String username) {
        if (!login) {
            if (username.matches("^(?i)(?=.[a-z])(?=.[0-9])[a-z0-9#.!@$*&_]{5,12}$")) {
                if (Account.isThereAccountWithUsername(username)) {
                    subMenuStatus = commandProcessor.setSubMenuStatus();
                    commandProcessor.setSubMenuStatus(SubMenuStatus.PASSWORD);
                    outputNo = 2;
                } else outputNo = 14;
            } else outputNo = 0;
        } else outputNo = 24;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public void checkPassword(String password) {
        if (password.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
            if (Account.isThereAccountWithUsernameAndPassword(username, password)) {
                loginAccount = Account.getAccountWithUsername(username);
                login = true;
                findRole();
                commandProcessor.setSubMenuStatus(subMenuStatus);
                outputNo = 15;
            } else outputNo = 14;
        } else outputNo = 3;
        OutputMassageHandler.showAccountOutput(outputNo);
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

    //gson
    public void viewPersonalInfo() {
        //if(login) {
        OutputHandler.showAccountInformation();
        // }outputHandler.showAccountOutput(25);
    }

    public void processEdit(String field) {
        // if(login) {
        if(!field.matches("(?i)(?:username|password|last name|email|phone number)")) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
            this.field = field;
            OutputMassageHandler.showOutputWithString(field, 3);
        }else outputNo = 16;
        // }else outputNo = 25;
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public void editField(String edit) {
        if (this.field.equalsIgnoreCase("password")) {
            if (edit.matches("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$")) {
                loginAccount.setPassword(edit);
                outputNo = 17;
            } else outputNo = 3;
        } else if (this.field.equalsIgnoreCase("name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                loginAccount.setName(edit);
                outputNo = 18;
            } else outputNo = 5;
        } else if (this.field.equalsIgnoreCase("last name")) {
            if (edit.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")) {
                loginAccount.setLastname(edit);
                outputNo = 19;
            } else outputNo = 7;
        } else if (this.field.equalsIgnoreCase("Email")) {
            if (edit.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                loginAccount.setEmail(edit);
                outputNo = 20;
            } else outputNo = 9;
        } else if (this.field.equalsIgnoreCase("Phone number")) {
            if (edit.matches("09[0-9]{9}")) {
                loginAccount.setPhoneNo(Integer.parseInt(edit));
                outputNo = 21;
            } else outputNo = 11;
        }
        OutputMassageHandler.showAccountOutput(outputNo);
    }

    public void processLogout() {
        if (login) {
            loginAccount = null;
            login = false;
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            outputNo = 22;
        } else outputNo = 25;
        OutputMassageHandler.showAccountOutput(outputNo);
    }
}