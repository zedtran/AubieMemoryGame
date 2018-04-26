package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*
 * Launches splash screen
 *
 * Created by Jordan, Don, and Blake on 4/25/2018.
 */
public class AubieSplashActivity extends Activity {




    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle bundle) {
        final int SPLASH_DISPLAY_LENGTH = 1500; // Duration of wait

        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(AubieSplashActivity.this, AubieMainActivity.class);
                AubieSplashActivity.this.startActivity(mainIntent);
                AubieSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

