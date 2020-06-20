package model.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.accounts.Seller;
import model.firms.Company;
import model.firms.Factory;
import model.firms.Firm;
import model.firms.Workshop;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class AccountRequest extends Request {

    private static String username = null;
    private static String password = null;
    private static String name = null;
    private static String lastname = null;
    private static  String Email = null;
    private static double phoneNo = 0;
    private static Date birthdayDate = null;

    private static String firmName = null;
    private static double FirmPhoneNO = 0;
    private static String firmAddress = null;
    private static String firmEmail = null;
    private static String FirmType = null;
    private static Firm firm;
    private Account selectedAccount;
    private static ArrayList<AccountRequest> allAccountRequests = new ArrayList<>();
    public static Type accountRequestType = new TypeToken<ArrayList<AccountRequest>>() {
    }.getType();


    public static void setAllAccountRequests(ArrayList<AccountRequest> allAccountRequests) {
        AccountRequest.allAccountRequests = allAccountRequests;
    }

    public static ArrayList<AccountRequest> getAllAccountRequests() {
        return allAccountRequests;
    }

    public AccountRequest(String requestID) throws IOException {
        super(requestID);
        allAccountRequests.add(this);
        writeInJ();
    }

@Override
    public  void declineRequest() {
        Request.getAllRequests().remove(this);
        allAccountRequests.remove(this);
    }

    @Override
    public   void acceptRequest() throws IOException {
        Seller seller = new Seller(username);
        createFirm();
        firm = Firm.getFirmWithID(firmName);
        seller.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate, firm);
        firm.setDetailToFirm(FirmPhoneNO, firmAddress, firmEmail);
        Request.getAllRequests().remove(this);
        allAccountRequests.remove(this);

    }

    private static void createFirm() throws IOException {
        if (FirmType.equalsIgnoreCase("company")) {
            Company company = new Company(firmName);
        } else if (FirmType.equalsIgnoreCase("workshop")) {
            Workshop workshop = new Workshop(firmName);
        } else if (FirmType.equalsIgnoreCase("factory")) {
            Factory factory = new Factory(firmName);
        }
    }

    public void setPassword(String password) throws IOException {
        this.password = password;
        writeInJ();

    }

    public void setName(String name) throws IOException {
        this.name = name;
        writeInJ();

    }

    public void setLastname(String lastname) throws IOException {
        this.lastname = lastname;
        writeInJ();

    }

    public void setEmail(String email) throws IOException {
        Email = email;
        writeInJ();

    }

    public void setPhoneNo(double phoneNo) throws IOException {
        this.phoneNo = phoneNo;
        writeInJ();

    }

    public void setUsername(String username) throws IOException {
        this.username = username;
        writeInJ();

    }

    //..........................................................
    public void setFirmName(String firmName) throws IOException {
        this.firmName = firmName;
        writeInJ();

    }

    public void setFirmPhoneNO(double firmPhoneNO) throws IOException {
        FirmPhoneNO = firmPhoneNO;
        writeInJ();

    }

    public void setFirmAddress(String firmAddress) throws IOException {
        this.firmAddress = firmAddress;
        writeInJ();

    }

    public void setFirmEmail(String firmEmail) throws IOException {
        this.firmEmail = firmEmail;
        writeInJ();

    }

    public void setFirmType(String firmType) throws IOException {
        FirmType = firmType;
        writeInJ();

    }


    public static void writeInJ() throws IOException {
        FileHandling.setGson(new Gson());
        String json = FileHandling.getGson().toJson(AccountRequest.allAccountRequests, accountRequestType);
        FileHandling.writeInFile(json, "accountRequest.json");
    }

    public void sellerAccountDetails(String username, String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate) throws IOException {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.Email = Email;
        this.phoneNo = phoneNo;
        writeInJ();
    }

    public static String getUsername() {
        return username;
    }

    public static String getName() {
        return name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static String getEmail() {
        return Email;
    }

    public static double getPhoneNo() {
        return phoneNo;
    }

    public static Date getBirthdayDate() {
        return birthdayDate;
    }

    public static String getFirmName() {
        return firmName;
    }

    public static double getFirmPhoneNO() {
        return FirmPhoneNO;
    }

    public static String getFirmAddress() {
        return firmAddress;
    }

    public static String getFirmEmail() {
        return firmEmail;
    }

    public static String getFirmType() {
        return FirmType;
    }
}