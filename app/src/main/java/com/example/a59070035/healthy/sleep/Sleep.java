package com.example.a59070035.healthy.sleep;

public class Sleep {
    String date;
    String timeToSleep;
    String timeWakeUp;
    public Sleep(){}

    public Sleep(String date, String timeToSleep, String timeWakeUp){
        setDate(date);
        setTimeToSleep(timeToSleep);
        setTimeWakeUp(timeWakeUp);
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
