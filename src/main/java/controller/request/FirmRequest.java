package controller.request;

public class FirmRequest extends Request {
    private String name;
    private double phoneNO;
    private String address;


    public FirmRequest(String requestID) {
        super(requestID);
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


}
