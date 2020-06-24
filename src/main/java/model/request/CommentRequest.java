package model.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.productRelated.Comment;
import model.productRelated.CommentStatus;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommentRequest extends Request {

    private  String title;
    private  String content;
    private  Account personToVote;
    private  Product product;
    private  String id;
    private static ArrayList<CommentRequest> allCommentRequests = new ArrayList<>();
    public static Type commentRequestType = new TypeToken<ArrayList<CommentRequest>>() {
    }.getType();

    public CommentRequest(String requestID) throws IOException {
        super(requestID);
        allCommentRequests.remove(this);
        if(Account.getAccountWithUsername(this.getSeller()) instanceof Customer) {
            Customer customer = (Customer) Account.getAccountWithUsername(this.getSeller());
            customer.addCommentRequest(this);
        }
        writeInJ();
    }

    @Override
    public  void declineRequest() throws IOException {
        Request.getAllRequests().remove(this);
        allCommentRequests.remove(this);
        Comment comment = Comment.getCommentFromId(id);
        comment.setCommentStatus(CommentStatus.NOTAPPROVEDBYTHEMANAGER);
        if(Account.getAccountWithUsername(this.getSeller()) instanceof Customer) {
            Customer customer = (Customer) Account.getAccountWithUsername(this.getSeller());
            customer.removeCommentRequest(this);
        }
        writeInJ();
    }

    @Override
    public  void acceptRequest() throws IOException {
        Comment comment =Comment.getCommentFromId(id);
        comment.setDetail(title,content,personToVote,product);
        comment.setCommentStatus(CommentStatus.CONFIRMED);
        product.setComment(comment);
        Request.getAllRequests().remove(this);
        allCommentRequests.remove(this);
        if(Account.getAccountWithUsername(this.getSeller()) instanceof Customer) {
            Customer customer = (Customer) Account.getAccountWithUsername(this.getSeller());
            customer.removeCommentRequest(this);
        }
        writeInJ();
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