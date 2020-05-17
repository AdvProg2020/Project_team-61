package view;


public class OutputMassageHandler {


    public static void showOutput(int output) {
        if (output == 0) {
            System.out.println("your command is invalid");
        } else if (output == 1) {
            System.out.println("exit");
        }
    }

    public static void showProductsOutput(int output) {
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
        }
    }

    public static void showManageOutput(int output) {
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

    public static void showCustomerOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("there is no product with this productId ");
        } else if (output == 2) {
            System.out.println("please enter number of products you want to add");
        } else if (output == 3) {
            System.out.println("please enter number of products you want to remove");
        } else if (output == 4) {
            System.out.println("number format is invalid");
        } else if (output == 5) {
            System.out.println("please enter your phone number");
        } else if (output == 6) {
            System.out.println("you have to login first");
        } else if (output == 7) {
            System.out.println("there is no discount code with this id");
        } else if (output == 8) {
            System.out.println("there is no log with this id");
        } else if (output == 9) {
            System.out.println("you aren't a customer");
        } else if (output == 10) {
            System.out.println("your discount is incorrect");
        } else if (output == 11) {
            System.out.println("number must be between 1 to 5");
        } else if (output == 12) {
            System.out.println("");
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

    public static void showSellerOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("there is no product with this productId ");
        } else if (output == 2) {
            System.out.println("please enter field of products you want to change (Name|price|category|additional details|number Of Product)");
        } else if (output == 3) {
            System.out.println("please enter your product's new value");
        } else if (output == 4) {
            System.out.println("product name change request sent");
        } else if (output == 5) {
            System.out.println("product price change request sent");
        } else if (output == 6) {
            System.out.println("product name format is invalid");
        } else if (output == 7) {
            System.out.println("product price format is invalid");
        } else if (output == 8) {
            System.out.println("number of product format is invalid");
        } else if (output == 9) {
            System.out.println("number Of Product change request sent");
        } else if (output == 10) {
            System.out.println("please enter product id");
        } else if (output == 11) {
            System.out.println("please enter product name");
        } else if (output == 12) {
            System.out.println("please enter product price");
        } else if (output == 13) {
            System.out.println("please enter product category name");
        } else if (output == 14) {
            System.out.println("additional detail change request sent");
        } else if (output == 15) {
            System.out.println("additional detail format is invalid");
        } else if (output == 16) {
            System.out.println("please enter number of product");
        } else if (output == 17) {
            System.out.println("product add request sent");
        } else if (output == 18) {
            System.out.println("product removed");
        } else if (output == 19) {
            System.out.println("there is no sale with this name");
        } else if (output == 20) {
            System.out.println("please enter off field you want to change");
        } else if (output == 21) {
            System.out.println("please enter your off new value");
        } else if (output == 22) {
            System.out.println("you can't change this product");
        } else if (output == 23) {
            System.out.println("field format is invalid (Name|price|category|additional details|number Of Product)");
        } else if (output == 24) {
            System.out.println("there is no category with this name");
        } else if (output == 25) {
            System.out.println("category format is invalid");
        } else if (output == 26) {
            System.out.println("please enter product additional detail");
        } else if (output == 27) {
            System.out.println("there is no product with this id");
        } else if (output == 28) {
            System.out.println("");
        } else if (output == 29) {
            System.out.println("");
        }
    }

    public static void showSaleOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("there is no product with this productId ");
        } else if (output == 2) {
            System.out.println("please enter field of products you want to change (Name|price|category|additional details|number Of Product)");
        } else if (output == 3) {
            System.out.println("please enter your product's new value");
        } else if (output == 4) {
            System.out.println("product name change request sent");
        } else if (output == 5) {
            System.out.println("product price change request sent");
        } else if (output == 6) {
            System.out.println("product name format is invalid");
        } else if (output == 7) {
            System.out.println("product price format is invalid");
        } else if (output == 8) {
            System.out.println("number of product format is invalid");
        } else if (output == 9) {
            System.out.println("number Of Product change request sent");
        } else if (output == 10) {
            System.out.println("please enter product id");
        } else if (output == 11) {
            System.out.println("please enter product name");
        } else if (output == 12) {
            System.out.println("please enter product price");
        } else if (output == 13) {
            System.out.println("please enter product category name");
        } else if (output == 14) {
            System.out.println("additional detail change request sent");
        } else if (output == 15) {
            System.out.println("additional detail format is invalid");
        } else if (output == 16) {
            System.out.println("please enter number of product");
        } else if (output == 17) {
            System.out.println("product add request sent");
        } else if (output == 18) {
            System.out.println("product removed");
        } else if (output == 19) {
            System.out.println("there is no sale with this name");
        } else if (output == 20) {
            System.out.println("please enter off field you want to change");
        } else if (output == 21) {
            System.out.println("please enter your off new value");
        } else if (output == 22) {
            System.out.println("you can't change this product");
        } else if (output == 23) {
            System.out.println("field format is invalid (Name|price|category|additional details|number Of Product)");
        } else if (output == 24) {
            System.out.println("there is no category with this name");
        } else if (output == 25) {
            System.out.println("category format is invalid");
        } else if (output == 26) {
            System.out.println("please enter product additional detail");
        } else if (output == 27) {
            System.out.println("there is no product with this id");
        } else if (output == 28) {
            System.out.println("");
        } else if (output == 29) {
            System.out.println("");
        }
    }

    public static void showAccountOutput(int output) {
        if (output == 0) {
            System.out.println("");
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
            System.out.println("no account field matches with this (username|password|last name|email|phone number|firm)");
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
            System.out.println("you can't change firm information");
        } else if (output == 28) {
            System.out.println("please enter firm name you want to change");
        } else if (output == 29) {
            System.out.println("please enter your birthday Date");
        } else if (output == 30) {
            System.out.println("birthday Date format is invalid");
        } else if (output == 31) {
            System.out.println("please enter firm name");
        } else if (output == 32) {
            System.out.println("username format is invalid");
        } else if (output == 33) {
            System.out.println("you can't login");
        } else if (output == 34) {
            System.out.println("enter new value");
        } else if (output == 35) {
            System.out.println("");
        } else if (output == 36) {
            System.out.println("");
        } else if (output == 37) {
            System.out.println("");
        } else if (output == 38) {
            System.out.println("");
        } else if (output == 39) {
            System.out.println("");
        } else if (output == 40) {
            System.out.println("");
        }
    }

    public static void showFirmOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("no firm field matches with this (name|address|email|phone number)");
        } else if (output == 2) {
            System.out.println("please enter new value");
        } else if (output == 3) {
            System.out.println("firm name format is invalid");
            // } else if (output == 4) {
            //     System.out.println("you already send your request");
        } else if (output == 5) {
            System.out.println("please enter firm's field you want to change");
        } else if (output == 6) {
            System.out.println("firm's phone number format is invalid");
        } else if (output == 7) {
            System.out.println("phone number request for new value sent");
        } else if (output == 8) {
            System.out.println("firm's address format is invalid");
        } else if (output == 9) {
            System.out.println("address request for new value sent");
        } else if (output == 10) {
            System.out.println("firm's Email format is invalid");
        } else if (output == 11) {
            System.out.println("Email number request for new value sent");
        } else if (output == 12) {
            System.out.println("you dont have firm with this name");
        } else if (output == 13) {
            System.out.println("please enter firm's name");
        } else if (output == 14) {
            System.out.println("please enter firm's phone number");
        } else if (output == 15) {
            System.out.println("please enter firm's email");
        } else if (output == 16) {
            System.out.println("please enter firm's address");
        } else if (output == 17) {
            System.out.println("register account request sent to manager");
        } else if (output == 18) {
            System.out.println("firm's type format is invalid (company|factory|workshop)");
        } else if (output == 19) {
            System.out.println("please enter firm's type");
        }
    }

    public static void showReceiverInfo(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("current phone number format is invalid");
        } else if (output == 2) {
            System.out.println("please enter your address");
        } else if (output == 3) {
            System.out.println("address format is invalid");
        } else if (output == 4) {
            System.out.println("do you want fast post? (yes|no)");
        } else if (output == 5) {
            System.out.println("answer must be yes or no");
        } else if (output == 6) {
            System.out.println("do yo have discount code? (yes|no)");
        } else if (output == 7) {
            System.out.println("");
        } else if (output == 8) {
            System.out.println("");
        }
    }

    public static void showOutputWithString(String string, int output) {
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
        } else if (output == 7) {
            System.out.println("category " + string + " removed");
        } else if (output == 8) {
            System.out.println("your credit " + string);
        }
    }

    public static void showOutputWith2String(String first, String second, int output) {
        if (output == 1) {
            System.out.println("you get " + first + " to product " + second);
        } else if (output == 2) {
            System.out.println("product " + first + " " + second + " number changed");
        } else if (output == 3) {
            System.out.println();
        }
    }


}
