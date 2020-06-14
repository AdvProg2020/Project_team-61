package view;


public class OutputMassageHandler {
    private static String text;

    public static void showProductsOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("there is no filter with this id");
        } else if (output == 2) {
            System.out.println("filter disabled");
        } else if (output == 3) {
            System.out.println("there is no sort with this id");
        } else if (output == 4) {
            System.out.println("sort disabled");
        } else if (output == 5) {
            System.out.println("there is no product with this id");
        } else if (output == 6) {
            System.out.println("this filter isn't available");
        } else if (output == 7) {
            System.out.println("this sort isn't available");
        } else if (output == 8) {
            System.out.println("please enter 2 number");
        } else if (output == 9) {
            System.out.println("sort format is in valid");
        } else if (output == 10) {
            System.out.println("please enter category name to filter");
        } else if (output == 11) {
            System.out.println("please enter firm name to filter");
        } else if (output == 12) {
            System.out.println("please enter product name to filter");
        } else if (output == 13) {
            System.out.println("there is no category with this id");
        } else if (output == 14) {
            System.out.println("there is no firm with this id");
        } else if (output == 15) {
            System.out.println("there is no product with this id");
        }
    }

    public static void showProductOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("please enter comment title");
        } else if (output == 2) {
            System.out.println("please enter comment content");
        } else if (output == 3) {
            System.out.println("you already commented on this product");
        }
    }


    public static void showManagerOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 2) {
            System.out.println("product Id format is invalid");
        } else if (output == 3) {
            System.out.println("no product exist with this Id");
        } else if (output == 4) {
            System.out.println("discount code Id format is inavlid");
        } else if (output == 5) {
            System.out.println("there is no discount code with this Id");
        } else if (output == 8) {
            System.out.println("start of discount period format is invalid");
        } else if (output == 10) {
            System.out.println("end of discount period format is invalid");
        } else if (output == 12) {
            System.out.println("maximum discount amount format is invalid");
        } else if (output == 14) {
            System.out.println("total time of use format is invalid");
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
        }else if (output == 25) {
            System.out.println("there is discount code with this id");
        } else if (output == 26) {
            System.out.println("date must be after current date");
        } else if (output == 27) {
            System.out.println("discount amount changed");
        } else if (output == 28) {
            System.out.println("discount amount format is invalid");
        } else if (output == 29) {
            System.out.println("account added to list");
        }else if (output == 30) {
            System.out.println("username format is invalid");
        } else if (output == 31) {
            System.out.println("there is no account with this username");
        } else if (output == 32) {
            System.out.println("account removed from list");
        } else if (output == 34) {
            System.out.println("there is a category with this name");
        } else if (output == 36) {
            System.out.println("please enter username you want to add to discount code list (if you enter finish process end)");
        } else if (output == 37) {
            System.out.println("discount code add");
        } else if (output == 39) {
            System.out.println("product add to category");
        } else if (output == 40) {
            System.out.println("product removed from category");
        } else if (output == 41) {
            System.out.println("trait removed");
        } else if (output == 42) {
            System.out.println("trait format is invalid");
        } else if (output == 43) {
            System.out.println("trait add");
        } else if (output == 45) {
            System.out.println("category add");
        } else if (output == 46) {
            System.out.println("there is no product with this id");
        }
    }

    public static void showCategoryOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            System.out.println("trait removed");
        } else if (output == 2) {
            System.out.println("trait format is invalid");
        } else if (output == 3) {
            System.out.println("trait add");
        } else if (output == 9) {
            System.out.println("you aren't a customer");
        } else if (output == 10) {
            System.out.println("your discount is incorrect");
        } else if (output == 11) {
            System.out.println("number must be between 1 to 5");
        } else if (output == 12) {
            System.out.println("there is no log yet");
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
            text =("you don't have permission for new account");
        } else if (output == 29) {
            text =("Your request has not been reviewed");
        }
    }


    public static void showCustomerOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            text =("there is no product with this productId ");
        } else if (output == 4) {
            text =("number format is invalid");
        } else if (output == 7) {
            text =("there is no discount code with this id");
        } else if (output == 8) {
            text =("there is no log with this id");
        } else if (output == 10) {
            text =("product number increase");
        } else if (output == 11) {
            text =("number must be between 1 to 5");
        } else if (output == 12) {
            text =("there is no log yet");
        } else if (output == 13) {
            text =("you didn't buy this product");
        } else if (output == 14) {
            text =("score add to product");
        } else if (output == 15) {
            text =("product number decrese");
        }
    }

    public static void showPurchaseOutput(int output){
        if (output == 0) {
            text =("please enter discount id");
        } else if (output == 1) {
            text =("answer must be (yes|no)");
        } else if (output == 2) {
            text =("enter payment to finish buying process");
        } else if (output == 3) {
            text =("you dont have this discount id");
        } else if (output == 4) {
            text =("date is invalid");
        } else if (output == 5) {
            text =("you can't use iy anymore");
        } else if (output == 6) {
            text =("you have to login first");
        } else if (output == 7) {
            text =("please enter your phone number");
        } else if (output == 8) {
            text =("you aren't a customer");
        }
    }

    public static void showSellerOutput(int output) {
        if (output == 0) {
            System.out.println("number");
        } else if (output == 1) {
            text =("there is no product with this productId ");
        } else if (output == 4) {
            text =("product name change request sent");
        } else if (output == 5) {
            text =("product price change request sent");
        } else if (output == 6) {
            text =("product name format is invalid");
        } else if (output == 7) {
            text =("product price format is invalid");
        } else if (output == 8) {
            text =("number of product format is invalid");
        } else if (output == 9) {
            text =("number Of Product change request sent");
        } else if (output == 14) {
            text =("additional detail change request sent");
        } else if (output == 15) {
            text =("additional detail format is invalid");
        } else if (output == 18) {
            text =("product removed");
        } else if (output == 19) {
            text =("product id must not be finish");
        } else if (output == 20) {
            text =("category field format is invalid");
        } else if (output == 21) {
            text =("there is no category with this id");
        } else if (output == 22) {
            text =("you can't change this product");
        } else if (output == 23) {
            text =("field format is invalid (Name|price|category|additional details|number Of Product)");
        } else if (output == 24) {
            text =("there is no category with this name");
        } else if (output == 25) {
            text =("category format is invalid");
        } else if (output == 27) {
            text =("there is no product with this id");
        } else if (output == 28) {
            text =("sale request sent");
        } else if (output == 29) {
            text =("value format is invalid");
        } else if (output == 31) {
            text =("there is no trait in category");
        }else if (output == 33) {
            text =("new value request sent");
        }
    }

    public static void showSaleOutput(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            text =("there is no sale with this name");
        } else if (output == 3) {
            text =("off field format is invalid");
        } else if (output == 5) {
            text =("you cant't edit this off");
        } else if (output == 7) {
            text =("there is an off with this id");
        } else if (output == 8) {
            text =("there is no product with this id");
        } else if (output == 9) {
            text =("start of sale period format is invalid");
        } else if (output == 10) {
            text =("please enter start of sale period date");
        } else if (output == 11) {
            text =("start of sale period request sent");
        } else if (output == 12) {
            text =("date is invalid");
        } else if (output == 13) {
            text =("end of sale period request sent");
        } else if (output == 14) {
            text =("end of sale period format is invalid");
        } else if (output == 15) {
            text =("sale amount format is invalid");
        } else if (output == 16) {
            text =("sale amount request sent");
        } else if (output == 17) {
            text =("product removed");
        } else if (output == 18) {
            text =("product add");
        } else if (output == 19) {
            text =("product id format is invalid");
        }

    }


    public static String showAccountOutput(int output) {
        if (output == 0) {
            return ("");
        } else if (output == 1) {
            text =("a user exists with this username");
        } else if (output == 3) {
            text =("password format is invalid");
        } else if (output == 5) {
            text =("name format is invalid");
        } else if (output == 7) {
            text =("last name format is invalid");
        } else if (output == 9) {
            text =("email format is invalid");
        } else if (output == 11) {
            text =("phone number format is invalid");
        } else if (output == 12) {
            text =("register successful");
        } else if (output == 13) {
            text =("no user exist with this username");
        } else if (output == 14) {
            text =("your password is wrong");
        } else if (output == 16) {
            text =("no account field matches with this (username|password|last name|email|phone number|firm)");
        } else if (output == 22) {
            text =("logout");
        } else if (output == 23) {
            text =("you cannot create manager profile");
        } else if (output == 24) {
            text =("You've logged in before");
        } else if (output == 25) {
            text =("you have to login first");
        } else if (output == 26) {
            text =("no role matches with this");
        } else if (output == 27) {
            text =("you can't change firm information");
        } else if (output == 30) {
            text =("birthday Date format is invalid");
        } else if (output == 32) {
            text =("username format is invalid");
        } else if (output == 33) {
            text =("you can't login");
        } else if (output == 35) {
            text =("there is no account with this user name");
        } else if (output == 36)
            text =("username format is invalid");
        return text ;
    }

    public static String showFirmOutput(int output) {
        if (output == 0) {
            text =("");
        } else if (output == 1) {
            text =("no firm field matches with this (name|address|email|phone number)");
        } else if (output == 3) {
            text =("firm name format is invalid");
        } else if (output == 6) {
            text =("firm's phone number format is invalid");
        } else if (output == 7) {
            text =("phone number request for new value sent");
        } else if (output == 8) {
            text =("firm's address format is invalid");
        } else if (output == 9) {
            text =("address request for new value sent");
        } else if (output == 10) {
            text =("firm's Email format is invalid");
        } else if (output == 11) {
            text =("Email number request for new value sent");
        } else if (output == 12) {
            text =("you dont have firm with this name");
        } else if (output == 17) {
            text =("register account request sent to manager");
        } else if (output == 18) {
            text =("firm's type format is invalid (company|factory|workshop)");
        }
        return text;
    }

    public static void showReceiverInfo(int output) {
        if (output == 0) {
            System.out.println("");
        } else if (output == 1) {
            text =("current phone number format is invalid");
        } else if (output == 3) {
            text =("address format is invalid");
        } else if (output == 4) {
            text =("do you want fast post? (yes|no)");
        } else if (output == 5) {
            text =("answer must be yes or no");
        } else if (output == 6) {
            text =("do yo have discount code? (yes|no)");
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

    public static void show(String show){
        System.out.println(show);
    }





}