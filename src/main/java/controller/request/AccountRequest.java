package controller.request;

import model.accounts.Account;

public class AccountRequest extends Request {

    public AccountRequest(String requestID) {
        super(requestID);
    }

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String Email;
    private double phoneNo;
    private Account selectedAccount;

    public void sellerAccountDetails(String username ,String password,String name, String lastname, String Email, double phoneNo){
        this.username = username;
        this.password= password;
        this.name=name;
        this.lastname= lastname;
        this.Email =Email;
        this.phoneNo=phoneNo;
    }

    public void acceptRequestDetail(){
        selectedAccount = Account.getAccountWithUsername(username);
        selectedAccount.setDetailsToAccount(password,name,lastname,Email,phoneNo);
    }

}
