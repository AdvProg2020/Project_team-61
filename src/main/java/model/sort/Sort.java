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

    //if view->1   score ->2   date ->3

    //sorts----------------------------------------------------------------------

    public ArrayList<Product> numberOfViewsSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForView);
        listOfSorts.add(1,newArrayOfProductSort);
        helpSort.add(newArrayOfProductSort);
        return newArrayOfProductSort;
    }

    public ArrayList<Product> scoreSort() {
        Collections.sort(newArrayOfProductSort,Product.productComparatorForScore);
        listOfSorts.add(2,newArrayOfProductSort);
        helpSort.add(newArrayOfProductSort);
        return newArrayOfProductSort;
    }

    //dateSort

    //other---------------------------------------------------------------------------

    //finish
    public ArrayList<Product> disableSort(){
        if (sortName.equals("numberOfViewFilter")){
            numberOfSort=1;
        }
        else if (sortName.equals("ScoreFilter")){
            numberOfSort=2;
        }
        else if (sortName.equals("dateFilter")){
            numberOfSort=3;
        }
        helpSort.remove(helpSort.indexOf(listOfSorts.get(numberOfSort)));
        listOfSorts.remove(numberOfSort);

        return helpSort.get(helpSort.size()-1);
    }

    //finish
    public ArrayList<String> currentSorts() {
        ArrayList<String> current=new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            if (listOfSorts.get(i)!=null){
                if (i==1){
                    current.add("view");
                }
                else if (i==2){
                    current.add("score");
                }
                else if (i==3){
                    current.add("date");
                }
            }
        }
        return current;
    }



}