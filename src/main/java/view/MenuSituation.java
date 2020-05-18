package view;

import controller.menus.LoginMenu;
import controller.menus.ManagerMenu;
import controller.menus.RegisterMenu;
import controller.menus.SellerMenu;

public class MenuSituation {
    private int outputNo;

    private static void accountFields() {
        if (RegisterMenu.getDetailMenu() == 0) {
            System.out.println("please enter your password");
        } else if (RegisterMenu.getDetailMenu() == 1) {
            System.out.println("please enter your name");
        } else if (RegisterMenu.getDetailMenu() == 2) {
            System.out.println("please enter your last name");
        } else if (RegisterMenu.getDetailMenu() == 3) {
            System.out.println("please enter your Email name");
        } else if (RegisterMenu.getDetailMenu() == 4) {
            System.out.println("please enter your phone number name");
        } else if (RegisterMenu.getDetailMenu() == 5) {
            System.out.println("please enter your birthday date name");
        }
    }

    private static void firmFields() {
        if (RegisterMenu.getDetailMenu() == 0) {
            System.out.println("please enter firm name");
        } else if (RegisterMenu.getDetailMenu() == 1) {
            System.out.println("please enter firm phone number");
        } else if (RegisterMenu.getDetailMenu() == 2) {
            System.out.println("please enter your firm address");
        }
    }


    private static void discountFields() {
        if (ManagerMenu.getDetailMenu() == 0) {
            System.out.println("please enter start of discount date");
        } else if (ManagerMenu.getDetailMenu() == 1) {
            System.out.println("please enter end of discount date");
        } else if (ManagerMenu.getDetailMenu() == 2) {
            System.out.println("please enter max amount");
        } else if (ManagerMenu.getDetailMenu() == 3) {
            System.out.println("please enter total time of use");
        } else if (RegisterMenu.getDetailMenu() == 4) {
            System.out.println("please enter amount");
        } else if (RegisterMenu.getDetailMenu() == 5) {
            System.out.println("please enter account username");
        }
    }

    private static void categoryField() {
        if (ManagerMenu.getDetailMenu() == 0) {
            System.out.println("please enter product");
        } else if (ManagerMenu.getDetailMenu() == 1) {
            System.out.println("please enter trait");
        }
    }

    public static void productField() {
        if (SellerMenu.getDetailMenu() == 0) {
            System.out.println("please enter id");
        } else if (SellerMenu.getDetailMenu() == 1) {
            System.out.println("please enter name");
        } else if (SellerMenu.getDetailMenu() == 2) {
            System.out.println("please enter price");
        } else if (SellerMenu.getDetailMenu() == 3) {
            System.out.println("please enter category names");
        } else if (SellerMenu.getDetailMenu() == 4) {
            System.out.println("please enter additional details");
        } else if (SellerMenu.getDetailMenu() == 5) {
            System.out.println("please enter number of product");
        } else if (SellerMenu.getDetailMenu() == 6) {
            System.out.println("please enter trait");
        }
    }

    public static void saleField() {
        if (SellerMenu.getDetailMenu() == 0) {
            System.out.println("please enter id");
        } else if (SellerMenu.getDetailMenu() == 1) {
            System.out.println("please enter start of sale");
        } else if (SellerMenu.getDetailMenu() == 2) {
            System.out.println("please enter end of sale");
        } else if (SellerMenu.getDetailMenu() == 3) {
            System.out.println("please enter amount");
        } else if (SellerMenu.getDetailMenu() == 4) {
            System.out.println("please enter product");
        }
    }

