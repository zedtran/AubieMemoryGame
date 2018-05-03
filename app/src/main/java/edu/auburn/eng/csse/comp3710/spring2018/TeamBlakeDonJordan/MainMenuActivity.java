package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/* Main Menu Activity
 *
 * Launch screen. User picks to either start the app, go to the leaderboards, change options,
 * or look at extras
 *
 * Created by Jordan, Don, and Blake on 4/23/2018.
 */
public class MainMenuActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        Button mPlayAubieGameButton = findViewById(R.id.aubiegame);
        Button mLeaderBoardsButton = findViewById(R.id.leadboards);
        Button mOptionsButton = findViewById(R.id.options);
        Button mPlaySimonGameButton = findViewById(R.id.simongame);


        //sets up listeners for buttons
        mPlayAubieGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Play Aubie

                Intent intent = new Intent(MainMenuActivity.this, HolderActivity.class);
                intent.putExtra("PLAY_ORIGINAL", false);  //sets bundle to be sent to next fragment
                startActivity(intent);
            }
        });
        mPlaySimonGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Play Simon
                Intent intent = new Intent(MainMenuActivity.this, HolderActivity.class);
                intent.putExtra("PLAY_ORIGINAL", true);  //sets bundle to be sent to next fragment
                startActivity(intent);
            }
        });
        mLeaderBoardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Leaderboards
                Intent intent = new Intent(MainMenuActivity.this, LeaderBoardsActivity.class);
                startActivity(intent);
            }
        });
        mOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Options Activity
                Intent intent = new Intent(MainMenuActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

    }
}

