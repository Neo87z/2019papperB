package com.example.a2019mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2019mad.Database.DBHandler;
import com.example.a2019mad.Database.DatabaseMaster;

import java.util.List;

public class GameOverview extends AppCompatActivity {
    TextView GameName;
    RatingBar rateBar;
    EditText Comment;
    Button SaveButton;

    TextView Rate;
    ListView Comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("ID").toString();
        GameName=findViewById(R.id.textView3);
        GameName.setText(i1.getStringExtra("GameNAme"));
        Comments=findViewById(R.id.CommentLsist);
        rateBar=findViewById(R.id.ratingBar2);
        Comment=findViewById(R.id.EditTextuserComment);
        SaveButton=findViewById(R.id.button2);
        Rate=findViewById(R.id.textView8);


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveCommentRatting();

            }
        });

        DBHandler DBMS= new DBHandler(getApplicationContext());
        List CommentList;

        String rate =DBMS.getRate(i1.getStringExtra("GameNAme"));
        if(rate.equals("NaN")){
            Rate.setText("0.0");

        }
        else{
            Rate.setText(rate);
        }


        CommentList= DBMS.GetGameComments(i1.getStringExtra("GameNAme"));
        if(CommentList.size() == 0){

        }else {
            ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,CommentList);
            Comments.setAdapter(arrayAdapter);
        }


    }

    public void SaveCommentRatting(){
        Intent i1=getIntent();
        rateBar=findViewById(R.id.ratingBar2);
        Comment=findViewById(R.id.EditTextuserComment);
        DBHandler DBMS= new DBHandler(getApplicationContext());
        float rate =rateBar.getRating();
        String newRate=Float.toString(rate);
        long exec=DBMS.SaveCommentRatting(Comment.getText().toString(),newRate,i1.getStringExtra("GameNAme"));
        if(exec >0 ){
            Toast.makeText(this, "Comment Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }

    }
}