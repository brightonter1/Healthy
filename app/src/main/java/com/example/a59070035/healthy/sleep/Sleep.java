package com.example.a59070035.healthy.sleep;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Sleep {



    String date;
    String timeToSleep;
    String timeWakeUp;
    String uid;
    static String id;

    public String calculateTime(String t1, String t2) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String dateStart = "14 "+t1;
        String dateStop = "15 "+t2;
        SimpleDateFormat format2 = new SimpleDateFormat("dd HH:mm");
        try {
            if(format.parse(t2).getTime() > format.parse(t1).getTime()){
                dateStop ="14 "+t2;
            }
            Date d1 = format2.parse(dateStart);
            Date d2 = format2.parse(dateStop);
            long diff = d2.getTime() - d1.getTime();
            long hours = TimeUnit.MILLISECONDS.toHours(diff);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
            return String.format("%02d:%02d", hours, minutes);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public Sleep(){}

    public Sleep(String date, String timeToSleep, String timeWakeUp, String uid){
        this.date = date;
        this.timeToSleep = timeToSleep;
        this.timeWakeUp = timeWakeUp;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeToSleep() {
        return timeToSleep;
    }

    public void setTimeToSleep(String timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public String getTimeWakeUp() {
        return timeWakeUp;
    }

    public void setTimeWakeUp(String timeWakeUp) {
        this.timeWakeUp = timeWakeUp;
    }
}
