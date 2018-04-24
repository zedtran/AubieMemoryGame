package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Random;

/**
 * Board class
 *
 * Contain all the logic for the game
 *
 * Created by Jordan, Don, and Blake on 4/12/2018.
 */

public class Board implements Parcelable {

    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String YELLOW = "yellow";
    private static final String GREEN = "green";
    private static final String ORANGE = "orange";


    private Button mRedButton;
    private Button mBlueButton;
    private Button mYellowButton;
    private Button mGreenButton;
    private Button mOrangeButton;

    private int mScore = 0;
    private int mInputNumber = 0;
    private ArrayList<String> mSequence = new ArrayList<>();
    private ArrayList<Animator> AnimatorArray = new ArrayList<>();
    private AnimatorSet mAnimation = new AnimatorSet();
    private boolean playOriginal;
    /* Board()
     * constructor, runs first sequence
     */
    Board(Button mRedB, Button mBlueB, Button mYellowB, Button mGreenB, Button mOrangeB, boolean playOriginal) {
        if (playOriginal) simonSequence();
        else aubieSequence();
        this.playOriginal = playOriginal;
        this.mRedButton = mRedB;
        this.mBlueButton = mBlueB;
        this.mYellowButton = mYellowB;
        this.mGreenButton = mGreenB;
        this.mOrangeButton = mOrangeB;
    }

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
    public Button getButton(String color) {
        switch (color) {
            case RED:
                return mRedButton;
            case BLUE:
                return mBlueButton;
            case YELLOW:
                return mYellowButton;
            case GREEN:
                return mGreenButton;
            case ORANGE:
                return mOrangeButton;
            default:
                return null;    //shouldn't happen
        }
    }

    /* aubieSequence()
     * creates the input sequence that needs to
     * be replicated by the player
     * input number set to zero at each new sequence
     */
    private void aubieSequence() {
        String choices[] = {RED, BLUE, YELLOW, GREEN, ORANGE};
        mSequence = new ArrayList<>(mScore + 1);
        mInputNumber = 0;
        Random rand = new Random();
        AnimatorArray  = new ArrayList<>();
        int index = mScore + 1;
        do //using a do here so when the score is initially zero, beginning of game, it will still loop
        {
            String chosenColor = choices[rand.nextInt(5)];
            mSequence.add(chosenColor);
            AnimatorArray.add(flashButton(getButton(chosenColor)));
            index--;
        } while (index > 0);
        mSequence.trimToSize(); //just in case mSequence gets to big
        AnimatorArray.trimToSize();
        mAnimation = new AnimatorSet();
        mAnimation.playSequentially(AnimatorArray);
        mAnimation.start();

    }
    /* simonSequence()
     * creates the input sequence that builds on prior iterations
     * that needs to be replicated by the player
     * input number set to zero at each new sequence
     */
    private void simonSequence() {
        String choices[] = {RED, BLUE, YELLOW, GREEN, ORANGE};
        mInputNumber = 0;
        Random rand = new Random();
        String chosenColor = choices[rand.nextInt(5)];
        mSequence.add(chosenColor);
        AnimatorArray.add(flashButton(getButton(chosenColor)));
        mSequence.trimToSize(); //just in case mSequence gets to big
        AnimatorArray.trimToSize();
        if(mScore == 1){
            AnimatorArray.set(0, flashButton(getButton(mSequence.get(0))));
        }
        //wont work first try since the view isnt created yet
        mAnimation = new AnimatorSet();
        mAnimation.playSequentially(AnimatorArray);
        mAnimation.start();

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
                if(playOriginal) simonSequence();
                else aubieSequence();

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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mSequence.size(); i++) builder.append(mSequence.get(i) + " ");
        return builder.toString();
    }

    /* reset()
     * Resets game
     * returns false to make gameOver false
     */
    public boolean reset() {
        mScore = 0;
        mAnimation.end();
        if(playOriginal){
            mSequence = new ArrayList<>();
            AnimatorArray = new ArrayList<>();
            simonSequence();
        }
        else aubieSequence();
        return false;
    }

    /* getSequenceList()
     * returns list of sequence objects
     * may not be needed
     */
    public ArrayList<String> getSequenceList() {
        return mSequence;
    }

    /* flashButton(mButton)
     * adds animator to the button to be flashed
     */
    public Animator flashButton(Button mButton) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mButton, "alpha", 1, 0);   //sets so the opacity of the object goes from 100% to 0%
        anim.setDuration(500);                          //time it takes to run the animator, so 500 milliseconds
        anim.setRepeatMode(ValueAnimator.REVERSE);      //runs the animator and then reverses it and runs it again
        anim.setRepeatCount(1);                         //repeats only once
        return anim;
    }
    /* isAnimatorRunning()
     * returns true/false if animator is running
     */
    public boolean isAnimatorRunning(){
        return mAnimation.isRunning();
    }

    public void playAnimation(){
        if(!playOriginal && mScore == 0){           //when you choose aubie's game and its the initial run the animation is finicky and needs to be forced to play
            AnimatorArray.set(0, flashButton(getButton(mSequence.get(0))));
            mAnimation = new AnimatorSet();
            mAnimation.playSequentially(AnimatorArray);
        }
        mAnimation.end();
        mAnimation.start();
    }

    ////////////////////////////////////////////////////////////////
    //                                                            //
    //                      parcelable stuff                      //
    //                                                            //
    ////////////////////////////////////////////////////////////////

    private Board(Parcel in) {
        mSequence = in.readArrayList(String.class.getClassLoader());
        AnimatorArray = in.readArrayList(Animator.class.getClassLoader());
        mScore = in.readInt();
        playOriginal = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mSequence);
        dest.writeList(AnimatorArray);
        dest.writeInt(mScore);
        dest.writeByte((byte) (playOriginal ? 1 : 0));
    }

    public void MyParcelable() {
        // Normal actions performed by class, since this is still a normal object!
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        public Board[] newArray(int size) {
            return new Board[size];
        }
    };
}
