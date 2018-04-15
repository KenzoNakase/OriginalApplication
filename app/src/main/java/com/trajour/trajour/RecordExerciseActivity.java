package com.trajour.trajour;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Spinner;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RecordExerciseActivity extends AppCompatActivity implements View.OnClickListener, DatabaseReference.CompletionListener {

    private Toolbar mToolbar;
    private int mYear, mMonth, mDay;
    private ProgressDialog mProgress;
    private EditText mExerciseDate1;
    private Spinner mSpinnerBodyPart1;
    private Spinner  mSpinnerExercise1;
    private EditText mEditWeight1;
    private EditText mEditRep1;
    private EditText mEditSet1;
    private Button mSaveButton;

    public static Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DATE);

    private View.OnClickListener mOnDateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(RecordExerciseActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;
                            String dateString = mYear + "/" + String.format("%02d",(mMonth + 1)) + "/" + String.format("%02d", mDay);
                            mExerciseDate1.setText(dateString);
                        }
                    },
                    year, month, day);
            datePickerDialog.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record_exercise);

        mExerciseDate1 = (EditText)findViewById(R.id.editExerciseDate1);
        mExerciseDate1.setOnClickListener(mOnDateClickListener);

        mSpinnerBodyPart1 = (Spinner) findViewById(R.id.spinnerBodyPart1);
        mSpinnerExercise1 = (Spinner) findViewById(R.id.spinnerExercise1);
        mEditWeight1 = (EditText) findViewById(R.id.editWeight1);
        mEditRep1 = (EditText) findViewById(R.id.editRep1);
        mEditSet1 = (EditText) findViewById(R.id.editSet1);

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
                DatabaseReference dailyExerciseRef = dataBaseReference.child(Const.UsersPATH).child(user.getUid()).child(Const.DailyExercisesPATH);

                Map<String, String> data = new HashMap<String, String>();

                //
                String exerciseDate = mExerciseDate1.getText().toString();
                String bodyPart = mSpinnerBodyPart1.toString();
                String exercise = mSpinnerExercise1.toString();
                String weight = mEditWeight1.getText().toString();
                String rep = mEditRep1.getText().toString();
                String set = mEditSet1.getText().toString();

                if (exerciseDate.length() == 0) {
                    // 質問が入力されていない時はエラーを表示するだけ
                    Snackbar.make(v, "日付を入力して下さい", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (bodyPart.length() == 0) {
                    // 質問が入力されていない時はエラーを表示するだけ
                    Snackbar.make(v, "部位を入力して下さい", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (exercise.length() == 0) {
                    // 質問が入力されていない時はエラーを表示するだけ
                    Snackbar.make(v, "トレーニング種目を入力して下さい", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (rep.length() == 0) {
                    // 質問が入力されていない時はエラーを表示するだけ
                    Snackbar.make(v, "回数を入力して下さい", Snackbar.LENGTH_LONG).show();
                    return;
                }

                data.put("exerciseDate", exerciseDate);
                data.put("bodyPart", bodyPart);
                data.put("exercise", exercise);
                data.put("weight", weight);
                data.put("rep", rep);
                data.put("set", set);

                dailyExerciseRef.push().setValue(data, this);
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
