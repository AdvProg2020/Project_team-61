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
}
