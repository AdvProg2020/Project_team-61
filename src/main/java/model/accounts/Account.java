package model.accounts;


import model.firms.Firm;

import model.off.DiscountCode;

import java.io.IOException;
import java.util.*;

public abstract class Account {
    String username = null;
    String name = null;
    String lastname = null;
    String password = null;
    String email = null;
    double phoneNo = 0;
    double credit = 500000000;
    String role = null;
    double currentPhoneNo = 0;
    String address = null;
    Date birthdayDate = null;
    public Firm firm = null;
    boolean fast = false;

    private static ArrayList<Account> allAccounts;
    private static ArrayList<Date> birthdayDates = new ArrayList<>();
//    public static Type AccountType = new TypeToken<ArrayList<Account>>() {
//    }.getType();


    public void setDetailsToAccount(String password, String name, String lastname, String Emai, double phoneNo, Date birthdayDat, Firm firm) throws IOException {
        if (password != null) {
            this.password = password;
        }
        if (name != null) {
            this.name = name;
        }
        if (lastname != null) {
            this.lastname = lastname;
        }
        if (email != null) {
            this.email = Emai;
        }
        if (phoneNo != 0) {
            this.phoneNo = phoneNo;
        }
        if (birthdayDate != null) {
            if (birthdayDat instanceof Date) {
                this.birthdayDate = (Date) birthdayDat;
                birthdayDates.add(birthdayDate);
            }
        }
        if (firm != null) {
            this.firm = firm;
        }
//        writeInJ();
        Customer.writeInJ();
        Manager.writeInJ();
        Seller.writeInJ();
    }


    public Account(String username) throws IOException {
        this.username = username;
        allAccounts.add(this);

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
    public void setName(String name) throws IOException {
        this.name = name;
        Customer.writeInJ();
        Manager.writeInJ();
        Seller.writeInJ();

    }

    public void setLastname(String lastname) throws IOException {
        this.lastname = lastname;
//        writeInJ();

    }

    public void setPassword(String password) throws IOException {
        this.password = password;
//        writeInJ();

    }

    public void setEmail(String email) throws IOException {
        this.email = email;
//        writeInJ();

    }

    public void setPhoneNo(int phoneNo) throws IOException {
        this.phoneNo = phoneNo;
//        writeInJ();

    }

    public Firm getFirm() {
        return firm;
    }

    public void setCredit(double credit) throws IOException {
        this.credit = credit;
//        writeInJ();

    }

    public void setCurrentPhoneNo(double currentPhoneNo) throws IOException {
        this.currentPhoneNo = currentPhoneNo;
//        writeInJ();

    }

    public void setAddress(String address) throws IOException {
        this.address = address;
//        writeInJ();

    }

    public void setFast(boolean fast) throws IOException {
        this.fast = fast;
//        writeInJ();

    }

    //-----------------------------------------------------------------
    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public String getAddress() {
        return address;
    }

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

    public static void setAllAccounts(ArrayList<Account> allAccounts) {
        Account.allAccounts = allAccounts;
    }

    //......................................................................


    public static Comparator<Account> accountComparatorForUsername = new Comparator<Account>() {

        public int compare(Account s1, Account s2) {
            String name1 = s1.getName().toUpperCase();
            String name2 = s2.getName().toUpperCase();
            return name1.compareTo(name2);
        }
    };

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", credit=" + credit +
                ", role='" + role + '\'' +
                ", currentPhoneNo=" + currentPhoneNo +
                ", address='" + address + '\'' +
                ", birthdayDate=" + birthdayDate +
                // ", allDiscountCodes=" + allDiscountCodes +
                '}';
    }
}
