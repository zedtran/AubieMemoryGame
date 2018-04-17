package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AubieFragment extends Fragment {

    private Button mGreenButton;
    private Button mOrangeButton;
    private Button mYellowButton;
    private Button mRedButton;
    private Button mBlueButton;
    private Button mResetButton;
    private Board mBoard;
    private static final String GREEN = "green";
    private static final String ORANGE = "orange";
    private static final String YELLOW = "yellow";
    private static final String RED = "red";
    private static final String BLUE = "blue";
    private boolean mGameOver = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_aubieboard, container, false);
        final TextView mSequence = (TextView) v.findViewById(R.id.test);
        final TextView mScoreBoard = (TextView) v.findViewById(R.id.score);

        //creates all buttons
        mGreenButton = (Button) v.findViewById(R.id.green);
        mOrangeButton = (Button) v.findViewById(R.id.orange);
        mRedButton = (Button) v.findViewById(R.id.red);
        mYellowButton = (Button) v.findViewById(R.id.yellow);
        mBlueButton = (Button) v.findViewById(R.id.blue);
        mResetButton = (Button) v.findViewById(R.id.reset);

        mBoard = new Board(mOrangeButton, mYellowButton, mGreenButton, mRedButton, mBlueButton);
        mSequence.setText(mBoard.getSequence());    //sets up default text views


        //sets up listeners for buttons
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                      //Green
                click(GREEN, mSequence, mScoreBoard);
            }
        });
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                  //Orange
                click(ORANGE, mSequence, mScoreBoard);
            }
        });
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                       //Yellow
                click(YELLOW, mSequence, mScoreBoard);
            }
        });
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                          //Red
                click(RED, mSequence, mScoreBoard);
            }
        });
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                         //Blue
                click(BLUE, mSequence, mScoreBoard);
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBoard.reset();
                mGameOver = false;
                mSequence.setText(mBoard.getSequence());
                mScoreBoard.setText("Score: " + Integer.toString(mBoard.getScore()));
            }
        });
        return v;
    }
    /* click(color, mSequence, mScoreBoard)
     * deals with player clicks. sets new sequence (will need to be removed eventually, sets game
     * over and sets score
     */
    public void click(String color, TextView mSequence, TextView mScoreBoard) {
         if(!mGameOver) {
            mGameOver = mBoard.checkInput(color);
            mSequence.setText(mBoard.getSequence());
            mScoreBoard.setText("Score: " + Integer.toString(mBoard.getScore()));
        }
        if(mGameOver) {
            mSequence.setText("GAME OVER");
        }
    }



}
