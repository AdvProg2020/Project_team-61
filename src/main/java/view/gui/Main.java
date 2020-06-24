package view.gui;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.off.DiscountCode;
import model.off.Sale;
import model.productRelated.Category;
import model.productRelated.Product;
import model.request.*;
import view.FileHandling;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

//import controller.request.*;

public class Main extends Application {
    private final int widthScene = 1800;
    private final int heightScene = 700;
    static Stage primStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainMenuFx.class.getClassLoader().getResource("mainMenuFx.fxml")));
        primaryStage.setTitle("market");
        primaryStage.setScene(new Scene(root, widthScene, heightScene));
        primStage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {

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


        Type sellerType = new TypeToken<ArrayList<Seller>>(){}.getType();
        try {
            JsonReader reader5 =new JsonReader(new FileReader("seller.json"));
            ArrayList<Seller> sellersArrayList = FileHandling.getGson().fromJson(reader5,sellerType);
            if (null==sellersArrayList){
                sellersArrayList=new ArrayList<>();
            }
            Seller.setAllSellers(sellersArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","seller.json");
            Seller.setAllSellers(new ArrayList<>());
        }


        Type managerType = new TypeToken<ArrayList<Manager>>(){}.getType();
        try {
            JsonReader reader6 =new JsonReader(new FileReader("manager.json"));
            ArrayList<Manager> managerArrayList = FileHandling.getGson().fromJson(reader6,managerType);
            if (null==managerArrayList){
                managerArrayList=new ArrayList<>();
            }
            Manager.setAllManagers(managerArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","manager.json");
            Manager.setAllManagers(new ArrayList<>());
        }


        Type CustomerType = new TypeToken<ArrayList<Customer>>(){}.getType();
        try {
            JsonReader reader7 =new JsonReader(new FileReader("customer.json"));
            ArrayList<Customer> customerArrayList = FileHandling.getGson().fromJson(reader7,CustomerType);
            if (null==customerArrayList){
                customerArrayList=new ArrayList<>();
            }
            Customer.setAllCustomers(customerArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","customer.json");
            Customer.setAllCustomers(new ArrayList<>());
        }

        Account.setAllAccounts(new ArrayList<>());
        Account.getAllAccounts().addAll(Customer.getAllCustomers());
        Account.getAllAccounts().addAll(Manager.getAllManagers());
        Account.getAllAccounts().addAll(Seller.getAllSellers());

        Type AccountReType = new TypeToken<ArrayList<AccountRequest>>(){}.getType();
        try {
            JsonReader reader8 =new JsonReader(new FileReader("accountRequest.json"));
            ArrayList<AccountRequest> accountRequestsArrayList = FileHandling.getGson().fromJson(reader8,AccountReType);
            if (null==accountRequestsArrayList){
                accountRequestsArrayList=new ArrayList<>();
            }
            AccountRequest.setAllAccountRequests(accountRequestsArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","accountRequest.json");
            AccountRequest.setAllAccountRequests(new ArrayList<>());
        }




        Type CommentReType = new TypeToken<ArrayList<CommentRequest>>(){}.getType();
        try {
            JsonReader reader9 =new JsonReader(new FileReader("commentRequest.json"));
            ArrayList<CommentRequest> commentRequests = FileHandling.getGson().fromJson(reader9,CommentReType);
            if (null==commentRequests){
                commentRequests=new ArrayList<>();
            }
            CommentRequest.setAllCommentRequests(commentRequests);
        }catch (IOException e){
            FileHandling.writeInFile("","commentRequest.json");
            CommentRequest.setAllCommentRequests(new ArrayList<>());
        }

        Request.setAllRequests(new ArrayList<>());
        Request.getAllRequests().addAll(CommentRequest.getAllCommentRequests());


        for (Seller seller : Seller.getAllSellers()) {
            Request.getAllRequests().addAll(seller.getAllAccountRequests());

            Request.getAllRequests().addAll(seller.getAllProductRequests());

            Request.getAllRequests().addAll(seller.getAllSaleRequests());

            Product.getProductList().addAll(seller.getAllProduct());

            Sale.getAllSales().addAll(seller.getAllSales());
        }

        for (Manager manager : Manager.getAllManagers()) {
            DiscountCode.getAllDiscountCodes().addAll(manager.getAllDiscountCodes());
        }

        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            System.out.println(discountCode.getDiscountId());
        }

        for (Sale sale : Sale.getAllSales()) {
            System.out.println(sale.getOffId()+"#");
        }
        Application.launch(args);

    }

}