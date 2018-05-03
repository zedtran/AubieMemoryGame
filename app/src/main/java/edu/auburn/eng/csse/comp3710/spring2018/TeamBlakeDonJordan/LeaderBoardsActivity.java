package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        ArrayList<String> userList = new ArrayList<>();
        for(User user : users){
            userList.add(user.toString());
        }
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry", "WebOS","Ubuntu","Windows7","Max OS X"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, userList);
        ListView listView = findViewById(R.id.leaderboard);
        listView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {      //saves mBoard on state destruction
        super.onSaveInstanceState(outState);
    }


}



