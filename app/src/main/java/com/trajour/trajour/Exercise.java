package com.trajour.trajour;

import java.io.Serializable;


public class Exercise implements Serializable {

    private String mExercise;

    public String getExercise() {
        return mExercise;
    }

    public Exercise(String exercise) {
        mExercise = exercise;

    }
}