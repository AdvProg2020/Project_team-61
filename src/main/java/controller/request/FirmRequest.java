package controller.request;

import model.firms.Firm;

import java.util.ArrayList;

public class FirmRequest extends Request {
    private String name = null;
    private double phoneNO = 0;
    private String address = null;
    private String Email = null;
    private ArrayList<FirmRequest> allFirmRequests;



    public FirmRequest(String requestID) {
        super(requestID);
        allFirmRequests.add(this);
    }

    @Override
    public void acceptRequest() {
        Firm firm = Firm.getFirmWithID(name);
        firm.setDetailToFirm(name,phoneNO,address,Email);
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

}
