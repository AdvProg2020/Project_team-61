package controller.request;

import com.google.gson.reflect.TypeToken;
import controller.menus.LoginMenu;
import model.accounts.Account;
import model.accounts.Seller;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public  abstract class Request {
    private String requestText;
    private String requestDate;
    private Account seller;
    static ArrayList<Request> allRequests = new ArrayList<>();
    LocalDateTime now;

    public Request(String requestID) throws IOException {
        this.requestText = requestID;

        //?
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        now = LocalDateTime.now();
        requestDate = dtf.format(now);
        seller = (Seller) LoginMenu.getLoginAccount();
        allRequests.add(this);
        writeInJ();
    }

    public LocalDateTime getNow() {
        return now;
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestText() {
        return requestText;
    }

    public abstract void declineRequest();

    public abstract void acceptRequest() throws IOException;

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
        FileHandling.setFileName("request.json");
        FileHandling.setJsonString(json);
        FileHandling.writeInFile(json,"request.json");
    }

    public static Comparator<Request> productComparatorForScore = new Comparator<Request>() {

        public int compare(Request o1, Request o2) {
            return o1.getNow().compareTo(o2.getNow());
        }
    };


    @Override
    public String toString() {
        return "Request{" +
                "requestText='" + requestText + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", seller=" + seller +
                '}';
    }
}