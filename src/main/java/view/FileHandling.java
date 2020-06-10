package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.accounts.Account;
import model.productRelated.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandling {

    public static String fileName;
    public static String jsonString;
    public static Gson gson = new GsonBuilder().create();




    private static ArrayList<String> fileNames = new ArrayList<>();

    public static ArrayList<String> getFileNames() {
        return fileNames;
    }

    public static void setFileName(String fileName) {
        FileHandling.fileName = fileName;
    }

    public static void setJsonString(String jsonString) {
        FileHandling.jsonString = jsonString;
    }


    //others----------------------------------------------------------------------------------------------------------

    public static void writeInFile(String json,String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        try {
            writer.append(json);
            System.out.println("Successfully serialized operators!");
        }catch (IOException ex) {
            System.err.format("An IO Exception was occurred: %s%n", ex);
            System.exit(-1);
        }finally {
            writer.flush();
            //writer.close();
        }
    }



    public static void setGson(Gson gson) {
        FileHandling.gson = gson;
    }

    public static Gson getGson() {
        return gson;
    }
}