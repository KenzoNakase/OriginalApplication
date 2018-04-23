package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseMenuList3Adapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<WeightRepSet> mWeightRepSetArrayList;

    public ExerciseMenuList3Adapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mWeightRepSetArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWeightRepSetArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_exercise_menu3, parent, false);
        }

        TextView textWeight10 = (TextView) convertView.findViewById(R.id.textWeight10);
        textWeight10.setText(mWeightRepSetArrayList.get(position).getWeight());

        TextView textRep4 = (TextView) convertView.findViewById(R.id.textRep4);
        textRep4.setText(mWeightRepSetArrayList.get(position).getRep());

        TextView textSet5 = (TextView) convertView.findViewById(R.id.textSet5);
        textSet5.setText(mWeightRepSetArrayList.get(position).getSet());


        return convertView;
    }

    public void setWeightRepSetArrayList(ArrayList<WeightRepSet> WeightRepSetArrayList) {
        mWeightRepSetArrayList = WeightRepSetArrayList;
    }
}
