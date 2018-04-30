package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

/**
 * Created by donaldtran on 4/29/18.
 *
 */


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class ScoreboardDBHelper extends SQLiteOpenHelper {


    public ScoreboardDBHelper(Context context) {
        super(context, ScoreboardDBContract.DATABASE_NAME, null, ScoreboardDBContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScoreboardDBContract.ScoreboardEntry.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    // This database is only a cache for online data, so its upgrade policy is
    // to simply to discard the data and start over
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScoreboardDBContract.ScoreboardEntry.DELETE_TABLE);
        onCreate(db);
    }

    // Add a new score to the table
    public void addUserScore(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES, user.mUsername);
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES, user.mScore);
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED, System.currentTimeMillis());
        user.mId = db.insertOrThrow(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, null, values);
        Log.d("deleteUserScore", user.toString());
        db.close();

    }

    public List<User> getAllUsers(){

        List<User> userArrayList = new ArrayList<>();

        // This query should be the equivalent of getting us the top 10 high scores
        String query = "SELECT * FROM " + ScoreboardDBContract.ScoreboardEntry.TABLE_NAME +
                "ORDER BY " + ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES + " ASC LIMIT 10";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user;

        if (cursor.moveToFirst()){
            do{
                user = new User(cursor.getString(0), cursor.getInt(1), cursor.getInt(2));
                userArrayList.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }

        Log.d("getAllUsers", userArrayList.toString());

        return userArrayList;
    }

    public long updateUserScore(User user){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES, user.mUsername);
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES, user.mScore);
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED, System.currentTimeMillis());
        user.mId = db.update(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, values, ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES + " = ?", new String[]{String.valueOf(user.getUserName())});
        db.close();
        Log.d("updateUserScore", user.toString());
        return user.mId;
    }

    public void deleteUserScore(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        user.mId = db.delete(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES + " = ?", new String[]{String.valueOf(user.getUserName())});
        db.close();
        Log.d("deleteUserScore", user.toString());
    }

}
