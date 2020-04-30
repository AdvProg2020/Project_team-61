package controller.menus;


import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.log.Log;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.CommandProcessor;
import view.SubMenuStatus;

import java.util.ArrayList;


public class ProductMenu {
    private int outputNo;
    private int inputNo;
    private Comment comment;
    private CommandProcessor commandProcessor;
    private Product product;
    private Account account;
    private LoginMenu loginMenu;
    Customer customer;

    public ProductMenu(Product product) {
        this.product = product;
    }

    //finish
    public ArrayList<String> processDigest() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        return product.getInfo();
    }

    //finish
    public void addToCart(){
        product.addProductToLog(account.getUsername(),product.getId());
    }

    //finish
    public Seller selectSeller(String username){
        for (Seller seller : product.getListOfSellers()) {
            if (seller.getUsername().equals(username)){
                return seller;
            }
        }
        return null;
    }

    public void processAttributes() {

    }

    //finish//doubt
    public void processCompare(String productID) {
        Product productToCompare=Product.getProductById(productID);
        productToCompare.getInfo();
        product.getInfo();
    }

    //aLittleUnfinished
    public void processComments() {
        product.getScore();
        product.listOfComments(product.getId());
    }

    //finish//doubt
    public void addComments(String content){
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        product.addComment(product.getId(),loginMenu.getLoginAccount(),content);
    }

}
