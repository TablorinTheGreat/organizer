package com.example.user1.organizer.datatypes;

import android.support.annotation.NonNull;

/**
 * Created by user1 on 09/11/2017.
 */

public class scheHour implements Comparable {
     String hour;
     int num;
    public static String TABLE_NAME="schedule_hours";
    public static String HOUR_TITLE="hour_name";
    public static String HOUR_NUM_TITLE="hour_num";
    public static String DAY_NUM_TITLE="day_num";

    public scheHour(String hour,int num) {
        this.hour = hour;
        this.num = num;
    }
public boolean isequal(scheHour s){
    if(s.hour.equals(hour)&&s.num==num)
        return true;
    return false;
}
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }





        @Override
        public int compareTo(@NonNull Object o) {
            if(num>((scheHour)o).getNum())
                return 1;
            else if(num<((scheHour)o).getNum())
            return -1;
            else
        return 0;
    }
}
