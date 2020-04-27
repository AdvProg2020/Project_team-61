package model.productRelated;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.log.BuyLog;
import model.log.Log;

import java.util.*;
public class Product {

    //productDetail
    private String productId;
    private String productName;
    private String companiesName;
    private double price;
    private Seller seller;
    private ProductStatus productStatus;
    private Category category;
    private double averageScore;
    private int numberOfProducts;
    private boolean isInSale;
    private boolean hasDiscount;
    private String additionalDetail;
    private int numberOfViews;
    private int totalNumberOfBuyers;
    //trueIsCountable
    private boolean countableOrNot;

    //lists
    private ArrayList<Seller> listOfSellers = new ArrayList<Seller>();
    private HashMap<Category,ArrayList<Product>> listOfAllProducts = new HashMap<Category, ArrayList<Product>>();
    private static ArrayList<Product> allProduct = new ArrayList<Product>();
    private HashMap<Product,Integer> allProductsWithViews;

    //objectsAdded
    private Comment comment;
    public Score score;
    private Log log;
    private Account account;
    private Date date;



    public Product(String productId) {
        this.productId = productId;
        allProduct.add(this);
    }



    //finish
    public void setDetailProduct (String name , String companiesName , double price , Seller seller , int numberOfProducts ) {
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


    //settersAndGetters----------------------------------------------------------------------------------

    public String  getId () {
        return productId;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setCountableOrNot(boolean countableOrNot) {
        this.countableOrNot = countableOrNot;
    }
    public boolean getCountableOrNot(){
        return countableOrNot;
    }

    public int getNumberOfView () {
        return numberOfViews;
    }
    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public double getAverageScore () {
        for (Product product : allProduct) {
            if (product.getId().equals(productId)){
                return product.score.getAverageScore();
            }
        }
        return 0;
    }
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }
    public String getAdditionalDetail() {
        return additionalDetail;
    }

    public void setProductStatus ( ProductStatus status ){
        productStatus = status ;
    }
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public boolean getOnSale () {
        return isInSale;
    }
    public void setInSale(boolean inSale) {
        isInSale = inSale;
    }

    public Category getCategory() {
        return category;
    }

    public String getCompaniesName() {
        return companiesName;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }
    public boolean getHasDiscount() {
        return hasDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public Score getScore() {
        return score;
    }

    //othersTobeHandel-------------------------------------------------------------------------------

    //finish
    public Product getProductById ( String id ) {
        for (Product product : allProduct) {
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    //finish
    public List listOfComments ( String id) {
        Product product=getProductById(id);
        return product.comment.allComments;
    }

    //finish
    public ArrayList<Seller> getListOfSellers () {
        return listOfSellers;
    }

    //finish
    public  void deleteProduct ( String productId ){
        Product product=getProductById(productId);
        Iterator iterator = allProduct.iterator();
        while(iterator.hasNext()) {
            Product product1 = (Product) iterator.next();
            if(product1.equals(product)) {
                iterator.remove();
            }
        }
    }

    //finish
    public void viewProductStatus (String productId){
        Product product=getProductById(productId);
        System.out.println(product.productStatus);

    }

    //finish
    public boolean isThereProductWithId (String productId){
        Product product=getProductById(productId);
        if (product==null){
            return false;
        }
        else return true;
    }

    //finish
    public void viewAllAdditionalProductStatus ( String productId){
        Product product=getProductById(productId);
        System.out.println(product.additionalDetail);
    }

    //finish
    public void addTotalNumberOfBuyers() {
        if (BuyLog.isBought(productId)){
            totalNumberOfBuyers++;
        }
    }

    //finish
    public static ArrayList<Product> getProductList(){
        return allProduct;
    }

    //finish
    public int getProductLListSize (){
        return allProduct.size();
    }


    //finish
    public static ArrayList<Comment> allCommentsOnProduct ( String productId){
        Product product=null;
        product = product.getProductById(productId);
        return product.comment.allComments;
    }

    //finish//doubt
    public void addProductToLog(String userName , String productId){
        Product product=getProductById(productId);
        seller.saleLog.addProductToSaleLog(product);
        Customer customer= (Customer) account.getAccountWithUsername(userName);
        customer.buyLog.addProductToBuyLog(product);
    }

    public static Comparator<Product> productComparatorForView = new Comparator<Product>() {

        public int compare(Product s1, Product s2) {

            int productView1 = s1.getNumberOfView();
            int productView2 = s2.getNumberOfView();
            return productView1- productView2;

        }
    };

    //finish
    public static Comparator<Product> productComparatorForScore = new Comparator<Product>() {

        public int compare(Product s1, Product s2) {

            double productScore1 = s1.getAverageScore();
            double productScore2 = s2.getAverageScore();
            return Double.compare(productScore1,productScore2);

        }
    };


}
