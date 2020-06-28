package model.log;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SaleLogShow {
    public String saleLogId;
    public LocalDateTime localDateTime;
    public static ArrayList<SaleLogShow> list = new ArrayList<>();

    public SaleLogShow() {
        list.add(this);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public static ArrayList<SaleLogShow> getList() {
        return list;
    }

    public String getSaleLogId() {
        return saleLogId;
    }

    public static void setList(ArrayList<SaleLogShow> list) {
        SaleLogShow.list = list;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setSaleLogId(String saleLogId) {
        this.saleLogId = saleLogId;
    }
}
