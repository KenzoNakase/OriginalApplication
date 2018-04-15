package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordExerciseListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<RecordWeight> mRecordExerciseArrayList;

    public RecordExerciseListAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mRecordExerciseArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecordExerciseArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_record_excercise, parent, false);
        }

        TextView textExcercise1 = (TextView) convertView.findViewById(R.id.textExercise1);
        textExcercise1.setText(mRecordExerciseArrayList.get(position).getExercise());

        TextView textExerciseDate1 = (TextView) convertView.findViewById(R.id.textExerciseDate1);
        textExerciseDate1.setText(mRecordExerciseArrayList.get(position).getExerciseDate());

        TextView textWeight8 = (TextView) convertView.findViewById(R.id.textWeight8);
        textWeight8.setText(mRecordExerciseArrayList.get(position).getWeight());

        TextView textRep2 = (TextView) convertView.findViewById(R.id.textRep2);
        textRep2.setText(mRecordExerciseArrayList.get(position).getRep());

        TextView textSet3 = (TextView) convertView.findViewById(R.id.textSet3);
        textSet3.setText(mRecordExerciseArrayList.get(position).getSet());

        return convertView;
    }

    public void setRecordExcerciseArrayList(ArrayList<RecordWeight> recordExcerciseArrayList) {
        mRecordExerciseArrayList = recordExcerciseArrayList;
    }
}
