package model.productRelated;

import model.accounts.Account;
import model.accounts.Customer;

import java.util.ArrayList;
import java.util.Iterator;

public class Comment {

    //commentDetail
    private boolean isSold;
    String title;
    String content;
    CommentStatus commentStatus;
    String id;
    String personName;

    //objectAdded

    private Customer personToVote;
    String proId;


    //list
    public static ArrayList<Comment> allComments = new ArrayList<Comment>();

    public Comment(String id) {
        this.id = id;
        allComments.add(this);
    }

    public String getTitle() {
        return title;
    }

    public static boolean isThereCommentWithId(String id) {

        for (Comment comment : allComments) {
            if (comment.id.equalsIgnoreCase(id)) return true;
        }
        return false;
    }


//setterAndGetter--------------------------------------------------------------------------------------------

    public void setDetail(String title , String content , Customer personToVote){
        if (title != null){
            this.title = title;
        }
        if (content != null){
            this.content = content;
        }
        if (personToVote != null){
            this.personToVote=personToVote;
            personName = personToVote.getName();
        }
        allComments.add(this);
    }

    public String getId() {
        return id;
    }

    public static Comment getCommentFromId(String id){
        for (Comment comment : allComments) {
            if (comment.getId().equals(id)){
                return comment;
            }
        }
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setAllComments() {
        allComments.add(this);
    }

    public  ArrayList<Comment> getAllComments() {
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

    public String getContent() {
        return content;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public String getPersonName() {
        return personName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setAllComments(ArrayList<Comment> allComments) {
        Comment.allComments = allComments;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonToVote(Customer personToVote) {
        this.personToVote = personToVote;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public static ArrayList<Comment> getCommentsOfPro(String proId){
        Product product = Product.getProductById(proId);
        return product.proComments;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "isSold=" + isSold +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentStatus=" + commentStatus +
                ", personToVote=" + personToVote +
                '}';
    }
}