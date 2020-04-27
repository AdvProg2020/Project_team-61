package controller.request;

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

    public Request(String requestID) {
        this.requestText = requestID;
        allRequests.add(this);
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
