package model.firms;

import java.util.ArrayList;

public abstract class Firm implements Comparable<Firm>{
    String ID;
    String name;
    String phoneNO;
    private ArrayList<Firm> allFirms;

    public Firm(String ID) {
        this.ID = ID;
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

    public int compareTO(Firm firm){
        return 0;
    }

    public void deleteFirm(String ID){
        allFirms.remove(getFirmWithID(ID));
    }

    public int getFirmListSize(){
        return allFirms.size();
    }
}
