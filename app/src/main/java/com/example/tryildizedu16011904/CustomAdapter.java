package com.example.tryildizedu16011904;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    DBmanager db;
    int a;
    private AlertDialog mDialog;
    private int mListRowPosition;
    private Context context;
    private Activity activity;
    private ArrayList id;
    private ArrayList questions;
    private ArrayList correct_answer;
    private ArrayList wrong_answer1;
    private ArrayList wrong_answer2;
    private ArrayList wrong_answer3;
    private ArrayList wrong_answer4;

    CustomAdapter(Activity activity, Context context, ArrayList id,ArrayList questions,ArrayList correct_answer,ArrayList wrong_answer1, ArrayList wrong_answer2, ArrayList wrong_answer3,ArrayList wrong_answer4){
        this.activity = activity;
        this.context = context;
        this.id=id;
        this.questions=questions;
        this.correct_answer=correct_answer;
        this.wrong_answer1=wrong_answer1;
        this.wrong_answer2=wrong_answer2;
        this.wrong_answer3=wrong_answer3;
        this.wrong_answer4=wrong_answer4;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_questions, parent, false);
        db=new DBmanager(this.context);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.question_txt.setText(String.valueOf(questions.get(position)));
        holder.correct_answer_txt.setText(String.valueOf(correct_answer.get(position)));
        holder.wrong_answer_txt1.setText(String.valueOf(wrong_answer1.get(position)));
        holder.wrong_answer_txt2.setText(String.valueOf(wrong_answer2.get(position)));
        holder.wrong_answer_txt3.setText(String.valueOf(wrong_answer3.get(position)));
        holder.wrong_answer_txt4.setText(String.valueOf(wrong_answer4.get(position)));
        holder.number.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        TextView question_txt,number,correct_answer_txt,wrong_answer_txt1,wrong_answer_txt2,wrong_answer_txt3,wrong_answer_txt4;
        ImageButton imageButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_txt=itemView.findViewById(R.id.write);
            correct_answer_txt=itemView.findViewById(R.id.a);
            wrong_answer_txt1=itemView.findViewById(R.id.b);
            wrong_answer_txt2=itemView.findViewById(R.id.c);
            wrong_answer_txt3=itemView.findViewById(R.id.d);
            wrong_answer_txt4=itemView.findViewById(R.id.e);

            number=itemView.findViewById(R.id.number);
            imageButton=itemView.findViewById(R.id.menu);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
        private void showPopupMenu(View view){
            PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.edit:
                    Intent myIntent = new Intent(context.getApplicationContext(), edit_question.class);
                     a=getAdapterPosition();
                    myIntent.putExtra("question_id", (Integer) id.get(a));
                    context.startActivity(myIntent);
                    return  true;
                case R.id.delete:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure?");

                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            a=getAdapterPosition();

                            db.delete((Integer) id.get(a));
                            questions.remove(a);
                            correct_answer.remove(a);
                            wrong_answer1.remove(a);
                            wrong_answer2.remove(a);
                            wrong_answer3.remove(a);
                            wrong_answer4.remove(a);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context.getApplicationContext(),"Canceled",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                default:
                    return false;
            }
        }
    }


}