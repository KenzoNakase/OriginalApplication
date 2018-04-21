package com.trajour.trajour;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;
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

public class ExerciseMenuListActivity extends AppCompatActivity {

    private ListView mListView;
    private ExerciseMenu mExerciseMenu;
    private ArrayList<ExerciseMenu> mExerciseMenuArrayList;
    private ExerciseMenuListAdapter mAdapter;
    private DatabaseReference mExerciseMenuListRef;

    private ExerciseMenuListAdapter mExerciseMenuListAdapter;

    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();

            String uid = (String) map.get("uid");
            String exerciseMenuUid = (String) map.get("exerciseMenuUid");
            String exerciseMenuDate = (String) map.get("date");
            String exerciseMenuName = (String) map.get("name");

            ExerciseMenu exerciseMenu = new ExerciseMenu(dataSnapshot.getKey(), exerciseMenuDate, exerciseMenuName);
            mExerciseMenuArrayList.add(exerciseMenu);
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
        setContentView(R.layout.content_exercise_menu_list);

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter = new ExerciseMenuListAdapter(this);
        mExerciseMenuArrayList = new ArrayList<ExerciseMenu>();
        mAdapter.notifyDataSetChanged();

        mExerciseMenuArrayList.clear();
        mAdapter.setExerciseMenuArrayList(mExerciseMenuArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mExerciseMenuListRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH);
        mExerciseMenuListRef.addChildEventListener(mEventListener);

    }

}
