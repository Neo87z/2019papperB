package com.example.a2019mad.Database;

import android.provider.BaseColumns;

public class DatabaseMaster {
    private DatabaseMaster(){

    }

    public static final class Users implements BaseColumns {

        public  static final String TABLE_NAME="users";
        public  static final String COLUMN_NAME_USERNAME="Username";
        public  static final String COLUMN_NAME_PASSWORD="Password";
        public  static final String COLUMN_NAME_USERTYPE="UserType";

    }

    public static final class Game implements BaseColumns {

        public  static final String TABLE_NAME="games";
        public  static final String COLUMN_NAME_GAME_NAME="Game_NAME";
        public  static final String COLUMN_NAME_GAME_YEAR="Game_YEAR";


    }
    public static final class Comments implements BaseColumns {

        public  static final String TABLE_NAME="Comments";
        public  static final String COLUMN_NAME_GAME_NAME="Game_NAME";
        public  static final String COLUMN_NAME_GAME_RATING="Game_RATING";
        public  static final String COLUMN_NAME_GAME_COMMENTS="Game_COMMENTS";

    }

}
