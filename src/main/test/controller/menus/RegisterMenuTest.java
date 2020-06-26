package controller.menus;

import model.accounts.Account;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import static controller.menus.RegisterMenu.detailMenu;

public class RegisterMenuTest {

    //RegisterMenu registerMenu;

    @Test
    public void TestRegister() throws IOException, ParseException {
        int detailMenu=0;
        try {
            RegisterMenu.processRegister("manager", "heliazesht","img");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("invalid input");
        }
       // detailMenu++;
        RegisterMenu.completeRegisterProcess("heliazesht",detailMenu);
        detailMenu++;
        RegisterMenu.completeRegisterProcess("helia",detailMenu);
        detailMenu++;
        RegisterMenu.completeRegisterProcess("akhtarkavian",detailMenu);
        detailMenu++;
        RegisterMenu.completeRegisterProcess("helia@yahoo.com",detailMenu);
       detailMenu++;
        RegisterMenu.completeRegisterProcess("09102157189",detailMenu);
        detailMenu++;
        String detail="12/12/2018";
        Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(detail);
        RegisterMenu.completeRegisterProcess(detail,detailMenu);
        Assert.assertTrue(Account.getAllAccounts().contains(Account.getAccountWithUsername("heliazesht")));
        Assert.assertTrue(Account.getAccountWithUsername("heliazesht").getUsername().equalsIgnoreCase("heliazesht"));
        Assert.assertTrue(Account.getAccountWithUsername("heliazesht").getName().equalsIgnoreCase("helia"));
        Assert.assertTrue(Account.getAccountWithUsername("heliazesht").getRole().equalsIgnoreCase("manager"));

        Assert.assertTrue(Account.getAccountWithUsername("heliazesht").getLastname().equalsIgnoreCase("akhtarkavian"));
        //Assert.assertTrue(Account.getAccountWithUsername("heliazesht").getEmail().equalsIgnoreCase("mana"));
        Assert.assertEquals(Account.getAccountWithUsername("heliazesht").getPhoneNo(), Double.parseDouble("09102157189"),1e-8);
        Assert.assertEquals(Account.getAccountWithUsername("heliazesht").getBirthdayDate(),(inputDate));
    }
}