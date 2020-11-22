package com.example.a2019mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2019mad.Database.DBHandler;

public class AddGame extends AppCompatActivity {

    EditText GameName, GameYear;
    Button AddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        GameName=findViewById(R.id.EditTextGameName);
        GameYear=findViewById(R.id.EditTextGameYear);
        AddButton=findViewById(R.id.ADdGame);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGame();
            }
        });
    }

    public void AddGame(){
        GameName=findViewById(R.id.EditTextGameName);
        GameYear=findViewById(R.id.EditTextGameYear);
        DBHandler DBMS= new DBHandler(getApplicationContext());
        boolean x=DBMS.AddGame(GameName.getText().toString(),GameYear.getText().toString());
        if (x == true){
            Toast.makeText(this, "Game Added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, " Error", Toast.LENGTH_SHORT).show();
        }

    }
}