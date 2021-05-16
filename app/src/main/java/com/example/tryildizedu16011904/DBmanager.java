package com.example.tryildizedu16011904;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBmanager extends SQLiteOpenHelper {
    private static final String dbname = "Stude.db";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    public DBmanager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT ,surname TEXT,email TEXT,number TEXT,password TEXT)");
        db.execSQL("CREATE TABLE questions (ID INTEGER PRIMARY KEY AUTOINCREMENT ,question TEXT ,correct_answer TEXT,wrong_answer1 TEXT ,wrong_answer2 TEXT ,wrong_answer3 TEXT ,wrong_answer4 TEXT,user_email TEXT)");
        db.execSQL("CREATE TABLE exams (ID INTEGER PRIMARY KEY AUTOINCREMENT,question_id TEXT,user_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS exams");

    }


    public boolean insert_question(String question, String correct_answer,String wrong_answer1,String wrong_answer2 , String wrong_answer3,String wrong_answer4,String user_email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question",question);
        contentValues.put("correct_answer",correct_answer);
        contentValues.put("wrong_answer1",wrong_answer1);
        contentValues.put("wrong_answer2",wrong_answer2);
        contentValues.put("wrong_answer3",wrong_answer3);
        contentValues.put("wrong_answer4",wrong_answer4);
        contentValues.put("user_email",user_email);
        long ins = db.insert("questions",null,contentValues);
        if(ins==1)
            return false;
        else
            return true;
    }

    public void create_exam(String question_id,String user_id){
        SQLiteDatabase db = this.getWritableDatabase();

    }


    public boolean insert_user(String name, String surname,String email,String number , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        contentValues.put("email",email);
        contentValues.put("number",number);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins==1)
            return false;
        else
            return true;
    }
    public Boolean chkUsername(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if (cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public Cursor get_question(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
           cursor  = db.rawQuery("Select * from questions where user_email=?", new String[]{email});
        }catch(SQLException e){
            System.out.println(e);
        }
        return cursor;
    }


    public Cursor get_questionby_id(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery("SELECT * FROM questions WHERE ID ="+id,null);

        return cursor;
    }





    public Boolean userpassw(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }
    public void delete(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="DELETE FROM questions WHERE ID='"+id+"'";
        db.execSQL(query);
    }


}