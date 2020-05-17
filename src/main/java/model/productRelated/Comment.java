package model.productRelated;

import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
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

    public static ArrayList<Comment> getAllComments() {
        return allComments;
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

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Comment>>(){}.getType();
        String json= FileHandling.getGson().toJson(getAllComments(),collectionType);
        FileHandling.turnToArray(json+" "+"commend.json");
    }

    @Override
    public String toString() {
        return "Comment{" +
                "isSold=" + isSold +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentStatus=" + commentStatus +
                ", productToComment=" + productToComment +
                ", personToVote=" + personToVote +
                '}';
    }
}