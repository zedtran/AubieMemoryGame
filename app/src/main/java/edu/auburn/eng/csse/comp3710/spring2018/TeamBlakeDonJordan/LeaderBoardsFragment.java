package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Fragment;
import java.sql.Date;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* Leaderboards Fragment
 *
 * Handles saving and retrieving of player scores
 *
 * Created by Jordan, Don, and Blake on 4/23/2018.
 */

public class LeaderBoardsFragment extends Fragment {
    private static final String DEBUG_TAG = "SimpleDB Log";
    private static final String DATABASE_NAME = "leadboards.db";

    // TABLE (COLUMN) NAMES
    private static final String TABLE_USER_SCORES = "tbl_users_scores";

    // SQL CREATE AND DROP TABLE STATEMENTS
    private static final String CREATE_USER_SCORE_TABLE = "CREATE TABLE tbl_users_scores (id INTEGER PRIMARY KEY AUTOINCREMENT , username TEXT, dateadded DATE, score TEXT);";
    private static final String DROP_USER_SCORE_TABLE = "DROP TABLE tbl_users_scores;";

    // Our database instance
    private SQLiteDatabase mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){}
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {      //saves mBoard on state destruction
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leaderboards, container, false);
        //runDatabase();
        return v;
    }

    @SuppressLint("WrongConstant")
    public void runDatabase() {
        mDatabase = mDatabase.openOrCreateDatabase(DATABASE_NAME, null, null);

        // SET SOME DATABASE CONFIGURATION INFO
        mDatabase.setLocale(Locale.getDefault()); // Set the locale
        mDatabase.setVersion(mDatabase.getVersion()+1); // Sets the database version.

        // CREATE TABLE
        mDatabase.execSQL(CREATE_USER_SCORE_TABLE);
    }

    // Add a book to the book table
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.mUsername);
        values.put("score", user.mScore);
        values.put("dateadded", user.mDateAdded.toString());
        user.mId = mDatabase.insertOrThrow(TABLE_USER_SCORES, null, values);
    }

    // Helper class to encapsulate User information programmatically
    class User {
        String mUsername;
        Date mDateAdded;
        long mId;
        String mScore;

        public User(String username, Date dateAdded, String score) {
            mUsername = username;
            mDateAdded = dateAdded;
            mScore = score;
            mId = -1;
        }
    }
}