    public static void processHelp() {
        if (CommandProcessor.getInternalMenu() == InternalMenu.CHANGEDETAILS) {

        }
        if (CommandProcessor.getMenuStatus() == MenuStatus.MAINMENU) {
            System.out.println("create account [type][username]\nlogin [username]\nlogout\noffs\nproducts");
        } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PASSWORD) {
            System.out.println("please enter your password");
        } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.REGISTERATIONDETAILS) {
            accountFields();
        }else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDFIRM) {
                firmFields();

        }//ManagerMenu
        else if (CommandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("view personal info\nedit[field]\nmanage users\nmanage all products\ncreate discount code\nview discount codes\nmange requests\nmanage categories");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEUSERS) {
                System.out.println("view [username]\ndelete user [username]\ncreate manager profile");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEALLPRODUCTS) {
                System.out.println("remove [productId]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWDISCOUNTCODES) {
                System.out.println("view discount code [code]\nedit discount code [code]\nremove discount code [code]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEREQUESTS) {
                System.out.println("details [requestId]\naccept [requestId]\ndecline [requestId]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATEGORIES) {
                System.out.println("edit [category]\nadd [category]\nremove [category]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                System.out.println("edit [field]");
            }//
            else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                System.out.println("please enter discount code id");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                discountFields();
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                System.out.println("please enter field you want to change");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                System.out.println("please enter your new value for " + ManagerMenu.getField());
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                System.out.println("now you have access to manage category commands");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                System.out.println("please enter your new value for " + ManagerMenu.getField());
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                categoryField();
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CREATEMANAGERACCOUNT) {
                System.out.println("please enter username");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITACCOUNT) {
                accountFields();
            }
        }//SellerMenu
        else if (CommandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("view personal info\nview company information\nview sales history\nmanage products\nadd product\nremove product [product Id]\nshow categories\nview offs\nview balance");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEPRODUCTS) {
                System.out.println("view [productId]\nview buyers [productId]\nedit [productId]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWOFFS) {
                System.out.println("view [offId]\nedit [offId]\nadd off");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                System.out.println("edit [field]");
            }
            //
            else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PRODUCTFIELD) {
                System.out.println("please enter field");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITPRODUCT) {
                System.out.println("please enter your new value for " + SellerMenu.getField());
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDPRODUCT) {
                productField();
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.SALEFIELD) {
                System.out.println("please enter field");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITSALE) {
                System.out.println("please enter your new value for " + SellerMenu.getField());
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDSALE) {
                System.out.println("you can choose field to change category");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITSELLERACCOUNT) {
                System.out.println("please enter your new value for " + LoginMenu.getField());
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMNAME) {
                System.out.println("enter firm's name");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMFIELD) {
                System.out.println("enter field you want to change");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITFIRM) {
                System.out.println("please enter your new value for " + LoginMenu.getField());
            }
            //CustomerMenu
        } else if (CommandProcessor.getMenuStatus() == MenuStatus.CUSTOMERMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("view personal info\nview cart\npurchase\nview orders\nview balance\nview discount codes");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWCART) {
                System.out.println("show products\nview [productId]\nincrease [productId]\ndecrease [productId]\nshow total price\npurchase");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWORDERS) {
                System.out.println("show order [orderId]\nrate [productId] [1-5]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                System.out.println("edit [field]");
            }//
            else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.INCREASEPRODUCTNUMBER) {
                System.out.println("enter number of product you want to add");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DECREASEPRODUCTNUMBER) {
                System.out.println("enter number of product you want to remove");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITACCOUNT) {
                System.out.println("please enter your new value for " + LoginMenu.getField());
            }else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.RECIVERINFORMATION) {
                System.out.println("please enter your current phone number");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.HAVEDISCOUNT) {
                System.out.println("do you have discount (yes|no)");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CHECKDISCOUNTCODE) {
                System.out.println("please enter your discount");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PAYMENT) {
                System.out.println("please enter payment");
            }
            //ProductsMenu
        } else if (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("products\nview categories\nfiltering\nsorting\nshow products\nshow products [productsId]");
            }
            //ProductMenu
        } else if (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("digest\nattributes\ncompare [productID]\nComments\noffs\nshow product [productId]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DIGEST) {
                System.out.println("add to cart\nselect seller [seller_username]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTS) {
                System.out.println("Add comment\nTitle:\nContent:");
            }//
            else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTSTITLE) {
                System.out.println("enter your comment title");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTSCONTENT) {
                System.out.println("enter your comment content");
            }
            //SaleMenu
        } else if (CommandProcessor.getMenuStatus() == MenuStatus.SALEMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                System.out.println("offs\nshow product [productId]\nfiltering\nsorting");
            }
        } else if ((CommandProcessor.getMenuStatus() == MenuStatus.SALEMENU) || (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU)) {
             if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FILTERING) {
                System.out.println("show available filters\nfilter [an available]\ncurrent filters[]\ndisable filter [a selected filter]");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.SORTING) {
                System.out.println("show available sorts\nsort [an available sort]\ncurrent sort\ndisable sort");
             } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PERIODFILTER) {
                 System.out.println("enter 2 number to filter");
             } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMFILTER) {
                 System.out.println("enter firm name to filter");
             } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYNAMEFILTER) {
                 System.out.println("enter category name to filter");
             } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PRODUCTNAMEFILTER) {
                 System.out.println("enter product name to filter");
             }
        }
        System.out.println("\nback\nhelp");

    }


    public static void processBack() {
        if (CommandProcessor.getInternalMenu() == InternalMenu.CHANGEDETAILS) {
            System.out.println("you must finish process first");
        }
        if (CommandProcessor.getMenuStatus() == MenuStatus.MAINMENU) {
            System.out.println("this is first page");
        } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PASSWORD) {
            CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
            System.out.println("now you are in main menu");
        }//ManagerMenu
        else if (CommandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                System.out.println("now you are in main menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEUSERS) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEALLPRODUCTS) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWDISCOUNTCODES) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEREQUESTS) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGECATEGORIES) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
            }//
            else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDDISCOUNTCODE) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("now you are in manger menu");
                //  } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILDESCOUNTCODE) {
                //  System.out.println("you must finish create discount code");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DISCOUNTCODEFIELD) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWDISCOUNTCODES);
                System.out.println("now you have access to view discount codes commands");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITDISCOUNTCODE) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.DISCOUNTCODEFIELD);
                System.out.println("now you can choose field to change discount code");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYFIELD) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGECATEGORIES);
                System.out.println("now you have access to manage category commands");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITCATEGORY) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.CATEGORYFIELD);
                System.out.println("now you can choose field to change category");
                //   } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DETAILCATEGORY) {
                //      System.out.println("you must finish create category");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CREATEMANAGERACCOUNT) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MANAGEUSERS);
                RegisterMenu.setManagerWant(false);
                System.out.println("now you have access to manage users command");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITACCOUNT) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
                System.out.println("you can choose field to edit");
            } else if (CommandProcessor.getMenuStatus() == MenuStatus.SELLERMENU) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("now you are in main menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MANAGEPRODUCTS) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("now you are in seller menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWOFFS) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("now you are in seller menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("now you are in seller menu");
                }
                //
                else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PRODUCTFIELD) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("now you are in seller menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITPRODUCT) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.PRODUCTFIELD);
                    System.out.println("you can choose product field to edit");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITSPECIFICATION) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.EDITPRODUCT);
                    System.out.println("you can choose new key");
                    //  } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDPRODUCT) {
                    //    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    //      System.out.println("now you are in seller menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.SALEFIELD) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWOFFS);
                    System.out.println("you have access to view offs command");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITSALE) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.SALEFIELD);
                    System.out.println("you can choose field to edit");
                    // } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.ADDSALE) {
                    //      CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWOFFS);
                    //      System.out.println("");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITSELLERACCOUNT) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
                    System.out.println("you can choose field to edit");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMNAME) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
                    System.out.println("you can choose field to edit");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMFIELD) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMNAME);
                    System.out.println("you can choose firm");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITFIRM) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FIRMFIELD);
                    System.out.println("you can choose firm's field");
                }
                //CustomerMenu
            } else if (CommandProcessor.getMenuStatus() == MenuStatus.CUSTOMERMENU) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("you are in main menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWCART) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("you are in customer menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWORDERS) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("you are in customer menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.VIEWPERSONALINFO) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("now you are in customer menu");
                }//
                else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.INCREASEPRODUCTNUMBER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
                    System.out.println("you have access to view cart commands");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DECREASEPRODUCTNUMBER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
                    System.out.println("you have access to view cart commands");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.EDITACCOUNT) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWPERSONALINFO);
                    System.out.println("you can choose field to change");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.RECIVERINFORMATION) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.VIEWCART);
                    System.out.println("you have access to view cart commands");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.HAVEDISCOUNT) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.RECIVERINFORMATION);
                    System.out.println("please enter your current phone number");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CHECKDISCOUNTCODE) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.HAVEDISCOUNT);
                    System.out.println("do you have discount (yes|no)");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PAYMENT) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.CHECKDISCOUNTCODE);
                    System.out.println("please enter your discount");
                }
                //ProductsMenu
            } else if (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("you are in main menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FILTERING) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("you are in products menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.SORTING) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                    System.out.println("you are in products menu");
                }
                //ProductMenu
            } else if (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTMENU) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("you are in main menu");
                }
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.DIGEST) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in product menu");
            } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTS) {
                CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                System.out.println("you are in product menu");
            }
            // else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTSTITLE) {
            //    CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
            // System.out.println("you are in manger menu");
            // } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.COMMENTSCONTENT) {
            //     CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
            // System.out.println("enter comment content");
            //}
            //SaleMenu
            else if (CommandProcessor.getMenuStatus() == MenuStatus.SALEMENU) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("you are in main menu");
                }
            } else if ((CommandProcessor.getMenuStatus() == MenuStatus.SALEMENU) || (CommandProcessor.getMenuStatus() == MenuStatus.PRODUCTSMENU)) {
                if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.MAINMENU) {
                    CommandProcessor.setMenuStatus(MenuStatus.MAINMENU);
                    System.out.println("you are in main menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FILTERING) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.SORTING) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PERIODFILTER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
                    System.out.println("you have access to filter menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.FIRMFILTER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
                    System.out.println("you have access to filter menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.CATEGORYNAMEFILTER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
                    System.out.println("you have access to filter menu");
                } else if (CommandProcessor.getSubMenuStatus() == SubMenuStatus.PRODUCTNAMEFILTER) {
                    CommandProcessor.setSubMenuStatus(SubMenuStatus.FILTERING);
                    System.out.println("you have access to filter menu");
                }
            }
        }

    }


}