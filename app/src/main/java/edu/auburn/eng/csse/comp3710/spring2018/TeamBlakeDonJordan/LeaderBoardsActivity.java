package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Locale;


/** Leaderboards
 *
 * The Data Adapter for Storing Scores
 *
 * Created by Jordan, Don, and Blake on 4/23/2018.
 *
 */

public class LeaderBoardsActivity extends Activity {
    private static final String DEBUG_TAG = "SimpleDB Log";

    private ScoreboardDBHelper dbHelper;

    // Our database instance
    private SQLiteDatabase mDatabase;


    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        mContext = (Context) getBaseContext();
        dbHelper = new ScoreboardDBHelper(mContext);
        if(savedInstanceState != null) { }
        Log.d(DEBUG_TAG, "onCreate()");

        ArrayList<User> users = dbHelper.getTopTenUsers();
        //Collections.reverse(users); // Reverses the list to put highest score on top
        ArrayList<String> userList = new ArrayList<>();
        int i = 1;

        int nameLength;
        String boardName;
        int numSpacesToAddName = 0;

        int scoreStrLength;
        int numSpacesToSubScore = 11;

        for(User user : users) {
            boardName = user.getUserName();
            if (i == 1) {
                userList.add(String.format("%-8s %-9s %-10s %-17s", "RANK #", "NAME", "SCORE", "DATE"));
            }
            if (user.getUserName().length() < 10) {
                nameLength = user.getUserName().length();
                numSpacesToAddName = 10 - nameLength;
            }
            if (String.valueOf(user.getScore()).length() > 1) {
                if (user.getScore() > 99999) {
                    user.setScore(99999);
                }
                scoreStrLength = String.valueOf(user.getScore()).length();
                numSpacesToSubScore = 13 - scoreStrLength;
            }
            userList.add(String.format(Locale.getDefault(),
                    "%-8s" +
                            "%-" + (7 + numSpacesToAddName) + "s" +
                            "%-" + numSpacesToSubScore + "s" +
                            "%-17s",
                    String.valueOf(i++),
                    boardName,
                    String.valueOf(user.getScore()),
                    user.getFormattedDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, userList);
        ListView listView = findViewById(R.id.leaderboard);
        listView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {      //saves mBoard on state destruction
        super.onSaveInstanceState(outState);
    }


}



