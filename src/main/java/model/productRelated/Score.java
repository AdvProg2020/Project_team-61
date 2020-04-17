package model.productRelated;

import model.accounts.Account;

import java.util.ArrayList;

public class Score implements Comparable{

    Account personToScore;
    Product productTOScore;
    int score;
    double averageScore;
    ArrayList<Integer> allScores=new ArrayList<Integer>();
    public Score(Account personToScore, Product productTOScore, int score) {
        this.personToScore = personToScore;
        this.productTOScore = productTOScore;
        this.score = score;
        allScores.add(score);
    }

    //finish
    public void setScore(int score) {
        this.score = score;
        calculateAverageScore(score);
    }

    //finish
    public int getScore() {
        return score;
    }

    //finish
    public double calculateAverageScore (int score ){
        double total=0;
        for(int i=0; i<allScores.size(); i++){
            total = total + allScores.get(i);
        }
        return total;
    }

    //finish
    public double getAverageScore() {
        return averageScore;
    }

    public int compareTo(Object o) {
        return 0;
    }
}
