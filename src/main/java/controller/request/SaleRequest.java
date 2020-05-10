package controller.request;

import model.off.SaleStatus;

import java.util.Date;

public class SaleRequest extends Request {
    private String offId;
    private Date startOfSalePeriod;
    private Date endOfSalePeriod;
    private Date startOfDiscountPeriod;
    private Date endOfDiscountPeriod;
    private double saleAmount;
    private int totalTimeOfUse;
    private double maximumAmountOfDiscount;
    private SaleStatus saleStatus;

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public void setOffId(String offId) {
        this.offId = offId;
    }

    public void setStartOfSalePeriod(Date startOfSalePeriod) {
        this.startOfSalePeriod = startOfSalePeriod;
    }

    public void setEndOfSalePeriod(Date endOfSalePeriod) {
        this.endOfSalePeriod = endOfSalePeriod;
    }

    public void setStartOfDiscountPeriod(Date startOfDiscountPeriod) {
        this.startOfDiscountPeriod = startOfDiscountPeriod;
    }

    public void setEndOfDiscountPeriod(Date endOfDiscountPeriod) {
        this.endOfDiscountPeriod = endOfDiscountPeriod;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public void setTotalTimeOfUse(int totalTimeOfUse) {
        this.totalTimeOfUse = totalTimeOfUse;
    }

    public void setMaximumAmountOfDiscount(double maximumAmountOfDiscount) {
        this.maximumAmountOfDiscount = maximumAmountOfDiscount;
    }



    public SaleRequest(String requestID) {
        super(requestID);
    }



}
