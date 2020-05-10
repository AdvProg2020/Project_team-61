package controller.menus;


import model.accounts.Account;
import model.accounts.Seller;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputMassageHandler;
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

    private OutputMassageHandler outputHandler = new OutputMassageHandler();

    //finish
    public void processDigest() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(productsMenu.getProductId());
        ArrayList<String> info=selectedProduct.getInfo();
    }

    //finish
    public void addToCart() {
        selectedProduct.addProductToLog(account.getUsername(),selectedProduct.getId(),1);
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
    public void selectSeller(String username) {
        Seller seller1;
        if (checkSeller(username)) {
            for (Seller seller : selectedProduct.getListOfSellers()) {
                if (seller.getUsername().equals(username)){
                seller1=seller;
                }
            }
        }
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
    public void addComments() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
    }

    public void titleOfComment(String title){
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        selectedProduct.addCommentTitle(title);
    }

    //finish
    public void contentOfComment(String content){
        commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        selectedProduct.addCommentContent(content);
    }

}
