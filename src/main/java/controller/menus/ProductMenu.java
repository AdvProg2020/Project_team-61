package controller.menus;


import model.accounts.Account;
import model.accounts.Seller;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputHandler;
import view.SubMenuStatus;

import java.util.ArrayList;

public class ProductMenu {
    private int outputNo;
    private int inputNo;
    private Comment comment;
    private CommandProcessor commandProcessor;
    private Account account;
    private ProductsMenu productsMenu;
    private Product selectedProduct;
    private LoginMenu loginMenu;

    private OutputHandler outputHandler = new OutputHandler();

    //finish
    public ArrayList<String> processDigest() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(productsMenu.getProductId());
        return selectedProduct.getInfo();
    }

    //finish
    public void addToCart(int amount) {
        selectedProduct.addProductToLog(account.getUsername(),selectedProduct.getId(),amount);
    }

    //finish
    private boolean checkSeller(String seller) {
        if (seller.matches("")) {
            if (account.isThereAccountWithUsername(seller)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    //finish
    public Seller selectSeller(String username) {
        if (checkSeller(username)) {
            for (Seller seller : selectedProduct.getListOfSellers()) {
                if (seller.getUsername().equals(username)){
                return seller;
                }
            }
        }
        return null;
    }


    public void processAttributes() {

    }

    //finish
    public void processCompare(String productID) {
        Product productToCompare=Product.getProductById(productID);
        productToCompare.getInfo();
        selectedProduct.getInfo();
    }

    //comment--------------------------------------------------------------------

    //finish
    public void processComments() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        selectedProduct.getScore();
        selectedProduct.listOfComments(selectedProduct.getId());
    }

    //finish
    public void addComments(String content) {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
        selectedProduct.addComment(selectedProduct.getId(),loginMenu.getLoginAccount(),content);
    }

    public void titleOfComment(String title){
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
    }

    public void contentOfComment(String content){
        commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
    }

}
