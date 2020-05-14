package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandling {

    public static Gson gson = new GsonBuilder().create();

    public static void turnToArray(String arrayAndFile) throws IOException {
        ArrayList<String> parts = new ArrayList<String>(
                Arrays.asList(arrayAndFile.split(" ")));
        writeInFile(parts.get(0),parts.get(1));
    }

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
            writer.close();
        }
    }

    public static Gson getGson() {
        return gson;
    }
}
