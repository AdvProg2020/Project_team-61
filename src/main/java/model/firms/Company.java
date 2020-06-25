package model.firms;

import java.io.IOException;
import java.util.ArrayList;

public class Company extends Firm {
    private static ArrayList<Company> allCompanies = new ArrayList<>();

    public Company(String ID) throws IOException {
        super(ID);
        allCompanies.add(this);
        type ="company";
  //      writeInJ();
    }
//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Company>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Company.allCompanies,collectionType);
//        FileHandling.turnToArray(json+" "+"company.json");
//    }


    @Override
    public String toString() {
        return super.toString();
    }
}