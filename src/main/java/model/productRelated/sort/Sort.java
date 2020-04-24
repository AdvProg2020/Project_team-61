package model.productRelated.sort;

import model.productRelated.Product;
import model.productRelated.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class Sort {
    public Product product;
    public Score score;
    String sortName;
    int numberOfSort=0;
    ArrayList<Product> newArrayOfProductSort = new ArrayList<>();
    ArrayList<ArrayList<Product>> listOfSorts = new ArrayList<>();
    ArrayList<ArrayList<Product>> helpSort = new ArrayList<>();

    //if view->1   score ->2   date ->3

    //sorts----------------------------------------------------------------------
//    public ArrayList<Product> numberOfViewsSort() {
//
//        newArrayOfProductSort.replaceAll(Collections.sort(product.productList(),product.score.getAverageScore()));
//        listOfSorts.add(1,Collections.sort(product.productList(),product.score.getAverageScore()));
//        helpSort.add(Collections.sort(product.productList(),product.score.getAverageScore()));
//        return newArrayOfProductSort;
//    }
//
//    public ArrayList<Product> scoreSort() {
//        newArrayOfProductSort.replaceAll(Collections.sort(product.productList(),product.score.getAverageScore()));
//        listOfSorts.add(2,Collections.sort(product.productList(),product.score.getAverageScore()::));
//        helpSort.add(Collections.sort(product.productList(),product.score.getAverageScore()));
//        return newArrayOfProductSort;
//    }

    //dateSort

    //other---------------------------------------------------------------------------

    //finish
    public ArrayList<Product> disableSort(){
        if (sortName.equals("categoryFilter")){
            numberOfSort=1;
        }
        else if (sortName.equals("companiesFilter")){
            numberOfSort=2;
        }
        else if (sortName.equals("discountFilter")){
            numberOfSort=3;
        }
        else if (sortName.equals("productNameFilter")) {
            numberOfSort = 4;
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
                    current.add("category");
                }
                else if (i==2){
                    current.add("companisName");
                }
                else if (i==3){
                    current.add("discount");
                }
                else if (i==4){
                    current.add("productName");
                }
            }
        }
        return current;
    }




}
