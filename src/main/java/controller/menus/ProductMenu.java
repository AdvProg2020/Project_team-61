package controller.menus;


import model.accounts.Account;
import model.productRelated.Comment;
import model.productRelated.Product;
import view.CommandProcessor;
import view.OutputHandler;
import view.SubMenuStatus;

public class ProductMenu {
    private int outputNo;
    private int inputNo;
    private Comment comment;
    private CommandProcessor commandProcessor;
    private Account account;
    private ProductsMenu productsMenu;
    private Product selectedProduct;

    private OutputHandler outputHandler = new OutputHandler();

    public void processDigest() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
        //IN PRODUCTE BAYAD VIJHEGIASHO NESHOON BDIM
        //kln mikhai add kni ... az in selected estefade kn
        selectedProduct = Product.getProductById(productsMenu.getProductId());
    }

    public void addToCart() {

    }

    private boolean checkSeller(String seller) {
        if (seller.matches("")) {
            if (account.isThereAccountWithUsername(seller)) {
                return true;
            } else inputNo = 0;
        } else inputNo = 0;
        outputHandler.showAccountOutput(inputNo);
        return false;
    }

    public void selectSeller(String username) {
        if (checkSeller(username)) {

        }
    }

    public void processAttributes() {

    }

    public void processCompare(String productID) {

    }

    //comment--------------------------------------------------------------------
    public void processComments() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
    }

    public void addComments() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSTITLE);
    }

    public void titleOfComment(String title){
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTSCONTENT);
    }

    public void contentOfComment(String content){
        commandProcessor.setSubMenuStatus(SubMenuStatus.MAINMENU);
    }

}
