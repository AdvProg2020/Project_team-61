package controller.request;

import model.accounts.Account;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;

import java.util.ArrayList;

public class CommentRequest extends Request {

    private String title;
    private String content;
    private Account personToVote;
    private Product product;
    private String id;
    private ArrayList<CommentRequest> allCommentRequests = new ArrayList<>();

    public CommentRequest(String requestID) {
        super(requestID);
        allCommentRequests.remove(this);
    }


    public void declineRequest() {
        allRequests.remove(this);
        allCommentRequests.remove(this);
        Comment comment = Comment.getCommentFromId(id);
        comment.setCommentStatus(CommentStatus.NOTAPPROVEDBYTHEMANAGER);
    }


    public void acceptRequest() {
        Comment comment =Comment.getCommentFromId(id);
        comment.setDetail(title,content,personToVote,product);
        comment.setCommentStatus(CommentStatus.CONFIRMED);
        product.setComment(comment);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPersonToVote(Account personToVote) {
        this.personToVote = personToVote;
    }

}