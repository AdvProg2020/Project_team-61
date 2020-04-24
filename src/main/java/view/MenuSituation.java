package view;

public class MenuSituation {
    private CommandProcessor commandProcessor;


    void processBack() {

    }

    void processHelp() {
       if (commandProcessor.getMenuStatus()==MenuStatus.MAINMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus()==MenuStatus.REGISTERMENUE) {
           System.out.println("");
       } else if (commandProcessor.getMenuStatus()==MenuStatus.USERMENU) {
           System.out.println("");
       } else if (commandProcessor.getMenuStatus()==MenuStatus.CUSTOMERMENU) {
           System.out.println("");
       } else if (commandProcessor.getMenuStatus()==MenuStatus.MANAGERMENU) {
           System.out.println("");
       } else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
        }else if (commandProcessor.getMenuStatus()==MenuStatus.LOGINMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.PRODUCTMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SALEMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
           System.out.println("");
       }

    }
}
