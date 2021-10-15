package com.example.onebeep;

public class ExampleItem {
    private String mAlarmTime;
    private String mOnOrOff;
    private String mRepeat;
    private String mBeepTwice;

    public ExampleItem(String alarmTime, String onOrOff, String repeat, String beepTwice){
        mAlarmTime = alarmTime;
        mOnOrOff = onOrOff;
        mRepeat = repeat;
        mBeepTwice = beepTwice;
    }

    public String getAlarmTime() {
        return mAlarmTime;
    }

    public String getOnOrOff() {
        return mOnOrOff;
    }

    public String getRepeat() {
        return mRepeat;
    }

    public String getmBeepTwice() {
        return mBeepTwice;
    }


}
