package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBmanager db;
    static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File root = new File(getExternalCacheDir(),"exams/exam.txt");
        if (root.exists()) {
            root.delete();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText userName = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        db=new DBmanager(this);
        Button signin= (Button) findViewById(R.id.signin);
        Button signup= (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SingUp.class);
                startActivity(myIntent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userName.getText().toString();
                String passwordStr = password.getText().toString();

                if(db.userpassw(userNameStr,passwordStr)==true){
                    Toast.makeText(MainActivity.this,"Succesfully loggedin",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, Menu.class);
                    myIntent.putExtra("email",userNameStr);
                    startActivity(myIntent);
                }else{
                    Toast.makeText(MainActivity.this,"Wrong mail or password. If you dont register before click signup button",Toast.LENGTH_SHORT).show();
                    count++;
                    if(count==3){
                        Intent myIntent = new Intent(MainActivity.this, SingUp.class);
                        startActivity(myIntent);
                        count=0;
                    }
                }


            }
        });

    }
}