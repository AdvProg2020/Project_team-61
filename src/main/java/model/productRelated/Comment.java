package model.productRelated;
import model.accounts.Account;

import java.util.ArrayList;
import java.util.Iterator;

public class Comment {

    //commentDetail
    private boolean isSold;
    private String commentContent;
    private CommentStatus commentStatus;

    //objectAdded
    private Product productToComment;
    private Account personToVote;


    //list
    public ArrayList<Comment> allComments = new ArrayList<Comment>();



    public Comment(Product productToCommit, Account personToVote) {
        this.productToComment = productToCommit;
        this.personToVote = personToVote;
        allComments.add(this);
    }


    //setterAndGetter--------------------------------------------------------------------------------------------

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    public Account getPersonToVote() {
        return personToVote;
    }
    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    //other------------------------------------------------------------------------------------------------------

    //finish
    public void deleteCommentOnProduct (Comment comment){
        for (Comment allComment : allComments) {
            if (allComment.equals(comment)){
                Iterator iterator = allComments.iterator();
                while(iterator.hasNext()) {
                    Comment comment1 = (Comment) iterator.next();
                    if(comment1.equals(comment)) {
                        iterator.remove();
                    }
                }
            }
        }
    }

}