package controller.request;

import model.accounts.Account;
import model.accounts.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Request {
    private String requestID;
    private String requestText;
    private Date requestDate;
    private Seller seller;
    private ArrayList<Request> allRequests;
    private boolean isRequested;
    private boolean requestAccepted;

    public Request(String requestID) {
        this.requestID = requestID;
    }

    public Request getRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestID.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }

    public boolean isRequested() {
        return isRequested;
    }

    public boolean isThereRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestID.equalsIgnoreCase(requestID)) return true;
        }
        return false;
    }

    public boolean getIsRequestAccepted() {
        return requestAccepted;
    }

    public void setIsRequestAccepted(boolean requestAccepted) {
        this.requestAccepted = requestAccepted;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public Request listRequest(){
        Iterator iterator = allRequests.iterator();
        while(iterator.hasNext()){
            Request selectedRequest = (Request) iterator.next();
            return selectedRequest;
        }
        return null;
    }

    public int getRequestListSize(){
        return allRequests.size();
    }

    public String getRequestText() {
        return requestText;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Seller getSeller() {
        return seller;
    }
}
