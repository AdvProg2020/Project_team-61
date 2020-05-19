import view.CommandProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.run();
    }
   
}
