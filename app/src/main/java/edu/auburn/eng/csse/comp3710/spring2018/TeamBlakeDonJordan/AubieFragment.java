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
        mScoreBoard = v.findViewById(R.id.score);
        Bundle bundle = getArguments();


        boolean mPlayOriginal = true;
        if (bundle != null) {
            mPlayOriginal = bundle.getBoolean("PLAY_ORIGINAL");
        }
        if (savedInstanceState != null) mBoard.updateBoard(v);
        Button mRedButton = v.findViewById(R.id.red);
        Button mBlueButton = v.findViewById(R.id.blue);
        Button mYellowButton = v.findViewById(R.id.yellow);
        Button mGreenButton = v.findViewById(R.id.green);
        Button mOrangeButton = v.findViewById(R.id.orange);
        Button mResetButton =  v.findViewById(R.id.reset);
        Button mReplayButton = v.findViewById(R.id.replay);

        //creates all buttons

        if(savedInstanceState == null) {
            mBoard = new Board(mPlayOriginal, v);
        }
        mBoard.flashButton(mBoard.getButton(mBoard.getSequenceList().get(0))).start();  //flash initial animation
        mGameOverText = v.findViewById(R.id.gameover); //assigns  game over text view
        TextView gameType = v.findViewById(R.id.gameType);
        if(mPlayOriginal)  gameType.setText("Simon Memory Game");
        else  gameType.setText("Aubie's Memory Game");
        mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));

        //sets up listeners for buttons
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Red
                //mRedButton.clearAnimation();
                click(getString(R.string.RED));
            }
        });
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                              //Blue
                //mBlueButton.clearAnimation();
                click(getString(R.string.BLUE));
            }
        });
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Yellow
                //mYellowButton.clearAnimation();
                click(getString(R.string.YELLOW));
            }
        });
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                             //Green
                //mGreenButton.clearAnimation();
                click(getString(R.string.GREEN));
            }
        });
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
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
                mGameOverText.setVisibility(View.INVISIBLE);
                mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
            }
        });
        mReplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                        //Replay
                   mBoard.playAnimation();
                   replayCount++;
            }
        });
        return v;
    }

    @Override
    public void onStop() {
        //mediaPlayer.release();
        //mediaPlayer = null;
        super.onStop();
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
               mScoreBoard.setText(getString(R.string.score, Integer.toString(mBoard.getScore())));
               if(!mGameOver) mediaPlayer.start();
           }
           if (mGameOver) {
               mGameOverText.setVisibility(View.VISIBLE);
               v.findViewById(R.id.board).setVisibility(View.INVISIBLE);
           }
       }
    }
}
