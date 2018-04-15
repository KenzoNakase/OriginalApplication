package com.trajour.trajour;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
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

public class RecordExerciseListActivity extends AppCompatActivity {

    private ListView mListView;
    private RecordExercise mRecordExercise;
    private ArrayList<RecordExercise> mRecordExerciseArrayList;
    private RecordExerciseListAdapter mAdapter;
    private DatabaseReference mDailyExerciseRef;

    private RecordExerciseListAdapter mRecordExerciseListAdapter;

    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();

            String Uid = (String) map.get("uid");
            String exerciseDate = (String) map.get("exerciseDate");
            String bodyPart = (String) map.get("bodyPart");
            String exercise= (String) map.get("exercise");
            String weight = (String) map.get("weight");
            String rep = (String) map.get("rep");
            String set = (String) map.get("set");

            RecordExercise recordExercise = new RecordExercise(Uid, exerciseDate, bodyPart, exercise, weight, rep, set);
            mRecordExerciseArrayList.add(recordExercise);
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
        setContentView(R.layout.content_record_exercise_list);

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter = new RecordExerciseListAdapter(this);
        mRecordExerciseArrayList = new ArrayList<RecordExercise>();
        mAdapter.notifyDataSetChanged();

        mRecordExerciseArrayList.clear();
        mAdapter.setRecordExerciseArrayList(mRecordExerciseArrayList);
        mListView.setAdapter(mAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mDailyExerciseRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.DailyExercisesPATH);
        mDailyExerciseRef.addChildEventListener(mEventListener);

    }

}
