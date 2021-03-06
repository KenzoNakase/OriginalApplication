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

public class ExerciseMenu2Activity extends AppCompatActivity implements View.OnClickListener, DatabaseReference.CompletionListener {

    private TextView mMenuName;
    private ExerciseMenu mExerciseMenu;
    private ProgressDialog mProgress;
    private Spinner  mSpinnerExercise1;
    private Button mSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_menu2);

        Bundle extras = getIntent().getExtras();
        mExerciseMenu = (ExerciseMenu) extras.get("exerciseMenu");

        mMenuName = (TextView)findViewById(R.id.textMenuName3);
        mMenuName.setText(mExerciseMenu.getExerciseMenuName());

        mSpinnerExercise1 = (Spinner) findViewById(R.id.spinnerExercise1);

        mSaveButton = (Button) findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("投稿中...");

    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            // キーボードが出てたら閉じる
            InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference exerciseMenuRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.ExercisesMenusPATH).child(mExerciseMenu.getExerciseMenuUid()).child(Const.ExerciseMenuExercisePATH);

                Map<String, Object> data = new HashMap<String, Object>();

                String exercise = mSpinnerExercise1.getSelectedItem().toString();


                if (exercise.length() == 0) {
                    // 質問が入力されていない時はエラーを表示するだけ
                    Snackbar.make(v, "トレーニング種目を入力して下さい", Snackbar.LENGTH_LONG).show();
                    return;
                }

                data.put("exercise", exercise);
                exerciseMenuRef.push().setValue(data, this);
                Intent intent = new Intent(getApplicationContext(), ExerciseMenuList2Activity.class);
                intent.putExtra("exerciseMenu", mExerciseMenu);
                intent.putExtra("exercise", exercise);
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
