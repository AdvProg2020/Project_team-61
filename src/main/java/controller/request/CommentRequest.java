package controller.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommentRequest extends Request {

    private static String title;
    private static String content;
    private static Account personToVote;
    private static Product product;
    private static String id;
    private static ArrayList<CommentRequest> allCommentRequests = new ArrayList<>();
    public static Type commentRequestType = new TypeToken<ArrayList<CommentRequest>>() {
    }.getType();

    public CommentRequest(String requestID) throws IOException {
        super(requestID);
        allCommentRequests.remove(this);
        writeInJ();
    }

    @Override
    public  void declineRequest() {
        Request.getAllRequests().remove(this);
        allCommentRequests.remove(this);
        Comment comment = Comment.getCommentFromId(id);
        comment.setCommentStatus(CommentStatus.NOTAPPROVEDBYTHEMANAGER);
    }

    @Override
    public  void acceptRequest() {
        Comment comment =Comment.getCommentFromId(id);
        comment.setDetail(title,content,personToVote,product);
        comment.setCommentStatus(CommentStatus.CONFIRMED);
        product.setComment(comment);
        Request.getAllRequests().remove(this);
        allCommentRequests.remove(this);
    }

    public void setId(String id) throws IOException {
        this.id = id;
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

    public static void setAllCommentRequests(ArrayList<CommentRequest> allCommentRequests) {
        CommentRequest.allCommentRequests = allCommentRequests;
    }

    public static ArrayList<CommentRequest> getAllCommentRequests() {
        return allCommentRequests;
    }

    public static void writeInJ() throws IOException {
        FileHandling.setGson(new Gson());
        String json = FileHandling.getGson().toJson(CommentRequest.allCommentRequests, commentRequestType);
        FileHandling.writeInFile(json, "commentRequest.json");
    }

}