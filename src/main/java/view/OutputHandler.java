package view;

import controller.request.Request;
import model.accounts.Account;
import model.firms.Firm;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Product;

public class OutputHandler {
    private CommandProcessor commandProcessor;
    // 0 = invalidCommand
    // 1 = exit

    /* register menu
    username password name lastname email phoneNo
     */
    public void showOutput(int output) {
        if (output == 0) {
            System.out.println("your command is invalid");
        } else if (output == 1) {
            System.out.println("exit");
        }
    }

    public void showManagerMenuOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("please enter manager username");
        } else if (output == 2) {
            System.out.println("product Id format is invalid");
        } else if (output == 3) {
            System.out.println("no product exist with this Id");
        } else if (output == 4) {
            System.out.println("discount code Id format is inavlid");
        } else if (output == 5) {
            System.out.println("no discount code exist with this Id");
        } else if (output == 6) {
            System.out.println("please enter discount code Id");
        } else if (output == 7) {
            System.out.println("please enter start of discount period");
        } else if (output == 8) {
            System.out.println("start of discount period format is invalid");
        } else if (output == 9) {
            System.out.println("please enter end of discount period");
        } else if (output == 10) {
            System.out.println("end of discount period format is invalid");
        } else if (output == 11) {
            System.out.println("please enter maximum discount amount");
        } else if (output == 12) {
            System.out.println("maximum discount amount format is invalid");
        } else if (output == 13) {
            System.out.println("please enter total time of use");
        } else if (output == 14) {
            System.out.println("total time of use format is invalid");
        } else if (output == 15) {
            System.out.println("please enter discount code field you want to change");
        } else if (output == 16) {
            System.out.println("start of discount period changed");
        } else if (output == 17) {
            System.out.println("end of discount period changed");
        } else if (output == 18) {
            System.out.println("maximum discount amount changed");
        } else if (output == 19) {
            System.out.println("total time of use changed");
        } else if (output == 20) {
            System.out.println("request format is invalid");
        } else if (output == 21) {
            System.out.println("no request exist with this ID");
        } else if (output == 22) {
            System.out.println("category format is invalid");
        } else if (output == 23) {
            System.out.println("no category exist with this name");
        } else if (output == 24) {
            System.out.println("total time of use format is invalid");
        }
    }


    public void showCustomerOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("please enter product number");
        } else if (output == 2) {
            System.out.println("please enter your password");
        } else if (output == 3) {
            System.out.println("password format is invalid");
        } else if (output == 4) {
            System.out.println("please enter your name");
        } else if (output == 5) {
            System.out.println("name format is invalid");
        } else if (output == 6) {
            System.out.println("please enter your lastname");
        } else if (output == 7) {
            System.out.println("lastname format is invalid");
        } else if (output == 8) {
            System.out.println("please enter your Email");
        } else if (output == 9) {
            System.out.println("email format is invalid");
        } else if (output == 10) {
            System.out.println("please enter your phone number");
        } else if (output == 11) {
            System.out.println("phone number format is invalid");
        } else if (output == 12) {
            System.out.println("register successful");
        } else if (output == 13) {
            System.out.println("no user exist with this username");
        } else if (output == 14) {
            System.out.println("your password is wrong");
        } else if (output == 15) {
            System.out.println("login successful");
        } else if (output == 16) {
            System.out.println("no field matches with this");
        } else if (output == 17) {
            System.out.println("password changed");
        } else if (output == 18) {
            System.out.println("name changed");
        } else if (output == 19) {
            System.out.println("last name changed");
        } else if (output == 20) {
            System.out.println("Email changed");
        } else if (output == 21) {
            System.out.println("phone number changed");
        } else if (output == 22) {
            System.out.println("logout");
        } else if (output == 23) {
            System.out.println("you cannot create manager profile");
        } else if (output == 24) {
            System.out.println("You've logged in before");
        } else if (output == 25) {
            System.out.println("you have to login first");
        } else if (output == 26) {
            System.out.println("no role matches with this");
        } else if (output == 27) {
            System.out.println("you have already requested for new account");
        } else if (output == 28) {
            System.out.println("you don't have permission for new account");
        } else if (output == 29) {
            System.out.println("Your request has not been reviewed");
        }
    }

    public void showAccountOutput(int output) {
        if (output == 0) {
            System.out.println("username format is invalid");
        } else if (output == 1) {
            System.out.println("a user exists with this username");
        } else if (output == 2) {
            System.out.println("please enter your password");
        } else if (output == 3) {
            System.out.println("password format is invalid");
        } else if (output == 4) {
            System.out.println("please enter your name");
        } else if (output == 5) {
            System.out.println("name format is invalid");
        } else if (output == 6) {
            System.out.println("please enter your lastname");
        } else if (output == 7) {
            System.out.println("lastname format is invalid");
        } else if (output == 8) {
            System.out.println("please enter your Email");
        } else if (output == 9) {
            System.out.println("email format is invalid");
        } else if (output == 10) {
            System.out.println("please enter your phone number");
        } else if (output == 11) {
            System.out.println("phone number format is invalid");
        } else if (output == 12) {
            System.out.println("register successful");
        } else if (output == 13) {
            System.out.println("no user exist with this username");
        } else if (output == 14) {
            System.out.println("your password is wrong");
        } else if (output == 15) {
            System.out.println("login successful");
        } else if (output == 16) {
            System.out.println("no field matches with this");
        } else if (output == 17) {
            System.out.println("password changed");
        } else if (output == 18) {
            System.out.println("name changed");
        } else if (output == 19) {
            System.out.println("last name changed");
        } else if (output == 20) {
            System.out.println("Email changed");
        } else if (output == 21) {
            System.out.println("phone number changed");
        } else if (output == 22) {
            System.out.println("logout");
        } else if (output == 23) {
            System.out.println("you cannot create manager profile");
        } else if (output == 24) {
            System.out.println("You've logged in before");
        } else if (output == 25) {
            System.out.println("you have to login first");
        } else if (output == 26) {
            System.out.println("no role matches with this");
        } else if (output == 27) {
            System.out.println("you have already requested for new account");
        } else if (output == 28) {
            System.out.println("you don't have permission for new account");
        } else if (output == 29) {
            System.out.println("Your request has not been reviewed");
        }
    }

    public void showOutputWithString(String string, int output) {
        if (output == 1) {
            System.out.println("username " + string + " deleted");
        } else if (output == 2) {
            System.out.println("product " + string + " removed");
        } else if (output == 3) {
            System.out.println("please enter your new  " + string);
        } else if (output == 4) {
            System.out.println("discount code " + string + " removed");
        } else if (output == 5) {
            System.out.println("request " + string + " accepted");
        } else if (output == 6) {
            System.out.println("request " + string + " declined");
        }else if (output == 7) {
            System.out.println("category " + string + " removed");
        }else if (output == 8) {
            System.out.println("your credit " + string );
        }
    }

    public void showOutputWith2String(String first, String second, int output) {
        if (output == 1) {
            System.out.println("you get " + first + " to product " + second);
        }
    }

    // 1 = view personal info
    public void showObjectOutput(Object object, int size, int number) {
        for (int i = 0; i < size; i++) {
            System.out.println(object.getClass().getName());
        }
        if (number == 1) {
            System.out.println("" + object.getClass());
        }
    }

    public void showDiscountCode(DiscountCode discountCode) {
        System.out.println();
    }

    public void showRequest(Request request) {

    }

    public void showFirm(Firm firm) {

    }

    public void showProduct(Product product) {

    }

    public void showSale(Sale sale) {

    }

    public void showAccount(Account account) {
        System.out.println("username: " + account.getUsername() + "\n" +
                "name: " + account.getName() + "\n" +
                "last name: " + account.getLastname() + "\n" +
                "email: " + account.getEmail() + "\n" +
                "phone number: " + account.getPhoneNo() + "\n");
    }
}


