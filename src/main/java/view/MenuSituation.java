package view;

public class MenuSituation {
    private view.commandProcessor commandProcessor;


    void processBack() {
        if (commandProcessor.getMenuStatus() == MenuStatus.MAINMENU) {
            System.out.println("this is first page");
        }  if (commandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("you are in main menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEUSERS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEALLPRODUCTS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWDISCOUNTCODES) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEREQUESTS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATEGORIES) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            }//
            else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                System.out.println("you must finish create discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("you have access to view discount codes commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("you can choose field to change discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("you have access to manage category commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("you can choose field to change category");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                System.out.println("you must finish create category");
            }
        }//

        if (commandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("you are in main menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEPRODUCTS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in seller menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWOFFS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in seller menu");
            }//
            else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                System.out.println("you must finish create discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("you have access to view discount codes commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("you can choose field to change discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("you have access to manage category commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("you can choose field to change category");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                System.out.println("you must finish create category");
            }
        }  if (commandProcessor.getMenuStatus() == MenuStatus.CUSTOMERMENU) {
            if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("you are in main menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWCART) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in customer menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWORDERS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in customer menu");
            }//
            else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                System.out.println("you must finish create discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("you have access to view discount codes commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("you can choose field to change discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("you have access to manage category commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("you can choose field to change category");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                System.out.println("you must finish create category");
            }
        }  if (commandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU) {
            if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("you are in main menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.FILTERING) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in products menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.SORTING) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in products menu");
            }//
            else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                System.out.println("you must finish create discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("you have access to view discount codes commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("you can choose field to change discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("you have access to manage category commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("you can choose field to change category");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                System.out.println("you must finish create category");
            }
        }  if (commandProcessor.getMenuStatus() == MenuStatus.PRODUCTMENU) {
            if (commandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("you are in main menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DIGEST) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in product menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTS) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in product menu");
            }//
            else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in manger menu");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                System.out.println("you must finish create discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("you have access to view discount codes commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("you can choose field to change discount code");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("you have access to manage category commands");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                commandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("you can choose field to change category");
            } else if (commandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                System.out.println("you must finish create category");
            }
        } if (commandProcessor.getMenuStatus() == MenuStatus.SALEMENU) {
            commandProcessor.setMenuStatus(MenuStatus.MAINMENU);
            System.out.println("you are in main menu");
        }

    }


    public void processHelp() {
    }


}