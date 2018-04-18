package com.trajour.trajour;

import java.io.Serializable;


public class RecordExercise implements Serializable {
    private String mUid;
    private String mExerciseUid;
    private String mExerciseDate;
    private String mBodyPart;
    private String mExercise;
    private String mWeight;
    private String mRep;
    private String mSet;

    public String getUid() {
        return mUid;
    }

    public String getExerciseUid() {
        return mExerciseUid;
    }

    public String getExerciseDate() {
        return mExerciseDate;
    }

    public String getBodyPart() {
        return mBodyPart;
    }

    public String getExercise() {
        return mExercise;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getRep() {
        return mRep;
    }

    public String getSet() {
        return mSet;
    }

    public RecordExercise(String uid, String exerciseUid, String exerciseDate, String bodyPart, String exercise, String weight, String rep, String set) {
        mUid = uid;
        mExerciseUid = exerciseUid;
        mExerciseDate = exerciseDate;
        mBodyPart = bodyPart;
        mExercise = exercise;
        mWeight = weight;
        mRep = rep;
        mSet = set;
    }
}