package view;

import controller.menus.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private static MenuStatus menuStatus = MenuStatus.MAINMENU;
    private static SubMenuStatus subMenuStatus = SubMenuStatus.MAINMENU;
    private static InternalMenu internalMenu = InternalMenu.MAINMENU;
    //
    //


    public static MenuStatus getMenuStatus() {
        return menuStatus;
    }

    public static void setMenuStatus(MenuStatus menuStatus) {
        CommandProcessor.menuStatus = menuStatus;
    }

    public static SubMenuStatus getSubMenuStatus() {
        return subMenuStatus;
    }

    public static void setSubMenuStatus(SubMenuStatus subMenuStatus) {
        CommandProcessor.subMenuStatus = subMenuStatus;
    }

    public static InternalMenu getInternalMenu() {
        return internalMenu;
    }

    public static void setInternalMenu(InternalMenu internalMenu) {
        CommandProcessor.internalMenu = internalMenu;
    }

    private String[] regex = {"(?i)create\\s+account\\s+((?!^ +$)^.+$)",
            "(?i)login\\s+((?!^ +$)^.+$)",//1
            "(?i)edit\\s+((?!^ +$)^.+$)",//2
            "(?i)remove\\s+product\\s+((?!^ +$)^.+$)",//3
            "(?i)show\\s+product\\s+((?!^ +$)^.+$)",//4
            "(?i)compare\\s+((?!^ +$)^.+$)",//5
            "(?i)view\\s+((?!^ +$)^.+$)",//6
            "(?i)delete\\s+user((?!^ +$)^.+$)",//7
            "(?i)remove\\s+((?!^ +$)^.+$)",//8
            "(?i)view\\s+discount\\s+code((?!^ +$)^.+$)",//9
            "(?i)edit\\s+discount\\s+code((?!^ +$)^.+$)",//10
            "(?i)remove\\s+discount\\s+code((?!^ +$)^.+$)",//11
            "(?i)details\\s+((?!^ +$)^.+$)",//12
            "(?i)accept\\s+((?!^ +$)^.+$)",//13
            "(?i)decline\\s+((?!^ +$)^.+$)",//14
            "(?i)edit\\s+((?!^ +$)^.+$)",//15
            "(?i)add\\s+((?!^ +$)^.+$)",//16
            "(?i)remove\\s+((?!^ +$)^.+$)",//17
            "(?i)view\\s+buyers\\s+((?!^ +$)^.+$)",//18
            "(?i)increase((?!^ +$)^.+$)",//19
            "(?i)decrease((?!^ +$)^.+$)",//20
            "(?i)show\\s+order((?!^ +$)^.+$)",//21
            "(?i)rate((?!^ +$)^.+$) (\\d+)",//22
            "(?i)filter((?!^ +$)^.+$)",//23
            "(?i)disable\\s+filter",//24
            "(?i)sort((?!^ +$)^.+$)",//25
            "(?i)select seller",//26
            "((?!^ +$)^.+$)",//27
            ""


    };

    private Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }

    public void run() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).trim().equalsIgnoreCase("exit")) {
            if (input.equalsIgnoreCase("back")) {
                MenuSituation.processBack();
            }
            if (input.equalsIgnoreCase("help")) {
                MenuSituation.processHelp();
            }
            if (internalMenu == InternalMenu.MAINMENU) {
                if (input.matches(regex[1])) {
                    LoginMenu.processLogin(getMatcher(input, regex[1]).group(1));
                } else if (input.equalsIgnoreCase("logout")) {
                    LoginMenu.processLogout();
                } else if (input.matches(regex[0])) {
                    RegisterMenu.processRegister(getMatcher(input, regex[0]).group(1), getMatcher(input, regex[0]).group(2));
                }
            }
            if (subMenuStatus == subMenuStatus.PASSWORD) {
                if (input.matches(regex[27])) {
                    LoginMenu.checkPassword(getMatcher(input, regex[27]).group(1));
                }
            }
            if (subMenuStatus == subMenuStatus.REGISTERATIONDETAILS) {
                if (input.matches(regex[27])) {
                    RegisterMenu.completeRegisterProcess(getMatcher(input, regex[27]).group(1));
                }
            }

            if (menuStatus == MenuStatus.MANAGERMENU) {
                //ManagerMenu
                if (subMenuStatus == SubMenuStatus.MAINMENU) {
                    if (input.equalsIgnoreCase("manage users")) {
                        ManagerMenu.processManageUsers();
                    } else if (input.equalsIgnoreCase("view personal info")) {
                        LoginMenu.viewPersonalInfo();
                    } else if (input.equalsIgnoreCase("manage all products")) {
                        ManagerMenu.processManageAllProducts();
                    } else if (input.equalsIgnoreCase("create discount code")) {
                        ManagerMenu.processCreateDiscountCode();
                    } else if (input.equalsIgnoreCase("view discount codes")) {
                        ManagerMenu.processViewDiscountCodes();
                    } else if (input.equalsIgnoreCase("manage requests")) {
                        ManagerMenu.processManageRequests();
                    } else if (input.equalsIgnoreCase("manage categories")) {
                        ManagerMenu.processManageCategories();
                    } else if (subMenuStatus == SubMenuStatus.VIEWPERSONALINFO) {
                        if (input.matches(regex[2])) {
                            LoginMenu.processEdit(getMatcher(input, regex[2]).group(1));
                        }
                    }
                } else if (subMenuStatus == SubMenuStatus.MANAGEUSERS) {
                    if (input.matches(regex[6])) {
                        ManagerMenu.view(getMatcher(input, regex[6]).group(1));
                    } else if (input.matches(regex[7])) {
                        ManagerMenu.deleteUser(getMatcher(input, regex[7]).group(1));
                    } else if (input.equalsIgnoreCase(" create manager profile ")) {
                        ManagerMenu.createManagerProfile();
                    }
                } else if (subMenuStatus == SubMenuStatus.MANAGEALLPRODUCTS) {
                    if (input.matches(regex[8])) {
                        ManagerMenu.removeProduct(getMatcher(input, regex[8]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.VIEWDISCOUNTCODES) {
                    if (input.matches(regex[9])) {
                        ManagerMenu.viewDiscountCode(getMatcher(input, regex[9]).group(1));
                    } else if (input.matches(regex[10])) {
                        ManagerMenu.editDiscountCode(getMatcher(input, regex[10]).group(1));
                    } else if (input.matches(regex[11])) {
                        ManagerMenu.removeDiscountCode(getMatcher(input, regex[11]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.MANAGEREQUESTS) {
                    if (input.matches(regex[12])) {
                        ManagerMenu.detailsRequest(getMatcher(input, regex[12]).group(1));
                    } else if (input.matches(regex[13])) {
                        ManagerMenu.acceptRequest(getMatcher(input, regex[13]).group(1));
                    } else if (input.matches(regex[14])) {
                        ManagerMenu.declineRequest(getMatcher(input, regex[14]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.MANAGECATEGORIES) {
                    if (input.matches(regex[15])) {
                        ManagerMenu.editCategory(getMatcher(input, regex[15]).group(1));
                    } else if (input.matches(regex[16])) {
                        ManagerMenu.addCategory(getMatcher(input, regex[16]).group(1));
                    } else if (input.matches(regex[17])) {
                        ManagerMenu.removeCategory(getMatcher(input, regex[17]).group(1));
                    }
                    //sub
                    else if (subMenuStatus.equals(SubMenuStatus.DISCOUNTCODEFIELD)) {
                        if (input.matches(regex[27])) {
                            ManagerMenu.discountCodeField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITDISCOUNTCODE)) {
                        if (input.matches(regex[27])) {
                            ManagerMenu.editDiscountCodeField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.ADDDISCOUNTCODE)) {
                        if (input.matches(regex[15])) {
                            ManagerMenu.createNewDiscountCode(getMatcher(input, regex[16]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.DETAILDESCOUNTCODE)) {
                        if (input.matches(regex[15])) {
                            ManagerMenu.setDetailToDiscountCode(getMatcher(input, regex[16]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.DETAILCATEGORY)) {
                        if (input.matches(regex[27])) {
                            ManagerMenu.setDetailToCategory(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.CATEGORYFIELD)) {
                        if (input.matches(regex[27])) {
                            ManagerMenu.categoryField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITCATEGORY)) {
                        if (input.matches(regex[27])) {
                            ManagerMenu.editCategoryField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == subMenuStatus.EDITACCOUNT) {
                        if (input.matches(regex[27])) {
                            LoginMenu.editAccount(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == subMenuStatus.CREATEMANAGERACCOUNT) {
                        if (input.matches(regex[27])) {
                            RegisterMenu.processRegister("manager", getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == subMenuStatus.EDITSELLERACCOUNT) {
                        if (input.matches(regex[27])) {
                            LoginMenu.editSellerField(getMatcher(input, regex[27]).group(1));
                        }
                    }

                }
                if (menuStatus == MenuStatus.SELLERMENU) {
                    // SellerMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("view company information")) {
                            SellerMenu.processViewCompanyInformation();
                        } else if (input.equalsIgnoreCase("view personal info")) {
                            LoginMenu.viewPersonalInfo();
                        } else if (input.equalsIgnoreCase("view sales history")) {
                            SellerMenu.processViewSalesHistory();
                        } else if (input.equalsIgnoreCase("manage products")) {
                            SellerMenu.processManageProducts();
                        } else if (input.equalsIgnoreCase("add product")) {
                            SellerMenu.processAddProduct();
                        } else if (input.matches(regex[3])) {
                            SellerMenu.processRemoveProduct(getMatcher(input, regex[3]).group(1));
                        } else if (input.equalsIgnoreCase("show categories")) {
                            SellerMenu.processShowCategories();
                        } else if (input.equalsIgnoreCase("view offs")) {
                            SellerMenu.processViewOffs();
                        } else if (input.equalsIgnoreCase("view balance")) {
                            SellerMenu.processViewBalance();
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGEPRODUCTS) {
                        if (input.matches(regex[6])) {
                            SellerMenu.viewProduct(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[18])) {
                            SellerMenu.viewBuyersProduct(getMatcher(input, regex[18]).group(1));
                        } else if (input.matches(regex[15])) {
                            SellerMenu.editProduct(getMatcher(input, regex[15]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWOFFS) {
                        if (input.matches(regex[6])) {
                            SellerMenu.viewOff(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[15])) {
                            SellerMenu.editOff(getMatcher(input, regex[15]).group(1));
                        } else if (input.equalsIgnoreCase("add off")) {
                            SellerMenu.addOff();
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWPERSONALINFO) {
                        if (input.matches(regex[2])) {
                            LoginMenu.processEdit(getMatcher(input, regex[2]).group(1));
                        }
                    }
                    ///$$$$$$$$$$$$$
                    if (subMenuStatus.equals(SubMenuStatus.PRODUCTFIELD)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.productField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITPRODUCT)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.editProductField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.ADDPRODUCT)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.addProduct(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.SALEFIELD)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.offField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITSALE)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.editProductField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.FIRMFIELD)) {
                        if (input.matches(regex[27])) {
                            LoginMenu.firmField(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITFIRM)) {
                        if (input.matches(regex[27])) {
                            LoginMenu.editFirm(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.FIRMNAME)) {
                        if (input.matches(regex[27])) {
                            LoginMenu.firmName(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus.equals(SubMenuStatus.ADDSALE)) {
                        if (input.matches(regex[27])) {
                            SellerMenu.setDetailsToSale(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == subMenuStatus.EDITSELLERACCOUNT) {
                        if (input.matches(regex[27])) {
                            LoginMenu.editSellerField(getMatcher(input, regex[27]).group(1));
                        }
                    }
                    if (subMenuStatus == subMenuStatus.ADDFIRM) {
                        if (input.matches(regex[27])) {
                            RegisterMenu.createFirm(getMatcher(input, regex[27]).group(1));
                        }
                    }


                }
                if (menuStatus == MenuStatus.CUSTOMERMENU) {
                    //CustomerMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("view cart")) {
                            CustomerMenu.processViewCart();
                        } else if (input.equalsIgnoreCase("view personal info")) {
                            LoginMenu.viewPersonalInfo();
                        } else if (input.equalsIgnoreCase("purchase")) {
                            CustomerMenu.processPurchase();
                        } else if (input.equalsIgnoreCase("view orders")) {
                            CustomerMenu.processViewOrders();
                        } else if (input.equalsIgnoreCase("view balance")) {
                            CustomerMenu.processViewBalance();
                        } else if (input.equalsIgnoreCase("view discount codes")) {
                            CustomerMenu.processViewDiscountCodes();
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWCART) {
                        if (input.equalsIgnoreCase("show products")) {
                            CustomerMenu.showProducts();
                        } else if (input.matches(regex[6])) {
                            CustomerMenu.viewProduct(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[19])) {
                            CustomerMenu.increaseProductNumber(getMatcher(input, regex[19]).group(1));
                        } else if (input.matches(regex[20])) {
                            CustomerMenu.decreaseProductNumber(getMatcher(input, regex[20]).group(1));
                        } else if (input.equalsIgnoreCase("show total price")) {
                            CustomerMenu.showTotalPrice();
                        } else if (input.equalsIgnoreCase("purchase")) {
                            CustomerMenu.purchase();
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWORDERS) {
                        if (input.matches(regex[21])) {
                            CustomerMenu.showOrder(getMatcher(input, regex[21]).group(1));
                        } else if (input.matches(regex[22])) {
                            CustomerMenu.rateProduct(getMatcher(input, regex[22]).group(1), Integer.parseInt(getMatcher(input, regex[22]).group(2)));
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWPERSONALINFO) {
                        if (input.matches(regex[2])) {
                            LoginMenu.processEdit(getMatcher(input, regex[2]).group(1));
                        }
                        //SUB
                    } else if (subMenuStatus == SubMenuStatus.INCREASEPRODUCTNUMBER) {
                        if (input.matches(regex[21])) {
                            CustomerMenu.increaseLogProduct(getMatcher(input, regex[21]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.DECREASEPRODUCTNUMBER) {
                        if (input.matches(regex[21])) {
                            CustomerMenu.decreaseLogProduct(getMatcher(input, regex[21]).group(1));
                        }
                    } else if (subMenuStatus == subMenuStatus.EDITACCOUNT) {
                        if (input.matches(regex[27])) {
                            LoginMenu.editAccount(getMatcher(input, regex[27]).group(1));
                        }
                    }
                }
                //ReceiverInformation
                if (menuStatus == MenuStatus.PURCHASE) {
                    if (subMenuStatus == SubMenuStatus.RECIVERINFORMATION) {
                        if (input.matches(regex[27])) {
                            RegisterMenu.receiverInformation(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.HAVEDISCOUNT) {
                        if (input.matches(regex[27])) {
                            CustomerMenu.haveDiscount(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.CHECKDISCOUNTCODE) {
                        if (input.matches(regex[27])) {
                            CustomerMenu.discountCodeValidation(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.PAYMENT) {
                        if (input.matches(regex[27])) {
                            CustomerMenu.payment(getMatcher(input, regex[27]).group(1));
                        }
                    }
                }
                /*if (menuStatus == MenuStatus.PRODUCTSMENU || menuStatus == MenuStatus.MAINMENU) {
                    //ProductsMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("products")) {
                            productsMenu.processProducts();
                        } else if (input.equalsIgnoreCase("view categories")) {
                            productsMenu.processViewCategories();
                        } else if (input.equalsIgnoreCase("filtering")) {
                            productsMenu.processFiltering();
                        } else if (input.equalsIgnoreCase("sorting")) {
                            productsMenu.processSorting();
                        } else if (input.equalsIgnoreCase("show products")) {
                            productsMenu.processShowProducts();
                        } else if (input.matches(regex[4])) {
                            productsMenu.processShowProductsID(getMatcher(input, regex[4]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.FILTERING) {
                        if (input.equalsIgnoreCase("show available filters ")) {
                            productsMenu.showAvailableFilters();
                        } else if (input.matches(regex[23])) {
                            productsMenu.filter(getMatcher(input, regex[23]).group(1));
                        } else if (input.equalsIgnoreCase("current filters")) {
                            productsMenu.currentFilters();
                        } else if (input.matches(regex[24])) {
                            productsMenu.disableFilter(getMatcher(input, regex[24]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.SORTING) {
                        if (input.equalsIgnoreCase("show available sorts")) {
                            productsMenu.showAvailableSorts();
                        } else if (input.matches(regex[25])) {
                            productsMenu.sort(getMatcher(input, regex[25]).group(1));
                        } else if (input.equalsIgnoreCase("current sort")) {
                            productsMenu.currentSorts();
                        } else if (input.equalsIgnoreCase("disable sort")) {
                            productsMenu.disableSort();
                        }
                    } /*else if (input.equalsIgnoreCase("purchase")) {
                        customerMenu.processPurchase();
                    }
                    */

            } else if (menuStatus == MenuStatus.PRODUCTMENU) {
                //ProductMenu
                if (subMenuStatus == SubMenuStatus.MAINMENU) {
                    if (input.equalsIgnoreCase("digest")) {
                        ProductMenu.processDigest();
                    } else if (input.equalsIgnoreCase("attributes")) {
                        ProductMenu.processAttributes();
                    } else if (input.matches(regex[5])) {
                        ProductMenu.processCompare(getMatcher(input, regex[5]).group(1));
                    } else if (input.equalsIgnoreCase("Comments")) {
                        ProductMenu.processComments();
                    }
                } else if (subMenuStatus == SubMenuStatus.DIGEST) {
                    if (input.equalsIgnoreCase("add to cart")) {
                        ProductMenu.addToCart();
                    } else if (input.matches(regex[25])) {
                        ProductMenu.selectSeller(getMatcher(input, regex[25]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.COMMENTS) {
                    if (input.equalsIgnoreCase("add comment")) {
                        ProductMenu.addComments();
                    }
                } else if (subMenuStatus == SubMenuStatus.COMMENTSTITLE) {
                    if (input.matches(regex[27])) {
                        ProductMenu.titleOfComment(getMatcher(input, regex[27]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.COMMENTSCONTENT) {
                    if (input.matches(regex[27])) {
                        ProductMenu.contentOfComment(getMatcher(input, regex[27]).group(1));
                    }
                }
            }
            if (menuStatus == MenuStatus.PRODUCTSMENU|| menuStatus == MenuStatus.SALEMENU) {
                if (input.equalsIgnoreCase("filtering")) {
                    ProductsMenu.processFiltering();
                } else if (input.equalsIgnoreCase("sorting")) {
                    ProductsMenu.processSorting();
                } else if (subMenuStatus == SubMenuStatus.FILTERING) {
                    if (input.equalsIgnoreCase("show available filters ")) {
                        ProductsMenu.showAvailableFilters();
                    } else if (input.matches(regex[23])) {
                        ProductsMenu.filter(getMatcher(input, regex[23]).group(1));
                    } else if (input.equalsIgnoreCase("current filters")) {
                        ProductsMenu.currentFilters();
                    } else if (input.matches(regex[24])) {
                        ProductsMenu.disableFilter(getMatcher(input, regex[24]).group(1));
                    }
                } else if (subMenuStatus == SubMenuStatus.SORTING) {
                    if (input.equalsIgnoreCase("show available sorts")) {
                        ProductsMenu.showAvailableSorts();
                    } else if (input.matches(regex[25])) {
                        ProductsMenu.sort(getMatcher(input, regex[25]).group(1));
                    } else if (input.equalsIgnoreCase("current sort")) {
                        ProductsMenu.currentSorts();
                    } else if (input.equalsIgnoreCase("disable sort")) {
                        ProductsMenu.disableSort();
                    }
                }
            }
            if ( (menuStatus == MenuStatus.MAINMENU)) {
                //SaleMenu
                if (input.equalsIgnoreCase("offs")) {
                    SaleMenu.processOffs();
                }else if (input.equalsIgnoreCase("products")) {
                    ProductsMenu.processProducts();
                }
            } else {
                OutputMassageHandler.showOutput(0);
            }
            if (input.equals("exit")) {
                OutputMassageHandler.showOutput(1);
            }


        }


    }
}

}