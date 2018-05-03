package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Date; // DATE_TYPE is specified in ScoreboardDBContract


/*
 * Launches main menu fragment
 *
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */
public class AubieMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainIntent);

    }
}
