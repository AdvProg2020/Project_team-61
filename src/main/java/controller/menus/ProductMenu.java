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


import java.util.ArrayList;

public class ProductMenu {

    private int outputNo;
    private Comment comment;
    private Account loginAccount = LoginMenu.getLoginAccount();
    private ProductsMenu productsMenu;
    private Product selectedProduct;
    private BuyLog buyLog;
    private CommandProcessor commandprocessor;


    //finish
    public void processDigest() {
        commandprocessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        selectedProduct = Product.getProductById(productsMenu.getProductId());
        ArrayList<String> info = selectedProduct.getInfo();
    }


    public void addToCart() {
        if (Product.isFirstProduct()) {
            //???????????????????
            buyLog = new BuyLog(LoginMenu.getLoginAccount().getUsername());
        }
        selectedProduct.addProductToLog(account.getUsername(), selectedProduct.getId(), 1);
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

    //finish//json
    public void processCompare(String productID) {
        Product productToCompare = Product.getProductById(productID);
        OutputHandler.compareProducts(productToCompare.getInfo(), selectedProduct.getInfo());
    }

    //comment--------------------------------------------------------------------

    //
    public void processComments() {
        commandprocessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
        selectedProduct.getScore();
        selectedProduct.listOfComments(selectedProduct.getId());
    }

    //finish
    public void addComments() {
        commandprocessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
    }

    public void titleOfComment(String title) {
        commandprocessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
        comment = new Comment();
        //selectedProduct.addCommentTitle(title);
    }

    //finish
    public void contentOfComment(String content) {
        commandprocessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
        //selectedProduct.addCommentContent(content);
    }

}
