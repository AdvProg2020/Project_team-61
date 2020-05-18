package controller.menus;

import controller.request.CommentRequest;
import controller.request.Request;
import model.accounts.Account;
import model.accounts.Seller;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
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
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
        String id= LoginMenu.getLoginAccount()+ "comment";
        if(Request.isThereRequestFromID(id)){
            Request.deleteRequest(id);
        }
        commentRequest = new CommentRequest(id);
        commentRequest.setPersonToVote(LoginMenu.getLoginAccount());
        commentRequest.setProduct(selectedProduct);
        String commentId = LoginMenu.getLoginAccount() + " comment on " +selectedProduct;
        Comment comment = new Comment(id);
        commentRequest.setId(commentId);
        comment.setCommentStatus(CommentStatus.WAITINGFORAPPROVAL);
        OutputMassageHandler.showProductsOutput(1);
    }


    public static void titleOfComment(String title)  {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        commentRequest.setTitle(title);
        OutputMassageHandler.showProductsOutput(2);

    }


    public static void contentOfComment(String content)  {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        commentRequest.setContent(content);
    }

}