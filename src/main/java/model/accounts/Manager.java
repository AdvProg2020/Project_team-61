package model.accounts;

import java.util.ArrayList;

public class Manager extends Account {

    private ArrayList<Manager> allManagers;

    public Manager(String username) {
        super(username);
        role = "manager";
    }


    public void removeProductID(String productID){

    }


    public int compareTo(Account o) {
        return 0;
    }
}
