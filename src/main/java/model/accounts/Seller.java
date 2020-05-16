package model.accounts;

import com.google.gson.reflect.TypeToken;
import model.firms.Firm;
import model.log.SaleLog;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Seller extends Account {
    private Firm firm;
    private ArrayList<SaleLog> saleLogsHistory ;
    private static ArrayList<Seller> allSellers;
    public SaleLog saleLog;

    public Seller(String username) throws IOException {
        super(username);
        role = "seller";
        allSellers.add(this);
        writeInJ();
    }


    public Firm getFirm() {
        return firm;
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Seller>>(){}.getType();
        String json= FileHandling.getGson().toJson(Seller.allSellers,collectionType);
        FileHandling.turnToArray(json+" "+"seller.json");
    }

    @Override
    public String toString() {
        return "Seller{" +
                "firm=" + firm +
                ", saleLogsHistory=" + saleLogsHistory +
                ", saleLog=" + saleLog +
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
                ", usedDiscount=" + usedDiscount +
                ", allDiscountCodes=" + allDiscountCodes +
                '}';
    }
}