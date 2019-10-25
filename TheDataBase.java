package com.example.mathapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mathapp.UtilityVar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TheDataBase extends SQLiteOpenHelper{

    public TheDataBase(Context theContext) {
        super(theContext, UtilityVar.DATABASE_NAME, null, UtilityVar.DATABASE_VERSION);

    }

    //we create our table here remember sql
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PLAYER_TABLE;

        //Create Table command
        CREATE_PLAYER_TABLE = "CREATE TABLE " + UtilityVar.TABLE_NAME + "(" + UtilityVar.KEY_ID + " INTEGER PRIMARY KEY,"
                + UtilityVar.KEY_NAME + " TEXT," + UtilityVar.KEY_SCORE + " INTEGER"  + ")";

        db.execSQL(CREATE_PLAYER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_THE_TABLE = "DROP IF TABLE EXISTS";

        db.execSQL(DROP_THE_TABLE, new String [] {UtilityVar.DATABASE_NAME});

        onCreate(db);

    }

    //Add player info to db
    public void addPlayer(Player thePlayer) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        //store player info
        values.put(UtilityVar.KEY_NAME, thePlayer.getName());
        values.put(UtilityVar.KEY_SCORE, thePlayer.getScore());

        //Insert player data into a row
        db.insert(UtilityVar.TABLE_NAME, null, values);

        db.close();
    }


    //Get a Player
    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //sets cursor to the row that has the id
        Cursor cursor = db.query(UtilityVar.TABLE_NAME,
                new String[]{ UtilityVar.KEY_ID, UtilityVar.KEY_NAME, UtilityVar.KEY_SCORE},
                UtilityVar.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //prepare player object to be returned
        Player tempPlayer = new Player();
        tempPlayer.setId(Integer.parseInt(cursor.getString(0)));
        tempPlayer.setName(cursor.getString(1));

        //not sure about this one
        tempPlayer.setScore(Integer.parseInt(cursor.getString(2)));

        return tempPlayer;
    }


    //Get db count
    public int getDBCount() {
        String countQuery = "SELECT * FROM " + UtilityVar.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }


    //Get all Players
    public List <Player> getAllPlayers() {

        SQLiteDatabase myDb = this.getReadableDatabase();
        //Select all contacts
        String selectAll = "SELECT * FROM " + UtilityVar.TABLE_NAME;
        Cursor myCursor = myDb.rawQuery(selectAll, null);

        //retrieve top 5 Players doesnt sort properly
        //Cursor myCursor = myDb.query(UtilityVar.TABLE_NAME,
        //  new String[]{ UtilityVar.KEY_ID, UtilityVar.KEY_NAME, UtilityVar.KEY_SCORE},
         //     null, null,null, null, UtilityVar.KEY_SCORE + " ASC", null);

       List <Player> playerList = new ArrayList<>();

        //Loop through our data
        if (myCursor.moveToFirst()) {
            do {

                //create tempPlayer Info
                Player tempPlayer = new Player();

                tempPlayer.setId(Integer.parseInt(myCursor.getString(0)));
                tempPlayer.setName(myCursor.getString(1));
                tempPlayer.setScore(Integer.parseInt(myCursor.getString(2)));

                //add contact objects to our list

                   playerList.add(tempPlayer);
            }while (myCursor.moveToNext());
        }

        return playerList;
    }

}
