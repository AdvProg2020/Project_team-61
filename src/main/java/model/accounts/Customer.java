package model.accounts;

import com.google.gson.reflect.TypeToken;
import model.log.BuyLog;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Customer extends Account {

    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    private ArrayList<BuyLog> BuyLogsHistory = new ArrayList<>();

    public Customer(String username) throws IOException {
        super(username);
        role = "customer";
        allCustomers.add(this);
   //     writeInJ();
    }

    public ArrayList<BuyLog> getBuyLogsHistory() {
        return BuyLogsHistory;
    }


    public void addLog(BuyLog buyLog) throws IOException {
        BuyLogsHistory.add(buyLog);
        writeInJ();

    }

//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Customer>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Customer.allCustomers,collectionType);
//        FileHandling.turnToArray(json+" "+"customer.json");
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "BuyLogsHistory=" + BuyLogsHistory +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", credit=" + credit +
                ", role='" + role + '\'' +
                ", currentPhoneNo=" + currentPhoneNo +
                ", address='" + address + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", allDiscountCodes=" + allDiscountCodes +
                '}';
    }
}