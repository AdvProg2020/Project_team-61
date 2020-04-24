package model.productRelated;
import model.accounts.Account;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public abstract class Score implements Comparable {

    //detail
    int score;
    double averageScore;


    //objectAdded
    Product productTOScore;
    Account personToScore;

    //lists
    ArrayList<Score> allScores=new ArrayList<>();


    public Score(Account personToScore, Product productTOScore, int score) {
        this.personToScore = personToScore;
        this.productTOScore = productTOScore;
        this.score = score;
        allScores.add(this);
    }



    //setterAndGetters---------------------------------------------------------
    public void setScore(int score) {
        this.score = score;
        calculateAverageScore(score);
    }
    public int getScore() {
        return score;
    }
    public double getAverageScore() {
        return averageScore;
    }
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
    public ArrayList<Score> getAllScores() {
        return allScores;
    }

    //others-------------------------------------------------------------------

    //finish
    public void calculateAverageScore (int score ){
        double total=0;
        for(int i=0; i<allScores.size(); i++){
            total = total + allScores.get(i).getScore();
        }
        setAverageScore(total);
    }



}
