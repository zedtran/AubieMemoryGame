package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jsosn on 4/12/2018.
 */

public class Board {

    private Light mBlueLight = new Light("blue");
    private Light mOrangeLight = new Light("orange");
    private Light mGreenLight = new Light("green");
    private Light mYellowLight = new Light("yellow");
    private Light mRedLight = new Light("red");



    private ArrayList<String> mSequence;
    private int mInputNumber = 0;

    /* Board()
     * constructor, runs first sequence
     */
    Board(android.widget.Button mOrangeButton, android.widget.Button mYellowButton,
          android.widget.Button mGreenButton, android.widget.Button mRedButton,
          android.widget.Button mBlueButton){

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
            case "blue":
                return mBlueLight;
            case "orange":
                return mOrangeLight;
            case "green":
                return mGreenLight;
            case "yellow":
                return mYellowLight;
            case "red":
                return mRedLight;
            default:
                return null;    //shouldnt happen
        }
    }


    /* inputSequence()
     * creates the input sequence that needs to
     * be replicated by the player
     * input number set to zero at each new sequence
     */
    public void aubieSequence(){
        String choices[] = {"blue", "orange", "green", "yellow", "red"};
        mSequence = new ArrayList(mScore+1);
        mInputNumber = 0;
        Random rand = new Random();
        int mChoice;
        int index = mScore+1;
        do{     //using a do here so when the score is initially zero, beginning of game, it will still loop
            mChoice = rand.nextInt(5) ;
            mSequence.add(choices[mChoice]);
            if(mScore != 0)
                getLight(choices[mChoice]).flashButton();
            index--;
        }while(index > 0);
        mSequence.trimToSize(); //just in case mSequence gets to big
        if (mScore != 0)
            flashAnimation();
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
    public String getSequenceString(){
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

    public ArrayList<String> getSequence(){
        return mSequence;
    }

    public void flashAnimation(){
        int i = 0;
        while(i < mSequence.size()){
            final Light mLight = getLight(mSequence.get(i));
            mLight.flashButton();  //lights up button [code not complete]
            //mLight.getButton().clearAnimation();
            i++;
        }


    }
}
