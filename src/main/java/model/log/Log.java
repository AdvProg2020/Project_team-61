package model.log;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class Log {

    String logId;
    public static LocalDateTime localDateTimeForLog;
    DeliveryStatus deliveryStatus;
    private boolean ItsFinal;

    private static ArrayList<Log> allLogs = new ArrayList<>();


    public Log(String logId) throws IOException {
        this.logId = logId;
        allLogs.add(this);
        localDateTimeForLog = LocalDateTime.now();

    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


    public void setItsFinal(boolean itsFinal) {
        ItsFinal = itsFinal;
    }


    public void deleteLog(String id) {
        allLogs.remove(BuyLog.getLogWithId(id));
    }


    public static Log getLogWithId(String logId) {
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)) {
                return allLog;
            }
        }
        return null;
    }


    public static boolean isThereLogWithID(String logId) {
        for (Log allLog : allLogs) {
            if (allLog.logId.equals(logId)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", ItsFinal=" + ItsFinal +
                '}';
    }


}