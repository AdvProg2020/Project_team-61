package model.firms;

import java.util.ArrayList;

public abstract class Firm implements Comparable<Firm>{

    private String name;
    private double phoneNO;
    private String address;

    private ArrayList<Firm> allFirms;

    public Firm(String name) {
        this.name = name;
    }

    public Firm getFirmWithID(String ID){

        for(Firm firm : allFirms){
            if(firm.name.equalsIgnoreCase(ID))return firm;
        }
        return null;
    }

    public boolean isThereFirmWithID(String ID){

        for(Firm firm : allFirms){
            if(firm.name.equalsIgnoreCase(ID))return true;
        }
        return false;
    }

    public void deleteFirm(String ID){
        allFirms.remove(getFirmWithID(ID));
    }

}
