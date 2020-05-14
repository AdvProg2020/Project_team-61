package controller.request;

import model.accounts.Account;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class AccountRequest extends Request {

    private static String username;
    private static String password;
    private static String name;
    private static String lastname;
    private static String Email;
    private static double phoneNo;
    private static Data birthdayDate;
    private static Account selectedAccount;
    private ArrayList<AccountRequest> allAccountRequests;

    public AccountRequest(String requestID) {
        super(requestID);
        allAccountRequests.add(this);
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhoneNo(double phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void sellerAccountDetails(String username, String password, String name, String lastname, String Email, double phoneNo, Data birthdayDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.Email = Email;
        this.phoneNo = phoneNo;
    }

    public static void acceptRequestDetail() {
        selectedAccount = Account.getAccountWithUsername(username);
        selectedAccount.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate);
    }

}
