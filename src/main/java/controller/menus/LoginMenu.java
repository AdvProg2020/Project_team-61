package controller.menus;

import model.accounts.Account;
import view.CommandProcessor;
import view.MenuStatus;
import view.OutputHandler;
import view.SubMenuStatus;

public class LoginMenu {
    private boolean Login;
    private int inputNo;
    private int outputNo;
    private Account account;
    private Account loginAccount;
    private CommandProcessor commandProcessor;
    private String field = null;

    public boolean isLogin() {
        return Login;
    }


    public void processLogin(String username) {
        if (username.matches(" ")) {
            if (account.isThereAccountWithUsername(username)) {
                loginAccount = account.getAccountWithUsername(username);
                findRole();
            }
        }

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
        OutputHandler.showObjectOutput(loginAccount,1,1);
    }

    public void processEdit(String field) {
        commandProcessor.setSubMenuStatus(SubMenuStatus.EDITACCOUNT);
        this.field = field;
    }

    public void editField(String edit) {
        if (this.field.equalsIgnoreCase("password")) {
            loginAccount.setPassword(edit);
        } else if (this.field.equalsIgnoreCase("name")) {
            loginAccount.setName(edit);
        } else if (this.field.equalsIgnoreCase("lastname")) {
            loginAccount.setLastname(edit);
        } else if (this.field.equalsIgnoreCase("Email")) {
            loginAccount.setEmail(edit);
        } else if (this.field.equalsIgnoreCase("Phone number")) {
            loginAccount.setPhoneNo(Integer.parseInt(edit));
        }
    }

    public void processLogout() {
        loginAccount = null;
        commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
    }
}
