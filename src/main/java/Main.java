import view.CommandProcessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        CommandProcessor commandProcessor = new CommandProcessor();

        commandProcessor.run();
        Scanner in =new Scanner(System.in);
        int a=in.nextInt();
        System.out.println(a);

    }
}
