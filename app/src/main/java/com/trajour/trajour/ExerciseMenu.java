package com.trajour.trajour;

import java.io.Serializable;


public class ExerciseMenu implements Serializable {
    private String mUid;
    private String mExerciseMenuUid;
    private String mExerciseMenuDate;
    private String mExerciseMenuName;


    public String getUid() {
        return mUid;
    }

    public String getExerciseUid() {
        return mExerciseMenuUid;
    }

    public String getExerciseMenuDate() {
        return mExerciseMenuDate;
    }

    public String getExerciseMenuName() {
        return mExerciseMenuName;
    }


    public ExerciseMenu(String uid, String exerciseMenuUid, String exerciseMenuDate, String exerciseMenuName) {
        mUid = uid;
        mExerciseMenuUid = exerciseMenuUid;
        mExerciseMenuDate = exerciseMenuDate;
        mExerciseMenuName = exerciseMenuName;

    }
}