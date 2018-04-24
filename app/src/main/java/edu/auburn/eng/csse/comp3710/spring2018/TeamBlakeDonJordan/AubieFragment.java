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

import java.util.Formatter;
import java.util.Locale;

/**
 * AubieFragment
 * main fragment of the app. Runs most of the code here. Essentially causes the game to work
 *
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */

public class AubieFragment extends Fragment {


    private TextView mGameOverText;
    private Board mBoard;
    private boolean mGameOver = false;
    private TextView mScoreBoard;
    private static final String KEY_BOARD = "Board";
    private int replayCount = 0;
    private View v;
    private MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){                     //restores mBoard after state destroyed
            mBoard = savedInstanceState.getParcelable(KEY_BOARD);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {      //saves mBoard on state destruction
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_BOARD, mBoard);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_aubieboard, container, false);

        //creates all views
        mScoreBoard = v.findViewById(R.id.score);
        Bundle bundle = getArguments();
        Button mResetButton =  v.findViewById(R.id.reset);
        Button mReplayButton = v.findViewById(R.id.replay);
        TextView gameType = v.findViewById(R.id.gameType);
        mGameOverText = v.findViewById(R.id.gameover); //assigns  game over text view
        boolean mPlayOriginal = true;


        if (bundle != null) mPlayOriginal = bundle.getBoolean("PLAY_ORIGINAL"); //sets play original value

        if(mPlayOriginal)  gameType.setText("Simon Memory Game");   //decided to play simon or aubie game
        else  gameType.setText("Aubie's Memory Game");

        if(savedInstanceState == null) mBoard = new Board(mPlayOriginal, v);
        else  mBoard.updateBoard(v);

        mBoard.addFlash(mBoard.getButton(mBoard.getSequenceList().get(0))).start();  //flash initial animation

        //sets up listeners for buttons
        mBoard.getRedButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Red
                //mRedButton.clearAnimation();
                click(getString(R.string.RED));
            }
        });
        mBoard.getBlueButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                              //Blue
                //mBlueButton.clearAnimation();
                click(getString(R.string.BLUE));
            }
        });
        mBoard.getYellowButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Yellow
                //mYellowButton.clearAnimation();
                click(getString(R.string.YELLOW));
            }
        });
        mBoard.getGreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                             //Green
                //mGreenButton.clearAnimation();
                click(getString(R.string.GREEN));
            }
        });
        mBoard.getOrangeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Orange
                //mOrangeButton.clearAnimation();
                click(getString(R.string.ORANGE));
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                        //Reset
                v.findViewById(R.id.board).setVisibility(View.VISIBLE);
                mGameOver = mBoard.reset(); //sets gameOver to false
                v.findViewById(R.id.gameOverScreen).setVisibility(View.INVISIBLE);  //hides game over screen
                v.findViewById(R.id.replay).setVisibility(View.VISIBLE);        //sets replay to visible
            }
        });
        mReplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                        //Replay
                   mBoard.playAnimation();
                   mBoard.resetInputCount();
                   replayCount++;
            }
        });
        return v;
    }

    /* click(color, mScoreBoard)
     * deals with player clicks. sets new sequence (will need to be removed eventually, sets game
     * over and sets score
     */
    public void click(String color) {
       if(!mBoard.isAnimatorRunning()) {
           switch (color){
               case "red":
                   mediaPlayer = MediaPlayer.create(getContext(), R.raw.anote_red);
                   break;
               case "blue":
                   mediaPlayer = MediaPlayer.create(getContext(), R.raw.enote_blue);
                   break;
               case "yellow":
                   mediaPlayer = MediaPlayer.create(getContext(), R.raw.csharpnote_yellow);
                   break;
               case "green":
                   mediaPlayer = MediaPlayer.create(getContext(), R.raw.enote_green);
                   break;
               case "orange":
                   mediaPlayer = MediaPlayer.create(getContext(), R.raw.fnote_orange);
                   break;
               default:
           }
           if (!mGameOver) {
               mGameOver = mBoard.checkInput(color);
               if(!mGameOver) mediaPlayer.start();
           }
           if (mGameOver) {
               v.findViewById(R.id.gameOverScreen).setVisibility(View.VISIBLE); //sets game over screen to visible
               v.findViewById(R.id.board).setVisibility(View.INVISIBLE);        //sets board to invisible
               v.findViewById(R.id.replay).setVisibility(View.INVISIBLE);       //sets replay to invisible
               mScoreBoard.setText(setScore());
           }
       }
    }

    public String setScore(){
        int longestSequence = mBoard.getScore();
        String difficulty = "normal";
        int difficultyMultiplier = 1;
        switch (difficulty){
            case "normal":
                difficultyMultiplier = 2;
            default:
                difficultyMultiplier = 1;
        }
        int finalScore = (longestSequence - replayCount) * difficultyMultiplier;
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.US);
        formatter.format("Final Score: %1$2d" +
                "\nLongest Sequence: %2$2d" +
                "\nNum of Replays: %3$2d" +
                "\nDifficulty: %4$s",finalScore, longestSequence, replayCount, difficulty);
        return formatter.toString();
    }
}
