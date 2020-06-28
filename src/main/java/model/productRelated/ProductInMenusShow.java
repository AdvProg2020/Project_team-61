package model.productRelated;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;


public class ProductInMenusShow {


    public String name;
    public double  price;
    public ImageView productImage;
    public String productImageId;
    public String id;
    public String category;
    public String seller;
    public Comment comment;
    public String additionalDetail;
    public int numberOfProduct;
    public double score = 50;
    public String firm;
    public static ArrayList<ProductInMenusShow> list = new ArrayList<>();
    public StackPane stackPane = new StackPane();
    public Label inSaleOrFinishLabel = new Label();

    public ProductInMenusShow(String id) {
        this.id = id;
        list.add(this);
        inSaleOrFinishLabel.setVisible(true);
        inSaleOrFinishLabel.setText("IS AVAILABLE");
        inSaleOrFinishLabel.setTextFill(Color.DARKBLUE);
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getName() {
        return name;
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public ArrayList<ProductInMenusShow> getList() {
        return list;
    }

    public Label getInSaleOrFinishLabel() {
        return inSaleOrFinishLabel;
    }

    public void setInSaleOrFinishLabel(Label inSaleOrFinishLabel) {
        this.inSaleOrFinishLabel = inSaleOrFinishLabel;
    }

    public String getCategory() {
        return category;
    }

    public Comment getComment() {
        return comment;
    }

    public double getScore() {
        return score;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public String getAdditionalDetail() {
        return additionalDetail;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductImageId() {
        return productImageId;
    }

    public String getSeller() {
        return seller;
    }

    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setList(ArrayList<ProductInMenusShow> list) {
        this.list = list;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductImage(ImageView productImage) {
        this.productImage = productImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductImageId(String productImageId) {
        this.productImageId = productImageId;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public static String getIdWithImage(ImageView imageView){
        for (ProductInMenusShow show : list) {
            if (show.productImage.equals(imageView)){
                return show.getId();
            }
        }
        return null;
    }
}