package model.firms;

public abstract class Firm implements Comparable<Firm>{
    String ID;
    String name;
    String phoneNO;

    public Firm(String ID) {
        this.ID = ID;
    }

    public Firm getFirmWithID(String ID){
        return null;
    }

    public boolean isThereFirmWithID(String ID){
        return false;
    }

    public void viewFirm(String ID){

    }

    public int compareTO(Firm firm){
        return 0;
    }

    public void deleteFirm(String ID){

    }

    public int getFirmListSize(){
        return 0;
    }
}
