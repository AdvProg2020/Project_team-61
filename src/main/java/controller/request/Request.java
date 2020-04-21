package controller.request;

import model.accounts.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Request {
    private String requestID;
    private String requestText;
    private Date requestDate;
    private Seller seller;
    private ArrayList<String> allRequests;
    private boolean isRequested;
    private boolean requestAccepted;

    public Request(String requestID) {
        this.requestID = requestID;
    }

    public void getRequestFromID(String requestID){

    }

    public boolean getIsRequested() {
        return isRequested;
    }

    public boolean isThereRequestFromID(String requestID){
        return true;
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
}
