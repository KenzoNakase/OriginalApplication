package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseMenuListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<ExerciseMenu> mExerciseMenuArrayList;

    public ExerciseMenuListAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mExerciseMenuArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExerciseMenuArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_exercise_menu, parent, false);
        }

        TextView textExerciseMenu1 = (TextView) convertView.findViewById(R.id.textExerciseMenu1);
        textExerciseMenu1.setText(mExerciseMenuArrayList.get(position).getExerciseMenuName());

        TextView textExerciseMenuDate1 = (TextView) convertView.findViewById(R.id.textExerciseMenuDate1);
        textExerciseMenuDate1.setText(mExerciseMenuArrayList.get(position).getExerciseMenuDate());

        return convertView;
    }

    public void setExerciseMenuArrayList(ArrayList<ExerciseMenu> ExerciseMenuArrayList) {
        mExerciseMenuArrayList = ExerciseMenuArrayList;
    }
}
