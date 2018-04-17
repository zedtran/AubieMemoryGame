package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jsosn on 4/12/2018.
 */

public class Board {

    private Key mBlueKey = new Key("blue");
    private Key mOrangeKey = new Key("orange");
    private Key mGreenKey = new Key("green");
    private Key mYellowKey = new Key("yellow");
    private Key mRedKey = new Key("red");



    private ArrayList<String> mSequence;
    private int mInputNumber = 0;

    /* Board()
     * constructor, runs first sequence
     */
    Board(android.widget.Button mOrangeButton, android.widget.Button mYellowButton,
          android.widget.Button mGreenButton, android.widget.Button mRedButton,
          android.widget.Button mBlueButton){
        aubieSequence();
        mBlueKey.setButton(mBlueButton);
        mOrangeKey.setButton(mBlueButton);
        mYellowKey.setButton(mBlueButton);
        mGreenKey.setButton(mBlueButton);
        mRedKey.setButton(mRedButton);
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
    public Key getButton(String color) {
        switch (color) {
            case "blue":
                return mBlueKey;
            case "orange":
                return mOrangeKey;
            case "green":
                return mGreenKey;
            case "yellow":
                return mYellowKey;
            default:
                return null;    //shouldnt happen
        }
    }

    /* setLit(color)
     * set button to light up
     * may be replaced in the future, not sure if
     * a function is worth it
     */
    public void setLit(String color){
        getButton(color).setLit(true);
    }

    /* inputSequence()
     * creates the input sequence that needs to
     * be replicated by the player
     * input number set to zero at each new sequence
     */
    public void aubieSequence(){
        String choices[] = {"blue", "orange", "green", "yellow"};
        mSequence = new ArrayList(mScore+1);
        mInputNumber = 0;
        Random rand = new Random();
        int mChoice;
        int index = mScore+1;
        do{     //using a do here so when the score is initially zero, beginning of game, it will still loop
            mChoice = rand.nextInt(3);  //randomly generates sequence
            mSequence.add(choices[mChoice]);
            getButton(choices[mChoice]).flashButton();  //lights up button [code not complete]
            index--;
        }while(index > 0);
        mSequence.trimToSize(); //just in case mSequence gets to big
    }

    /* checkInput(color)
     * checks player input with the sequence that is expected to be inputted
     * if correct, the input number is increased
     * if the input number is equal to the length of the sequence list then a new sequence needs to be generated
     * if the sequence is not correct, the game is over
     */
    public boolean checkInput(String color){
        String correctColor = mSequence.get(mInputNumber);
        mInputNumber++;
        if(correctColor == color){//correct input
            if(mInputNumber == mSequence.size()){//new sequence needs to be generated
                mScore++;
                aubieSequence();
            }
            else {}//waits for next button press
            return false;
        }
        else {  //incorrect input... game over
            return true;
        }
    }
    /* getSequence()
     * Returns expected sequence of colors
     */
    public String getSequence(){
        String sequence = "";
        for(int i = 0; i < mSequence.size(); i++){
            sequence += mSequence.get(i) + " ";
        }
        return sequence;
    }
    /* reset()
     * Resets game
     */
    public void reset(){
        mScore = 0;
        aubieSequence();
    }
}
