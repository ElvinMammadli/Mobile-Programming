package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class List_question extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> question , correct_answer ,wrong_answer1,wrong_answer2,wrong_answer3,wrong_answer4;
    ArrayList<Integer> id;
    DBmanager db;
    CustomAdapter customAdapter;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        db=new DBmanager(this);
        recyclerView=findViewById(R.id.select);
        id=new ArrayList<>();
        question=new ArrayList<>();
        correct_answer=new ArrayList<>();
        wrong_answer1=new ArrayList<>();
        wrong_answer2=new ArrayList<>();
        wrong_answer3=new ArrayList<>();
        wrong_answer4=new ArrayList<>();
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        storeData();
        customAdapter = new CustomAdapter(List_question.this,this, id,question,correct_answer,wrong_answer1,wrong_answer2,wrong_answer3,wrong_answer4 );
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(List_question.this));

    }
    public void storeData(){
        Cursor cursor = db.get_question(email);
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                id.add(cursor.getInt(0));
                question.add(cursor.getString(1));
                correct_answer.add(cursor.getString(2));
                wrong_answer1.add(cursor.getString(3));
                wrong_answer2.add(cursor.getString(4));
                wrong_answer3.add(cursor.getString(5));
                wrong_answer4.add(cursor.getString(6));
            }
            cursor.moveToFirst();
        }
    }
}