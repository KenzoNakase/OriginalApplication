package com.trajour.trajour;

import java.io.Serializable;


public class RecordWeight implements Serializable {
    private String mUid;
    private String mBodyWeightUid;
    private String mDate;
    private String mHeight;
    private String mBodyWeight;
    private String mBodyFatPercentage;

    public String getUid() {
        return mUid;
    }

    public String getBodyWeightUid() {
        return mBodyWeightUid;
    }

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

    public RecordWeight(String Uid, String bodyWeightUid, String date, String height, String bodyWeight, String bodyFatPercentage) {
        mUid = Uid;
        mBodyWeightUid = bodyWeightUid;
        mDate = date;
        mHeight = height;
        mBodyWeight = bodyWeight;
        mBodyFatPercentage = bodyFatPercentage;
    }
}