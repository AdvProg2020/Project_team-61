package model.accounts;

import model.log.BuyLog;
import model.off.DiscountCode;
import model.log.SaleLog;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Account {
    String username;
    String name;
    String lastname;
    String password;
    String email;
    double phoneNo;
    double credit;
    String role;
    double currentPhoneNo;
    String address;
    ArrayList<DiscountCode> discountCodeLists;
    ArrayList<SaleLog> allSaleLogs;
    ArrayList<BuyLog> allBuyLogs;
    private  static ArrayList<Account> allAccounts;

    public  void setDetailsToAccount(String password, String name, String lastname, String Email, double phoneNo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = Email;
        this.phoneNo = phoneNo;
    }


    public Account(String username) {
        this.username = username;
    }

    public static boolean isThereAccountWithUsername(String username) {

        for (Account account : allAccounts) {
            if (account.username.equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static Account getAccountWithUsername(String username) {

        for (Account account : allAccounts) {
            if (account.username.equalsIgnoreCase(username)) return account;
        }
        return null;
    }

    public static void deleteAccount(String username) {
        allAccounts.remove(getAccountWithUsername(username));
    }

    public static boolean isThereAccountWithUsernameAndPassword(String username, String password) {

        for (Account account : allAccounts) {
            if (account.username.equalsIgnoreCase(username) && account.password.equals(password)) return true;
        }
        return false;

    }

    public Account listUsers() {
        Iterator iterator = allAccounts.iterator();
        while (iterator.hasNext()) {
            Account selectedAccount = (Account) iterator.next();
            return selectedAccount;
        }
        return null;
    }

    public int compareTO(Account account) {
        return 0;
    }

    public String getRole() {
        return role;
    }

    //----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setCurrentPhoneNo(double currentPhoneNo) {
        this.currentPhoneNo = currentPhoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //-----------------------------------------------------------------
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public double getPhoneNo() {
        return phoneNo;
    }

    public double getCredit() {
        return credit;
    }
}