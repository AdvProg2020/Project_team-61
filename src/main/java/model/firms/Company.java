package model.firms;

import java.util.ArrayList;

public class Company extends Firm {
    private ArrayList<Company> allCompanies;

    public Company(String ID) {
        super(ID);
        allCompanies.add(this);
    }


}
