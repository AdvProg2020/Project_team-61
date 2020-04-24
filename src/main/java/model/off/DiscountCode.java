package off;
import java.util.*;
import java.util.ArrayList;

public class DiscountCode implements Comparable{
    private int discountId;
    private Date startOfPeriod;
    private Date endOfPeriod;
    private double maxDiscountAmount;
    private int totalTimesOfUse;
    private ArrayList<Account> allCustomersWithDiscountCode;
    private static ArrayList<DiscountCode> allDiscountCodes;

    public DiscountCode(int discountId, Date startOfPeriod, Date endOfPeriod, double maxDiscountAmount, int totalTimesOfUse, ArrayList<model.accounts.Account> allCustomersWithDiscountCode) {
        this.discountId = discountId;
        this.startOfPeriod = startOfPeriod;
        this.endOfPeriod = endOfPeriod;
        this.maxDiscountAmount = maxDiscountAmount;
        this.totalTimesOfUse = totalTimesOfUse;
        this.allCustomersWithDiscountCode =new ArrayList <>();
        allDiscountCodes.add(this);
    }

    public void setStartOfPeriod(Date startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }
    public void setDiscountDetail(Date endOfDiscountPeriod,Date startOfPeriod,double maxDiscountAmount,int totalTimesOfUse){
        this.endOfPeriod = endOfDiscountPeriod;
        this.startOfPeriod = startOfPeriod;
        this.maxDiscountAmount = maxDiscountAmount;
        this.totalTimesOfUse = totalTimesOfUse;

    }
    public void setEndOfPeriod(Date endOfDiscountPeriod) {
        this.endOfPeriod = endOfDiscountPeriod;
    }

    public void setMaxDiscountAmount(double maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public Date getStartOfDiscountPeriod() {
        return startOfPeriod;
    }

    public Date getEndOfDiscountPeriod() {
        return endOfPeriod;
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
                ", startOfDiscountPeriod=" + startOfPeriod +
                ", endOfDiscountPeriod=" + endOfPeriod +
                ", maxDiscountAmount=" + maxDiscountAmount +
                ", totalTimesOfUse=" + totalTimesOfUse +
                ", allCustomersWithDiscountCode=" + allCustomersWithDiscountCode +
                '}';
    }
       public ArrayList<DiscountCode>ListDiscountCodes(){
           return  allDiscountCodes;

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int getDiscountCodeListSize(){
        return allDiscountCodes.size();
    }
}
