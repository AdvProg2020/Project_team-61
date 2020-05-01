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
    int phoneNo;
    double credit;
    String role;
    ArrayList<DiscountCode> discountCodeLists;
    ArrayList<SaleLog> allSaleLogs;
    ArrayList<BuyLog> allBuyLogs;
    ArrayList<Account> allAccounts;


    public Account(String username) {
        this.username = username;
    }

    public boolean isThereAccountWithUsername(String username) {

        for(Account account : allAccounts){
            if (account.username.equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public Account getAccountWithUsername(String username) {

        for(Account account : allAccounts){
            if (account.username.equalsIgnoreCase(username)) return account;
        }
        return null;
    }

    public void deleteAccount(String username){
        allAccounts.remove(getAccountWithUsername(username));
    }

    public boolean isThereAccountWithUsernameAndPassword(String username, String password) {

        for(Account account : allAccounts){
            if (account.username.equalsIgnoreCase(username) && account.password.equals(password)) return true;
        }
        return false;

    }

    public Account listUsers(){
        Iterator iterator = allAccounts.iterator();
        while(iterator.hasNext()){
            Account selectedAccount = (Account) iterator.next();
          return selectedAccount;
        }
        return null;
    }

    public int compareTO(Account account) {
        return  0;
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
    public int getPhoneNo() {
        return phoneNo;
    }

    public double getCredit() {
        return credit;
    }
}
