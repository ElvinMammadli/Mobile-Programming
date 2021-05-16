package com.example.tryildizedu16011904;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_question extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    DBmanager db;
    Button add;
     EditText question;
    EditText correct;
    EditText wrong1;
    EditText wrong2;
    EditText wrong3;
    EditText wrong4;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ImageView image=findViewById(R.id.image_select);
        ImageView video=findViewById(R.id.video_select);
        ImageView audio=findViewById(R.id.audio_select);
         question= findViewById(R.id.question);
         correct= findViewById(R.id.correct);
         wrong1= findViewById(R.id.wrong1);
         wrong2= findViewById(R.id.wrong2);
         wrong3= findViewById(R.id.wrong3);
         wrong4= findViewById(R.id.wrong4);
         add = findViewById(R.id.add_question);
        db=new DBmanager(this);
        Intent myintent= getIntent();

        email=getIntent().getStringExtra("email");
        image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

        video.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_IMAGE);
        });

        audio.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("audio/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_IMAGE);
        });

        add.setOnClickListener(v -> {
            if (question.getText().toString().equals("") || correct.getText().toString().equals("") || wrong1.getText().toString().equals("") || wrong2.getText().toString().equals("") || wrong3.getText().toString().equals("") || wrong4.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Fields cant be empty", Toast.LENGTH_SHORT).show();

            } else {

                if (db.insert_question(question.getText().toString(), correct.getText().toString(), wrong1.getText().toString(), wrong2.getText().toString(), wrong3.getText().toString(), wrong4.getText().toString(),email)) {
                    Toast.makeText(getApplicationContext(), "Question added", Toast.LENGTH_SHORT).show();

                }
                else{
                Toast.makeText(getApplicationContext(), "We have problem", Toast.LENGTH_SHORT).show();
            }
        }
            });

    }
}