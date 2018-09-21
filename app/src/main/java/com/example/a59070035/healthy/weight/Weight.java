package com.example.a59070035.healthy.weight;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Brightonter on 9/8/2018.
 */

public class Weight{
    String date;
    int weight;
    String status;

    public Weight(){}

    public Weight(String date, int weight, String status){
        this.date = date;
        this.weight = weight;
        this.status = status;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static Comparator<Weight> DateComparator = new Comparator<Weight>() {
        @Override
        public int compare(Weight index1, Weight index2) {
            String str1 = index1.getDate();
            String str2 = index2.getDate();

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
                Date date1 = format.parse(str1);
                Date date2 = format.parse(str2);
                if (date1.compareTo(date2) > 0){
                    return 1;
                }else if (date1.compareTo(date2) < 0){
                    return -1;
                }else
                    return 0;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    };
}


