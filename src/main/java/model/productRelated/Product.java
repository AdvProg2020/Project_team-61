package model.productRelated;

import model.accounts.Seller;
import model.log.Log;

import java.util.*;
public class Product implements Comparable{


    private String productId;
    private String productName;
    private String companiesName;
    private double price;
    private Seller seller;
    private ProductStatus productStatus;
    private ArrayList<Seller> listOfSellers = new ArrayList<Seller>();
    private HashMap<Category,ArrayList<Product>> listOfAllProducts = new HashMap<Category, ArrayList<Product>>();
    private static ArrayList<Product> allProduct = new ArrayList<Product>();
    private int totalNumberOfBuyers;
    private Category category;
    private double averageScore;
    private int numberOfProducts;
    private boolean isInSale;
    private boolean hasDiscount;
    private String additionalDetail;
    private int numberOfViews;
    private HashMap<Product,Integer> allProductsWithViews;
    private Comment comment;
    private Score score;
    private Log log;
    //finish
    public Product(String productId) {
        this.productId = productId;
    }

    //finish//doubt
    public void setDetailProduct (String name , String companiesName , double price , Seller seller , int numberOfProducts) {
        this.productName = name;
        this.companiesName=companiesName;
        this.price=price;
        this.seller=seller;
        this.numberOfProducts=numberOfProducts;
        this.category=category;
        allProduct.add(this);
        listOfSellers.add(seller);
        listOfAllProducts.put(category,allProduct);
    }

    //finish
    public int getNumberOfView () {
        return numberOfViews;
    }

    //finish
    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    //finish
    public String  getId () {
        return productId;
    }

    //should be input
    public void setAdditionalDetail ( String detail) {

    }

    //finish
    public static List listOfComments ( String id) {
        for (Product product : allProduct) {
            if (product.getId().equals(id)){
                return product.comment.allCommentsOnProduct(id);
            }
        }
        return null;
    }

    //finish
    public void setProductStatus ( ProductStatus amount ){
        productStatus = amount ;
    }

    //finish
    private double getAverageScore (String productId) {
        for (Product product : allProduct) {
            if (product.getId().equals(productId)){
                return product.score.getAverageScore();
            }
        }
        return 0;
    }

    public void setAverageScore ( int averageScore ) {

    }

    //finish
    public Product getProductById ( String id ) {
        for (Product product : allProduct) {
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    //its wrong
    public ArrayList<Seller> getListOfSellers (Product product ) {
        return listOfSellers;
    }

    public  void deleteProduct ( String productId ){
        for (Product product : allProduct) {
            if (product.getId().equals(productId)){
                Iterator iterator = allProduct.iterator();
                while(iterator.hasNext()) {
                    Product product1 = (Product) iterator.next();
                    if(product1.equals(product)) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public void changeProductStatus ( String productId ){

    }

    //finish
    public void viewProductStatus (String productId){
        for (Product product : allProduct) {
            if (product.getId().equals(productId)) {
                System.out.println(product.productStatus);
            }
        }
    }

    //finish
    public boolean getOnSale () {
        return isInSale;
    }

    //finish
    public void setInSale(boolean inSale) {
        isInSale = inSale;
    }

    //finish
    public boolean isThereProductWithId (String productId){
        for (Product product : allProduct) {
            if (product.getId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    public void viewProduct (Product product){

    }

    //finish
    public void viewAllAdditionalProductStatus ( String productId){
        for (Product product : allProduct) {
            if (product.getId().equals(productId)){
                System.out.println(product.additionalDetail);
            }
        }
    }

    public void addTotalNumberOfBuyers() {

    }

    public int compareTo(Object o) {
        return 0;
    }

}
