package controller.request;

import controller.menus.LoginMenu;
import model.accounts.Account;
import model.accounts.Seller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

//import com.sun.org.apache.regexp.internal.RE;

public abstract class Request {
    private String requestText;
    private String requestDate;
    private Account seller;
    private static ArrayList<Request> allRequests;
    LocalDateTime now;
//    public static Type RequestType = new TypeToken<ArrayList<Request>>() {
//    }.getType();

    public Request(String requestID) throws IOException {
        this.requestText = requestID;

        //?
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        now = LocalDateTime.now();
        requestDate = dtf.format(now);
        if(LoginMenu.getLoginAccount() instanceof Seller) {
            seller = (Seller) LoginMenu.getLoginAccount();
        }
        allRequests.add(this);
//        writeInJ();
    }

    public LocalDateTime getNow() {
        return now;
    }

    public static void setAllRequests(ArrayList<Request> allRequests) {
        Request.allRequests = allRequests;
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestText() {
        return requestText;
    }

    public static void declineRequest(String request) {
        if(request.matches("add sale:\\s+\\S+")){
            SaleRequest.declineRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*wants add product\\s+\\S+")){
            ProductRequest.declineRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*wants seller account")){
            AccountRequest.declineRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*comment on\\s*\\S+")){
            CommentRequest.declineRequest(getRequestFromID(request));
        }

    }

    public void acceptRequest(String request) throws IOException {
        if(request.matches("add sale:\\s+\\S+")){
            SaleRequest.acceptRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*wants add product\\s+\\S+")){
            ProductRequest.acceptRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*wants seller account")){
            AccountRequest.acceptRequest(getRequestFromID(request));
        }else  if(request.matches("\\S+\\s*comment on\\s*\\S+")){
            CommentRequest.acceptRequest(getRequestFromID(request));
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

//    public static void writeInJ() throws IOException {
//        FileHandling.setGson(new Gson());
//        String json = FileHandling.getGson().toJson(Request.allRequests, RequestType);
//        FileHandling.writeInFile(json, "request.json");
//    }

//    public static void readFile() throws FileNotFoundException {
//        JsonReader reader = new JsonReader(new FileReader("request.json"));
//        ArrayList<Request> list = FileHandling.getGson().fromJson(reader,RequestType);
//        if (null== list){
//            list = new ArrayList<>();
//        }
//        Request.setAllRequests(list);
//    }
//
//    public Request() {
//    }

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
