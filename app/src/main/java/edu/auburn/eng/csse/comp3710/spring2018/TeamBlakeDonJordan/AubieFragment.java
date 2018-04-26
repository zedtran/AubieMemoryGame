package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;



import android.app.Fragment;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private Board mBoard;
    private boolean mGameOver = false;
    private TextView mScoreBoard;
    private static final String KEY_BOARD = "Board";
    private int replayCount = 0;
    private View v;
    private SoundPool sp = new SoundPool.Builder().build(); //(5, AudioManager.STREAM_MUSIC, 0);
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
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean mPlayOriginal = true;
        String difficulty = sharedPrefs.getString("pref_difficulty", "Easy");

        if (bundle != null) mPlayOriginal = bundle.getBoolean("PLAY_ORIGINAL"); //sets play original value

        if(mPlayOriginal)  gameType.setText(getString(R.string.simonTitle));   //decided to play simon or aubie game
        else  gameType.setText(getString(R.string.aubieTitle));


        if(savedInstanceState == null){
            mBoard = new Board(mPlayOriginal, v, difficulty, getContext()); //difficulty will be chosen through preferences Easy/Normal/Hard/Extreme
        }
        else  mBoard.updateBoard(v);

        mBoard.getLight(mBoard.getSequenceList().get(0)).addAnimation().start();  //flash initial animation



        /* soundId for Later handling of sound pool */
        final int redSound = sp.load(getContext(), R.raw.anote_red, 1); // in 2nd param u have to pass your desire ringtone
        final int blueSound = sp.load(getContext(), R.raw.enote_blue, 1); // in 2nd param u have to pass your desire ringtone
        final int yellowSound = sp.load(getContext(), R.raw.csharpnote_yellow, 1); // in 2nd param u have to pass your desire ringtone
        final int greenSound = sp.load(getContext(), R.raw.enote_green, 1); // in 2nd param u have to pass your desire ringtone
        final int orangeSound = sp.load(getContext(), R.raw.fnote_orange, 1); // in 2nd param u have to pass your desire ringtone

        //sets up listeners for buttons
        mBoard.getRedLight().getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                               //Red
                //mRedLight.clearAnimation();
                click(getString(R.string.RED), redSound);
            }
        });
        mBoard.getBlueLight().getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                              //Blue
                //mBlueLight.clearAnimation();
                click(getString(R.string.BLUE), blueSound);
            }
        });
        mBoard.getYellowLight().getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Yellow
                //mYellowLight.clearAnimation();
                click(getString(R.string.YELLOW), yellowSound);
            }
        });
        mBoard.getGreenLight().getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                             //Green
                //mGreenLight.clearAnimation();
                click(getString(R.string.GREEN), greenSound);
            }
        });
        mBoard.getOrangeLight().getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {                            //Orange
                //mOrangeLight.clearAnimation();
                click(getString(R.string.ORANGE), orangeSound);
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
    public void click(String color, int sound) {
        if(!mBoard.isAnimatorRunning()) {
            if (!mGameOver) {
                mGameOver = mBoard.checkInput(color);
                if (!mGameOver) sp.play(sound, 1, 1, 0, 0, 1);
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

        int finalScore = (longestSequence - replayCount) * mBoard.getDifficultyModifier();
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder, Locale.US);
        formatter.format("Final Score: %1$2d" +
                "\nLongest Sequence: %2$2d" +
                "\nNumber of Replays: %3$2d" +
                "\nDifficulty: %4$s",finalScore, longestSequence, replayCount, mBoard.getDifficulty());
        return formatter.toString();
    }
}
