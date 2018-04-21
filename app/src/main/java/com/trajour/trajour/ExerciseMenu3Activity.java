package com.trajour.trajour;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ExerciseMenu3Activity extends AppCompatActivity implements View.OnClickListener, DatabaseReference.CompletionListener {

    private TextView mMenuName;
    private String mExerciseMenuUid;
    private ProgressDialog mProgress;
    private TextView mTextExercise2;
    private EditText mEditWeight1;
    private EditText mEditRep1;
    private EditText mEditSet1;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_menu3);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String exercise = intent.getStringExtra("exercise");
        mExerciseMenuUid = intent.getStringExtra("exerciseMenuUid");

        mMenuName = (TextView)findViewById(R.id.textMenuName3);
        mMenuName.setText(name);

        mTextExercise2 = (TextView)findViewById(R.id.textExercise2);
        mTextExercise2.setText(exercise);

        mEditWeight1 = (EditText) findViewById(R.id.editWeight1);
        mEditRep1 = (EditText) findViewById(R.id.editRep1);
        mEditSet1 = (EditText) findViewById(R.id.editSet1);

        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("投稿中...");

    }

    @Override
    public void onClick(View v) {
        if (v == mNextButton) {
            // キーボードが出てたら閉じる
            InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference exerciseMenuRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH).child(mExerciseMenuUid).child(Const.ExerciseMenuExercisePATH).child(mExerciseMenuUid);

                Map<String, Object> data = new HashMap<String, Object>();

                String weight = mEditWeight1.getText().toString();
                String rep = mEditRep1.getText().toString();
                String set = mEditSet1.getText().toString();

                data.put("weight", weight);
                data.put("rep", rep);
                data.put("set", set);

                exerciseMenuRef.push().setValue(data, this);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                mProgress.show();


            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        }
    }


    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        mProgress.dismiss();
        if (databaseError == null) {
            finish();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "保存に失敗しました", Snackbar.LENGTH_LONG).show();
        }
    }

}
