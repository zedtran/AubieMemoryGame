package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;



import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */

public class AubieFragment extends Fragment {


    private TextView mSequence;
    private Board mBoard;
    private boolean mGameOver = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (savedInstanceState != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Button mGreenButton;
        Button mOrangeButton;
        Button mYellowButton;
        Button mRedButton;
        Button mBlueButton;
        Button mResetButton;

        View v = inflater.inflate(R.layout.fragment_aubieboard, container, false);
        mSequence = v.findViewById(R.id.test);
        final TextView mScoreBoard = v.findViewById(R.id.score);

        //creates all buttons
        mGreenButton = v.findViewById(R.id.green);
        mOrangeButton = v.findViewById(R.id.orange);
        mRedButton = v.findViewById(R.id.red);
        mYellowButton = v.findViewById(R.id.yellow);
        mBlueButton = v.findViewById(R.id.blue);
        mResetButton =  v.findViewById(R.id.reset);

        mBoard = new Board(mOrangeButton, mYellowButton, mGreenButton, mRedButton, mBlueButton);
        //mSequence.setText(mBoard.getSequenceString());    //sets up default text views

        runFirstFlash();


        //sets up listeners for buttons
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                      //Green
                //mGreenButton.clearAnimation();
                click(getString(R.string.GREEN), mSequence, mScoreBoard);
            }
        });
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                  //Orange
                //mOrangeButton.clearAnimation();
                click(getString(R.string.ORANGE), mSequence, mScoreBoard);
            }
        });
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                       //Yellow
                //mYellowButton.clearAnimation();
                click(getString(R.string.YELLOW), mSequence, mScoreBoard);
            }
        });
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                          //Red
                //mRedButton.clearAnimation();
                click(getString(R.string.RED), mSequence, mScoreBoard);
            }
        });
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                         //Blue
                //mBlueButton.clearAnimation();
                click(getString(R.string.BLUE), mSequence, mScoreBoard);
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {            //Reset Button
            @Override
            public void onClick(View view) {
                mBoard.reset();
                mGameOver = false;
                //mSequence.setText(mBoard.getSequenceString());
                mSequence.setText("");
                runFirstFlash();
                mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
            }
        });
        return v;
    }

    /* click(color, mSequence, mScoreBoard)
     * deals with player clicks. sets new sequence (will need to be removed eventually, sets game
     * over and sets score
     */
    public void click(String color, TextView mSequence, TextView mScoreBoard) {
        if (!mGameOver) {
            mGameOver = mBoard.checkInput(color);
            //mSequence.setText(mBoard.getSequenceString());
            mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
        }
        if (mGameOver) {
            mSequence.setText(getString(R.string.gameOver));
        }
    }


    private void runFirstFlash() {
        mBoard.flashButton(mBoard.getButton(mBoard.getSequenceList().get(0)));
    }

}
