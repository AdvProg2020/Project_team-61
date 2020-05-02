package controller.request;

import model.accounts.Account;
import model.accounts.Seller;

import java.util.ArrayList;
import java.util.Date;


public class Request {
    private String requestText;
    private Date requestDate;
    private Seller seller;
    private boolean isRequested = false;
    private boolean requestAccepted = false;
    private boolean requestViewed = false;
    private ArrayList<Request> allRequests;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String Email;
    private double phoneNo;
    private Account account;
    private Account selectedAccount;



    public Request(String requestID) {
        this.requestText = requestID;
        allRequests.add(this);
    }

    public void sellerAccountDetails(String username ,String password,String name, String lastname, String Email, double phoneNo){
        this.username = username;
        this.password= password;
        this.name=name;
        this.lastname= lastname;
        this.Email =Email;
        this.phoneNo=phoneNo;
    }

    public void acceptRequest(String requestId){
        if(requestId.matches("((?!^ +$)^.+$) wants seller account")){
            //
          selectedAccount = account.getAccountWithUsername(username);
          selectedAccount.setDetailsToAccount(password,name,lastname,Email,phoneNo);
        }
    }

    public void declineRequest(String requestId){
        allRequests.remove(getRequestFromID(requestId));
    }


    public Request getRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestText.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }
    public boolean isThereRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestText.equalsIgnoreCase(requestID)) return true;
        }
        return false;
    }


    public boolean isRequestViewed() {
        return requestViewed;
    }
    public void setRequestViewed(boolean requestViewed) {
        this.requestViewed = requestViewed;
    }

    public boolean isRequestAccepted() {
        return requestAccepted;
    }
    public void setRequestAccepted(boolean requestAccepted) {
        this.requestAccepted = requestAccepted;
    }


}
