package model.off;

import model.accounts.Account;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


public class DiscountCode {
    private String discountId;
    private static LocalDateTime startOfDiscountPeriod;
    private static LocalDateTime endOfDiscountPeriod;
    private int discountAmount;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private Account manager;
    private static ArrayList<Account> allCustomersWithDiscountCode = new ArrayList<Account>();
    private static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();

    public DiscountCode(String discountId) throws IOException {
        this.discountId = discountId;
        allDiscountCodes.add(this);
     //   writeInJ();
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public static ArrayList<DiscountCode> getAllDiscountCodes() {
        return allDiscountCodes;
    }

    public void addAccount(Account customer) throws IOException {
        allCustomersWithDiscountCode.add(customer);
        customer.addDiscountCode(getDiscountWithId(discountId));
    }
    public void removeAccount(Account account) throws IOException {
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

//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<DiscountCode>>(){}.getType();
//        String json= FileHandling.getGson().toJson(DiscountCode.allDiscountCodes,collectionType);
//        FileHandling.turnToArray(json+" "+"discountCode.json");
//    }


    public static Comparator<DiscountCode> productComparatorForView = new Comparator<DiscountCode>() {

        public int compare(DiscountCode s1, DiscountCode s2) {

            int productView1 = s1.getDiscountAmount();
            int productView2 = s2.getDiscountAmount();
            return productView1- productView2;

        }
    };


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