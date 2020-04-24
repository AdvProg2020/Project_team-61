package model.log;

import model.productRelated.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static model.productRelated.Product.allProduct;

public abstract class Log{

    //detail
    String logId;
    Date date;
    DeliveryStatus deliveryStatus;
    boolean itsDone;
    Product product;

    //list
    ArrayList<Log> allLogs = new ArrayList<Log>();


    public Log(String logId , Date date) {
        this.date = date;
        this.logId=logId;
        allLogs.add(this);
    }

    //setterAndGetters-----------------------------------------------------------------------

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    //others--------------------------------------------------------------------------------

    //finish
    public void deleteLog (String id){
        Iterator iterator = allLogs.iterator();
        while(iterator.hasNext()) {
            Log log = (Log) iterator.next();
            if(log.logId.equals(id)) {
                iterator.remove();
            }
        }
    }

    //finish
    public Log getLogWithId (String logId){
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)){
                return allLog;
            }
        }
        return null;
    }

    //finish
    public boolean isThereLogWithID (String logId){
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)){
                return true;
            }
        }
        return false;
    }

}
