import view.commandprocessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        commandprocessor commandProcessor = new commandprocessor();

        commandProcessor.run();
        Scanner in =new Scanner(System.in);
        int a=in.nextInt();
        System.out.println(a);

    }
}
