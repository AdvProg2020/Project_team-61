package controller.request;

import model.accounts.Account;
import model.accounts.Seller;
import model.firms.Company;
import model.firms.Factory;
import model.firms.Firm;
import model.firms.Workshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AccountRequest extends Request {

    private String username = null;
    private String password = null;
    private String name = null;
    private String lastname = null;
    private String Email = null;
    private double phoneNo = 0;
    private Date birthdayDate = null;

    private String firmName = null;
    private double FirmPhoneNO = 0;
    private String firmAddress = null;
    private String firmEmail = null;
    private String FirmType = null;
    private Firm firm;
    private Account selectedAccount;
    private ArrayList<AccountRequest> allAccountRequests = new ArrayList<>();

    public AccountRequest(String requestID) throws IOException {
        super(requestID);
        allAccountRequests.add(this);
    }

    @Override
    public void declineRequest() {
        Request.getAllRequests().remove(this);
        allAccountRequests.remove(this);
    }


    public void acceptRequest() throws IOException {
        Seller seller = new Seller(username);
        createFirm();
        firm = Firm.getFirmWithID(firmName);
        seller.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate, firm);
        firm.setDetailToFirm(FirmPhoneNO, firmAddress, firmEmail);

    }

    private void createFirm() throws IOException {
        if (FirmType.equalsIgnoreCase("company")) {
            Company company = new Company(firmName);
        } else if (FirmType.equalsIgnoreCase("workshop")) {
            Workshop workshop = new Workshop(firmName);
        } else if (FirmType.equalsIgnoreCase("factory")) {
            Factory factory = new Factory(firmName);
        }
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

    public void setUsername(String username) {
        this.username = username;
    }

    //..........................................................
    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setFirmPhoneNO(double firmPhoneNO) {
        FirmPhoneNO = firmPhoneNO;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public void setFirmEmail(String firmEmail) {
        this.firmEmail = firmEmail;
    }

    public void setFirmType(String firmType) {
        FirmType = firmType;
    }

    public void sellerAccountDetails(String username, String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.Email = Email;
        this.phoneNo = phoneNo;
    }


}