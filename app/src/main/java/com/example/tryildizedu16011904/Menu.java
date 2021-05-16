package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    String email;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button add = (Button) findViewById(R.id.add_question);
        Button list = (Button) findViewById(R.id.list);
        Button settings= (Button) findViewById(R.id.settings);
        Button create= (Button) findViewById(R.id.create);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu.this, Add_question.class);

                myIntent.putExtra("email",email);
                startActivity(myIntent);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu.this, List_question.class);
                myIntent.putExtra("email",email);
                startActivity(myIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu.this, ExamSettings.class);

                startActivity(myIntent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu.this, CreateExam.class);
                myIntent.putExtra("email",email);
                startActivity(myIntent);
            }

        });
    }

}