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
import android.util.Log;

public class ExerciseMenuList3Activity extends AppCompatActivity  {

    private ListView mListView;
    private Exercise mExercise;
    private ArrayList<WeightRepSet> mWeightRepSetArrayList;
    private ExerciseMenuList3Adapter mAdapter;
    private DatabaseReference mWeightRepSetRef;

    private ExerciseMenu mExerciseMenu;
    private String mExerciseUid;
    private Button mAddWeightRepSetButton;

    private View.OnClickListener mOnAddWeightRepSetButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mAddWeightRepSetButton) {
                Intent intent = new Intent(getApplicationContext(), ExerciseMenu3Activity.class);
                //intent.putExtra("exerciseMenu", mExerciseMenu);
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
            Log.d("TechAcademy", String.valueOf(map));

            String weight = (String) map.get("weight");
            String rep = (String) map.get("rep");
            String set = (String) map.get("set");

            WeightRepSet weightRepSet = new WeightRepSet(weight, rep, set);
            mWeightRepSetArrayList.add(weightRepSet);
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
        setContentView(R.layout.content_exercise_menu_list3);

        //Bundle extras = getIntent().getExtras();
        //mExerciseMenu = (ExerciseMenu) extras.get("exerciseMenu");
        //mExerciseUid = extras.getString("exerciseUid");

        Bundle extras = getIntent().getExtras();
        mExerciseMenu = (ExerciseMenu) extras.get("exerciseMenu");
        mExercise = (Exercise) extras.get("exercise");

        // ListViewの準備
        mListView = (ListView) findViewById(R.id.listView1);
        mAdapter = new ExerciseMenuList3Adapter(this);
        mWeightRepSetArrayList = new ArrayList<WeightRepSet>();
        mAdapter.notifyDataSetChanged();

        mWeightRepSetArrayList.clear();
        mAdapter.setWeightRepSetArrayList(mWeightRepSetArrayList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(getApplicationContext(), ExerciseMenu3Activity.class);
                //intent.putExtra("exercise", mWeightRepSetArrayList.get(position));
                //intent.putExtra("exerciseMenu", mExerciseMenu);
                //startActivity(intent);
            }
        });

        mAddWeightRepSetButton = (Button) findViewById(R.id.addWeightRepSetButton);
        mAddWeightRepSetButton.setOnClickListener(mOnAddWeightRepSetButtonClickListener);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
        mWeightRepSetRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH).child(mExerciseMenu.getExerciseMenuUid()).child(Const.ExerciseMenuExercisePATH).child(mExercise.getExerciseUid());
        mWeightRepSetRef.addChildEventListener(mEventListener);



    }

}
