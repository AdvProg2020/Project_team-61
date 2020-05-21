package model.productRelated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Category{
    private String name;
    private static ArrayList <String> traits= new ArrayList<>();
    private static ArrayList <Category>subCategories = new ArrayList<>();
    private static ArrayList <Product> allProducts = new ArrayList<>();
    private static ArrayList <Category> allCategories = new ArrayList<>();


    public Category(String name) throws IOException {
        this.name = name;
        allCategories.add(this);
//        writeInJ();
    }

    public void addTrait(String trait){
        traits.add(trait);
    }

    public static void addKey(){
        for (Product product : allProducts) {
            for (String tr : traits) {
                product.getCategorySpecifications().put(tr, null);
            }
        }
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public void removeTrait(String trait){
        traits.remove(trait);
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }


    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public static Category getCategoryWithName(String name){
        for( Category category:allCategories){
            if (category.getName().equalsIgnoreCase(name)){
                return category;
            }
        }
        return null;
    }

    public static boolean isThereCategoryWithName(String name){
        for ( Category category:allCategories){
            if (category.getName().equalsIgnoreCase(name))
                return true;
        }
        return true;

    }

    public void addProductToCategory(Product product){
        allProducts.add(product);
    }
    public void removeProductToCategory(Product product){
        allProducts.remove(product);
    }

    public static void deleteCategory(String name){
        allCategories.remove(getCategoryWithName(name));
    }

    public int getCategoryListSize(){
        return allCategories.size();
    }
    public ArrayList<Category> listCategories(){
        return allCategories;
    }


    public static Comparator<Category> productComparatorForView = new Comparator<Category>() {

        public int compare(Category s1, Category s2) {

            String productView1 = s1.getName();
            String productView2 = s2.getName();
            return productView1.compareTo(productView2);

        }
    };



    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", traits='" + traits + '\'' +
                ", subCategories=" + subCategories +
                ", allProducts=" + allProducts
                ;
    }
//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Category.allCategories,collectionType);
//        FileHandling.turnToArray(json+" "+"category.json");
//    }
}