package model.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public abstract class Log implements Comparable{

    String logId;
    Date date;
    DeliveryStatus deliveryStatus;
    boolean itsDone;
    ArrayList<Log> allLogs = new ArrayList<Log>();

    public Log(String logId , Date date) {
        this.date = date;
        this.logId=logId;
        allLogs.add(this);
    }

    public void deleteLog (String id){
        Iterator iterator = allLogs.iterator();
        while(iterator.hasNext()) {
            Log log = (Log) iterator.next();
            if(log.logId.equals(id)) {
                iterator.remove();
            }
        }
    }

    public Log getLogWithId (String logId){
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)){
                return allLog;
            }
        }
        return null;
    }

    public boolean isThereLogWithID (String logId){
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)){
                return true;
            }
        }
        return false;
    }

    public void addProductToLog (String productId){}

    public int compareTo(Object o) {
        return 0;
    }

}
