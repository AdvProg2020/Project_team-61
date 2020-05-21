package model.firms;

import java.io.IOException;
import java.util.ArrayList;

public class Factory extends Firm {
    private static ArrayList<Factory> allFactories = new ArrayList<>();

    public Factory(String ID) throws IOException {
        super(ID);
        allFactories.add(this);
        //      writeInJ();
    }
//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Factory>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Factory.allFactories,collectionType);
//        FileHandling.turnToArray(json+" "+"factory.json");
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}