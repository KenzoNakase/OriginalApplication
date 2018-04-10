package com.trajour.trajour;

import android.Manifest;
import android.app.ProgressDialog;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RecordWeightActivity extends AppCompatActivity implements View.OnClickListener, DatabaseReference.CompletionListener {

    private Toolbar mToolbar;
    private ProgressDialog mProgress;
    private EditText mEditDate1;
    private EditText mEditHeight1;
    private EditText mEditBodyWeight1;
    private EditText mEditBodyFatPercentage1;
    private Button mSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // UIの準備
        setTitle("体重・体脂肪率を記録する");

        mEditDate1 = (EditText) findViewById(R.id.editDate1);
        mEditHeight1 = (EditText) findViewById(R.id.editHeight1);
        mEditBodyWeight1 = (EditText) findViewById(R.id.editWeight1);
        mEditBodyFatPercentage1 = (EditText) findViewById(R.id.editBodyFatPercentage1);

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

            DatabaseReference dataBaseReference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference bodyWeightRef = dataBaseReference.child(Const.BodyWeightsPATH );

            Map<String, String> data = new HashMap<String, String>();

            //
            String date = mEditDate1.getText().toString();
            String height = mEditHeight1.getText().toString();
            String bodyWeight = mEditBodyWeight1.getText().toString();
            String bodyFatPercentage = mEditBodyFatPercentage1.getText().toString();

            if (date.length() == 0) {
                // 質問が入力されていない時はエラーを表示するだけ
                Snackbar.make(v, "日付を入力して下さい", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (height.length() == 0) {
                // 質問が入力されていない時はエラーを表示するだけ
                Snackbar.make(v, "身長を入力して下さい", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (bodyWeight.length() == 0) {
                // 質問が入力されていない時はエラーを表示するだけ
                Snackbar.make(v, "体重を入力して下さい", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (bodyFatPercentage.length() == 0) {
                // 質問が入力されていない時はエラーを表示するだけ
                Snackbar.make(v, "を入力して下さい", Snackbar.LENGTH_LONG).show();
                return;
            }


            data.put("date", date);
            data.put("height", height);
            data.put("BodyWeight", bodyWeight);
            data.put("bodyFatPercentage", bodyFatPercentage);

            bodyWeightRef.push().setValue(data, this);
            mProgress.show();
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