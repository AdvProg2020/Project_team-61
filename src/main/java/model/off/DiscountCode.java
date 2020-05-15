package model.off;

import model.accounts.Account;

import java.util.ArrayList;
import java.util.Date;

public class DiscountCode {
    private String discountId;
    private Date startOfDiscountPeriod;
    private Date endOfDiscountPeriod;
    private int discountAmount;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private static ArrayList<Account> allCustomersWithDiscountCode = new ArrayList<Account>();
    private static ArrayList<DiscountCode> allDiscountCodes;

    public DiscountCode(String discountId) {
        this.discountId = discountId;
        allDiscountCodes.add(this);
    }

    public void addAccount(Account customer){
        allCustomersWithDiscountCode.add(customer);
        customer.addDiscountCode(getDiscountWithId(discountId));
    }
    public void removeAccount(Account account){
        allCustomersWithDiscountCode.remove(account);
        account.removeDiscountCode(getDiscountWithId(discountId));
    }

    public void setStartOfDiscountPeriod(Date startOfDiscountPeriod) {
        this.startOfDiscountPeriod = startOfDiscountPeriod;
    }

    public static boolean discountMatchAccount(String username){

        for (Account account : allCustomersWithDiscountCode) {
            if (account.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static boolean discountDateValid( Date start,Date end){
        Date currentDate=new Date();
        if(start.after(currentDate) && end.after(currentDate)&& end.after(start) ){
            return  true;
        }
        return false;
    }
    public void deleteExpiredDiscount(){
        Date currentDate=new Date();
        for (DiscountCode discountCode : allDiscountCodes) {
            if (discountCode.getEndOfDiscountPeriod().before(currentDate)){
                allDiscountCodes.remove(discountCode);
            }
        }
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setTotalTimesOfUse(int totalTimesOfUse) {
        this.totalTimesOfUse = totalTimesOfUse;
    }

    public void setEndOfDiscountPeriod(Date endOfDiscountPeriod) {
        this.endOfDiscountPeriod = endOfDiscountPeriod;
    }

    public void setMaxDiscountAmount(double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public String getDiscountId() {
        return discountId;
    }

    public Date getStartOfDiscountPeriod() {
        return startOfDiscountPeriod;
    }

    public Date getEndOfDiscountPeriod() {
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

    public static void deleteDiscount(String id) {
        allDiscountCodes.remove(getDiscountWithId(id));

    }
    public double CalculatePriceAfterDiscount(double totalPrice){
        totalPrice*=(1-this.discountAmount/100);
        return totalPrice;
    }

    /*
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

     */
}