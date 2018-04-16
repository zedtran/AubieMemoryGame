package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AubieMainActivity extends AppCompatActivity {

        private Button mGreenButton;
        private Button mOrangeButton;
        private Button mYellowButton;
        private Button mRedButton;
        private Board mBoard;
        private static final String GREEN = "green";
        private static final String ORANGE = "orange";
        private static final String YELLOW = "yellow";
        private static final String RED = "red";
        private static final String BLUE = "blue";

    private boolean gameOver = false;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aubie_main);
            if(savedInstanceState != null){}

            TextView mTextView = (TextView) findViewById(R.id.test);
            mBoard = new Board();
            mTextView.setText(mBoard.getSequence());
            //creates all buttons
            /*mGreenButton = (Button) findViewById(R.id.green);
            mOrangeButton = (Button) findViewById(R.id.orange);
            mRedButton = (Button) findViewById(R.id.red);
            mYellowButton = (Button) findViewById(R.id.yellow);
            mBlueButton = (Button) findViewById(R.id.blue);

            //sets up listeners for buttons
            mGreenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {                //Green
                    gameOver = mBoard.checkInput(GREEN);                         //change later to button text
                }
            });
            mOrangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {                //Orange
                    gameOver = mBoard.checkInput(ORANGE);
                }
            });
            mYellowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {                //Yellow
                    gameOver = mBoard.checkInput(YELLOW);
                }
            });
            mRedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {                //Red
                   gameOver = mBoard.checkInput(RED);
                }
            });
            mBlueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {                //Blue
                   gameOver = mBoard.checkInput(RED);
                }
            });

            */

        }

}
