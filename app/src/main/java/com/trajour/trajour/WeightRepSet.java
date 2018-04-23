package com.trajour.trajour;

import java.io.Serializable;


public class WeightRepSet implements Serializable {


    private String mWeight;
    private String mRep;
    private String mSet;

    public String getWeight() {
        return mWeight;
    }

    public String getRep() {
        return mRep;
    }

    public String getSet() {
        return mSet;
    }


    public WeightRepSet(String weight, String rep, String set) {

        mWeight = weight;
        mRep = rep;
        mSet = set;

    }
}