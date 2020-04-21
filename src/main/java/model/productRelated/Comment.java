package model.productRelated;
import model.accounts.Account;

import java.util.ArrayList;

public class Comment {

    private boolean isSold;
    private CommentStatus commentStatus;
    private Product productToComment;
    private Account personToVote;
    private ArrayList<Comment> allComments = new ArrayList<Comment>();
    private String commentContent;

    public Comment(boolean isSold, CommentStatus commentStatus, Product productToCommit, Account personToVote) {
        this.isSold = isSold;
        this.commentStatus = commentStatus;
        this.productToComment = productToCommit;
        this.personToVote = personToVote;
        allComments.add(this);
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    //its wrong
    public static ArrayList<String> allCommentsOnProduct ( String productId){
        product=null;
       Product product = product.getProductById();
    }

    public void deleteCustomerCommentOnProduct ( String accountId , String productId ){

    }
}
