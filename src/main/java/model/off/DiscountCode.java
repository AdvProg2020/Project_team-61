package model.off;

import com.google.gson.reflect.TypeToken;
import model.accounts.Account;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class DiscountCode {
    private String discountId;
    private static LocalDateTime startOfDiscountPeriod;
    private static LocalDateTime endOfDiscountPeriod;
    private int discountAmount;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private Account manager;
    private static ArrayList<Account> allCustomersWithDiscountCode = new ArrayList<Account>();
    private static ArrayList<DiscountCode> allDiscountCodes;

    public DiscountCode(String discountId) throws IOException {
        this.discountId = discountId;
        allDiscountCodes.add(this);
        writeInJ();
    }

    public void addAccount(Account customer){
        allCustomersWithDiscountCode.add(customer);
        customer.addDiscountCode(getDiscountWithId(discountId));
    }
    public void removeAccount(Account account){
        allCustomersWithDiscountCode.remove(account);
        account.removeDiscountCode(getDiscountWithId(discountId));
    }

    public static void setStartOfDiscountPeriod(LocalDateTime startOfDiscountPeriod) {
        DiscountCode.startOfDiscountPeriod = startOfDiscountPeriod;
    }

    public void setManager(Account manager) {
        this.manager = manager;
    }

    public static boolean discountMatchAccount(String username){

        for (Account account : allCustomersWithDiscountCode) {
            if (account.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static boolean discountDateValid(){
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(startOfDiscountPeriod) && now.isBefore(endOfDiscountPeriod)){
            return true;
        }
        return false;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setTotalTimesOfUse(int totalTimesOfUse) {
        this.totalTimesOfUse = totalTimesOfUse;
    }

    public static void setEndOfDiscountPeriod(LocalDateTime endOfDiscountPeriod) {
        DiscountCode.endOfDiscountPeriod = endOfDiscountPeriod;
    }

    public void setMaxDiscountAmount(double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public String getDiscountId() {
        return discountId;
    }

    public static LocalDateTime getStartOfDiscountPeriod() {
        return startOfDiscountPeriod;
    }

    public static LocalDateTime getEndOfDiscountPeriod() {
        return endOfDiscountPeriod;
    }

    public double getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public int getTotalTimesOfUse() {
        return totalTimesOfUse;
    }


    public ArrayList<Account> getAllCustomersWithDiscountCode() {
        return allCustomersWithDiscountCode;
    }

    public static boolean isThereDiscountWithId(String id) {
        for (DiscountCode discountCode : allDiscountCodes) {
            if (discountCode.getDiscountId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static DiscountCode getDiscountWithId(String id) {
        for (DiscountCode discountcode : allDiscountCodes) {
            if (discountcode.getDiscountId().equals(id)) {
                return discountcode;
            }
        }
        return null;
    }

    public void giveDiscountToRandomCustomers(){

    }

    public void giveDiscountInBirthday(){

    }

    public double calculate(double price){
        if (price < maxDiscountAmount){
            price = price-((price*discountAmount)/100);
        }
        else if (price > maxDiscountAmount){
            double amountCant = price-maxDiscountAmount;
            price = price - ((maxDiscountAmount*discountAmount)/100);
        }
        return price;
    }

    public static void deleteDiscount(String id) {
        allDiscountCodes.remove(getDiscountWithId(id));
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<DiscountCode>>(){}.getType();
        String json= FileHandling.getGson().toJson(DiscountCode.allDiscountCodes,collectionType);
        FileHandling.turnToArray(json+" "+"discountCode.json");
    }


    @Override
    public String toString() {
        return "DiscountCode{" +
                "discountId=" + discountId +
                ", startOfDiscountPeriod=" + startOfDiscountPeriod +
                ", endOfDiscountPeriod=" + endOfDiscountPeriod +
                ", maxDiscountAmount=" + maxDiscountAmount +
                ", totalTimesOfUse=" + totalTimesOfUse +
                ", allCustomersWithDiscountCode=" + allCustomersWithDiscountCode +
                '}';
    }

}