package com.trajour.trajour;

import java.io.Serializable;


public class Exercise implements Serializable {

    private String mExerciseUid;

    private String mExercise;

    public String getExerciseUid() {
        return mExerciseUid;
    }

    public String getExercise() {
        return mExercise;
    }


    public Exercise(String exerciseUid, String exercise) {
        mExerciseUid = exerciseUid;
        mExercise = exercise;
    }
}