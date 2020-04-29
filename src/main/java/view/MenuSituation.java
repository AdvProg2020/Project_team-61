package view;

public class MenuSituation {
    private CommandProcessor commandProcessor;


    void processBack() {
        if (commandProcessor.getMenuStatus()==MenuStatus.MAINMENU) {
            System.out.println("this is first page");
        } /*else if (commandProcessor.getMenuStatus()==MenuStatus.REGISTERMENUE) {
            commandProcessor.setMenuStatus();
            System.out.println("");
        } else if (commandProcessor.getMenuStatus()==MenuStatus.USERMENU) {
            System.out.println("");
        }*/ else if (commandProcessor.getMenuStatus()==MenuStatus.CUSTOMERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus()==MenuStatus.MANAGERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus()==MenuStatus.SELLERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        }else if (commandProcessor.getMenuStatus()==MenuStatus.PRODUCTSMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        }else if (commandProcessor.getMenuStatus()==MenuStatus.PRODUCTMENU) {
            //commandProcessor.setMenuStatus();
            System.out.println("");
        }else if (commandProcessor.getMenuStatus()==MenuStatus.SALEMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        }else if (commandProcessor.getMenuStatus()==MenuStatus.CREATIONMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
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

    void processHelp() {
       if (commandProcessor.getMenuStatus()==MenuStatus.MAINMENU) {
            System.out.println("user menu " +
                    "products menu" +
                    "sale menu" +
                    "");
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
