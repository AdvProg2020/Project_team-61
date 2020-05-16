package model.log;

import com.google.gson.reflect.TypeToken;
import model.productRelated.Product;
import view.FileHandling;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public abstract class Log{


    //detail
    String logId;
    DeliveryStatus deliveryStatus;
    boolean itsDone;
    Product product;

    //list
    private static ArrayList<Log> allLogs = new ArrayList<Log>();


    public Log(String logId) throws IOException {
        this.logId=logId;
        allLogs.add(this);
        writeInJ();
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
    public static boolean isThereLogWithID(String logId){
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)){
                return true;
            }
        }
        return false;
    }

    public static void writeInJ() throws IOException {
        Type collectionType = new TypeToken<ArrayList<Log>>(){}.getType();
        String json= FileHandling.getGson().toJson(Log.allLogs,collectionType);
        FileHandling.turnToArray(json+" "+"log.json");
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", itsDone=" + itsDone +
                ", product=" + product +
                '}';
    }
}