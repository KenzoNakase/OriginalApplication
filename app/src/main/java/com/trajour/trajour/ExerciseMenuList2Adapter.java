package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseMenuList2Adapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<Exercise> mExerciseArrayList;

    public ExerciseMenuList2Adapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mExerciseArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExerciseArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_exercise_menu2, parent, false);
        }

        TextView textExercise1 = (TextView) convertView.findViewById(R.id.textExercise1);
        textExercise1.setText(mExerciseArrayList.get(position).getExercise());


        return convertView;
    }

    public void setExerciseArrayList(ArrayList<Exercise> ExerciseArrayList) {
        mExerciseArrayList = ExerciseArrayList;
    }
}
