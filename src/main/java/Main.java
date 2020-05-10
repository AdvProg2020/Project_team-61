import view.commandProcessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        commandProcessor commandProcessor = new commandProcessor();

        commandProcessor.run();
        Scanner in =new Scanner(System.in);
        int a=in.nextInt();
        System.out.println(a);

    }
}
