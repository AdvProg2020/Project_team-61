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
    public static Type CustomerType = new TypeToken<ArrayList<Customer>>() {
    }.getType();


    public Customer(String username) throws IOException {
        super(username);
        role = "customer";
        allCustomers.add(this);
        writeInJ();
    }


    public static void setAllCustomers(ArrayList<Customer> allCustomers) {
        Customer.allCustomers = allCustomers;
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public ArrayList<BuyLog> getBuyLogsHistory() {
        return BuyLogsHistory;
    }


    public void addLog(BuyLog buyLog) throws IOException {
        BuyLogsHistory.add(buyLog);
        writeInJ();

    }

    public static void writeInJ() throws IOException {

        String json = FileHandling.getGson().toJson(Customer.allCustomers, CustomerType);
        FileHandling.writeInFile(json, "customer.json");

    }

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