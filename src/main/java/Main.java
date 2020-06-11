import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import controller.request.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Manager;
import model.accounts.Seller;
import model.off.Sale;
import model.productRelated.Category;
import model.productRelated.Product;
import view.CommandProcessor;
import view.FileHandling;
import view.GUI.AddProductMenuFX;
import view.GUI.ProductMenuFX;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    public static Stage stage;
    public static Scene scene;


    public void start(Stage stage) throws Exception {
        ProductMenuFX.showProPage(stage,scene);
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("productsMenu.fxml")));
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }
    public static void main(String[] args) throws IOException, ParseException {
        Application.launch(args);


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



//        Type registerType = new TypeToken<ArrayList<Request>>(){}.getType();
//        try {
//            JsonReader reader1 =new JsonReader(new FileReader("request.json"));
//            ArrayList<Request> requestArrayList = FileHandling.getGson().fromJson(reader1,registerType);
//            if (null==requestArrayList){
//                requestArrayList=new ArrayList<>();
//            }
//            Request.setAllRequests(requestArrayList);
//        }catch (IOException e){
//            FileHandling.writeInFile("","request.json");
//            Request.setAllRequests(new ArrayList<>());
//        }
//
//
//
//        try {
//            Type AccountType = new TypeToken<ArrayList<Account>>() {
//            }.getType();
//            JsonReader reader = new JsonReader(new FileReader("account.json"));
//            ArrayList<Account> list = FileHandling.getGson().fromJson(reader,AccountType);
//            if (null == list){
//                list = new ArrayList<Account>();
//            }
//            Account.setAllAccounts(list);
//        }catch (IOException e){
//            FileHandling.writeInFile("","account.json");
//            Account.setAllAccounts(new ArrayList<>());
//        }
//



        Type saleType = new TypeToken<ArrayList<Sale>>(){}.getType();
        try {
            JsonReader reader4 =new JsonReader(new FileReader("sale.json"));
            ArrayList<Sale> saleArrayList = FileHandling.getGson().fromJson(reader4,saleType);
            if (null==saleArrayList){
                saleArrayList=new ArrayList<>();
            }
            Sale.setAllSales(saleArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","sale.json");
            Sale.setAllSales(new ArrayList<>());
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


//        Type ProductReType = new TypeToken<ArrayList<ProductRequest>>(){}.getType();
//        try {
//            JsonReader reader10 =new JsonReader(new FileReader("productRequest.json"));
//            ArrayList<ProductRequest> productRequests = FileHandling.getGson().fromJson(reader10,ProductReType);
//            if (null==productRequests){
//                productRequests=new ArrayList<>();
//            }
//            ProductRequest.setAllProductRequests(productRequests);
//
//        }catch (IOException e){
//            FileHandling.writeInFile("","productRequest.json");
//            ProductRequest.setAllProductRequests(new ArrayList<>());
//        }


        Type SaleReType = new TypeToken<ArrayList<SaleRequest>>(){}.getType();
        try {
            JsonReader reader11 =new JsonReader(new FileReader("saleRequest.json"));
            ArrayList<SaleRequest> saleRequestsArrayList = FileHandling.getGson().fromJson(reader11,SaleReType);
            if (null==saleRequestsArrayList){
                saleRequestsArrayList=new ArrayList<>();
            }
            SaleRequest.setAllSaleRequests(saleRequestsArrayList);
        }catch (IOException e){
            FileHandling.writeInFile("","saleRequest.json");
            SaleRequest.setAllSaleRequests(new ArrayList<>());
        }

        Request.setAllRequests(new ArrayList<>());
        Request.getAllRequests().addAll(AccountRequest.getAllAccountRequests());
        Request.getAllRequests().addAll(CommentRequest.getAllCommentRequests());
        Request.getAllRequests().addAll(ProductRequest.getAllProductRequests());
        Request.getAllRequests().addAll(SaleRequest.getAllSaleRequests());


        //Account.readFile();
        // Request.readFile();
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.run();

    }
}
