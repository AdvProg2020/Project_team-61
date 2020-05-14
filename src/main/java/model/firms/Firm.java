package model.firms;

import com.google.gson.reflect.TypeToken;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Firm {

    private String name;
    private double phoneNO;
    private String address;
    private String Email;
    private static ArrayList<Firm> allFirms;

    public Firm(String name) {
        this.name = name;
        allFirms.add(this);
    }

    public void setDetailToFirm(String name, Double phoneNO, String address, String email){
        this.name= name;
        this.phoneNO=phoneNO;
        this.address= address;
        this.Email= email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(double phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public static Firm getFirmWithID(String ID){
        for(Firm firm : allFirms){
            if(firm.name.equalsIgnoreCase(ID))return firm;
        }
        return null;
    }

    public static boolean isThereFirmWithID(String ID){
        for(Firm firm : allFirms){
            if(firm.name.equalsIgnoreCase(ID))return true;
        }
        return false;
    }

    public void deleteFirm(String ID){
        allFirms.remove(getFirmWithID(ID));
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Firm>>(){}.getType();
        String json= FileHandling.getGson().toJson(Firm.allFirms,collectionType);
        FileHandling.turnToArray(json+" "+"firm.json");
    }

}
