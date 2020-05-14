package model.firms;

import com.google.gson.reflect.TypeToken;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Factory extends Firm {
    private static ArrayList<Factory> allFactories;

    public Factory(String ID) {
        super(ID);
        allFactories.add(this);
    }
    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Factory>>(){}.getType();
        String json= FileHandling.getGson().toJson(Factory.allFactories,collectionType);
        FileHandling.turnToArray(json+" "+"factory.json");
    }

}
