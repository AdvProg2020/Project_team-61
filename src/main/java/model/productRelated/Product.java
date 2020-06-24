package model.productRelated;

import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import model.accounts.Customer;
import model.accounts.Seller;
import model.firms.Company;
import model.firms.Firm;
import model.log.Log;
import model.off.Sale;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class Product {
    private static String productJson;

    //productDetail

    private String productId=null;
    private String productName=null;
    private Firm companiesName=null;
    private double price=0;
    private Seller seller=null;
    private ProductStatus productStatus=null;
    private Category category=null;
    private double averageScore=0;
    private int numberOfProducts=0;
    private boolean isInSale = false;
    private String additionalDetail =null;
    private int numberOfViews =0;
    private int totalNumberOfBuyers=0;
    private boolean isBought = false;
    String productImageId=null;

    //sample.lists
//    private  ArrayList<Account> listOfSellers = new ArrayList<Account>();
    private ArrayList<Customer> listOfBuyers = new ArrayList<>();
    private static ArrayList<Product> allProduct = new ArrayList<Product>();
    private ArrayList<String> info = new ArrayList<>();
    public static ArrayList<String> listOfId = new ArrayList<String>();
    private ArrayList<Comment> proComments = new ArrayList<Comment>();
    private ArrayList<Score> proScores = new ArrayList<>();
    private static HashMap<String, String> categorySpecifications = new HashMap<>();
    public ArrayList<String> productCategorySpecifications = new ArrayList<>();

    //objectsAdded
    private Comment comment;
    public Score score;
    private Log log;
    private Account account;
    private Sale sale;
    private Firm firm;


    public Product(String productId) throws IOException {
        if (productId != null) {
            this.productId = productId;
        }
        allProduct.add(this);
        listOfId.add(productId);
        //writeInJ();
    }


    public static HashMap<String, String> getCategorySpecifications() {
        return categorySpecifications;
    }

    public void setCategorySpecifications(HashMap<String, String> categorySpecifications) {
        this.categorySpecifications = categorySpecifications;
    }

    //finish
    //id,productImage,name,price,category,seller
    public void setDetailProduct(String productImageId, String name, double price, Category category, Account selle, Firm firm, int numberOfProducts) throws IOException {
        if (name != null) {
            this.productName = name;
        }
        if (firm != null) {
            this.firm = firm;
        }
        if (price != 0) {
            this.price = price;
        }
        if (seller != null) {
            if(selle instanceof Seller) {
                this.seller = (Seller) selle;
            }
        }
        if (numberOfProducts != 0) {
            this.numberOfProducts = numberOfProducts;
        }
        if (category != null) {
            this.category = category;
        }
        if (productImageId != null) {
            this.productImageId = productImageId;
        }
//        listOfSellers.add(seller);
        //       allProduct.add(this);
        writeInJ();
    }


    //settersAndGetters----------------------------------------------------------------------------------


    public ArrayList<String> getProductCategorySpecifications() {
        return productCategorySpecifications;
    }

    public void setProductCategorySpecifications(ArrayList<String> productCategorySpecifications) {
        this.productCategorySpecifications = productCategorySpecifications;
    }

    public String getProductImage() {
        return productImageId;
    }

    public void setProductImage(String productImage) {
        this.productImageId = productImage;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }

    public ArrayList<String> getInfo() {
        return info;
    }


    public static void setAllProduct(ArrayList<Product> allProduct) {
        Product.allProduct = allProduct;
    }

    public ArrayList<Customer> getListOfBuyers() {
        return listOfBuyers;
    }

    public void setIsBought(Boolean isBought) {
        this.isBought = isBought;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setScore(Score score) {
        this.score = score;
        proScores.add(score);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        proComments.add(comment);
    }

    public String getId() {
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

    public int getNumberOfView() {
        return numberOfViews;
    }

    public void setNumberOfViews() {
        this.numberOfViews = numberOfViews + 1;
    }

    public double getAverageScore() {
        for (Product product : allProduct) {
            if (product.getId().equals(productId)) {
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
//        setInfo();
    }

    public String getAdditionalDetail() {
        return additionalDetail;
    }

    public void setProductStatus(ProductStatus status) {
        productStatus = status;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public boolean getInSale() {
        return isInSale;
    }

    public void setInSale(boolean inSale) {
        isInSale = inSale;
    }

    public Category getCategory() {
        return category;
    }

    public Firm getCompaniesName() {
        return companiesName;
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


    public void setTotalNumberOfBuyers(int totalNumberOfBuyers) {
        this.totalNumberOfBuyers = totalNumberOfBuyers;
    }

    public int getTotalNumberOfBuyers() {
        return totalNumberOfBuyers;
    }

    public Seller getSeller() {
        return (Seller) seller;
    }


    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Comment getComment() {
        return comment;
    }

    //othersTobeHandel-------------------------------------------------------------------------------


    //checked
    public static Product getProductById(String id) {
        for (Product product : allProduct) {
            if (product.productId.equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }


//    public boolean ifProductHasSeller(String productId, String sellerUserName){
//        if (isThereProductWithId(productId)) {
//            for (Account seller : Product.getProductById(productId).getListOfSellers()) {
//                if (seller.equals(Seller.getAccountWithUsername(sellerUserName))){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    //finish
//    public ArrayList<Account> getListOfSellers () {
//        return listOfSellers;
//    }

    //finish
    public static void deleteProduct(String productId) {
        Product product = getProductById(productId);
        Iterator iterator = allProduct.iterator();
        while (iterator.hasNext()) {
            Product product1 = (Product) iterator.next();
            if (product1.equals(product)) {
                iterator.remove();
            }
        }
    }

    //checked
    public static boolean isThereProductWithId(String productId) {
        Product product = getProductById(productId);
        return product != null;
    }

    //finish
    public static ArrayList<Product> getProductList() {
        return allProduct;
    }

    //finish
    public int getProductListSize() {
        return allProduct.size();
    }

    //checked
    public static Product getProductWithName(String name) {
        for (Product product : allProduct) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    //finish
    public void deleteProductByCategory(String categoryId) {
        Category category = Category.getCategoryWithName(categoryId);
        category.getAllProducts().removeAll(category.getAllProducts());
    }

    //checked
    public static Comparator<Product> productComparatorForView = new Comparator<Product>() {

        public int compare(Product s1, Product s2) {

            int productView1 = s1.getNumberOfView();
            int productView2 = s2.getNumberOfView();
            return productView1 - productView2;

        }
    };

    //checked
    public static Comparator<Product> productComparatorForScore = new Comparator<Product>() {

        public int compare(Product s1, Product s2) {

            double productScore1 = s1.getAverageScore();
            double productScore2 = s2.getAverageScore();
            return Double.compare(productScore1, productScore2);

        }
    };

    public static Product getProductWithImage(String imageView) {
        for (Product product : allProduct) {
            if (product.getProductImage().equals(imageView)){

                return product;
            }
        }
        return null;
    }


    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Product>>() {
        }.getType();
        String json = FileHandling.getGson().toJson(Product.allProduct, collectionType);
        FileHandling.writeInFile(json, "product.json");
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", companiesName=" + companiesName +
                ", price=" + price +
                ", seller=" + seller +
                ", productStatus=" + productStatus +
                ", category=" + category +
                ", averageScore=" + averageScore +
                ", numberOfProducts=" + numberOfProducts +
                ", isInSale=" + isInSale +
                ", additionalDetail='" + additionalDetail + '\'' +
                ", numberOfViews=" + numberOfViews +
                ", totalNumberOfBuyers=" + totalNumberOfBuyers +
                ", isBought=" + isBought +
                ", info=" + info +
                ", comment=" + comment +
                ", score=" + score +
                ", log=" + log +
                ", account=" + account +
                ", sale=" + sale +
                '}';
    }
}