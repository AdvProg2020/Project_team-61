import view.CommandProcessor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.run();
    }
    /* public void run(){
        System.out.println("create account [type][username]\nlogin [username]\nview personal info\nedit[field]\nmanage users\nmanage all products\ncreate discount code\nview discount codes\nmange requests\nmanage categories");
        System.out.println("view personal info\nview company information\nview sales history\nmanage products\nadd product\nremove product [product Id]\nshow categories\nview offs\nview balance");
        System.out.println("view personal info\nview cart\npurchase\nview orders\nview balance\nview discount codes");
        System.out.println("products\nview categories\nfiltering\nsoting\nshow products\nshow products [productsId]");
        System.out.println("digest\nattributes\ncompare [productID]\nComments\noffs\nshow product [productId]");
        System.out.println("view [username]\ndelete user [username]\ncreate manager profile");
        System.out.println("remove [productId]");
        System.out.println("view discount code [code]\nedit discount code [code]\nremove discount code [code]");
        System.out.println("details [requestId]\naccept [requestId]\ndecline [requestId]");
        System.out.println("edit [category]\nadd [category]\nremove [category]");
        System.out.println("edit [field]");
        System.out.println("view [productId]\nview buyers [productId]\nedit [productId]");
        System.out.println("view [offId]\nedit [offId]\nadd off");
        System.out.println("edit [field]");
        System.out.println("show products\nview [productId]\nincrease [productId]");
        System.out.println("decrease [productId]\nshow total price\npurchase");
        System.out.println("show order [orderId]\nrate [productId] [1-5]");
        System.out.println("show available filters\nfilter [an available]\ncurrent filters[]\ndisable filter [a selected filter]");
        System.out.println("show available sorts\nsort [an available sort]\ncurrent sort\ndisable sort");
        System.out.println("add to cart\nselect seller [seller_username]");
        System.out.println("Add comment\nTitle:\nContent:");
    }

     */
}
