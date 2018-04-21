package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;



import android.app.Fragment;
import android.media.MediaPlayer;
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
        Button mRedButton;
        Button mBlueButton;
        Button mYellowButton;
        Button mGreenButton;
        Button mOrangeButton;
        Button mResetButton;


        View v = inflater.inflate(R.layout.fragment_aubieboard, container, false);
        mSequence = v.findViewById(R.id.test);
        final TextView mScoreBoard = v.findViewById(R.id.score);

        //creates all buttons
        mRedButton = v.findViewById(R.id.red);
        mBlueButton = v.findViewById(R.id.blue);
        mYellowButton = v.findViewById(R.id.yellow);
        mGreenButton = v.findViewById(R.id.green);
        mOrangeButton = v.findViewById(R.id.orange);
        mResetButton =  v.findViewById(R.id.reset);

        mBoard = new Board(mRedButton, mBlueButton, mYellowButton, mGreenButton, mOrangeButton);
        //mSequence.setText(mBoard.getSequenceString());    //sets up default text views


        //sets up listeners for buttons
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Red
                //mRedButton.clearAnimation();
                click(getString(R.string.RED), mSequence, mScoreBoard);
            }
        });
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                              //Blue
                //mBlueButton.clearAnimation();
                click(getString(R.string.BLUE), mSequence, mScoreBoard);
            }
        });
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Yellow
                //mYellowButton.clearAnimation();
                click(getString(R.string.YELLOW), mSequence, mScoreBoard);
            }
        });
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                             //Green
                //mGreenButton.clearAnimation();
                click(getString(R.string.GREEN), mSequence, mScoreBoard);
            }
        });
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Orange
                //mOrangeButton.clearAnimation();
                click(getString(R.string.ORANGE), mSequence, mScoreBoard);
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {            //Reset Button
            @Override
            public void onClick(View view) {                             //Reset
                mBoard.reset();
                mGameOver = false;
                mSequence.setText("");      //remove once mSequence is removed
                mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
            }
        });

        mBoard.flashButton(mBoard.getButton(mBoard.getSequenceList().get(0))).start();

        return v;
    }

    /* click(color, mSequence, mScoreBoard)
     * deals with player clicks. sets new sequence (will need to be removed eventually, sets game
     * over and sets score
     */
    public void click(String color, TextView mSequence, TextView mScoreBoard) {
       if(!mBoard.isAnimatorRunning()) {
           switch (color){
               case "red":
                   MediaPlayer.create(getContext(), R.raw.anote_red).start();
                   break;
               case "blue":
                   MediaPlayer.create(getContext(), R.raw.enote_blue).start();
                   break;
               case "yellow":
                   MediaPlayer.create(getContext(), R.raw.csharpnote_yellow).start();
                   break;
               case "green":
                   MediaPlayer.create(getContext(), R.raw.enote_green).start();
                   break;
               case "orange":
                   MediaPlayer.create(getContext(), R.raw.fnote_orange).start();
                   break;
               default:
           }
           if (!mGameOver) {
               mGameOver = mBoard.checkInput(color);
               //mSequence.setText(mBoard.getSequenceString());
               mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
           }
           if (mGameOver) {
               mSequence.setText(getString(R.string.gameOver));
           }
       }
    }
}
