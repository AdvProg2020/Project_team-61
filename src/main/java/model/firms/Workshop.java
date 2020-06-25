package model.firms;

import java.io.IOException;
import java.util.ArrayList;

public class Workshop extends Firm {
    private static ArrayList<Workshop> allWorkshops = new ArrayList<>();

    public Workshop(String ID) throws IOException {
        super(ID);
        allWorkshops.add(this);
        type = "worK shop";
  //      writeInJ();
    }
//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Workshop>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Workshop.allWorkshops,collectionType);
//        FileHandling.turnToArray(json+" "+"workshop.json");
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}