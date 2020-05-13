import view.CommandProcessor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        CommandProcessor commandProcessor = new CommandProcessor();

        commandProcessor.run();
        Scanner in =new Scanner(System.in);
        int a=in.nextInt();
        System.out.println(a);
        System.out.println("create account [type][username]");
        System.out.println("login [username]");
        System.out.println("view personal info"+"\n");
        System.out.println("edit[field]"+"\n");
        System.out.println("manage users"+"\n");
        System.out.println("manage all products"+"\n");
        System.out.println("create discount code"+"\n");
        System.out.println("view discount codes"+"\n");
        System.out.println("mange requests"+"\n");
        System.out.println("manage categories"+"\n");
        System.out.println("view personal info"+"\n");
        System.out.println("view company information"+"\n");
        System.out.println("view sales history"+"\n");
        System.out.println("manage products"+"\n");
        System.out.println("add product"+"\n");
        System.out.println("remove product [product Id]");
        System.out.println("show categories");
        System.out.println("view offs");
        System.out.println("view balance");
        System.out.println("view personal info");
        System.out.println("view cart");
        System.out.println("purchase");
        System.out.println("view orders");
        System.out.println("view balance");
        System.out.println("view discount codes");
        System.out.println("products");
        System.out.println("view categories");
        System.out.println("filtering");
        System.out.println("sorting");
        System.out.println("show products");
        System.out.println("show products [productsId]");
        System.out.println("digest");
        System.out.println("attributes");
        System.out.println("compare [productID]");
        System.out.println("Comments");
        System.out.println("offs");
        System.out.println("show product [productId]");
        System.out.println("view [username]\ndelete user [username]\n create manager profile");
        System.out.println("remove [productId]");
        System.out.println("view discount code [code]\nedit discount code [code]\nremove discount code [code]");
        System.out.println("details [requestId]\naccept [requestId]\ndecline [requestId]");
        System.out.println("edit [category]\nadd [category]\nremove [category]");
        System.out.println("edit [field]");
        System.out.println("view [productId]\nview buyers [productId]\nedit [productId]");
        System.out.println("view [offId]\n edit [offId]\nadd off");
        System.out.println("edit [field]");
        System.out.println("show products\nview [productId]\n increase [productId]");
        System.out.println("decrease [productId]\nshow total price\npurchase");
        System.out.println("show order [orderId]");
        System.out.println("rate [productId] [1-5]");
        System.out.println("show available filters\nfilter [an available]\ncurrent filters[]\ndisable filter [a selected filter]");
        System.out.println("show available sorts\nsort [an available sort]\n current sort\ndisable sort");
        System.out.println("add to cart\nselect seller [seller_username]");
        System.out.println("Add comment\nTitle:\nContent:");



    }

}
