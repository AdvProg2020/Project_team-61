package model.firms;

import java.util.ArrayList;

public class Factory extends Firm {

    private ArrayList<Factory> allFactories;

    public Factory(String ID) {
        super(ID);
    }

    public int compareTo(Firm o) {
        return 0;
    }
}