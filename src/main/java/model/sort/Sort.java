package model.sort;

import model.productRelated.Product;
import model.productRelated.Score;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Sort {
    public Product product;
    public Score score;
    String sortName;
    int numberOfSort=0;
    ArrayList<Product> newArrayOfProductSort = Product.getProductList();
    ArrayList<ArrayList<Product>> listOfSorts = new ArrayList<>();
    ArrayList<ArrayList<Product>> helpSort = new ArrayList<>();
    ArrayList<String> availableSorts=new ArrayList<>();

    public Sort(ArrayList<String> availableSorts) {
        availableSorts.add("numberOfView");
        availableSorts.add("score");
    }

    //if view->1   score ->2



    //sorts----------------------------------------------------------------------

    public ArrayList<Product> numberOfViewsSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForView);
        listOfSorts.add(1,newArrayOfProductSort);
        helpSort.add(newArrayOfProductSort);
        availableSorts.remove(0);
        return newArrayOfProductSort;
    }

    public ArrayList<Product> scoreSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForScore);
        listOfSorts.add(2,newArrayOfProductSort);
        helpSort.add(newArrayOfProductSort);
        availableSorts.remove(1);
        return newArrayOfProductSort;
    }


    //other---------------------------------------------------------------------------

    //finish
    public ArrayList<Product> disableSort(String sortName){
        if (sortName.equals("numberOfViewSort")){
            numberOfSort=1;
        }
        else if (sortName.equals("ScoreSort")){
            numberOfSort=2;
        }
        helpSort.remove(helpSort.indexOf(listOfSorts.get(numberOfSort)));
        listOfSorts.remove(numberOfSort);

        return helpSort.get(helpSort.size()-1);
    }

    //finish
    public ArrayList<String> currentSorts() {
        ArrayList<String> current=new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (listOfSorts.get(i)!=null){
                if (i==1){
                    current.add("view");
                }
                else if (i==2){
                    current.add("score");
                }
            }
        }
        return current;
    }

    public ArrayList<String> showAvailableSort(){
        return availableSorts;
    }

    public boolean ifAvailable(String sortId){
        if (availableSorts.contains(sortId)){
            return true;
        }
        else return false;
    }
}