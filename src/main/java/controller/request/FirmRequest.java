package controller.request;

import model.firms.Firm;

import java.util.ArrayList;

public class FirmRequest extends Request {
    private String name;
    private double phoneNO;
    private String address;
    private String Email;
    private ArrayList<FirmRequest> allFirmRequests;



    public FirmRequest(String requestID) {
        super(requestID);
        allFirmRequests.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNO(double phoneNO) {
        this.phoneNO = phoneNO;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public void acceptFirmRequest(){
        Firm firm = Firm.getFirmWithID(name);
        firm.setDetailToFirm(name,phoneNO,address,Email);

    }
}
