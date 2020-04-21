package main.java.model.off;
import java.util.*;
import java.util.ArrayList;

public class DiscountCode {
    private int discountId;
    private Date startOfDiscountPeriod;
    private Date endOfDiscountPeriod;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private ArrayList<model.accounts.Account> allCustomersWithDiscountCode;
    private static ArrayList<DiscountCode> allDiscountCodes;

    public DiscountCode(int discountId, Date startOfDiscountPeriod, Date endOfDiscountPeriod, double maxDiscountAmount, int totalTimesOfUse, ArrayList<model.accounts.Account> allCustomersWithDiscountCode) {
        this.discountId = discountId;
        this.startOfDiscountPeriod = startOfDiscountPeriod;
        this.endOfDiscountPeriod = endOfDiscountPeriod;
        this.maxDiscountAmount = maxDiscountAmount;
        this.totalTimesOfUse = totalTimesOfUse;
        this.allCustomersWithDiscountCode =new ArrayList <>();
        allDiscountCodes.add(this);
    }

    public void setStartOfDiscountPeriod(Date startOfDiscountPeriod) {
        this.startOfDiscountPeriod = startOfDiscountPeriod;
    }

    public void setEndOfDiscountPeriod(Date endOfDiscountPeriod) {
        this.endOfDiscountPeriod = endOfDiscountPeriod;
    }

    public void setMaxDiscountAmount(double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public int getDiscountId() {
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

    public ArrayList<model.accounts.Account> getAllCustomersWithDiscountCode() {
        return allCustomersWithDiscountCode;
    }

    public boolean isThereDiscountWithId(int id){
        for (DiscountCode discountCode : allDiscountCodes) {
            if(discountCode.getDiscountId() == id) {
                return true;
            }
        }
        return false;
    }
    public DiscountCode getDiscountWithId(int id){
        for ( DiscountCode discountcode:allDiscountCodes){
            if (discountcode.getDiscountId()==id){
                return discountcode;
            }
        }
        return null;
    }
    public void deleteDiscount(int id){
        allDiscountCodes.remove(getDiscountWithId(id));

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
    /*   public void viewDiscountCodes(){
        for (DiscountCode discountCode:allDiscountCodes){
            di
        }
    }*/
}
