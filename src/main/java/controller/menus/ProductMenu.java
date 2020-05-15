package controller.menus;

import model.accounts.Account;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputHandler;
import view.OutputMassageHandler;
import view.SubMenuStatus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class ProductMenu {

    private int outputNo;
    private Comment comment;
    private Product selectedProduct;
    private BuyLog buyLog;


    //finish
    public void processDigest() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(ProductsMenu.getProductId());
    }


    public void addToCart() throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        if (BuyLog.getFirstProduct()) {
            buyLog = new BuyLog(uniqueID);
        }
        buyLog.addProductToBuyLog(ProductsMenu.getProductId(),1);
    }

    //finish
    private boolean checkSeller(String seller) {
        //if (seller.matches("")) {
        if (Account.isThereAccountWithUsername(seller)) {
            return true;
        } else outputNo = 0;
        //} else outputNo = 0;
        OutputMassageHandler.showAccountOutput(outputNo);
        return false;
    }

    //finish
    public void selectSeller(String username) {
        Account seller1 = null;
        if (checkSeller(username)) {
            for (Account seller : selectedProduct.getListOfSellers()) {
                if (seller.getUsername().equals(username)) {
                    seller1 = seller;
                }
            }
        }
        if (seller1 != null) {
            selectedProduct.setSeller(seller1);
        }
    }

    //done
    public void allSellersForProduct() throws FileNotFoundException {
        OutputHandler.showAllSellersForOneProduct(selectedProduct.getId());
    }

    public void processAttributes() {

    }

//    public void processCompare(String productID) {
//        Product productToCompare = Product.getProductById(productID);
//        OutputHandler.compareProducts(productToCompare.getInfo(), selectedProduct.getInfo());
//    }

    //comment--------------------------------------------------------------------

    //done
    public void processComments() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        OutputHandler.showAllComments();
    }

    //done
    public void processCommentOnProduct() throws FileNotFoundException {
        OutputHandler.showCommentOnOneProduct(selectedProduct.getId());
        selectedProduct.getScore();
    }

    //done
    public void addComments() throws IOException {
        selectedProduct.setComment(comment=new Comment(selectedProduct,LoginMenu.getLoginAccount()));
        comment.setAllComments();
        Comment.writeInJ();
    }

    //done
    public void titleOfComment(String title) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        comment.setTitle(title);
        comment.setAllComments();
        Comment.writeInJ();
    }

    //done
    public void contentOfComment(String content) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        comment.setContent(content);
        comment.setAllComments();
        Comment.writeInJ();
    }

}
