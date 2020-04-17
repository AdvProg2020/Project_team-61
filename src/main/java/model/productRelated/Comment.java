package model.productRelated;
import model.accounts.Account;

import java.util.ArrayList;

public class Comment {

    private boolean isSold;
    private CommentStatus commentStatus;
    private Product productToComment;
    private Account personToVote;
    private ArrayList<Comment> allComments = new ArrayList<Comment>();
    public Product product;

    public Comment(boolean isSold, CommentStatus commentStatus, Product productToCommit, Account personToVote) {
        this.isSold = isSold;
        this.commentStatus = commentStatus;
        this.productToComment = productToCommit;
        this.personToVote = personToVote;
        allComments.add(this);
    }


    public void setCommentOnCommentSection (String comment){

    }

    //its wrong
    public static ArrayList<String> allCommentsOnProduct ( String productId){
        ArrayList<String> list = new ArrayList<String>();
        return list;
    }

    public void deleteCustomerCommentOnProduct ( Account account , Product product ){}
}
