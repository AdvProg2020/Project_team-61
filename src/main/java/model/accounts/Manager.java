package model.accounts;

import com.google.gson.reflect.TypeToken;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Manager extends Account {

    private static ArrayList<Manager> allManagers = new ArrayList<>();
    public static Type ManagerType = new TypeToken<ArrayList<Manager>>() {
    }.getType();


    public Manager(String username) throws IOException {
        super(username);
        role = "manager";
        allManagers.add(this);
        writeInJ();
    }

    public static void setAllManagers(ArrayList<Manager> allManagers) {
        Manager.allManagers = allManagers;
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }

    public static void writeInJ() throws IOException {

        String json = FileHandling.getGson().toJson(Manager.allManagers, ManagerType);
        FileHandling.writeInFile(json, "manager.json");

    }

    @Override
    public String toString() {
        return super.toString();
    }
}