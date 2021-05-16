package com.example.tryildizedu16011904;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SingUp extends AppCompatActivity {
    DBmanager db;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        final EditText name =  findViewById(R.id.name);
        final EditText surname =  findViewById(R.id.surname);
        final EditText email =  findViewById(R.id.email);
        final EditText number = findViewById(R.id.number);
        final EditText password =  findViewById(R.id.password);
        final EditText password2 =  findViewById(R.id.password2);

        Button signup= findViewById(R.id.signup);
        ImageView image=findViewById(R.id.image);
        db=new DBmanager(this);

        signup.setOnClickListener(v -> {


            if(email.getText().toString().equals("")||password.getText().toString().equals("")||password2.getText().toString().equals("")||name.getText().toString().equals("")||surname.getText().toString().equals("")||number.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Field can not be empty!", Toast.LENGTH_SHORT).show();
            }
            else if(!password.getText().toString().equals(password2.getText().toString())){
                Toast.makeText(getApplicationContext(), "Passwords can't be different", Toast.LENGTH_SHORT).show();

            }

            else{
                if(db.chkUsername(email.getText().toString())){
                    if(db.insert_user(name.getText().toString(), surname.getText().toString(), email.getText().toString(), number.getText().toString(), password.getText().toString())==true) {
                        Toast.makeText(SingUp.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SingUp.this,"User already exists",Toast.LENGTH_SHORT).show();

                }

            }
        });
        image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });

    }

}