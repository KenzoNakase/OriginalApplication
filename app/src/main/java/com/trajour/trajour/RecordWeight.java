package com.trajour.trajour;

import java.io.Serializable;
import java.util.ArrayList;

public class RecordWeight implements Serializable {
    private String mDate;
    private String mHeight;
    private String mBodyWeight;
    private String mBodyFatPercentage;

    public String getDate() {
        return mDate;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getBodyWeight() {
        return mBodyWeight;
    }

    public String getBodyFatPercentage() {
        return mBodyFatPercentage;
    }

    public RecordWeight(String date, String height, String bodyWeight, String bodyFatPercentage) {
        mDate = date;
        mHeight = height;
        mBodyWeight = bodyWeight;
        mBodyFatPercentage = bodyFatPercentage;
    }
}