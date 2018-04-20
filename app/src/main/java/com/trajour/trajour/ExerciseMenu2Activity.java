package com.trajour.trajour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class ExerciseMenu2Activity extends AppCompatActivity {

    private TextView mMenuName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_menu2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        mMenuName = (TextView)findViewById(R.id.textMenuName3);
        mMenuName.setText(name);
    }
}
