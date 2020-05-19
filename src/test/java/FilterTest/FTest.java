package FilterTest;

import model.accounts.Account;
import model.filtar.Filter;
import model.firms.Firm;
import model.productRelated.Category;
import model.productRelated.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class FTest {
    private static ArrayList<Product> productArrayList = new ArrayList<>();
    private static Product p1;
    private static Product p2;
    private static Product p3;

    public static void setProductArrayList() throws IOException {
        p1 = new Product("onion");
        p2 = new Product("apple");
        p3 = new Product("water");
        p1.setDetailProduct("AName3", new Firm("firm1") {
            @Override
            public void setDetailToFirm(Double phoneNO, String address, String email) {
                super.setDetailToFirm(phoneNO, address, email);
            }
        }, 1, new Account("account1") {
            @Override
            public void setDetailsToAccount(String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate, Firm firm) throws IOException {
                super.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate, firm);
            }
        }, 3, new Category("cat1"));


        p2.setDetailProduct("BName2", new Firm("firm2") {
            @Override
            public void setDetailToFirm(Double phoneNO, String address, String email) {
                super.setDetailToFirm(phoneNO, address, email);
            }
        }, 2, new Account("account2") {
            @Override
            public void setDetailsToAccount(String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate, Firm firm) throws IOException {
                super.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate, firm);
            }
        }, 4, new Category("cat2"));


        p3.setDetailProduct("AName3", new Firm("firm3") {
            @Override
            public void setDetailToFirm(Double phoneNO, String address, String email) {
                super.setDetailToFirm(phoneNO, address, email);
            }
        }, 1, new Account("account3") {
            @Override
            public void setDetailsToAccount(String password, String name, String lastname, String Email, double phoneNo, Date birthdayDate, Firm firm) throws IOException {
                super.setDetailsToAccount(password, name, lastname, Email, phoneNo, birthdayDate, firm);
            }
        }, 5, new Category("cat3"));

        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p3);
    }

    @Test
    public void filterName() throws IOException {
        setProductArrayList();
        Filter.setNewArrayOfProductFilter(productArrayList);
        ArrayList<Product> productArrayList1 = new ArrayList<>();
        productArrayList1.add(p1);
        productArrayList1.add(p3);
        Filter.productNameFilter("AName3");
        try {
            Assert.assertEquals(productArrayList,productArrayList1);
        } catch (AssertionError e) {
            System.out.println("Not equal");
            throw e;
        }
        System.out.println("Equal");
    }
}
