package controller.request;

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
    private static String id = null;
    private static String username = null;
    private static String password = null;
    private static String name = null;
    private static String lastname = null;
    private static  String Email = null;
    private static double phoneNo = 0;
    private static Date birthdayDate = null;
    private static  AccountRequest ar= null;
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

    public static AccountRequest getRequestFromID(String requestID){
        for(AccountRequest request : allAccountRequests){
            if (request.id.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }


    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allAccountRequests.remove(request);
    }


    public static  void acceptRequest(Request request) throws IOException {
        ar =AccountRequest.getRequestFromID(request.getRequestText());
        Seller seller = new Seller(ar.username);
        createFirm();
        ar.firm = Firm.getFirmWithID(ar.firmName);
        seller.setDetailsToAccount(ar.password, ar.name, ar.lastname,ar. Email, ar.phoneNo, ar.birthdayDate, ar.firm);
        ar.firm.setDetailToFirm(ar.FirmPhoneNO, ar.firmAddress, ar.firmEmail);
        Request.getAllRequests().remove(request);
        allAccountRequests.remove(request);

    }

    private static void createFirm() throws IOException {
        if (ar.FirmType.equalsIgnoreCase("company")) {
            Company company = new Company(ar.firmName);
        } else if (ar.FirmType.equalsIgnoreCase("workshop")) {
            Workshop workshop = new Workshop(ar.firmName);
        } else if (ar.FirmType.equalsIgnoreCase("factory")) {
            Factory factory = new Factory(ar.firmName);
        }
    }


    public static void setId(String id) throws IOException {
        AccountRequest.id = id;
        writeInJ();
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

    public void sellerAccountDetails(String username, String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.Email = Email;
        this.phoneNo = phoneNo;
    }


}