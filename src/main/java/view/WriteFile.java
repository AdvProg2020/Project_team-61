package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static Gson gson = new GsonBuilder().create();

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

}
