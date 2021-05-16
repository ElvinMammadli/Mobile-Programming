package com.example.tryildizedu16011904;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateExam extends AppCompatActivity {
    EditText time;
    RecyclerView recyclerView;
    ArrayList<String> question , correct_answer ,wrong_answer1,wrong_answer2,wrong_answer3,wrong_answer4;
    ArrayList<Integer> id;
    ArrayList<String> checked;
    String email;
    EditText point;
    Adapter adapter;
    EditText level;
    Button send;
    String s1;
    DBmanager db;
    Button create;
    String s2;
    int position;
    String s3;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
         s1 = sh.getString("time", "");
         s2 = sh.getString("point", "");
         s3 = sh.getString("level", "");
        db=new DBmanager(this);

        recyclerView=findViewById(R.id.select);
        id=new ArrayList<>();
        question=new ArrayList<>();
        correct_answer=new ArrayList<>();
        wrong_answer1=new ArrayList<>();
        wrong_answer2=new ArrayList<>();
        wrong_answer3=new ArrayList<>();
        wrong_answer4=new ArrayList<>();
        checked=new ArrayList<>();
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        checked=intent.getStringArrayListExtra("checked");
        storeData();
        create=findViewById(R.id.create_exam);
        send=findViewById(R.id.send);
        adapter = new Adapter(CreateExam.this,this, id,question,correct_answer,wrong_answer1,wrong_answer2,wrong_answer3,wrong_answer4,new AdapterListener(){

            @Override
            public void onCreateClicked(String a) throws IOException {
                Integer b=Integer.parseInt(a);
                 position=id.indexOf(b);
                 //WRITE TO FILE
                File root = new File(getExternalCacheDir(),"exams");
                if (!root.exists()) {
                    root.mkdirs();
                }

                try {
                    File gpxfile = new File(root, "exam.txt");
                    System.out.println("ROOOTTT"+root);
                    gpxfile.createNewFile();
                FileWriter writer = new FileWriter(gpxfile, true);
                writer.append(
                            "\nQUESTION: " + question.get(position)+
                            "\nA(Correct Answer): "+correct_answer.get(position)+
                                    "\nB: "+wrong_answer1.get(position)+
                                    "\nC: "+wrong_answer2.get(position)+
                                    "\nD: "+wrong_answer3.get(position)+
                                    "\nE: "+wrong_answer4.get(position)+
                                    "\n"

                        );

                writer.flush();
                writer.close();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateExam.this));
        time=(EditText)findViewById(R.id.time);
        point=findViewById(R.id.point);
        level=findViewById(R.id.level);
        change=findViewById(R.id.change);
        time.setText(s1);
        point.setText(s2);
        level.setText(s3);


        change.setOnClickListener(new View.OnClickListener() {
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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your Exam Created!", Toast.LENGTH_SHORT).show();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send file
                File file = new File(getExternalCacheDir().toString()+ "/exams/exam.txt");
                System.out.println(getExternalCacheDir());
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
                startActivity(Intent.createChooser(sharingIntent, "share file with"));

            }
        });

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