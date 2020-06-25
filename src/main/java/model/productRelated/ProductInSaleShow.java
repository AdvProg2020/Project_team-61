package model.productRelated;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Date;

public class ProductInSaleShow {
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
    public double saleAmount;
    public Date startOfSalePeriod;
    public Date endOfSalePeriod;
    public static ArrayList<ProductInSaleShow> list = new ArrayList<>();

    public ProductInSaleShow(String id) {
        this.id = id;
        list.add(this);
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Date getEndOfSalePeriod() {
        return endOfSalePeriod;
    }

    public Date getStartOfSalePeriod() {
        return startOfSalePeriod;
    }

    public void setEndOfSalePeriod(Date endOfSalePeriod) {
        this.endOfSalePeriod = endOfSalePeriod;
    }

    public void setStartOfSalePeriod(Date startOfSalePeriod) {
        this.startOfSalePeriod = startOfSalePeriod;
    }

    public String getName() {
        return name;
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public ArrayList<ProductInSaleShow> getList() {
        return list;
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

    public void setList(ArrayList<ProductInSaleShow> list) {
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
        for (ProductInSaleShow saleShow : list) {
            if (saleShow.getProductImage().equals(imageView)){
                return saleShow.id;
            }
        }
        return null;
    }
}
