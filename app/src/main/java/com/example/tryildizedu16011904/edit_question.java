package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class edit_question extends AppCompatActivity {
    int question_id;
    DBmanager db;
    Cursor cursor;
    EditText question;
    EditText  correct_answer1;
    EditText wrong_answer11;
    EditText wrong_answer12;
    EditText wrong_answer13;
    EditText wrong_answer14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);
        db=new DBmanager(this);
        Intent intent=getIntent();
        Toast.makeText(edit_question.this,"Edit  ",Toast.LENGTH_SHORT).show();

        question_id=intent.getIntExtra("question_id",0);
        cursor=db.get_questionby_id(question_id);
        cursor.moveToFirst();
        question=findViewById(R.id.question);
        correct_answer1=findViewById(R.id.correct_answer);
        wrong_answer11=findViewById(R.id.wrong_answer1);
        wrong_answer12=findViewById(R.id.wrong_answer2);
        wrong_answer13=findViewById(R.id.wrong_answer3);
        wrong_answer14=findViewById(R.id.wrong_answer4);
        question.setText(cursor.getString(1));
        correct_answer1.setText(cursor.getString(2));
        wrong_answer11.setText(cursor.getString(3));
        wrong_answer12.setText(cursor.getString(4));
        wrong_answer13.setText(cursor.getString(5));
        wrong_answer14.setText(cursor.getString(6));



    }
}