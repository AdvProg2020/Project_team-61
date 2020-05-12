package model.productRelated;

import model.accounts.Account;

import java.util.ArrayList;
import java.util.Iterator;

public class Comment {

    //commentDetail
    private boolean isSold;
    String title;
    String content;
    CommentStatus commentStatus;

    //objectAdded
    Product productToComment;
    private Account personToVote;


    //list
    public static ArrayList<Comment> allComments = new ArrayList<Comment>();


    public Comment(Product productToCommit, Account personToVote) {
        this.productToComment = productToCommit;
        this.personToVote = personToVote;
    }







    //setterAndGetter--------------------------------------------------------------------------------------------

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAllComments() {
        allComments.add(this);
    }

    public Account getPersonToVote() {
        return personToVote;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    //other------------------------------------------------------------------------------------------------------

    //finish
    public void deleteCommentOnProduct(Comment comment) {
        for (Comment allComment : allComments) {
            if (allComment.equals(comment)) {
                Iterator iterator = allComments.iterator();
                while (iterator.hasNext()) {
                    Comment comment1 = (Comment) iterator.next();
                    if (comment1.equals(comment)) {
                        iterator.remove();
                    }
                }
            }
        }
    }

}