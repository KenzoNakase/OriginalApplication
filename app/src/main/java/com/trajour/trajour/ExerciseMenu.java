package com.trajour.trajour;

import java.io.Serializable;


public class ExerciseMenu implements Serializable {

    private String mExerciseMenuUid;
    private String mExerciseMenuDate;
    private String mExerciseMenuName;

    public String getExerciseMenuUid() {
        return mExerciseMenuUid;
    }

    public String getExerciseMenuDate() {
        return mExerciseMenuDate;
    }

    public String getExerciseMenuName() {
        return mExerciseMenuName;
    }


    public ExerciseMenu(String exerciseMenuUid, String exerciseMenuDate, String exerciseMenuName) {
        mExerciseMenuUid = exerciseMenuUid;
        mExerciseMenuDate = exerciseMenuDate;
        mExerciseMenuName = exerciseMenuName;

    }
}