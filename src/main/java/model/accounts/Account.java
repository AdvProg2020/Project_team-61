package model.accounts;

import com.google.gson.reflect.TypeToken;
import model.filtar.Filter;
import model.firms.Firm;
import model.log.BuyLog;
import model.log.SaleLog;
import model.off.DiscountCode;
import view.FileHandling;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public abstract class Account {
    String username;
    String name;
    String lastname;
    String password;
    String email;
    double phoneNo;
    double credit = 500000000;
    String role;
    double currentPhoneNo;
    String address;
    Date birthdayDate;
    Firm firm;
    boolean fast;
    ArrayList<DiscountCode> allDiscountCodes;
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private static ArrayList<Date> birthdayDates = new ArrayList<>();


    public void setDetailsToAccount(String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate, Firm firm) throws IOException {

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
            this.email = Email;
        }
        if (phoneNo != 0) {
            this.phoneNo = phoneNo;
        }
        if (birthdayDate != null) {
            this.birthdayDate = (Date) birthdayDate;
            birthdayDates.add(birthdayDate);
        }
        if (firm != null) {
            this.firm = firm;
        }
        writeInJ();

    }


    public Account(String username) throws IOException {
        this.username = username;
        //writeInJ();
        allAccounts.add(this);
    }

    public void addDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.add(discountCode);
    }

    public void removeDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.remove(discountCode);
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

    public void setFast(boolean fast) {
        this.fast = fast;
    }

    //-----------------------------------------------------------------
    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
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
    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        if (FileHandling.isFileWithName("account.json")) {
            ArrayList<Account> list = FileHandling.getGson().fromJson(FileHandling.readFile("account.json"), collectionType);
            Account.setAllAccounts(list);
            FileHandling.getFileNames().add("account.json");
        }
        String json = FileHandling.getGson().toJson(Account.allAccounts, collectionType);
        FileHandling.writeInFile(json, "account.json");
        //FileHandling.getFileNames().add("account.json");

    }


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
                ", allDiscountCodes=" + allDiscountCodes +
                '}';
    }
}