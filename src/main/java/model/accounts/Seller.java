package model.accounts;

import model.firms.Firm;
import model.log.SaleLog;

import java.util.ArrayList;

public class Seller extends Account {
    private Firm firm;
    private ArrayList<SaleLog> saleLogsHistory ;
    private ArrayList<Seller> allSellers;

    public Seller(String username) {
        super(username);
        role = "seller";
    }

    public Firm getFirm() {
        return firm;
    }

    public int compareTo(Account o) {
        return 0;
    }

}
