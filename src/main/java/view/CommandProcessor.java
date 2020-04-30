package view;

import controller.menus.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private MenuStatus menuStatus = MenuStatus.MAINMENU;
    private ProductMenu productMenu = new ProductMenu();
    private ProductsMenu productsMenu = new ProductsMenu();
    private SaleMenu saleMenu = new SaleMenu();
    private CustomerMenu customerMenu = new CustomerMenu();
    private ManagerMenu managerMenu = new ManagerMenu();
    private SellerMenu sellerMenu = new SellerMenu();
    private LoginMenu loginMenu = new LoginMenu();
    private RegisterMenu registerMenu = new RegisterMenu();
    private MenuSituation menuSituation = new MenuSituation();
    private SubMenuStatus subMenuStatus = SubMenuStatus.MAINMENU;
    private OutputHandler outputHandler = new OutputHandler();

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
            "((?!^ +$)^.+$)"//27


    };

    private Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }

    public void setSubMenuStatus(SubMenuStatus subMenuStatus) {
        this.subMenuStatus = subMenuStatus;
    }

    public SubMenuStatus getSubMenuStatus() {
        return subMenuStatus;
    }

    public void setMenuStatus(MenuStatus menuStatus) {
        this.menuStatus = menuStatus;
    }

    public MenuStatus getMenuStatus() {
        return menuStatus;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).trim().equalsIgnoreCase("exit")) {
            if (input.equalsIgnoreCase("back")) {
                menuSituation.processBack();
            } else if (input.equalsIgnoreCase("help")) {
                menuSituation.processHelp();
            } else if (!(menuStatus == MenuStatus.CREATIONMENU)) {
                if (input.matches(regex[1])) {
                    loginMenu.processLogin(getMatcher(input, regex[1]).group(1));
                } else if (input.equalsIgnoreCase("logout")) {
                    loginMenu.processLogout();
                } else if (input.matches(regex[2])) {
                    loginMenu.processEdit(getMatcher(input, regex[2]).group(1));
                } else if (input.equalsIgnoreCase("view personal info")) {
                    loginMenu.viewPersonalInfo();
                } else if (subMenuStatus == subMenuStatus.EDITACCOUNT) {
                    if (input.matches(regex[27])) {
                        loginMenu.editField(getMatcher(input, regex[27]).group(1));
                    }
                } else if (subMenuStatus == subMenuStatus.PASSWORD) {
                    if (input.matches(regex[27])) {
                        loginMenu.checkPassword(getMatcher(input, regex[27]).group(1));
                    }
                } else if (input.matches(regex[0])) {
                    registerMenu.processRegister(getMatcher(input, regex[0]).group(1), getMatcher(input, regex[0]).group(2));
                }
                if (subMenuStatus == subMenuStatus.CREATEMANAGERACCOUNT) {
                    if (input.matches(regex[27])) {
                        registerMenu.processRegister("manager", getMatcher(input, regex[27]).group(1));
                    }
                }
            } else if (menuStatus == MenuStatus.CREATIONMENU) {
                if (subMenuStatus == subMenuStatus.REGISTERATIONDETAILS) {
                    if (input.matches(regex[27])) {
                        registerMenu.completeRegisterProcess(getMatcher(input, regex[27]).group(1));
                    }
                } else if (subMenuStatus.equals(SubMenuStatus.ADDDISCOUNTCODE)) {
                    if (input.matches(regex[15])) {
                        managerMenu.createNewDiscountCode(getMatcher(input, regex[16]).group(1));
                    }
                } else if (subMenuStatus.equals(SubMenuStatus.DETAILDESCOUNTCODE)) {
                    if (input.matches(regex[15])) {
                        managerMenu.setDetailToDiscountCode(getMatcher(input, regex[16]).group(1));
                    }
                } else if (menuStatus == MenuStatus.MANAGERMENU) {
                    //ManagerMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("manage users")) {
                            managerMenu.processManageUsers();
                        } else if (input.equalsIgnoreCase("manage all products")) {
                            managerMenu.processManageAllProducts();
                        } else if (input.equalsIgnoreCase("create discount code")) {
                            managerMenu.processCreateDiscountCode();
                        } else if (input.equalsIgnoreCase("view discount codes")) {
                            managerMenu.processViewDiscountCodes();
                        } else if (input.equalsIgnoreCase("manage requests")) {
                            managerMenu.processManageRequests();
                        } else if (input.equalsIgnoreCase("manage categories")) {
                            managerMenu.processManageCategories();
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGEUSERS) {
                        if (input.matches(regex[6])) {
                            managerMenu.view(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[7])) {
                            managerMenu.deleteUser(getMatcher(input, regex[7]).group(1));
                        } else if (input.equalsIgnoreCase(" create manager profile ")) {
                            managerMenu.createManagerProfile();
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGEALLPRODUCTS) {
                        if (input.matches(regex[8])) {
                            managerMenu.removeProduct(getMatcher(input, regex[8]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWDISCOUNTCODES) {
                        if (input.matches(regex[9])) {
                            managerMenu.viewDiscountCode(getMatcher(input, regex[9]).group(1));
                        } else if (input.matches(regex[10])) {
                            managerMenu.editDiscountCode(getMatcher(input, regex[10]).group(1));
                        } else if (input.matches(regex[11])) {
                            managerMenu.removeDiscountCode(getMatcher(input, regex[11]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGEREQUESTS) {
                        if (input.matches(regex[12])) {
                            managerMenu.detailsRequest(getMatcher(input, regex[12]).group(1));
                        } else if (input.matches(regex[13])) {
                            managerMenu.acceptRequest(getMatcher(input, regex[13]).group(1));
                        } else if (input.matches(regex[14])) {
                            managerMenu.declineRequest(getMatcher(input, regex[14]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGECATAGORIES) {
                        if (input.matches(regex[15])) {
                            managerMenu.editCategory(getMatcher(input, regex[15]).group(1));
                        } else if (input.matches(regex[16])) {
                            managerMenu.addCategory(getMatcher(input, regex[16]).group(1));
                        } else if (input.matches(regex[17])) {
                            managerMenu.removeCategory(getMatcher(input, regex[17]).group(1));
                        }
                    }
                    //$$$$$
                    else if (subMenuStatus.equals(SubMenuStatus.DISCOUNTCODEFIELD)) {
                        managerMenu.discountCodeField(getMatcher(input, regex[27]).group(1));
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITDISCOUNTCODE)) {
                        managerMenu.editDiscountCodeField(getMatcher(input, regex[27]).group(1));
                    } else if (subMenuStatus.equals(SubMenuStatus.DETAILCATEGORY)) {
                        managerMenu.setDetailToCategory(getMatcher(input, regex[27]).group(1));
                    } else if (subMenuStatus.equals(SubMenuStatus.CATEGORYFIELD)) {
                        managerMenu.categoryField(getMatcher(input, regex[27]).group(1));
                    } else if (subMenuStatus.equals(SubMenuStatus.EDITCATEGORY)) {
                        managerMenu.editCategoryField(getMatcher(input, regex[27]).group(1));
                    }
                } else if (menuStatus == MenuStatus.SELLERMENU) {
                    // SellerMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("view company information")) {
                            sellerMenu.processViewCompanyInformation();
                        } else if (input.equalsIgnoreCase("view sales history")) {
                            sellerMenu.processViewSalesHistory();
                        } else if (input.equalsIgnoreCase("manage products")) {
                            sellerMenu.processManageProducts();
                        } else if (input.equalsIgnoreCase("add product")) {
                            sellerMenu.processAddProduct();
                        } else if (input.matches(regex[3])) {
                            sellerMenu.processRemoveProduct(getMatcher(input, regex[3]).group(1));
                        } else if (input.equalsIgnoreCase("show categories")) {
                            sellerMenu.processShowCategories();
                        } else if (input.equalsIgnoreCase("view offs")) {
                            sellerMenu.processViewOffs();
                        } else if (input.equalsIgnoreCase("view balance")) {
                            sellerMenu.processViewBalance();
                        }
                    } else if (subMenuStatus == SubMenuStatus.MANAGEPRODUCTS) {
                        if (input.matches(regex[6])) {
                            sellerMenu.viewProduct(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[18])) {
                            sellerMenu.viewBuyersProduct(getMatcher(input, regex[18]).group(1));
                        } else if (input.matches(regex[15])) {
                            sellerMenu.editProduct(getMatcher(input, regex[15]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWOFFS) {
                        if (input.matches(regex[6])) {
                            sellerMenu.viewOff(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[15])) {
                            sellerMenu.editOff(getMatcher(input, regex[15]).group(1));
                        } else if (input.equalsIgnoreCase("add off")) {
                            sellerMenu.addOff();
                        }
                    }
                    ///$$$$$$$$$$$$$
                    if (subMenuStatus.equals(SubMenuStatus.PRODUCTFIELD)) {
                        sellerMenu.setField(getMatcher(input, regex[27]).group(1));
                        subMenuStatus = SubMenuStatus.EDITPRODUCT;
                    }
                    if (subMenuStatus.equals(SubMenuStatus.EDITPRODUCT)) {
                        sellerMenu.editProductField(getMatcher(input, regex[27]).group(1));
                    }
                    if (subMenuStatus.equals(SubMenuStatus.ADDPRODUCT)) {
                        sellerMenu.addProduct(getMatcher(input, regex[27]).group(1));
                    }
                    if (subMenuStatus.equals(SubMenuStatus.SALEFIELD)) {
                        sellerMenu.setField(getMatcher(input, regex[27]).group(1));
                        subMenuStatus = SubMenuStatus.EDITSALE;
                    }
                    if (subMenuStatus.equals(SubMenuStatus.EDITSALE)) {
                        sellerMenu.editProductField(getMatcher(input, regex[27]).group(1));
                    } else if (subMenuStatus.equals(SubMenuStatus.ADDSALE)) {
                        sellerMenu.setDetailsToSale(getMatcher(input, regex[27]).group(1));
                    }

                } else if (menuStatus == MenuStatus.CUSTOMERMENU) {
                    //CustomerMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("view cart")) {
                            customerMenu.processViewCart();
                        } else if (input.equalsIgnoreCase("purchase")) {
                            customerMenu.processPurchase();
                        } else if (input.equalsIgnoreCase("view orders")) {
                            customerMenu.processViewOrders();
                        } else if (input.equalsIgnoreCase("view balance")) {
                            customerMenu.processViewBalance();
                        } else if (input.equalsIgnoreCase("view discount codes")) {
                            //MANAGERAM DARE
                            customerMenu.processViewDiscountCodes();
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWCART) {
                        if (input.equalsIgnoreCase("show products")) {
                            customerMenu.showProducts();
                        } else if (input.matches(regex[6])) {
                            customerMenu.viewProduct(getMatcher(input, regex[6]).group(1));
                        } else if (input.matches(regex[19])) {
                            customerMenu.increaseProductNumber(getMatcher(input, regex[19]).group(1));
                        } else if (input.matches(regex[20])) {
                            customerMenu.decreaseProductNumber(getMatcher(input, regex[20]).group(1));
                        } else if (input.equalsIgnoreCase("show total price")) {
                            customerMenu.showTotalPrice();
                        } else if (input.equalsIgnoreCase("purchase")) {
                            customerMenu.purchase();
                        }
                    } else if (subMenuStatus == SubMenuStatus.VIEWORDERS) {
                        if (input.matches(regex[21])) {
                            customerMenu.showOrder(getMatcher(input, regex[21]).group(1));
                        } else if (input.matches(regex[22])) {
                            customerMenu.rateProduct(getMatcher(input, regex[22]).group(1), Integer.parseInt(getMatcher(input, regex[22]).group(2)));
                        }
                    }
                } else if (menuStatus == MenuStatus.PRODUCTSMENU || menuStatus == MenuStatus.MAINMENU) {
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
                    }

                } else if (menuStatus == MenuStatus.PRODUCTMENU) {
                    //ProductMenu
                    if (subMenuStatus == SubMenuStatus.MAINMENU) {
                        if (input.equalsIgnoreCase("digest")) {
                            productMenu.processDigest();
                        } else if (input.equalsIgnoreCase("attributes")) {
                            productMenu.processAttributes();
                        } else if (input.matches(regex[5])) {
                            productMenu.processCompare(getMatcher(input, regex[5]).group(1));
                        } else if (input.equalsIgnoreCase("Comments")) {
                            productMenu.processComments();
                        }
                    } else if (subMenuStatus == SubMenuStatus.DIGEST) {
                        if (input.equalsIgnoreCase("add to cart")) {
                            productMenu.addToCart();
                        } else if (input.matches(regex[25])) {
                            productMenu.selectSeller(getMatcher(input, regex[25]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.COMMENTS) {
                        if (input.equalsIgnoreCase("add comment")) {
                            productMenu.addComments();
                        }
                    } else if (subMenuStatus == SubMenuStatus.COMMENTSTITLE) {
                        if (input.matches(regex[27])) {
                            productMenu.titleOfComment(getMatcher(input, regex[27]).group(1));
                        }
                    } else if (subMenuStatus == SubMenuStatus.COMMENTSCONTENT) {
                        if (input.matches(regex[27])) {
                            productMenu.contentOfComment(getMatcher(input, regex[27]).group(1));
                        }
                    }
                } else if ((menuStatus == MenuStatus.SALEMENU) || (menuStatus == MenuStatus.MAINMENU)) {
                    //SaleMenu
                    if (input.equalsIgnoreCase("offs")) {
                        saleMenu.processOffs();
                    } else if (input.matches(regex[4])) {
                        //moshtarak
                        saleMenu.processShowProductsID(getMatcher(input, regex[4]).group(1));
                    }
                } else {
                    outputHandler.showOutput(0);
                }

            }
            if (input.equals("exit")) {
                outputHandler.showOutput(1);
            }

        }

    }
