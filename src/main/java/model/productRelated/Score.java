package model.productRelated;

import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Score {

    //detail
    int score;
    double averageScore;


    //objectAdded
    Product productTOScore;
    Account personToScore;

    //lists
    public  static ArrayList<Score> allScores = new ArrayList<>();


    public Score(Account personToScore, Product productTOScore, int score) throws IOException {
        this.personToScore = personToScore;
        this.productTOScore = productTOScore;
        this.score = score;
        allScores.add(this);
        writeInJ();
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

    public static ArrayList<Score> getAllScores() {
        return allScores;
    }

    //others-------------------------------------------------------------------

    //finish
    public void calculateAverageScore(int score) {
        double total = 0;
        for (int i = 0; i < allScores.size(); i++) {
            total = +total + allScores.get(i).getScore();
        }
        productTOScore.setAverageScore(total);
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Score>>(){}.getType();
        String json= FileHandling.getGson().toJson(getAllScores(),collectionType);
        FileHandling.turnToArray(json+" "+"score.json");
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", averageScore=" + averageScore +
                ", productTOScore=" + productTOScore +
                ", personToScore=" + personToScore +
                '}';
    }
}