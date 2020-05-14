package model.accounts;

import com.google.gson.reflect.TypeToken;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Manager extends Account {

    private static ArrayList<Manager> allManagers;

    public Manager(String username) throws IOException {
        super(username);
        role = "manager";
        allManagers.add(this);
        writeInJ();
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Manager>>(){}.getType();
        String json= FileHandling.getGson().toJson(Manager.allManagers,collectionType);
        FileHandling.turnToArray(json+" "+"manager.json");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
