package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.Animator;
import android.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */

public class Board {

    private static final String BLUE = "blue";
    private static final String RED = "red";
    private static final String ORANGE = "orange";
    private static final String YELLOW = "yellow";
    private static final String GREEN = "green";


    private Light mBlueLight = new Light();
    private Light mOrangeLight = new Light();
    private Light mGreenLight = new Light();
    private Light mYellowLight = new Light();
    private Light mRedLight = new Light();

    private ArrayList<String> mSequence;
    private int mInputNumber = 0;

    /* Board()
     * constructor, runs first sequence
     */
    Board(android.widget.Button mOrangeButton, android.widget.Button mYellowButton,
          android.widget.Button mGreenButton, android.widget.Button mRedButton,
          android.widget.Button mBlueButton) {

        aubieSequence();
        mBlueLight.setButton(mBlueButton);
        mOrangeLight.setButton(mOrangeButton);
        mYellowLight.setButton(mYellowButton);
        mGreenLight.setButton(mGreenButton);
        mRedLight.setButton(mRedButton);
    }

    private int mScore = 0;

    /* getScore()
     * returns player score
     */
    public int getScore() {
        return mScore;
    }

    /* setScore(score)
     * returns player score
     */
    public void setScore(int score) {
        mScore = score;
    }

    /* getButton(color)
     * returns gets button object
     */
    public Light getLight(String color) {
        switch (color) {
            case BLUE:
                return mBlueLight;
            case ORANGE:
                return mOrangeLight;
            case GREEN:
                return mGreenLight;
            case YELLOW:
                return mYellowLight;
            case RED:
                return mRedLight;
            default:
                return null;    //shouldn't happen
        }
    }


    /* inputSequence()
     * creates the input sequence that needs to
     * be replicated by the player
     * input number set to zero at each new sequence
     */
    private void aubieSequence() {
        String choices[] = {BLUE, ORANGE, GREEN, YELLOW, RED};
        mSequence = new ArrayList<>(mScore + 1);
        mInputNumber = 0;
        Random rand = new Random();
        ArrayList<Animator> AnimatorArray;
        int mChoice;
        int index = mScore + 1;
        AnimatorArray = new ArrayList<>();
        do
        {     //using a do here so when the score is initially zero, beginning of game, it will still loop
            mChoice = rand.nextInt(5);
            String choice = choices[mChoice];
            mSequence.add(choice);
            AnimatorArray.add(getLight(choice).flashButton());
            index--;
        } while (index > 0);
        mSequence.trimToSize(); //just in case mSequence gets to big
        if (mScore != 0) {
            AnimatorSet s = new AnimatorSet();
            s.playSequentially(AnimatorArray);
            s.start();
        }
    }

    /* checkInput(color)
     * checks player input with the sequence that is expected to be inputted
     * if correct, the input number is increased
     * if the input number is equal to the length of the sequence list then a new sequence needs to be generated
     * if the sequence is not correct, the game is over
     */
    public boolean checkInput(String color) {
        String correctColor = mSequence.get(mInputNumber);
        mInputNumber++;
        if (correctColor.equals(color)) {//correct input
            if (mInputNumber == mSequence.size()) {//new sequence needs to be generated
                mScore++;
                aubieSequence();
            }
            return false;
        } else {  //incorrect input... game over
            return true;
        }
    }

    /* getSequence()
     * Returns expected sequence of colors
     */
    public String getSequenceString() {
        String sequence = "";
        for (int i = 0; i < mSequence.size(); i++) sequence.concat(mSequence.get(i) + " ");
        return sequence;
    }

    /* reset()
     * Resets game
     */
    public void reset() {
        mScore = 0;
        aubieSequence();
    }

    public ArrayList<String> getSequence() {
        return mSequence;
    }
}
