package model.accounts;

import model.log.BuyLog;
import java.util.ArrayList;

public class Customer extends Account {

    private ArrayList<Customer> allCustomers;
    private ArrayList<BuyLog> BuyLogsHistory;
    public BuyLog buyLog;

    public Customer(String username) {
        super(username);
        role = "customer";
        allCustomers.add(this);
    }


}
