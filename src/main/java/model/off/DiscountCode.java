package model.off;

import model.accounts.Account;

import java.util.*;
import java.util.ArrayList;

public class DiscountCode {
    private String discountId;
    private Date startOfDiscountPeriod;
    private Date endOfDiscountPeriod;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private ArrayList<Account> allCustomersWithDiscountCode;
    private static ArrayList<DiscountCode> allDiscountCodes;

    public DiscountCode(String discountId) {
        this.discountId = discountId;
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

    public ArrayList<model.accounts.Account> getAllCustomersWithDiscountCode() {
        return allCustomersWithDiscountCode;
    }

    public boolean isThereDiscountWithId(String id){
        for (DiscountCode discountCode : allDiscountCodes) {
            if(discountCode.getDiscountId() == id) {
                return true;
            }
        }
        return false;
    }
    public DiscountCode getDiscountWithId(String id){
        for ( DiscountCode discountcode:allDiscountCodes){
            if (discountcode.getDiscountId()==id){
                return discountcode;
            }
        }
        return null;
    }
    public void deleteDiscount(String id){
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

    public void setTotalTimesOfUse(int parseInt) {
    }
    /*   public void viewDiscountCodes(){
        for (DiscountCode discountCode:allDiscountCodes){
            di
        }
    }*/
}
