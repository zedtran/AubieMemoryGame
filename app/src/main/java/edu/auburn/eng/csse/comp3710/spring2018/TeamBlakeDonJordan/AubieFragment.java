package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;



import android.app.Fragment;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
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

                //TODO: Custom Alert Dialog (DON: Just setting up what I have to do next)
                /**
                 * (1) Traverse high scores
                 *     tempHighScoreIndex = -1
                 *     for i = 1 to highest_score_index (NOTE: i = '1' != '0') {
                 *          IF (NEW_USER_SCORE > tbl_high_scores[i - 1])
                 *              IF (NEW_USER_SCORE <= tbl_high_scores[i])
                 *                  tempHighScoreIndex = i - 1
                 *                  BREAK
                 *          ELSE IF (NEW_USER_SCORE > tbl_high_scores[i])
                 *              tempHighScoreIndex = i
                 *              CONTINUE;
                 *     }
                 *
                 *     IF (tempHighScoreIndex == -1)
                 *          DISPLAY current leaderbaord
                 *
                 *     ELSE {
                 *          Alert Dialog for inputText name and congrats on "tempHighScoreIndex" placement on leaderboard
                 *          Set Default input text to "Anonymous"
                 *          User prompt to input.getText() for their name {text length > 0} -- No further conditional error checks (i.e. Don't worry about multiple entries of the same name, etc.)
                 *              ------------------------------------------------------------------------------------
                 *              | NOTE: USER WILL NOT HAVE OPTION TO CANCEL SCOREBOARD ENTRY                       |
                 *              |       They can only force close the app to ignore entry -- Alert MUST be handled |
                 *              ------------------------------------------------------------------------------------
                 *
                 *          IF (text input length <= 0)
                 *              TOAST input name > 0 REQUIRED
                 *              -- Possibly animate Alert Dialog to show wiggle behavior (Optional)
                 *          SQL query update table and replace tbl_high_scores[tempHighScoreIndex] = NEW_USER_SCORE
                 *          DISPLAY updated leaderbaord
                 *          RETURN to MAIN MENU or Continue Replay from last state
                 *     }
                 *
                 * NOTE:
                 *      - Might have to import relevant SQL Classes here depending on implementation
                 *      - Still need to decide on segue/transition from alert dialog to leaderboards display
                 *          >> Do we transition to activity_leaderboards view?
                 *              >> IF SO, would be simply put the current view on the backstack?
                 *              >> IF NOT, would we need to create a new fragment?
                 *                  CONS:
                 *                      - Seems redundant because we already have a leaderboards layout and db access for transactions in associated activity
                 *                      - Would negatively impact code readability
                 *                  PROS:
                 *                      - Code reuse
                 * USEFUL SOURCES:
                 *      (1) For creating an Alert Dialog: https://stackoverflow.com/a/10904665
                 *      (2) For wrapping up LeaderBoardsActivity.java: http://www.androiddom.com/2011/06/android-and-sqlite.html
                 *      (3) *For using SQLOpenHelper: https://developer.android.com/training/data-storage/sqlite#java
                 *
                 */
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
