import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import controller.request.Request;
import model.accounts.Account;
import model.productRelated.Category;
import model.productRelated.Product;
import view.CommandProcessor;
import view.FileHandling;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.FileAlreadyExistsException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Type productType = new TypeToken<ArrayList<Product>>(){}.getType();
        try {
            JsonReader reader2 =new JsonReader(new FileReader("product.json"));
            ArrayList<Product> productArrayList = FileHandling.getGson().fromJson(reader2,productType);
            if (null==productArrayList){
                productArrayList=new ArrayList<>();
            }
            Product.setAllProduct(productArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","product.json");
            Product.setAllProduct(new ArrayList<>());
        }



        Type categoryType = new TypeToken<ArrayList<Category>>(){}.getType();
        try {
            JsonReader reader3 =new JsonReader(new FileReader("category.json"));
            ArrayList<Category> categoryArrayList = FileHandling.getGson().fromJson(reader3,categoryType);
            if (null==categoryArrayList){
                categoryArrayList=new ArrayList<>();
            }
            Category.setAllCategories(categoryArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","category.json");
            Category.setAllCategories(new ArrayList<>());
        }


        Type registerType = new TypeToken<ArrayList<Request>>(){}.getType();
        try {
            JsonReader reader1 =new JsonReader(new FileReader("request.json"));
            ArrayList<Request> requestArrayList = FileHandling.getGson().fromJson(reader1,registerType);
            if (null==requestArrayList){
                requestArrayList=new ArrayList<>();
            }
            Request.setAllRequests(requestArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","request.json");
            Request.setAllRequests(new ArrayList<>());
        }



        try {
            Type AccountType = new TypeToken<ArrayList<Account>>() {
            }.getType();
            JsonReader reader = new JsonReader(new FileReader("account.json"));
            ArrayList<Account> list = FileHandling.getGson().fromJson(reader,AccountType);
            if (null == list){
                list = new ArrayList<Account>();
            }
            Account.setAllAccounts(list);
        }catch (IOException e){
            FileHandling.writeInFile("","account.json");
            Account.setAllAccounts(new ArrayList<>());
        }


        //Account.readFile();
       // Request.readFile();
         CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.run();
//        Scanner in = new Scanner(System.in);
//        int a = in.nextInt();
//        System.out.println(a);
    }
    /* public void run(){

        System.out.println("view personal info\nedit[field]\nmanage users\nmanage all products\ncreate discount code\nview discount codes\nmange requests\nmanage categories");
        System.out.println("create account [type][username]\nlogin [username]\nlogout\nback\nhelp");
        System.out.println("view personal info\nview company information\nview sales history\nmanage products\nadd product\nremove product [product Id]\nshow categories\nview offs\nview balance");
        System.out.println("view personal info\nview cart\npurchase\nview orders\nview balance\nview discount codes");
        System.out.println("products\nview categories\nfiltering\nsoting\nshow products\nshow products [productsId]");
        System.out.println("digest\nattributes\ncompare [productID]\nComments");
         System.out.println("offs\nshow product [productId]");
        System.out.println("view [username]\ndelete user [username]\ncreate manager profile");
        System.out.println("remove [productId]");
        System.out.println("view discount code [code]\nedit discount code [code]\nremove discount code [code]");
        System.out.println("details [requestId]\naccept [requestId]\ndecline [requestId]");
        System.out.println("edit [category]\nadd [category]\nremove [category]");
        System.out.println("edit [field]");
        System.out.println("view [productId]\nview buyers [productId]\nedit [productId]");
        System.out.println("view [offId]\nedit [offId]\nadd off");
        System.out.println("edit [field]");
        System.out.println("show products\nview [productId]\nincrease [productId]\ndecrease [productId]\nshow total price\npurchase");
        System.out.println("show order [orderId]\nrate [productId] [1-5]");
        System.out.println("show available filters\nfilter [an available]\ncurrent filters[]\ndisable filter [a selected filter]");
        System.out.println("show available sorts\nsort [an available sort]\ncurrent sort\ndisable sort");
        System.out.println("add to cart\nselect seller [seller_username]");
        System.out.println("Add comment\nTitle:\nContent:");
    }

     */
}
