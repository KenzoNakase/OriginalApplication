package com.trajour.trajour;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class RecordWeightListActivity extends AppCompatActivity {
    private ListView mListView;
    private RecordWeightListAdapter mRecordWeightListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // ListViewの設定
        mRecordWeightListAdapter = new RecordWeightListAdapter(RecordWeightListActivity.this);
        mListView = (ListView) findViewById(R.id.listView1);

        // ListViewをタップしたときの処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 入力・編集する画面に遷移させる
            }
        });

        // ListViewを長押ししたときの処理
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // タスクを削除する

                return true;
            }
        });

        reloadListView();
    }

    private void reloadListView() {

        // 後でTaskクラスに変更する
        List<String> recordWeightList = new ArrayList<String>();
        recordWeightList.add("aaa");
        recordWeightList.add("bbb");
        recordWeightList.add("ccc");

        mRecordWeightListAdapter.setRecordWeightList(recordWeightList);
        mListView.setAdapter(mRecordWeightListAdapter);
        mRecordWeightListAdapter.notifyDataSetChanged();
    }

}
