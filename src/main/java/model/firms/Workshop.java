package model.firms;

import java.util.ArrayList;

public class Workshop extends Firm {
    private ArrayList<Workshop> allWorkshops;

    public Workshop(String ID) {
        super(ID);
        allWorkshops.add(this);
    }


}
