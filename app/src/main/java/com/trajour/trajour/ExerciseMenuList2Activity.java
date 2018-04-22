package com.trajour.trajour;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.Map;

public class ExerciseMenuList2Activity extends AppCompatActivity  {

    private ListView mListView;
    private Exercise mExercise;
    private ArrayList<Exercise> mExerciseArrayList;
    private ExerciseMenuList2Adapter mAdapter;
    private DatabaseReference mExerciseListRef;

    private ExerciseMenu mExerciseMenu;
    private String mExerciseUid;
    private Button mAddExerciseButton;

    private View.OnClickListener mOnAddExerciseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mAddExerciseButton) {
                Intent intent = new Intent(getApplicationContext(), ExerciseMenu2Activity.class);
                intent.putExtra("exerciseMenu", mExerciseMenu);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        }
    };


    private ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();

            String exerciseUid = (String) map.get("exerciseUid");
            String exerciseName = (String) map.get("exercise");

            Exercise exercise = new Exercise(dataSnapshot.getKey(), exerciseName);
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
                Intent intent = new Intent(getApplicationContext(), ExerciseMenu3Activity.class);
                intent.putExtra("exercise", mExerciseArrayList.get(position));
                intent.putExtra("exerciseMenu", mExerciseMenu);
                startActivity(intent);
            }
        });

        mAddExerciseButton = (Button) findViewById(R.id.addExerciseButton);
        mAddExerciseButton.setOnClickListener(mOnAddExerciseClickListener);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mExerciseListRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH).child(mExerciseMenu.getExerciseMenuUid()).child(Const.ExerciseMenuExercisePATH);
        mExerciseListRef.addChildEventListener(mEventListener);

    }

}
