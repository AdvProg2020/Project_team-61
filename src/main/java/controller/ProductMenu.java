package controller;

import controller.menus.LoginMenu;
import model.request.CommentRequest;
import model.request.Request;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;
import view.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class ProductMenu {

    private static int outputNo =0;
    private static Product selectedProduct;
    private static BuyLog buyLog;
    private static CommentRequest commentRequest;


    public static BuyLog getBuyLog() {
        return buyLog;
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public static void processDigest() throws FileNotFoundException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(ProductsMenu.getProductId());
        OutputHandler.digest(selectedProduct.getId());
    }

    public static void addToCart() throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        if (BuyLog.getFirstProduct()) {
            buyLog = new BuyLog(uniqueID);
        }
        buyLog.addProductToBuyLog(ProductsMenu.getProductId(), 1);
    }


    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
    public static void processAttributes() throws FileNotFoundException {
        OutputHandler.attributes(selectedProduct.getId());
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
        String commentId = LoginMenu.getLoginAccount() + " comment on " + selectedProduct;
        if(!Comment.isThereCommentWithId(commentId)) {
            String id= LoginMenu.getLoginAccount()+ "comment";
            if (!Request.isThereRequestFromID(id)) {
//                Comment comment = new Comment(id);
//                comment.setCommentStatus(CommentStatus.WAITINGFORAPPROVAL);
                commentRequest = new CommentRequest(id);
                commentRequest.setPersonToVote(LoginMenu.getLoginAccount());
                commentRequest.setProduct(selectedProduct);
                commentRequest.setId(commentId);
            }else commentRequest = (CommentRequest) Request.getRequestFromID(id);
            outputNo = 1;
            CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
            CommandProcessor.setInternalMenu(InternalMenu.CHANGEDETAILS);
        }else outputNo= 3;
        OutputMassageHandler.showProductsOutput(outputNo);
    }


    public static void titleOfComment(String title) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        commentRequest.setTitle(title);
        OutputMassageHandler.showProductsOutput(2);

    }


    public static void contentOfComment(String content) throws IOException {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        CommandProcessor.setInternalMenu(InternalMenu.MAINMENU);
        commentRequest.setContent(content);
    }

}