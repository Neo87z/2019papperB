package com.example.a2019mad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a2019mad.Database.DBHandler;

import java.util.List;

public class GameList extends AppCompatActivity {

    ListView GameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        GameList=findViewById(R.id.GameList);
        DBHandler DBMS=new DBHandler(getApplicationContext());
        List MyGameList=DBMS.viewgames();
        List newList=MyGameList;

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,MyGameList);
        GameList.setAdapter(arrayAdapter);

        GameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String x1= (String) newList.get(position);
                position++;

                String x= Integer.toString(position);
                Intent i1 = new Intent(getApplicationContext(),GameOverview.class);

                i1.putExtra("GameNAme",x1);
                i1.putExtra("ID",x);
                startActivity(i1);
            }
        });

    }


}