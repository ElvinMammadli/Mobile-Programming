package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExamSettings extends AppCompatActivity {
    EditText time;
    EditText point;
    EditText level;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_settings);
        time=findViewById(R.id.exam_time);
        point=findViewById(R.id.time);
        level=findViewById(R.id.difficulty);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("MySharedPref", MODE_PRIVATE).edit();
                editor.putString("time", time.getText().toString());
                editor.putString("point", point.getText().toString());
                editor.putString("level", level.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Settings Changed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}