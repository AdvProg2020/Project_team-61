package controller.request;

import model.accounts.Account;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;

import java.io.IOException;
import java.util.ArrayList;

public class CommentRequest extends Request {

    private static String title;
    private static String content;
    private static Account personToVote;
    private static Product product;
    private static String id;
    private static ArrayList<CommentRequest> allCommentRequests = new ArrayList<>();

    public CommentRequest(String requestID) throws IOException {
        super(requestID);
        allCommentRequests.remove(this);
    }


    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allCommentRequests.remove(request);
        Comment comment = Comment.getCommentFromId(id);
        comment.setCommentStatus(CommentStatus.NOTAPPROVEDBYTHEMANAGER);
    }


    public static void acceptRequest(Request request) {
        Comment comment =Comment.getCommentFromId(id);
        comment.setDetail(title,content,personToVote,product);
        comment.setCommentStatus(CommentStatus.CONFIRMED);
        product.setComment(comment);
        Request.getAllRequests().remove(request);
        allCommentRequests.remove(request);
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