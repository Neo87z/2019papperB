package com.example.a2019mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2019mad.Database.DBHandler;
import com.example.a2019mad.Database.DatabaseMaster;

public class MainActivity extends AppCompatActivity {

    EditText Username,Pasword;
    Button LoginButton,RegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username=findViewById(R.id.EdditTextUserName);
        Pasword=findViewById(R.id.EdotTextPasswprd);
        LoginButton=findViewById(R.id.ButtonLogin);
        RegisterButton=findViewById(R.id.ButtonRegister);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
            }
        });
    }

    public void Login(){
        Username=findViewById(R.id.EdditTextUserName);
        Pasword=findViewById(R.id.EdotTextPasswprd);
        if(Username.getText().toString().equals("admin")){
            Intent i1 = new Intent(getApplicationContext(),AddGame.class);
            startActivity(i1);

        }else{
            DBHandler DBMS= new DBHandler(getApplicationContext());
            String stat=DBMS.LoginUser(Username.getText().toString(),Pasword.getText().toString());
            if(stat.equals("Pass")){
                Toast.makeText(this, "Logged in as User", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(getApplicationContext(),GameList.class);
                startActivity(i1);
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();

            }

        }


    }


    public void AddUser(){
        Username=findViewById(R.id.EdditTextUserName);
        Pasword=findViewById(R.id.EdotTextPasswprd);

        DBHandler DBMS= new DBHandler(getApplicationContext());
        long Exec=DBMS.registerUserUser(Username.getText().toString(),Pasword.getText().toString());
        if(Exec > 0){
            Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }
}