package controller.request;

import com.google.gson.reflect.TypeToken;
import controller.menus.LoginMenu;
import model.accounts.Account;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public  class Request {
    private String requestText;
    private String requestDate;
    private Account seller;
   // private boolean isRequested = false;
   // private boolean requestAccepted = false;
   // private boolean requestViewed = false;
    private static ArrayList<Request> allRequests;

    private LoginMenu loginMenu;


    public Request(String requestID) {
        this.requestText = requestID;

        //?
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        requestDate = dtf.format(now);
        seller = LoginMenu.getLoginAccount();
        allRequests.add(this);
    }

    public String getRequestText() {
        return requestText;
    }

    public static void declineRequest(String requestId){
        allRequests.remove(getRequestFromID(requestId));
    }

    public static void acceptRequest(String requestId){
        if(requestId.matches("((?!^ +$)^.+$) wants seller account")){
            AccountRequest.acceptRequestDetail();
        }
    }

    public static void deleteRequest(String id){
        allRequests.remove(getRequestFromID(id));
    }
    public static Request getRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestText.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }
    public static boolean isThereRequestFromID(String requestID){
        for(Request request : allRequests){
            if (request.requestText.equalsIgnoreCase(requestID)) return true;
        }
        return false;
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Request>>(){}.getType();
        String json= FileHandling.getGson().toJson(Request.allRequests,collectionType);
        FileHandling.turnToArray(json+" "+"request.json");
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestText='" + requestText + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", seller=" + seller +
                ", loginMenu=" + loginMenu +
                '}';
    }
}
