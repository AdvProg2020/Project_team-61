package controller.menus;

import model.accounts.Account;
import model.accounts.Seller;
import model.log.BuyLog;
import model.productRelated.Comment;
import model.productRelated.Product;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import view.CommandProcessor;
import view.OutputHandler;
import view.OutputMassageHandler;
import view.SubMenuStatus;

import java.util.ArrayList;

public class ProductMenu {

    private int outputNo;
    private Comment comment;
    private Account loginAccount = LoginMenu.getLoginAccount();
    private ProductsMenu productsMenu;
    private Product selectedProduct;
    private BuyLog buyLog;


    //finish
    public void processDigest() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(productsMenu.getProductId());

    }


    public void addToCart() {
        if (BuyLog.getFirstProduct()) {
            buyLog = new BuyLog(LoginMenu.getLoginAccount().getUsername());
        }
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
        Seller seller1 = null;
        if (checkSeller(username)) {
            for (Seller seller : selectedProduct.getListOfSellers()) {
                if (seller.getUsername().equals(username)) {
                    seller1 = seller;
                }
            }
        }
        if (seller1 != null) {
            selectedProduct.setSeller(seller1);
        }
    }

    //finish//json

    //TO COMMANDA NDIDM
    public void allSellersForProduct(String productId) {
        OutputHandler.showAllSellersForOneProduct(Product.getProductById(productId).getListOfSellers());
    }

    public void processAttributes() {

    }

//    public void processCompare(String productID) {
//        Product productToCompare = Product.getProductById(productID);
//        OutputHandler.compareProducts(productToCompare.getInfo(), selectedProduct.getInfo());
//    }

    //comment--------------------------------------------------------------------

    //
    public void processComments() {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        selectedProduct.getScore();
        comment.getAllComments();
    }

    //finish
    public void addComments() {
        selectedProduct.setComment(comment=new Comment(selectedProduct,loginAccount));
        comment.setAllComments();
    }

    public void titleOfComment(String title) {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        comment.setTitle(title);
        comment.setAllComments();
    }

    //finish
    public void contentOfComment(String content) {
        CommandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        comment.setContent(content);
        comment.setAllComments();
    }

}
