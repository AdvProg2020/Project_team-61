package controller.menus;

import model.accounts.Account;
import model.accounts.Seller;
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

    private static int outputNo =0;
    private static Comment comment;
    private static Product selectedProduct;
    private static BuyLog buyLog;


    public static BuyLog getBuyLog() {
        return buyLog;
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public static void processDigest() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(ProductsMenu.getProductId());
    }

    public static void addToCart() throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        if (BuyLog.getFirstProduct()) {
            buyLog = new BuyLog(uniqueID);
        }
        buyLog.addProductToBuyLog(ProductsMenu.getProductId(), 1);
    }


    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
    public static void processAttributes() {

    }

    public static void processCompare(String productID) throws FileNotFoundException {
        if (Product.isThereProductWithId(productID)) {
            OutputHandler.compareProducts(selectedProduct.getId(), productID);
        } else OutputMassageHandler.showProductOutput(4);
    }

    //comment--------------------------------------------------------------------
    public static void processComments() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        OutputHandler.showCommentOnOneProduct(selectedProduct.getId());
    }


    public static void addComments() throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
        selectedProduct.setComment(comment = new Comment(selectedProduct, LoginMenu.getLoginAccount()));
        comment.setAllComments();
        Comment.writeInJ();
        OutputMassageHandler.showProductsOutput(1);
    }


    public static void titleOfComment(String title) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        comment.setTitle(title);
        comment.setAllComments();
        OutputMassageHandler.showProductsOutput(2);
        Comment.writeInJ();
    }


    public static void contentOfComment(String content) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        comment.setContent(content);
        comment.setAllComments();
        Comment.writeInJ();
    }

}
