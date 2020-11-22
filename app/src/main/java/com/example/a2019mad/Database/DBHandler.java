package com.example.a2019mad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="GameList.db";
    public DBHandler( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query=
                "CREATE TABLE " + DatabaseMaster.Users.TABLE_NAME + " ("+
                        DatabaseMaster.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_USERTYPE + " TEXT)";
        db.execSQL(Query);

        String Query2=
                "CREATE TABLE " + DatabaseMaster.Game.TABLE_NAME + " ("+
                        DatabaseMaster.Game._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Game.COLUMN_NAME_GAME_YEAR + " TEXT," +
                        DatabaseMaster.Game.COLUMN_NAME_GAME_NAME + " TEXT)";
        db.execSQL(Query2);

        String Query3=
                "CREATE TABLE " + DatabaseMaster.Comments.TABLE_NAME + " ("+
                        DatabaseMaster.Comments._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME + " TEXT," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING + " TEXT," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENTS + " TEXT)";
        db.execSQL(Query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long registerUserUser(String Username, String Password){

        SQLiteDatabase DB= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(DatabaseMaster.Users.COLUMN_NAME_USERNAME,Username);
        CV.put(DatabaseMaster.Users.COLUMN_NAME_PASSWORD,Password);

        long Exec=DB.insert(DatabaseMaster.Users.TABLE_NAME,null,CV);
        return Exec;

    }

    public String LoginUser(String Username,String Password){
        String Stat="Fail";
        SQLiteDatabase DB=getReadableDatabase();
        String [] Projection ={
                DatabaseMaster.Users.COLUMN_NAME_PASSWORD
        };

        String Selection=DatabaseMaster.Users.COLUMN_NAME_USERNAME + " Like ?";
        String [] args= { Username };

        Cursor cursor= DB.query(
                DatabaseMaster.Users.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PASSWORD)).equals(Password)){
                Stat="Pass";
                break;
            }

        }
        return Stat;
    }


    public boolean AddGame(String GameName, String GameYear){
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(DatabaseMaster.Game.COLUMN_NAME_GAME_NAME,GameName);
        CV.put(DatabaseMaster.Game.COLUMN_NAME_GAME_YEAR,GameYear);
        boolean exec;
        long x=DB.insert(DatabaseMaster.Game.TABLE_NAME,null,CV);
        if(x > 0){
            exec=true;
        }else{
            exec=false;
        }
        return exec;
    }


    public List viewgames(){
            SQLiteDatabase DB= getReadableDatabase();
            String [] Projection={
                    DatabaseMaster.Game.COLUMN_NAME_GAME_NAME

            };

            Cursor cursor= DB.query(
                    DatabaseMaster.Game.TABLE_NAME,
                    Projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            List gameList= new ArrayList();
            while(cursor.moveToNext()){
               String x=(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Game.COLUMN_NAME_GAME_NAME)));
                gameList.add(x);

            }
            return gameList;
    }

    public List GetGameComments(String GameName){
        SQLiteDatabase DB= getReadableDatabase();
        String [] Projection={
                DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENTS

        };
        String Selection = DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME + " Like ?";
        String [] args = { GameName };

        Cursor cursor= DB.query(
                DatabaseMaster.Comments.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );

        List CommentList= new ArrayList();
        while(cursor.moveToNext()){
            String x=(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENTS)));
            CommentList.add(x);
        }
        return CommentList;
    }

    public long SaveCommentRatting(String Comment,String Ratting,String gameName){
        SQLiteDatabase DB= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENTS,Comment);
        CV.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING,Ratting);
        CV.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME,gameName);
        long exec=DB.insert(DatabaseMaster.Comments.TABLE_NAME,null,CV);
        return exec;
    }

    public String getRate(String gameName){
        SQLiteDatabase DB=getReadableDatabase();

        String [] Projection={
                DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING
        };

        String Selection=DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME + " Like ?";
        String [] args ={ gameName };
        Cursor cursor=DB.query(
                DatabaseMaster.Comments.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );
        float x=0;
        int count=0;
        while (cursor.moveToNext()){
            count++;
            String rate=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING));
            float y;
            y=Float.parseFloat(rate);
            x=x+y;
        }
        float avg;
        avg=x/count;
        String newRate=Float.toString(avg);
        return newRate;
    }
}
