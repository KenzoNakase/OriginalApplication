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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseMenuList2Activity extends AppCompatActivity {

    private ListView mListView;
    private Exercise mExercise;
    private ArrayList<Exercise> mExerciseArrayList;
    private ExerciseMenuList2Adapter mAdapter;
    private DatabaseReference mExerciseListRef;

    private ExerciseMenu mExerciseMenu;
    private String mExerciseUid;


    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();

            String exerciseName = (String) map.get("exercise");

            Exercise exercise = new Exercise(exerciseName);
            mExerciseArrayList.add(exercise);
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
        setContentView(R.layout.content_exercise_menu_list2);

        Bundle extras = getIntent().getExtras();
        mExerciseMenu = (ExerciseMenu) extras.get("exerciseMenu");
        mExerciseUid = extras.getString("exerciseUid");

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter = new ExerciseMenuList2Adapter(this);
        mExerciseArrayList = new ArrayList<Exercise>();
        mAdapter.notifyDataSetChanged();

        mExerciseArrayList.clear();
        mAdapter.setExerciseArrayList(mExerciseArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mExerciseListRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH).child(mExerciseMenu.getExerciseMenuUid()).child(Const.ExerciseMenuExercisePATH);
        mExerciseListRef.addChildEventListener(mEventListener);

    }

}
