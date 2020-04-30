package view;

public class MenuSituation {
    private CommandProcessor commandProcessor;


    void processBack() {
        if (commandProcessor.getMenuStatus() == MenuStatus.MAINMENU) {
            System.out.println("this is first page");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.CUSTOMERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.PRODUCTMENU) {
            commandProcessor.setMenuStatus(MenuStatus.PRODUCTSMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SALEMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.CREATIONMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("");
        } //
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEUSERS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEALLPRODUCTS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWDISCOUNTCODES) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEREQUESTS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATAGORIES) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } //
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEPRODUCTS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWOFFS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        }//
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWCART) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWORDERS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        }//
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.FILTERING) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.SORTING) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        }//
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DIGEST) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTS) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } //
        else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
            //commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you have to finish creating discount code process first");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you can choose new discount code to edit");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you can choose new field of discount code to edit");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATAGORIES) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
            //commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you have to finish creating discount code process first");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you can choose new discount code to edit");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("you can choose new field of discount code to edit");
        } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATAGORIES) {
            commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("");
        } //


    }

    void processHelp() {
        if (commandProcessor.getMenuStatus() == MenuStatus.MAINMENU) {
            System.out.println("user menu " +
                    "products menu" +
                    "sale menu" +
                    "");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.REGISTERMENUE) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.USERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.CUSTOMERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.LOGINMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.PRODUCTMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SALEMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        } else if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            System.out.println("");
        }


    }
}
