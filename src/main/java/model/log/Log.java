package model.log;

import model.productRelated.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public abstract class Log{


    //detail
    String logId;
    DeliveryStatus deliveryStatus;
    Product product;
    private  boolean ItsFinal;

    //list
    private static ArrayList<Log> allLogs = new ArrayList<Log>();


    public Log(String logId) throws IOException {
        this.logId=logId;
        allLogs.add(this);
        //       writeInJ();
    }

    //setterAndGetters-----------------------------------------------------------------------

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    //others--------------------------------------------------------------------------------


    public void setItsFinal(boolean itsFinal) {
        ItsFinal = itsFinal;
    }

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
    public static Log getLogWithId(String logId){
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

//    public static void writeInJ() throws IOException {
//        Type collectionType = new TypeToken<ArrayList<Log>>(){}.getType();
//        String json= FileHandling.getGson().toJson(Log.allLogs,collectionType);
//        FileHandling.turnToArray(json+" "+"log.json");
//    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", product=" + product +
                ", ItsFinal=" + ItsFinal +
                '}';
    }
}