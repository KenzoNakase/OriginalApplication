package com.trajour.trajour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OneRepetitionMaximumAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<String> mOneRepetitionMaximumList;

    public OneRepetitionMaximumAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOneRepetitionMaximumList(List<String> OneRepetitionMaximumList) {
        mOneRepetitionMaximumList = OneRepetitionMaximumList;
    }


    @Override
    public int getCount() {
        return mOneRepetitionMaximumList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOneRepetitionMaximumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);

        // 後でTaskクラスから情報を取得するように変更する
        textView1.setText(mOneRepetitionMaximumList.get(position));

        return convertView;
    }
}
