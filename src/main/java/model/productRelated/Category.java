package model.productRelated;
import com.google.gson.reflect.TypeToken;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Category{
    private String name;
    private static String traits;
    private static ArrayList <Category>subCategories = new ArrayList<>();
    private ArrayList <Product> allProducts = new ArrayList<>();
    private static ArrayList <Category> allCategories = new ArrayList<>();

   /* public Category(String name, String traits) {
        this.name = name;
        this.traits = traits;
        allCategories.add(this);
    }

    */

    public Category(String name) throws IOException {
        this.name = name;
        allCategories.add(this);
        writeInJ();
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
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", traits='" + traits + '\'' +
                ", subCategories=" + subCategories +
                ", allProducts=" + allProducts
                ;
    }
    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
        String json= FileHandling.getGson().toJson(Category.allCategories,collectionType);
        FileHandling.turnToArray(json+" "+"category.json");
    }
}