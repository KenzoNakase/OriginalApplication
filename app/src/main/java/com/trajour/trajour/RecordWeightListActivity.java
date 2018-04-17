package com.trajour.trajour;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordWeightListActivity extends AppCompatActivity {

    private ListView mListView;
    private RecordWeight mRecordWeight;
    private ArrayList<RecordWeight> mRecordWeightArrayList;
    private RecordWeightListAdapter mAdapter;
    private DatabaseReference mBodyWeightsRef;

    private RecordWeightListAdapter mRecordWeightListAdapter;

    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();

            String Uid = (String) map.get("uid");
            String bodyWeightUid = (String) map.get("bodyWeightUid");
            String date = (String) map.get("date");
            String height = (String) map.get("height");
            String bodyWeight = (String) map.get("bodyWeight");
            String bodyFatPercentage = (String) map.get("bodyFatPercentage");

            RecordWeight recordWeight = new RecordWeight(Uid, bodyWeightUid, date, height, bodyWeight, bodyFatPercentage);
            mRecordWeightArrayList.add(recordWeight);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_record_weight_list);

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter = new RecordWeightListAdapter(this);
        mRecordWeightArrayList = new ArrayList<RecordWeight>();
        mAdapter.notifyDataSetChanged();

        mRecordWeightArrayList.clear();
        mAdapter.setRecordWeightArrayList(mRecordWeightArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditRecordWeightActivity.class);
                intent.putExtra("recordWeight", mRecordWeightArrayList.get(position));
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mBodyWeightsRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.BodyWeightsPATH);
        mBodyWeightsRef.addChildEventListener(mEventListener);

    }

}
