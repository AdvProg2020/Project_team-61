package controller.menus;


import model.productRelated.Comment;
import view.CommandProcessor;
import view.SubMenuStatus;

public class ProductMenu {
    private int outputNo;
    private int inputNo;
    private Comment comment;
    private CommandProcessor commandProcessor;

    public void processDigest() {
        commandProcessor.setSubMenuStatus(SubMenuStatus.DIGEST);
    }

    public void addToCart(){

    }

    public void selectSeller(String username){

    }

    public void processAttributes() {

    }

    public void processCompare(String productID) {

    }

    public void processComments() {

    }

    public void addComments(){
        commandProcessor.setSubMenuStatus(SubMenuStatus.COMMENTS);
    }

}
