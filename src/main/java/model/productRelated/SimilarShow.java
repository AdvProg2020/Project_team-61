package model.productRelated;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SimilarShow {
    public String name;
    public double  price;
    public ImageView productImage;
    public static ArrayList<SimilarShow> list = new ArrayList<>();

    public SimilarShow() {
        list.add(this);
    }

    public String getName() {
        return name;
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public double getPrice() {
        return price;
    }

    public static void setList(ArrayList<SimilarShow> list) {
        SimilarShow.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductImage(ImageView productImage) {
        this.productImage = productImage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static ArrayList<SimilarShow> getList() {
        return list;
    }

}
