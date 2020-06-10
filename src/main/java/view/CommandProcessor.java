package view;

import controller.menus.*;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private static MenuStatus menuStatus = MenuStatus.MAINMENU;
    private static SubMenuStatus subMenuStatus = SubMenuStatus.MAINMENU;
    private static InternalMenu internalMenu = InternalMenu.MAINMENU;
    private boolean error = true;

    public static MenuStatus getMenuStatus() {
        return menuStatus;
    }

    public static void setMenuStatus(MenuStatus menuStatus) {
        CommandProcessor.menuStatus = menuStatus;
    }

    public static SubMenuStatus getSubMenuStatus() {
        return subMenuStatus;
    }

    public static void setSubMenuStatus(SubMenuStatus subMenuStatus) {
        CommandProcessor.subMenuStatus = subMenuStatus;
    }

    public static InternalMenu getInternalMenu() {
        return internalMenu;
    }

    public static void setInternalMenu(InternalMenu internalMenu) {
        CommandProcessor.internalMenu = internalMenu;
    }

    private boolean ifSeller() {
        if (LoginMenu.isLogin()) {
            Account loginAccount = LoginMenu.getLoginAccount();
            if (loginAccount instanceof Seller) {
                return true;
            }
        }
        return false;
    }

    private boolean ifManager() {
        if (LoginMenu.isLogin()) {
            Account loginAccount = LoginMenu.getLoginAccount();
            if (loginAccount instanceof Manager) {
                return true;
            }
        }
        return false;
    }

    private boolean ifCustomer() {
        if (LoginMenu.isLogin()) {
            Account loginAccount = LoginMenu.getLoginAccount();
            if (loginAccount instanceof Customer) {
                return true;
            }
        }
        return false;
    }


}