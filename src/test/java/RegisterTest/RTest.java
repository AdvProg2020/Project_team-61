package RegisterTest;

import model.accounts.Manager;
import model.firms.Firm;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class RTest {

    @Test
    public void registering() throws IOException {
        Manager manager = new Manager("roya");
        manager.setDetailsToAccount("123", "manager", "manager1", "roya@gmail.com", 9.112323434, new Date(), new Firm("firm") {
            @Override
            public void setDetailToFirm(Double phoneNO, String address, String email) {
                super.setDetailToFirm(phoneNO, address, email);
            }
        });

        try {
            Assert.assertEquals(manager,Manager.getAllAccounts().get(0));
        } catch (AssertionError e) {
            System.out.println("Not equal");
            throw e;
        }
        System.out.println("Equal");

    }



}
