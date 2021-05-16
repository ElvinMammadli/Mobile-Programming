package com.example.tryildizedu16011904;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import android.app.Activity;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<com.example.tryildizedu16011904.Adapter.MyViewHolder> {
    DBmanager db;
    private Context context;
    private Activity activity;
    private ArrayList<Integer> id;
    private ArrayList<String> questions;
    private ArrayList<String>  correct_answer;
    private ArrayList<String>  wrong_answer1;
    private ArrayList<String>  wrong_answer2;
    private ArrayList<String>  wrong_answer3;
    private ArrayList<String>  wrong_answer4;
    private AdapterListener listener;

    Adapter(Activity activity, Context context, ArrayList id,ArrayList questions,ArrayList correct_answer,ArrayList wrong_answer1, ArrayList wrong_answer2, ArrayList wrong_answer3,ArrayList wrong_answer4,AdapterListener listener){
        this.activity = activity;
        this.context = context;
        this.id=id;
        this.listener=listener;
        this.questions=questions;
        this.correct_answer=correct_answer;
        this.wrong_answer1=wrong_answer1;
        this.wrong_answer2=wrong_answer2;
        this.wrong_answer3=wrong_answer3;
        this.wrong_answer4=wrong_answer4;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(@NonNull final com.example.tryildizedu16011904.CustomAdapter.MyViewHolder holder, final int position) {
        holder.question_txt.setText(String.valueOf(questions.get(position)));
        holder.correct_answer_txt.setText(String.valueOf(correct_answer.get(position)));
        holder.wrong_answer_txt1.setText(String.valueOf(wrong_answer1.get(position)));
        holder.wrong_answer_txt2.setText(String.valueOf(wrong_answer2.get(position)));
        holder.wrong_answer_txt3.setText(String.valueOf(wrong_answer3.get(position)));
        holder.wrong_answer_txt4.setText(String.valueOf(wrong_answer4.get(position)));
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.select, parent, false);
        db=new DBmanager(this.context);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.MyViewHolder holder, int position) {
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    try {
                        listener.onCreateClicked(String.valueOf(id.get(position)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        holder.question_txt.setText(String.valueOf(questions.get(position)));
        holder.correct_answer_txt.setText(String.valueOf(correct_answer.get(position)));
        holder.wrong_answer_txt1.setText(String.valueOf(wrong_answer1.get(position)));
        holder.wrong_answer_txt2.setText(String.valueOf(wrong_answer2.get(position)));
        holder.wrong_answer_txt3.setText(String.valueOf(wrong_answer3.get(position)));
        holder.wrong_answer_txt4.setText(String.valueOf(wrong_answer4.get(position)));

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView question_txt, correct_answer_txt, wrong_answer_txt1, wrong_answer_txt2, wrong_answer_txt3, wrong_answer_txt4;
        CheckBox checkBox;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_txt = itemView.findViewById(R.id.write);
            correct_answer_txt = itemView.findViewById(R.id.a);
            wrong_answer_txt1 = itemView.findViewById(R.id.b);
            wrong_answer_txt2 = itemView.findViewById(R.id.c);
            wrong_answer_txt3 = itemView.findViewById(R.id.d);
            wrong_answer_txt4 = itemView.findViewById(R.id.e);
            checkBox=itemView.findViewById(R.id.checkBox);
        }
    }


}