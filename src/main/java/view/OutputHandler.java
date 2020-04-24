package view;

import controller.request.Request;
import model.accounts.Account;
import model.firms.Firm;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Product;

public class OutputHandler {
    private  CommandProcessor commandProcessor;
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
        if (commandProcessor.getMenuStatus() == MenuStatus.USERMENU) {
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
                System.out.println("please enter field you want to change");
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
            }
        }
        if (commandProcessor.getMenuStatus() == MenuStatus.MANAGERMENU) {
            if (output == 1) {
                System.out.println("username format is invalid");
            } else if (output == 2) {
                System.out.println("no user exist with this username");
            } else if (output == 3) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            } else if (output == 1) {
                System.out.println("");
            }
        }
    }

    public  void showOutputWithString (String string,int output){
        if (output == 1) {
            System.out.println("username " + string + "deleted");
        }
        if (output == 2) {
            System.out.println("product " + string + "removed");
        }
        if (output == 1) {
            System.out.println("username " + string + "deleted");
        }
    }
    // 1 = view personal info
    public  void showObjectOutput (Object object,int size, int number){
        for (int i = 0; i < size; i++) {
            System.out.println(object.getClass().getName());
        }
        if (number == 1) {
            System.out.println("" + object.getClass());
        }
    }

    public  void showDiscountCode (DiscountCode discountCode){
        System.out.println();
    }

    public void showRequest(Request request){

    }

    public void showFirm(Firm firm){

    }

    public void showProduct(Product product){

    }

    public void showSale(Sale sale){

    }

    public  void showAccount (Account account){
        System.out.println("username: " + account.getUsername() + "\n" +
                "name: " + account.getName() + "\n" +
                "last name: " + account.getLastname() + "\n" +
                "email: " + account.getEmail() + "\n" +
                "phone number: " + account.getPhoneNo() + "\n");
    }
}

