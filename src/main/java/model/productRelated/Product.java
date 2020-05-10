package model.productRelated;
import com.google.gson.Gson;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.log.BuyLog;
import model.log.Log;
import model.off.DiscountCode;
import model.off.Sale;

import java.util.*;
public class Product  {
    private Gson productGson;
    private String productJson;

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
    private boolean isBought;

    //lists
    private static ArrayList<Seller> listOfSellers = new ArrayList<Seller>();
    private HashMap<Category,ArrayList<Product>> listOfAllProducts = new HashMap<Category, ArrayList<Product>>();
    private static ArrayList<Product> allProduct = new ArrayList<Product>();
    private ArrayList<String> info=new ArrayList<>();
    public static ArrayList<String> listOfId=new ArrayList<String>();

    //objectsAdded
    private Comment comment;
    public Score score;
    private Log log;
    private Account account;
    private DiscountCode discountCode;
    private Sale sale;



    public Product(String productId) {
        this.productId = productId;
        listOfId.add(productId);
    }



    //finish
    public void setDetailProduct (String name , String companiesName , double price , Seller seller , int numberOfProducts ,Category category) {
        this.productName = name;
        this.companiesName=companiesName;
        this.price=price;
        this.seller=seller;
        this.numberOfProducts=numberOfProducts;
        this.category=category;
//        allProduct.add(this);
        listOfSellers.add(seller);
        listOfAllProducts.put(category,allProduct);
        allProduct.add(this);
        productJson=productGson.toJson(allProduct);

    }


    //settersAndGetters----------------------------------------------------------------------------------


    public void setProductJson(String productJson) {
        this.productJson = productJson;
    }

    public String getProductJson() {
        return productJson;
    }

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

    public boolean getInSale () {
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

    public void setSale(Sale sale) {
        this.sale = sale;
        setInSale(true);
    }
    public Sale getSale() {
        return sale;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
        setHasDiscount(true);
    }
    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setTotalNumberOfBuyers(int totalNumberOfBuyers) {
        this.totalNumberOfBuyers = totalNumberOfBuyers;
    }
    public int getTotalNumberOfBuyers() {
        return totalNumberOfBuyers;
    }

    public void setInfo(ArrayList<String> info) {
        info.add(getProductName());
        info.add(getCompaniesName());
        info.add(String.valueOf(getPrice()));
        if (hasDiscount){
           // info.add(discountCode.get)
        }
        info.add(getCategory().getName());
        info.add(seller.getName());
        info.add(String.valueOf(getAverageScore()));
        info.add(additionalDetail);

    }
    public ArrayList<String> getInfo() {
        return info;
    }

    public HashMap<Category, ArrayList<Product>> getListOfAllProducts() {
        return listOfAllProducts;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    //othersTobeHandel-------------------------------------------------------------------------------


    public void addCommentTitle(String title){
        comment.setCommentTitle(title);
    }
    public void addCommentContent(String content){
        comment.setCommentContent(content);
    }

    //finish
    public static Product getProductById(String id) {
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
    public static void deleteProduct ( String productId ){
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
    public static boolean isThereProductWithId (String productId){
        Product product=getProductById(productId);
        if (product==null){
            return false;
        }
        else return true;
    }

    //finish
    public static ArrayList<Product> getProductList(){
        return allProduct;
    }

    //finish
    public int getProductListSize (){
        return allProduct.size();
    }

    //finish//doubt
    public void addProductToLog(String userName , String productId,int amount){
        seller.saleLog.addProductToSaleLog(productId);
        Customer customer= (Customer) account.getAccountWithUsername(userName);
        customer.buyLog.addProductToBuyLog(productId,amount);
    }
    public Product getProductWithName(String name){
        for (model.productRelated.Product product : allProduct) {
            if (product.getProductName().equals(name)){
                return product;
            }

        }
        return null;
    }

    //finish
    public void deleteProductByCategory(String categoryId){
        ArrayList<Product> productArrayList=listOfAllProducts.get(categoryId);
        for (int i = 0; i < allProduct.size(); i++) {
            for (int i1 = 0; i1 < productArrayList.size(); i1++) {
                if (allProduct.get(i).equals(productArrayList.get(i1))){
                    Product.deleteProduct(allProduct.get(i).getId());
                }
            }
        }
    }


    //finish
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