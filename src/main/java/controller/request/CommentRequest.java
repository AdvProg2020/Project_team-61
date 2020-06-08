package controller.request;

import model.accounts.Account;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;

import java.io.IOException;
import java.util.ArrayList;

public class CommentRequest extends Request {
    private static String commentId = null;
    private static String id = null;
    private static String title= null;
    private static String content= null;
    private static Account personToVote= null;
    private static Product product= null;
    private static CommentRequest cr= null;
    private static ArrayList<CommentRequest> allCommentRequests = new ArrayList<>();

    public CommentRequest(String requestID) throws IOException {
        super(requestID);
        allCommentRequests.remove(this);
        writeInJ();
    }


    public static CommentRequest getRequestFromID(String requestID){
        for(CommentRequest request : allCommentRequests){
            if (request.commentId.equalsIgnoreCase(requestID)) return request;
        }
        return null;
    }


    public static void declineRequest(Request request) {
        Request.getAllRequests().remove(request);
        allCommentRequests.remove(request);
        Comment comment = Comment.getCommentFromId(commentId);
        comment.setCommentStatus(CommentStatus.NOTAPPROVEDBYTHEMANAGER);
    }


    public static void acceptRequest(Request request) {
        cr = CommentRequest.getRequestFromID(request.getRequestText());
        Comment comment =Comment.getCommentFromId(commentId);
        comment.setDetail(title,content,personToVote,product);
        comment.setCommentStatus(CommentStatus.CONFIRMED);
        product.setComment(comment);
        Request.getAllRequests().remove(request);
        allCommentRequests.remove(request);
    }

    public static void setId(String id) throws IOException {
        CommentRequest.id = id;
        writeInJ();
    }

    public static void setCommentId(String id) throws IOException {
        CommentRequest.id = id;
        writeInJ();
    }

    public void setTitle(String title) throws IOException {
        this.title = title;
        writeInJ();

    }

    public void setContent(String content) throws IOException {
        this.content = content;
        writeInJ();

    }

    public void setProduct(Product product) throws IOException {
        this.product = product;
        writeInJ();

    }

    public void setPersonToVote(Account personToVote) throws IOException {
        this.personToVote = personToVote;
        writeInJ();

    }

}