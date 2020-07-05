package model.off;

import model.productRelated.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class Auction {
    private String id;
    private LocalDate startOfPeriod;
    private LocalDate endOfPeriod;

    public ArrayList<Product> allProducts = new ArrayList<>();
    public static ArrayList<Product> allProSale = new ArrayList<>();
    private static ArrayList<Auction> allAuctions = new ArrayList<>();

    public Auction(String id) {
        this.id = id;
        allAuctions.add(this);
    }

    public Auction(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public LocalDate getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setStartOfPeriod(LocalDate startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public LocalDate getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setEndOfPeriod(LocalDate endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
    }

    public static Auction getWithId(String id) {
        for (Auction allAuction : allAuctions) {
            if (allAuction.id.equals(id)) {
                return allAuction;
            }
        }
        return null;
    }

    public static boolean isThereWithId(String id) {
        for (Auction allAuction : allAuctions) {
            if (allAuction.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
