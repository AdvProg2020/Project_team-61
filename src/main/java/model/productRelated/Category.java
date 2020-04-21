package main.java.model.productRelated;
import java.util.ArrayList;


public class Category{
    private String name;
    private String traits;
    private ArrayList <Category>subCategories;
    private ArrayList <model.productRelated.Product> allProducts;
    private static ArrayList <Category> allCategories;

    public Category(String name, String traits) {
        this.name = name;
        this.traits = traits;
        subCategories =new ArrayList<>();
        allProducts = new ArrayList <>();
        allCategories.add(this);
    }

    public String getName() {
        return name;
    }

    public String getTraits() {
        return traits;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public ArrayList<model.productRelated.Product> getAllProducts() {
        return allProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }
    public Category getCategoryWithName(String name){
        for( Category category:allCategories){
            if (category.getName().equalsIgnoreCase(name)){
                return category;
            }
        }
        return null;
    }
    public boolean isThereCategoryWithName(String name){
        for ( Category category:allCategories){
            if (category.getName().equalsIgnoreCase(name))
                return true;
            }
        return true;

    }
    public void deleteCategory(String name){
        allCategories.remove(getCategoryWithName(name));
    }
    public int getCategoryListSize(){
        return allCategories.size();
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

  /*  public Set<String> viewCategories(){
        for (model.productRelated.Category category : allCategories) {
             category.getName();
        }
          }*/
}


