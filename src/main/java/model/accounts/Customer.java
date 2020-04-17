package model.accounts;

import java.util.ArrayList;

public class Customer extends Account {
    private ArrayList<Customer> allCustomers;

    public Customer(String username) {
        super(username);
        role = "customer";
    }

    public int compareTo(Account o) {
        return 0;
    }
}
