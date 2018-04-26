package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
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
        Button mPlayGameButton = findViewById(R.id.playGame);
        Button mLeaderBoardsButton = findViewById(R.id.leadboards);
        Button mOptionsButton = findViewById(R.id.options);
        Button mExtrasButton = findViewById(R.id.extra);


        //sets up listeners for buttons
        mPlayGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Play
                /*findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                findViewById(R.id.mainMenu).setVisibility(View.GONE);*/
                Intent intent = new Intent(MainMenuActivity.this, HolderActivity.class);
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
        mExtrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Extras Fragment
                Intent intent = new Intent(MainMenuActivity.this, ExtrasActivity.class);
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
