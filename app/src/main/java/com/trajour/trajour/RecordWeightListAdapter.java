package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordWeightListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<RecordWeight> mRecordWeightArrayList;

    public RecordWeightListAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mRecordWeightArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecordWeightArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_record_weight, parent, false);
        }

        TextView textDate3 = (TextView) convertView.findViewById(R.id.textDate3);
        textDate3.setText(mRecordWeightArrayList.get(position).getDate());

        TextView textWeight6 = (TextView) convertView.findViewById(R.id.textWeight6);
        textWeight6.setText(mRecordWeightArrayList.get(position).getBodyWeight());

        TextView textBodyFatPercentage6 = (TextView) convertView.findViewById(R.id.textBodyFatPercentage6);
        textBodyFatPercentage6.setText(mRecordWeightArrayList.get(position).getBodyFatPercentage());

        return convertView;
    }

    public void setRecordWeightArrayList(ArrayList<RecordWeight> recordWeightArrayList) {
        mRecordWeightArrayList = recordWeightArrayList;
    }
}
